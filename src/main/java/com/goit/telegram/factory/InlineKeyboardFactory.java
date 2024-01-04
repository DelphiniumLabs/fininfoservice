package com.goit.telegram.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Фабрика кнопок под текстом
@Component
public class InlineKeyboardFactory {
    public InlineKeyboardMarkup getMarkap(Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for(Map.Entry<String, String> item : buttons.entrySet()){
            List<InlineKeyboardButton> button = new ArrayList<>();
            button.add(InlineKeyboardButton.builder()
                    .text(item.getKey())
                    .callbackData(item.getValue())
                    .build());
            rowList.add(button);
        }
        markup.setKeyboard(rowList);
        return markup;
    }
}
