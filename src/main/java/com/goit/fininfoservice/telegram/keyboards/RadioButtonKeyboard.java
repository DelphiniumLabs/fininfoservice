package com.goit.fininfoservice.telegram.keyboards;

import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.keyboards.factory.Keyboard;
import com.goit.fininfoservice.telegram.keyboards.factory.OptionsSet;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RadioButtonKeyboard extends OptionsSet implements Keyboard {

    //// mark class as radiobutton
    protected static final KeyBoardTypes KEY_BOARD_TYPE = KeyBoardTypes.RB;

    public RadioButtonKeyboard(Map<String, String> buttons) {
        super(buttons);
    }

    public RadioButtonKeyboard(Map<String, String> buttons, Set<String> choosenOptions) {
        super(buttons, choosenOptions);
    }

    @Override
    public void build() {
        for (Map.Entry<String, String> entry : super.getButtons().entrySet()) {
            if (super.getChoosenOptionsList().contains(entry.getValue())) {
                entry.setValue(String.valueOf(KEY_BOARD_TYPE) + SEPAR + 1 + SEPAR+entry.getValue());
            }
        }
    }

    @Override
    public InlineKeyboardMarkup getInlineKeyboardMarkup() throws EmptyButtonsMapException {
        var buttons = super.getButtons();
        var choosedValue = getChoosenOptionsList();

        if (buttons.entrySet().isEmpty()) {
            throw new EmptyButtonsMapException();
        }

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        if(choosedValue.isEmpty()){
        for (Map.Entry<String, String> item : buttons.entrySet()) {
            List<InlineKeyboardButton> button = new ArrayList<>();
            button.add(InlineKeyboardButton.builder()
                    .text(item.getKey())
                    .callbackData(String.valueOf(KEY_BOARD_TYPE) + SEPAR + 1 + SEPAR+item.getValue())
                    .build());

            rowList.add(button);
        }
        } else {
            for (Map.Entry<String, String> item : buttons.entrySet()) {
                List<InlineKeyboardButton> button = new ArrayList<>();
                button.add(InlineKeyboardButton.builder()
                        .text(choosedValue.get(0).equals(item.getValue())?
                                String.valueOf(ON_STATE_MARK)+ item.getKey():"")
                        .callbackData(String.valueOf(KEY_BOARD_TYPE) + SEPAR + 1 + SEPAR+item.getValue())
                        .build());

                rowList.add(button);
            }
        }
        markup.setKeyboard(rowList);


        return markup;
    }

    @Override
    protected void pressButtonHandler(String callbackData) {
        // еще не понял как
        String[] splitedCallbackData = callbackData.split(String.valueOf(SEPAR));
        saveChoosenOption(splitedCallbackData[2]);
    }

    @Override
    protected void saveChoosenOption(String option) {
        super.choosenOptions.clear();
        super.choosenOptions.add(option);
    }
}
