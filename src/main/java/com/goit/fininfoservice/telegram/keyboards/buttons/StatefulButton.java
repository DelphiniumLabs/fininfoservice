package com.goit.fininfoservice.telegram.keyboards.buttons;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatefulButton implements Pressable{

    private ButtonDTO buttonDTO;

    @Override
    public void press() {
        toggleState();
    }

    public boolean getState() {
        return buttonDTO.isOn();
    }

    public void setState(boolean nextState) {
        buttonDTO.setOn(nextState);
    }

    public boolean toggleState() {
        setState(!getState());
        return getState();
    }


}
