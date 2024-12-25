package com.goit.fininfoservice.telegram.keyboards.factory;

import com.goit.fininfoservice.telegram.keyboards.RadioButtonKeyboard;

import java.util.Map;

public class RadioButtonKeyboardFactory extends CustomKeyboardFactory {

    @Override
    protected Keyboard createCustomKeyboard(Map<String, String> buttons) {
        return new RadioButtonKeyboard(buttons);
    }
}
