package com.goit.fininfoservice.telegram.contoller;

import com.goit.fininfoservice.telegram.service.MessageService;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

//обработка команд
@Controller
public class ComandController {
    private MessageService service = new MessageService();

    public EditMessageText commandProcessing(Update update){
        String command = update.getCallbackQuery().getData();
        switch (command){
            case "/info":
                return service.infoPage(update);
            case "/updateInfoPage":
                return service.updateInfoPage(update);
            case "/backToMainPage":
                return service.mainPage(update);
            case "/settings":
                return service.settingPage(update);
            case "/backToSettingPage":
                return service.settingPage(update);
            case "/pointAmountSetting":
                return service.pointAmountSettingPage(update);
            case "/bankSetting":
                return service.bankSettingPage(update);
            case "/currencySetting":
                return service.currencySettingPage(update);
            case "/timeSetting":
                return service.timeSettingPage(update);

            default:
                return service.mainPage(update);
        }
    }
}
