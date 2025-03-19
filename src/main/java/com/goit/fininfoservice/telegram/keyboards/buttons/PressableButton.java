package com.goit.fininfoservice.telegram.keyboards.buttons;

import lombok.RequiredArgsConstructor;
import lombok.Data;

@RequiredArgsConstructor
@Data
public abstract class PressableButton implements Pressable{

    private final String label;
    private final String data;
    boolean on = false;

    public boolean getState() {
        return isOn();
    }

    public void setState(boolean nextState) {
        setOn(nextState);
    }

    public boolean toggleState() {
        setState(!getState());
        return getState();
    }
}
