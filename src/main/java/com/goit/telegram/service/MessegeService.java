package com.goit.telegram.service;

import com.goit.telegram.factory.InlineKeyboardFactory;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//функционал генерации каждой страницы
@Service
public class MessegeService {
    Map<String, String> mainPage = new LinkedHashMap<>();
    Map<String, String> infoPage = new LinkedHashMap<>();
    Map<String, String> settingPage = new LinkedHashMap<>();

    public EditMessageText mainPage(Update update){
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return EditMessageText.builder().text("Main page text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(mainPage))
                .build();
    }

    public SendMessage startPage(Update update){
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return SendMessage.builder().text("Hello text")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(mainPage))
                .build();
    }

    public EditMessageText infoPage(Update update){
        infoPage.put("Обновити", "/updateInfoPage");
        infoPage.put("Назад", "/backToMainPage");
        return EditMessageText.builder().text("Info text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(infoPage))
                .build();
    }

    public EditMessageText updateInfoPage(Update update){
        infoPage.put("Обновити", "/updateInfoPage");
        infoPage.put("Назад", "/backToMainPage");
        return EditMessageText.builder().text("New info text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(infoPage))
                .build();
    }

    public EditMessageText settingPage(Update update){
        settingPage.put("Кількість знаків після коми", "/pointAmountSetting");
        settingPage.put("Банк", "/bankSetting");
        settingPage.put("Валюта", "/currencySetting");
        settingPage.put("Час Сповіщень", "/timeSetting");
        settingPage.put("Назад", "/backToMainPage");
        return EditMessageText.builder().text("Setting text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(settingPage))
                .build();
    }

}
