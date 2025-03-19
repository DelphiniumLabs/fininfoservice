package com.goit.fininfoservice.telegram.messages.factory;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.messages.CommandMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandMessageCreator extends BotMessageCreator{
    @Override
    protected BotMessage create(Update update) {
        return new CommandMessage(update.getMessage());
    }
}
