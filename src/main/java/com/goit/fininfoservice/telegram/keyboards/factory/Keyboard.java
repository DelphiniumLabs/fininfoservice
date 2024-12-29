package com.goit.fininfoservice.telegram.keyboards.factory;

import com.goit.fininfoservice.telegram.keyboards.exceptions.EmptyButtonsMapException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

///
/// keyboards with specific behaviour
///
public interface Keyboard {

    void build();
    //InlineKeyboardMarkup getInlineKeyboardMarkup() throws EmptyButtonsMapException;
}
