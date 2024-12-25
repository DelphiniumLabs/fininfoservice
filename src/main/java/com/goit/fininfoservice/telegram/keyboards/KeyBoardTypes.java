package com.goit.fininfoservice.telegram.keyboards;

import java.util.Arrays;
///
/// types of inlineKeyboardMarkup keyboard
/// is used as first field in callBackData in inlineKeyboardMarkup
public enum KeyBoardTypes {
    CBX, // check box button
    RB,  // radio button
    CMD, // command button
    DTA; // data

    public static KeyBoardTypes getKeyBoardType(String keyBoardType) {
        for (KeyBoardTypes kbt : KeyBoardTypes.values()) {
            if (kbt.name().equals(keyBoardType)) {
                return kbt;
            }
        }
        throw new IllegalArgumentException("No such keyBoardType: "+keyBoardType+" Choose from:"+
                Arrays.toString(KeyBoardTypes.values()));
    }


}
