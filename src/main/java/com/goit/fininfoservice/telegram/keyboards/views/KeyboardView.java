package com.goit.fininfoservice.telegram.keyboards.views;

import com.goit.fininfoservice.telegram.keyboards.factory.OptionsSetKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface KeyboardView {
    public InlineKeyboardMarkup getKeyBoardView(OptionsSetKeyboard optionsSetKeyboard);
}
