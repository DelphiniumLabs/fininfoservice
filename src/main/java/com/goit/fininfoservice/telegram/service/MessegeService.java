package com.goit.fininfoservice.telegram.service;

import com.goit.fininfoservice.telegram.factory.InlineKeyboardFactory;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.LinkedHashMap;
import java.util.Map;

//функционал генерации каждой страницы
@Service
public class MessegeService {
    Map<String, String> mainPage = new LinkedHashMap<>();
    Map<String, String> infoPage = new LinkedHashMap<>();
    Map<String, String> settingPage = new LinkedHashMap<>();

    Map<String, String> pointAmountSettingPage = new LinkedHashMap<>();
    Map<String, String> bankSettingPage = new LinkedHashMap<>();
    Map<String, String> currencySettingPage = new LinkedHashMap<>();
    Map<String, String> timeSettingPage = new LinkedHashMap<>();


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

    public EditMessageText pointAmountSettingPage(Update update){
        pointAmountSettingPage.put("2", "/twoPointAmonunt");
        pointAmountSettingPage.put("3", "/threePointAmonunt");
        pointAmountSettingPage.put("4", "/fourPointAmonunt");
        pointAmountSettingPage.put("Назад", "/backToSettingPage");
        return EditMessageText.builder().text("Choose point Amount")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(pointAmountSettingPage))
                .build();
    }

    public EditMessageText bankSettingPage(Update update){
        bankSettingPage.put("Monobank", "/monobank");
        bankSettingPage.put("Privat", "/Privat");
        bankSettingPage.put("Other", "/Other");
        bankSettingPage.put("Назад", "/backToSettingPage");
        return EditMessageText.builder().text("Choose bank")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(bankSettingPage))
                .build();
    }

    public EditMessageText currencySettingPage(Update update){
        currencySettingPage.put("UAH", "/UAH");
        currencySettingPage.put("USD", "/USD");
        currencySettingPage.put("EUR", "/EUR");
        currencySettingPage.put("Назад", "/backToSettingPage");
        return EditMessageText.builder().text("Choose currency")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkap(currencySettingPage))
                .build();
    }


    public EditMessageText timeSettingPage(Update update){
        timeSettingPage.put("9", "/9Oclock");
        timeSettingPage.put("10", "/10Oclock");
        timeSettingPage.put("11", "/11Oclock");
        timeSettingPage.put("12", "/12Oclock");
        timeSettingPage.put("13", "/13Oclock");
        timeSettingPage.put("14", "/14Oclock");
        timeSettingPage.put("15", "/15Oclock");
        timeSettingPage.put("16", "/16Oclock");
        timeSettingPage.put("17", "/17Oclock");
        timeSettingPage.put("18", "/18Oclock");
        timeSettingPage.put("Відключити", "/NoNotify");
        timeSettingPage.put("Назад", "/backToSettingPage");
        return EditMessageText.builder().text("Choose time")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getDoubleLineMarkup(timeSettingPage))
                .build();
    }




}
