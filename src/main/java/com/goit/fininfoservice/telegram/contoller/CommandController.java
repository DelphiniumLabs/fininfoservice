package com.goit.fininfoservice.telegram.contoller;

import com.goit.fininfoservice.telegram.service.MessageService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

//обработка команд
@Setter
@Service
public class CommandController {
    @Autowired
    private  MessageService messageService;
    public EditMessageText commandProcessing(Update update){
        String command = update.getCallbackQuery().getData();
        return switch (command){
            case "/info" -> messageService.infoPage(update);
            case "/updateInfoPage" -> messageService.updateInfoPage(update);
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
}
