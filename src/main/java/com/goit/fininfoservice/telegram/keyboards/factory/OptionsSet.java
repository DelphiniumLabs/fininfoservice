package com.goit.fininfoservice.telegram.keyboards.factory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
///
/// This is base class for keyboards with set of options, contains common fields for all children
///
public abstract class OptionsSet{

    protected static final char ON_STATE_MARK = '✅';
    protected static final char OFF_STATE_MARK = ' ';
    protected static final char SEPAR = '~';
    private final Map<String,String> buttons;//  key  Button name, value - data
    protected Set<String> choosenOptions;

    protected OptionsSet(Map<String, String> buttons){
        this.buttons = buttons;
        this.choosenOptions = new HashSet<>();
    }
   protected OptionsSet(Map<String, String> buttons,Set<String> choosenOptions ){
        this.buttons = buttons;
        this.choosenOptions = choosenOptions;
    }

    protected abstract void pressButtonHandler(String callbackData);

    protected abstract void saveChoosenOption(String option);

    public List<String> getChoosenOptionsList(){
        return List.copyOf(choosenOptions);
    }

    // Getters
    protected Map<String,String> getButtons(){
        return buttons;
    }

}

