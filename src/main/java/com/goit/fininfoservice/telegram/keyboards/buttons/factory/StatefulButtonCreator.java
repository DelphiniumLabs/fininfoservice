package com.goit.fininfoservice.telegram.keyboards.buttons.factory;

import com.goit.fininfoservice.telegram.keyboards.buttons.Pressable;
import com.goit.fininfoservice.telegram.keyboards.buttons.StatefulButton;

public class StatefulButtonCreator extends ButtonCreator{

    @Override
    protected Pressable create() {
        return new StatefulButton();
    }
}
