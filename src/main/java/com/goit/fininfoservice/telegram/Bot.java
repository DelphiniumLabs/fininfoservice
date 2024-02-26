package com.goit.fininfoservice.telegram;


import com.goit.fininfoservice.telegram.contoller.CommandController;
import com.goit.fininfoservice.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Component
public class Bot extends TelegramLongPollingBot {
    private LocalDateTime lastOnUpdateReceived;
    private final MessageService messageService;
    private final CommandController commandController;
    public Bot(@Value("${bot.token}") String botToken, MessageService messageService, CommandController commandController){
        super(botToken);
        this.messageService=messageService;
        this.commandController=commandController;
        lastOnUpdateReceived=LocalDateTime.now().minus(500, ChronoUnit.MILLIS);
    }
    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    execute(messageService.startPage(update));
                }
            } else if (update.hasCallbackQuery()) {
                execute(commandController.commandProcessing(update));
            }
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "RateBot";
    }
    //--------------------------------------------------------------------------
// methods for Danila Adyrhaiev
    // Общий метод executeMethods, принимающий разные типы параметров
    public <T extends Serializable, Method extends BotApiMethod<T>> void executeMethods(Method method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Обработка ошибок при неудачных вызовах
        }
    }
    // Метод для списка аргументов
    public <T extends Serializable, Method extends BotApiMethod<T>> void executeMethods(List<Method> methods) {
        for (Method method : methods) {
            try {
                execute(method);
            } catch (TelegramApiException e) {
                e.printStackTrace(); // Обработка ошибок при неудачных вызовах
            }
        }
    }

}
