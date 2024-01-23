package com.goit.fininfoservice.telegram.keyboards.configuration;

import com.goit.fininfoservice.telegram.keyboards.factory.InlineKeyboardFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ControlButtonsConfig {
    @Bean
    Map<String,String> mainPage(){
        Map<String,String> mainPage =new LinkedHashMap<>();
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return mainPage;
    }
    @Bean
    @Lazy(true)
    public InlineKeyboardMarkup mainPageIkm(Map<String,String> mainPage){
        return new InlineKeyboardFactory().getMarkup(mainPage);
    }

    @Bean
    Map<String,String> infoPage(){
        Map<String,String> infoPage =new LinkedHashMap<>();
        infoPage.put("Обновити", "/updateInfoPage");
        infoPage.put("Назад", "/backToMainPage");
        return infoPage;
    }
    @Bean
    @Lazy(true)
    public InlineKeyboardMarkup infoPageIkm(Map<String,String> infoPage){
        return new InlineKeyboardFactory().getMarkup(infoPage);
    }

    @Bean
    Map<String,String> settingPage(){
        Map<String,String> settingPage =new LinkedHashMap<>();

        settingPage.put("Кількість знаків після коми", "/pointAmountSetting");
        settingPage.put("Банк", "/bankSetting");
        settingPage.put("Валюта", "/currencySetting");
        settingPage.put("Час Сповіщень", "/timeSetting");
        settingPage.put("Назад", "/backToMainPage");
        return  settingPage;
    }
    @Bean
    @Lazy(true)
    public InlineKeyboardMarkup settingPageIkm(Map<String,String> settingPage){
        return new InlineKeyboardFactory().getMarkup(settingPage);
    }

}
