package com.goit.fininfoservice.telegram.service;

import com.goit.fininfoservice.datasources.impl.PrivatBankDataSourceImpl;
import com.goit.fininfoservice.telegram.factory.InlineKeyboardFactory;
import com.goit.fininfoservice.utils.Constants;
import lombok.Setter;
import org.jvnet.hk2.annotations.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;

//функционал генерации каждой страницы
@Service
@Setter
public class MessageService {

    @Autowired
    private  Map<String, String> mainPage;
    @Autowired
    private Map<String, String> infoPage ;
    @Autowired
    private  Map<String, String> settingPage ;
    @Autowired
    private  Map<String, String> pointAmountSettingPage ;
    @Autowired
    private  Map<String, String> bankSettingPage ;
    @Autowired
    private  Map<String, String> currencySettingPage ;
    @Autowired
    private  Map<String, String> timeSettingPage ;

    @Autowired
    private PrivatBankDataSourceImpl  privatBankDataSource;

    public EditMessageText mainPage(Update update){

        return EditMessageText.builder().text("Main page text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(mainPage))
                .build();
    }

    public SendMessage startPage(Update update){

        return SendMessage.builder().text(Constants.GREETING)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(mainPage))
                .build();
    }

    public EditMessageText infoPage(Update update){
        String infoText = privatBankDataSource.fetchData().block();
        return EditMessageText.builder().text(
                Optional.ofNullable(infoText).
                        orElse("Someting goes wrong!!!"
                        ))
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(infoPage))
                .build();
    }

    public EditMessageText updateInfoPage(Update update){
        return EditMessageText.builder().text("New info text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(infoPage))
                .build();
    }

    public EditMessageText settingPage(Update update){
        return EditMessageText.builder().text("Setting text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(settingPage))
                .build();
    }

    public EditMessageText pointAmountSettingPage(Update update){

        return EditMessageText.builder().text("Choose point Amount")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(pointAmountSettingPage))
                .build();
    }

    public EditMessageText bankSettingPage(Update update){

        return EditMessageText.builder().text("Choose bank")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(bankSettingPage))
                .build();
    }

    public EditMessageText currencySettingPage(Update update){

        return EditMessageText.builder().text("Choose currency")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getMarkup(currencySettingPage))
                .build();
    }


    public EditMessageText timeSettingPage(Update update){

        return EditMessageText.builder().text("Choose time")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(new InlineKeyboardFactory().getDoubleLineMarkup(timeSettingPage))
                .build();
    }




}
