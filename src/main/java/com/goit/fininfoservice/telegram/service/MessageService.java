package com.goit.fininfoservice.telegram.service;

import com.goit.fininfoservice.datasources.DataSource;
import com.goit.fininfoservice.utils.Constants;
import lombok.Setter;
import org.jvnet.hk2.annotations.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Optional;

//функционал генерации каждой страницы
@Service
@Setter
public class MessageService {

    @Autowired
    private InlineKeyboardMarkup mainPageIkm; // Lazy init
    @Autowired
    private InlineKeyboardMarkup infoPageIkm ;
    @Autowired
    private  InlineKeyboardMarkup settingPageIkm ;
    @Autowired
    private  InlineKeyboardMarkup pointAmountSettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup bankSettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup currencySettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup timeSettingPageIkm;
    /*
    @Autowired
    private RestApiReactiveDataSource privatBankReactiveDataSource;*/

    @Autowired
    private DataSource<String> privatBankReactiveDataSource;
    public EditMessageText mainPage(Update update){

        return EditMessageText.builder().text("Main page text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.mainPageIkm) //new InlineKeyboardFactory().getMarkup(mainPage)
                .build();
    }

    public SendMessage startPage(Update update){

        return SendMessage.builder().text(Constants.GREETING)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(this.mainPageIkm)
                .build();
    }

    public EditMessageText infoPage(Update update){
        String infoText = privatBankReactiveDataSource.fetchData().block();
        return EditMessageText.builder().text(
                Optional.ofNullable(infoText).
                        orElse("Someting goes wrong!!!"
                        ))
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.infoPageIkm)
                .build();
    }

    public EditMessageText updateInfoPage(Update update){
        String infoText = Optional.ofNullable(
                privatBankReactiveDataSource
                        .fetchData()
                        .block())
                .orElse("Someting goes wrong!!!"
                );
        return EditMessageText.builder().text(infoText)
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.infoPageIkm)
                .build();
    }

    public EditMessageText settingPage(Update update){
        return EditMessageText.builder().text("Setting text")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.settingPageIkm)
                .build();
    }

    public EditMessageText pointAmountSettingPage(Update update){

        return EditMessageText.builder().text("Choose point Amount")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(pointAmountSettingPageIkm)
                .build();
    }

    public EditMessageText bankSettingPage(Update update){

        return EditMessageText.builder().text("Choose bank")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(bankSettingPageIkm)
                .build();
    }

    public EditMessageText currencySettingPage(Update update){

        return EditMessageText.builder().text("Choose currency")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(currencySettingPageIkm)
                .build();
    }


    public EditMessageText timeSettingPage(Update update){

        return EditMessageText.builder().text("Choose time")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(timeSettingPageIkm)
                .build();
    }




}
