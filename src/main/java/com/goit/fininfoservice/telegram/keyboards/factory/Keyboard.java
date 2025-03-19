package com.goit.fininfoservice.telegram.keyboards.factory;


import com.goit.fininfoservice.telegram.keyboards.KeyBoardTypes;


import java.util.Map;
import java.util.Set;

///
/// keyboards with specific behaviour
///
public interface Keyboard {

    void build();
    void toggleOption(String option); // Option state (off->on, on->off)
    Set<String> selectedOptions();
    Map<String, String> getButtons();
    KeyBoardTypes getKeyboardType();
    default void fff(){

    }
}
