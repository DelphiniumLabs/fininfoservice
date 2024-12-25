package com.goit.fininfoservice.telegram.keyboards.factory;

import com.goit.fininfoservice.telegram.keyboards.CheckBoxKeyboard;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckBoxKeyboardFactory extends CustomKeyboardFactory{


    @Override
    protected Keyboard createCustomKeyboard(Map<String,String> buttons) {
        return new CheckBoxKeyboard(buttons);
    }
}

