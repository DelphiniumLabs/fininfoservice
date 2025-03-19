package com.goit.fininfoservice.telegram.messages.factory;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class BotMessageCreator {
    protected abstract BotMessage create(Update update);
}
