package com.goit.fininfoservice.telegram.keyboards.factory;

import java.util.Map;
/// remake to Options set kyeboard
public abstract class CustomKeyboardFactory {
        public Keyboard create(Map<String,String> buttons) {
            Keyboard keyboard = createCustomKeyboard(buttons);
            keyboard.build();
            return keyboard;
        }
        protected abstract Keyboard createCustomKeyboard(Map<String,String> buttons);
    }

