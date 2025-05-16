package com.goit.fininfoservice.telegram.messages.factory;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.messages.CallbackQueryMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryMessageCreator extends BotMessageCreator
{
    @Override
    protected BotMessage create(Update update) {
        return new CallbackQueryMessage(update.getCallbackQuery());
    }
}
