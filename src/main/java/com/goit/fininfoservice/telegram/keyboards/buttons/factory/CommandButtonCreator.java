package com.goit.fininfoservice.telegram.keyboards.buttons.factory;

import com.goit.fininfoservice.telegram.keyboards.buttons.CommandButton;
import com.goit.fininfoservice.telegram.keyboards.buttons.Pressable;

public class CommandButtonCreator extends ButtonCreator {
    @Override
    protected Pressable create() {
        return new CommandButton();
    }
}
