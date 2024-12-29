package com.goit.fininfoservice.telegram.keyboards;

import com.goit.fininfoservice.telegram.keyboards.buttons.Button;
import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.keyboards.factory.Keyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public class CheckBoxKeyboard implements Keyboard {

    private static final KeyBoardTypes KEY_BOARD_TYPE = KeyBoardTypes.CBX;
    private static final char ON_STATE_MARK = '☑';
    private static final char OFF_STATE_MARK = '☐';
    private static final char SEPAR = '~';

    private final Map<String,String> buttons; // represent buttons key - button name; value - callback data

    public CheckBoxKeyboard(Map<String, String> buttons) {
        this.buttons = buttons;
    }

    List<Button> getButtonsView() {
        List<Button> buttonList = new ArrayList<>();
        if (buttons.entrySet().isEmpty()) {
            return buttonList;
        }
        for (Map.Entry<String, String> item : buttons.entrySet()) {
            List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
            buttonList.add(new Button(ON_STATE_MARK + item.getKey(),
                    item.getValue()));
        }
        return buttonList;
    }
    @Override
    public void build() {
        for(Map.Entry<String,String> entry : buttons.entrySet()){
            entry.setValue(String.valueOf(KEY_BOARD_TYPE) + SEPAR + 0 + SEPAR +
                    entry.getValue());
        }
    }
}
