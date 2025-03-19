package com.goit.fininfoservice.telegram.messages;

import com.goit.fininfoservice.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@RequiredArgsConstructor
public class CommandMessage implements BotMessage{
    private final Message command;

    @Override
    public Long getChatID() {
        return command.getChatId();
    }
    @Override
    public String getText(){
        return command.getText().trim().toLowerCase();
    }

    @Override
    public BotApiMethod<?> handle() {
        var chatId= command.getChatId();

        String responseText = "<b>Hi dear user!</b>"+Constants.GREETING;
        return SendMessage.builder().parseMode("HTML").text(responseText).chatId(chatId).build();
    }


}
