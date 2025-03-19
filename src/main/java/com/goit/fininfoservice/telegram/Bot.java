package com.goit.fininfoservice.telegram;


import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.controller.commands.CommandController;
import com.goit.fininfoservice.telegram.messages.updateresoving.UpdateTypeResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    private final UpdateTypeResolver updateTypeResolver;
    private final ExecutorService virtualThreadExecutor;
    private final CommandController commandController;

    public Bot( UpdateTypeResolver updateTypeResolver, ExecutorService updateHandlingExecutor,
                CommandController commandController, @Value("${bot.token}") String botToken){
        super(botToken);
        this.updateTypeResolver = updateTypeResolver;
        this.virtualThreadExecutor = updateHandlingExecutor;
        this.commandController = commandController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatID = extractChatId(update);
        safeExecute(commandController.processCommand(update).get());
        CompletableFuture.supplyAsync(()->update, virtualThreadExecutor).thenApply(updateTypeResolver::resolve)
                .thenApply(BotMessage::handle).thenAccept(this::safeExecute);

    }

    private void safeExecute(BotApiMethod< ? > method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            log.error("Ошибка при отправке сообщения {} чат {}", e.getMessage());
        }
    }

    void handleUpdate(CallbackQuery callbackQuery){
        log.info("handleUpdate  CallbackQuery {} - {}",
        callbackQuery.getMessage(),
        callbackQuery.getData());
    }

    @Override
    public String getBotUsername() {
       return "FinInfoUserBot";
    }

    public <T extends Serializable, M extends BotApiMethod<T>> void executeMethods(List<M> methods) {
        for (M method : methods) {
            try {
                execute(method);
            } catch (TelegramApiException e) {
                log.error("Exception: {}",e.getMessage());
            }
        }
    }

    public Long extractChatId(Update update) {
        // leave this function as a chatID extraction  cases list
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        } else if (update.hasMyChatMember()) {
            return update.getMyChatMember().getChat().getId();
        } else if (update.hasChannelPost()) {
            return update.getChannelPost().getChatId();
        }
        // Если нет chatId
        return null;
    }

}
