package com.goit.fininfoservice.telegram;


import com.goit.fininfoservice.telegram.contoller.CommandController;
import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.service.KeyboardMassageService;
import com.goit.fininfoservice.telegram.service.MessageService;
import com.goit.fininfoservice.utils.BotStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    private final KeyboardMassageService keyboardMassageService;

    private BotStatus botStatus = BotStatus.AWATING_COMDAND;

    public Bot(@Value("${bot.token}") String botToken, MessageService messageService,
               CommandController commandController, KeyboardMassageService keyboardMassageService){
        super(botToken);
        this.messageService=messageService;
        this.commandController=commandController;
        this.keyboardMassageService = keyboardMassageService;


        lastOnUpdateReceived=LocalDateTime.now().minus(500, ChronoUnit.MILLIS);
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (botStatus != BotStatus.STOPPED) {

                if (update.hasMessage() && update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/start")) {
                        botStatus = BotStatus.STARTED;
                       // execute(messageService.startPage(update));
                        sendApiMethodAsync(messageService.startPage(update));
                    }
                    if (update.getMessage().getText().equals("/stop")) {
                        botStatus = BotStatus.STOPPED;
                       System.out.println("--------->"+messageService.stopPage(update).toString());
                    }
                    if(update.getMessage().getText().equals("/tf")){

                        execute(keyboardMassageService.getTimeframes(update));
                    }
                } else if (update.hasCallbackQuery()) {

                    execute(commandController.commandProcessing(update));

                }
            } else {
                execute(messageService.stopPage(update));
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (EmptyButtonsMapException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {

        return "RateBot";
    }


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
