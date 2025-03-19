package com.goit.fininfoservice.telegram.messages.handlers;

import com.goit.fininfoservice.telegram.messages.BotMessage;

@FunctionalInterface
public interface BotMessageHandler {
    void handle(BotMessage message);
}
