package com.goit.fininfoservice.telegram.service;

import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.keyboards.factory.CheckBoxKeyboardFactory;
import com.goit.fininfoservice.telegram.keyboards.factory.RadioButtonKeyboardFactory;
import com.goit.fininfoservice.telegram.keyboards.views.OptitonSetKeyboardView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class KeyboardMassageService {
    private final CheckBoxKeyboardFactory checkBoxKeyboardFactory;
    private final RadioButtonKeyboardFactory radioButtonKeyboardFactory;
    private final OptitonSetKeyboardView keyboardView;
    @Autowired
    public KeyboardMassageService(CheckBoxKeyboardFactory checkBoxKeyboardFactory,
                                  RadioButtonKeyboardFactory radioButtonKeyboardFactory){
        this.checkBoxKeyboardFactory = checkBoxKeyboardFactory;
        this.radioButtonKeyboardFactory = radioButtonKeyboardFactory;
        this.keyboardView = new OptitonSetKeyboardView();
    }


    public SendMessage getCrypto(Update update) throws EmptyButtonsMapException {
        Map<String, String> cryptoSettingsPage = new LinkedHashMap<>();
        cryptoSettingsPage.put("BTC", "/BTC");
        cryptoSettingsPage.put("ETH", "/ETH");
        cryptoSettingsPage.put("LTC", "/LTC");
        cryptoSettingsPage.put("XRP", "/XRP");
        cryptoSettingsPage.put("ADA", "/ADA");
        cryptoSettingsPage.put("ETC", "/ETC");

        var cbxKb = checkBoxKeyboardFactory.create(cryptoSettingsPage);
        cbxKb.toggleOption("/XRP");
        return SendMessage.builder().text("**Choose your favorite crypto").parseMode("markdown")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(keyboardView.getKeyBoardView(cbxKb))
                .build();
    }

    public SendMessage getTimeframes(Update update) throws EmptyButtonsMapException {
        Map<String, String> timeSettingPage = new LinkedHashMap<>();
        timeSettingPage.put("5 min", "5");
        timeSettingPage.put("15 min", "15");
        timeSettingPage.put("1 h", "60");
        timeSettingPage.put("4 h", "240");
        timeSettingPage.put("1 day", "1440");
        timeSettingPage.put("1 week", "1080");
        var rbKb = radioButtonKeyboardFactory.create(timeSettingPage);
        rbKb.toggleOption("240");

        return SendMessage.builder().text("**Choose time frame").parseMode("markdown")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(keyboardView.getKeyBoardView(rbKb))
                .build();
    }

}
