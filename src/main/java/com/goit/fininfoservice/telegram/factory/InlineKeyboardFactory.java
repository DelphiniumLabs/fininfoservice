package com.goit.fininfoservice.telegram.factory;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;
import java.util.stream.IntStream;

//Фабрика кнопок под текстом
@Component
public class InlineKeyboardFactory {
    public InlineKeyboardMarkup getMarkup(Map<String, String> buttons){
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

    public InlineKeyboardMarkup getDoubleLineMarkup(Map<String, String> buttons){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        final Set<Map.Entry<String, String>> buttonsEntrySet = buttons.entrySet();
        final List<List<InlineKeyboardButton>> rowList =
                IntStream.range(0, buttonsEntrySet.size()/2).
                        mapToObj(
                                i->buttonsEntrySet.
                                stream().skip(i*2L).limit(2).
                                map(
                                        entry ->
                                                InlineKeyboardButton.builder().
                                                text(entry.getKey()).
                                                callbackData(entry.getValue())
                                                .build()
                                ).
                                toList()).
                        toList();

        markup.setKeyboard(rowList);
        return markup;
    }

}
