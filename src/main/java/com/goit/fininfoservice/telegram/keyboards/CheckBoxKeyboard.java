package com.goit.fininfoservice.telegram.keyboards;

import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.keyboards.factory.Keyboard;
import com.goit.fininfoservice.telegram.keyboards.factory.OptionsSetKeyboard;

import java.util.*;

public class CheckBoxKeyboard extends OptionsSetKeyboard implements Keyboard {


    public CheckBoxKeyboard(Map<String, String> buttons) {
        super(buttons);
    }

    @Override
    protected void pressButtonHandler(String callbackData) {
        String[] splitedCallbackData = callbackData.split(String.valueOf(SPLITTER));
        toggleOption(splitedCallbackData[2]);
    }

    @Override
    public KeyBoardTypes getKeyboardType() {
        return KeyBoardTypes.CBX;
    }

    @Override
    public void build() {
        try {
            if(super.getButtons().isEmpty()){
                throw new EmptyButtonsMapException("Empty Map<String, String> buttons. Add items or provide nonempty Map");
            }

        } catch (EmptyButtonsMapException e) {
            throw new EmptyButtonsMapException(e);
        }
    }

    @Override
    public void toggleOption(String option) {
        if(choosenOptions.contains(option)){
            choosenOptions.remove(option);
        } else {
            choosenOptions.add(option);
        }
    }

    @Override
    public Set<String> selectedOptions() {
        return choosenOptions;
    }

}
