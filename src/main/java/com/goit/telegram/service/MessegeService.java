package com.goit.telegram.service;

import com.goit.telegram.factory.InlineKeyboardFactory;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessegeService {
    Map<String, String> mainPage = new HashMap<String, String>();

    public EditMessageText mainPageKeyboard(Update update){
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return EditMessageText.builder().text("Main page text")
                .messageId(update.getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(mainPage))
                .chatId(update.getMessage().getChatId())
                .build();
    }

    public SendMessage startPageKeyboard(Update update){
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return SendMessage.builder().text("Hello text")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(mainPage))
                .build();
    }
}
