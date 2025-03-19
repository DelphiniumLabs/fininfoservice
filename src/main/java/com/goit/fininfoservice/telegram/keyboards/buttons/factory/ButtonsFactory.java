package com.goit.fininfoservice.telegram.keyboards.buttons.factory;

import com.goit.fininfoservice.telegram.keyboards.buttons.Pressable;
import org.springframework.stereotype.Component;

import java.util.Calendar;

///
/// Buttons Factory
///
public enum ButtonsFactory {
    STATEFUL_BUTTON(new StatefulButtonCreator()),
    COMMAND_BUTTON(new CommandButtonCreator());
    private final ButtonCreator creator;

    ButtonsFactory(ButtonCreator creator){
        this.creator=creator;
    }


    public Pressable getButton(String label,
                               String data,
                               boolean on) {
        return creator.create();
    }

}
