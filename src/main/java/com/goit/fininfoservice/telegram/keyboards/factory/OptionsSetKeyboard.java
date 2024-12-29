package com.goit.fininfoservice.telegram.keyboards.factory;

import com.goit.fininfoservice.telegram.keyboards.KeyBoardTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
///
/// This is base abstract class for keyboards with set of options, contains common fields for all children
///
public abstract class OptionsSetKeyboard {

    protected static final char ON_STATE_MARK = '✅';
    protected static final char OFF_STATE_MARK = ' ';
    protected static final char SPLITTER = '~';
    private final Map<String,String> buttons;//  key  Button name, value - data
    protected Set<String> choosenOptions;
   // Constractors block
    protected OptionsSetKeyboard(Map<String, String> buttons){
        this.buttons = buttons;
        this.choosenOptions = new HashSet<>();
    }
   protected OptionsSetKeyboard(Map<String, String> buttons, Set<String> choosenOptions ){
        this.buttons = buttons;
        this.choosenOptions = choosenOptions;
    }
    // ---------------------

    protected abstract void pressButtonHandler(String callbackData);

    protected abstract void saveChoosenOption(String option);

    public abstract KeyBoardTypes getKeyboardType();

    public List<String> getChoosenOptionsList(){
        return List.copyOf(choosenOptions);
    }

    // Getters
    public Map<String,String> getButtons(){
        return buttons;
    }

}

