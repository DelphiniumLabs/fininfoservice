package com.goit.fininfoservice.telegram.messages;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface BotMessage {
    Long getChatID();
    String getText();
    BotApiMethod <?> handle();
}
