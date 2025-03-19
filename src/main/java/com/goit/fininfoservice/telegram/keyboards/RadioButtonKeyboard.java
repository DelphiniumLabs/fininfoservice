package com.goit.fininfoservice.telegram.keyboards;

import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import com.goit.fininfoservice.telegram.keyboards.factory.Keyboard;
import com.goit.fininfoservice.telegram.keyboards.factory.OptionsSetKeyboard;
import java.util.Map;
import java.util.Set;

public class RadioButtonKeyboard extends OptionsSetKeyboard implements Keyboard {

    //// mark class as radiobutton
   protected static final KeyBoardTypes KEY_BOARD_TYPE = KeyBoardTypes.RB;

    public RadioButtonKeyboard(Map<String, String> buttons) {
        super(buttons);
    }

    public RadioButtonKeyboard(Map<String, String> buttons, Set<String> choosenOptions) {
        super(buttons, choosenOptions);
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
    public Set<String> selectedOptions() {
        return choosenOptions;
    }

    @Override
    public KeyBoardTypes getKeyboardType(){
        return KeyBoardTypes.RB;
    }



    @Override
    protected void pressButtonHandler(String callbackData) {
        // еще не понял как
        String[] splitedCallbackData = callbackData.split(String.valueOf(SPLITTER));
        toggleOption(splitedCallbackData[2]);
    }

    @Override
    public void toggleOption(String option) {
        super.choosenOptions.clear();
        super.choosenOptions.add(option);
    }
}
