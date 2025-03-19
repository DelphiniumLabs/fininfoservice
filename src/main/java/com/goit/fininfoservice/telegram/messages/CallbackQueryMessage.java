package com.goit.fininfoservice.telegram.messages;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@RequiredArgsConstructor
public class CallbackQueryMessage implements BotMessage{
    private final CallbackQuery callbackQuery;

    @Override
    public Long getChatID() {
        return callbackQuery.getMessage().getChatId();
    }

    @Override
    public String getText() {
        return callbackQuery.getMessage().getText();
    }

    @Override
    public BotApiMethod<?> handle() {
    return null;
    }

}
