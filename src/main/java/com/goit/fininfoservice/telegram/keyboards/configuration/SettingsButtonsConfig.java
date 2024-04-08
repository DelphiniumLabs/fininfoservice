package com.goit.fininfoservice.telegram.keyboards.configuration;

import com.goit.fininfoservice.telegram.keyboards.factory.InlineKeyboardFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class SettingsButtonsConfig {
    @Bean
    @Scope("prototype")
    @Lazy(true)
    Map<String, String> pointAmountSettingPage(){
        Map<String, String> pointAmountSettingPage = new LinkedHashMap<>();
        pointAmountSettingPage.put("2", "/twoPointAmount");
        pointAmountSettingPage.put("3", "/threePointAmount");
        pointAmountSettingPage.put("4", "/fourPointAmount");
        pointAmountSettingPage.put("Назад", "/backToSettingPage");
        return pointAmountSettingPage;
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    public InlineKeyboardMarkup pointAmountSettingPageIkm(Map<String,String> pointAmountSettingPage){
        return new InlineKeyboardFactory().getMarkup(pointAmountSettingPage);
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    Map<String, String> bankSettingPage() {
        Map<String, String> bankSettingPage = new LinkedHashMap<>();
        bankSettingPage.put("Monobank", "/monobank");
        bankSettingPage.put("Privat", "/Privat");
        bankSettingPage.put("Other", "/Other");
        bankSettingPage.put("Назад", "/backToSettingPage");
        return bankSettingPage;
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    public InlineKeyboardMarkup bankSettingPageIkm(Map<String,String> bankSettingPage){
        return new InlineKeyboardFactory().getMarkup(bankSettingPage);
    }

    @Bean
    @Scope("prototype")
    @Lazy(true)
    Map<String, String> currencySettingPage() {
        Map<String, String> currencySettingPage = new LinkedHashMap<>();
        currencySettingPage.put("UAH", "/UAH");
        currencySettingPage.put("USD", "/USD");
        currencySettingPage.put("EUR", "/EUR");
        currencySettingPage.put("Назад", "/backToSettingPage");
        return currencySettingPage;
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    public InlineKeyboardMarkup currencySettingPageIkm(Map<String,String> currencySettingPage){
        return new InlineKeyboardFactory().getMarkup(currencySettingPage);
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    Map<String, String> timeSettingPage() {
        Map<String, String> timeSettingPage = new LinkedHashMap<>();
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
        timeSettingPage.put("19", "/18Oclock");
        timeSettingPage.put("Відключити", "/NoNotify");
        timeSettingPage.put("Назад", "/backToSettingPage");
        return timeSettingPage;
    }
    @Bean
    @Scope("prototype")
    @Lazy(true)
    public InlineKeyboardMarkup timeSettingPageIkm(Map<String,String> timeSettingPage){
        return new InlineKeyboardFactory().getDoubleLineMarkup(timeSettingPage);
    }

}
