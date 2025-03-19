package com.goit.fininfoservice.telegram.controller.commands;


import com.goit.fininfoservice.telegram.botcommands.BotCommandHandler;
import com.goit.fininfoservice.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandController {

    private final MessageService messageService;
    private  final Map<String, BotCommandHandler> commandHandlers = new HashMap<>();

    public Optional<SendMessage > processCommand(Update update){
        String command = update.getMessage().getText(); //throws exception in case of callbackQuery present because of Null
        return commandHandlers.get(command).handle(update);
    }

    public EditMessageText commandProcessing(Update update){


        String command = update.getCallbackQuery().getData();

        return switch (command){
            case "/info" -> messageService.infoPage(update);
            case "/updateInfoPage" -> messageService.updateInfoPage1(update);// updateInfoPage1
            case "/backToMainPage" -> messageService.mainPage(update);
            case "/settings" -> messageService.settingPage(update);
            case "/backToSettingPage" -> messageService.settingPage(update);
            case "/pointAmountSetting" -> messageService.pointAmountSettingPage(update);
            case "/bankSetting" -> messageService.bankSettingPage(update);
            case "/currencySetting" -> messageService.currencySettingPage(update);
            case "/timeSetting" -> messageService.timeSettingPage(update);
            default -> messageService.mainPage(update);
        };

    }

    public void registerHandler(String code, BotCommandHandler commandHandler) {
        commandHandlers.put(code, commandHandler);
    }
}
