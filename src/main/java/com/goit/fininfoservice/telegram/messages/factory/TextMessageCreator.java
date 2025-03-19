package com.goit.fininfoservice.telegram.messages.factory;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.messages.TextMessage;
import jakarta.validation.constraints.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TextMessageCreator extends BotMessageCreator{
    @Override
    protected BotMessage create(Update update) {
        return new TextMessage(update.getMessage());
    }
}
