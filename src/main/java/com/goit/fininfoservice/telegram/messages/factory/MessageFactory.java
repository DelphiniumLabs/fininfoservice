package com.goit.fininfoservice.telegram.messages.factory;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.messages.TextMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public enum MessageFactory {
    TEXT_MESSAGE(new TextMessageCreator()),
    COMMAND_MESSAGE(new CommandMessageCreator()),
    CALLBACK_QUERY_MESSAGE(new CallbackQueryMessageCreator());
    private final BotMessageCreator creator;

    MessageFactory(BotMessageCreator creator){
        this.creator=creator;
    }
    public BotMessage get(Update update){
        return creator.create(update);
    }
}
