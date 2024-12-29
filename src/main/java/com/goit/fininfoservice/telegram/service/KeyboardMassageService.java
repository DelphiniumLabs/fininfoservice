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
                                  RadioButtonKeyboardFactory radioButtonKeyboardFactory,
                                  OptitonSetKeyboardView keyboardView){
        this.checkBoxKeyboardFactory = checkBoxKeyboardFactory;
        this.radioButtonKeyboardFactory = radioButtonKeyboardFactory;
        this.keyboardView = keyboardView;
    }


    public SendMessage getHours(Update update) throws EmptyButtonsMapException {
        Map<String, String> timeSettingPage = new LinkedHashMap<>();
        timeSettingPage.put("BTC", "/BTC");
        timeSettingPage.put("ETH", "/ETH");
        timeSettingPage.put("LTC", "/LTC");
        timeSettingPage.put("XRP", "/XRP");
        timeSettingPage.put("ADA", "/ADA");
        timeSettingPage.put("ETC", "/ETC");

        var ikm = checkBoxKeyboardFactory.create(timeSettingPage);
        return SendMessage.builder().text("**Choose your favorite crypto").parseMode("markdown")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(ikm.getInlineKeyboardMarkup())
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

        return SendMessage.builder().text("**Choose time frame").parseMode("markdown")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(keyboardView.getKeyBoardView(rbKb))
                .build();
    }

}
