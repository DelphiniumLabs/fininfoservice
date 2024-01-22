package com.goit.fininfoservice.telegram.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ButtonPagesConfiguration {
    @Bean
    public Map<String,String> mainPage(){
        Map<String,String> mainPage =new LinkedHashMap<>();
        mainPage.put("Отримати інфо", "/info");
        mainPage.put("Налаштування", "/settings");
        return mainPage;
    }

    @Bean
    public Map<String,String> infoPage(){
        Map<String,String> infoPage =new LinkedHashMap<>();
        infoPage.put("Обновити", "/updateInfoPage");
        infoPage.put("Назад", "/backToMainPage");
        return infoPage;
    }

    @Bean
    public  Map<String,String> settingPage(){
        Map<String,String> settingPage =new LinkedHashMap<>();

        settingPage.put("Кількість знаків після коми", "/pointAmountSetting");
        settingPage.put("Банк", "/bankSetting");
        settingPage.put("Валюта", "/currencySetting");
        settingPage.put("Час Сповіщень", "/timeSetting");
        settingPage.put("Назад", "/backToMainPage");
        return  settingPage;
    }
    @Bean
    public  Map<String, String> pointAmountSettingPage(){
        Map<String, String> pointAmountSettingPage = new LinkedHashMap<>();
        pointAmountSettingPage.put("2", "/twoPointAmount");
        pointAmountSettingPage.put("3", "/threePointAmount");
        pointAmountSettingPage.put("4", "/fourPointAmount");
        pointAmountSettingPage.put("Назад", "/backToSettingPage");
        return pointAmountSettingPage;
    }
    @Bean
    public  Map<String, String> bankSettingPage() {
        Map<String, String> bankSettingPage = new LinkedHashMap<>();
        bankSettingPage.put("Monobank", "/monobank");
        bankSettingPage.put("Privat", "/Privat");
        bankSettingPage.put("Other", "/Other");
        bankSettingPage.put("Назад", "/backToSettingPage");
        return bankSettingPage;
    }
    @Bean
    public  Map<String, String> currencySettingPage() {
        Map<String, String> currencySettingPage = new LinkedHashMap<>();
        currencySettingPage.put("UAH", "/UAH");
        currencySettingPage.put("USD", "/USD");
        currencySettingPage.put("EUR", "/EUR");
        currencySettingPage.put("Назад", "/backToSettingPage");
        return currencySettingPage;
    }
    @Bean
    public  Map<String, String> timeSettingPage() {
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
        timeSettingPage.put("Відключити", "/NoNotify");
        timeSettingPage.put("Назад", "/backToSettingPage");
        return timeSettingPage;
    }

}
