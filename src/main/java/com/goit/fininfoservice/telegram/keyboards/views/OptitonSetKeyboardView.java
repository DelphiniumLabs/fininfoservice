package com.goit.fininfoservice.telegram.keyboards.views;

import com.goit.fininfoservice.telegram.keyboards.factory.Keyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.goit.fininfoservice.telegram.keyboards.*;

public class OptitonSetKeyboardView implements KeyboardView{
    protected static final char CHOOSEN_MARK = '✅';
    protected static final char FREE_MARK = ' ';
    protected static final char SPLITTER = '~';

    @Override
    public InlineKeyboardMarkup getKeyBoardView(Keyboard optionsSetKeyboard) {

        var buttons = optionsSetKeyboard.getButtons();
        var choosenOptionsList = optionsSetKeyboard.selectedOptions();

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        if(choosenOptionsList.isEmpty()){
            for (Map.Entry<String, String> item : buttons.entrySet()) {
                List<InlineKeyboardButton> button = new ArrayList<>();
                button.add(InlineKeyboardButton.builder()
                        .text(FREE_MARK+item.getKey())
                        .callbackData(String.valueOf(optionsSetKeyboard.getKeyboardType()) +
                                SPLITTER + 0 + SPLITTER +item.getValue())
                        .build());
                rowList.add(button);
            }
        } else {
            for (Map.Entry<String, String> item : buttons.entrySet()) {
                List<InlineKeyboardButton> button = new ArrayList<>();
                button.add(InlineKeyboardButton.builder()
                        .text(choosenOptionsList.contains(item.getValue())?
                                CHOOSEN_MARK + item.getKey():FREE_MARK+item.getKey())
                        .callbackData(String.valueOf(optionsSetKeyboard.getKeyboardType()) +
                                SPLITTER + 1 + SPLITTER +item.getValue())
                        .build());
                rowList.add(button);
            }
            List<InlineKeyboardButton> button = new ArrayList<>();
            button.add(
                    InlineKeyboardButton.builder()
                            .text("<-Back")
                            .callbackData(String.valueOf(KeyBoardTypes.CMD) + SPLITTER + "/back")
                            .build());
            rowList.add(button);
        }
        markup.setKeyboard(rowList);
        return markup;

    }


}
