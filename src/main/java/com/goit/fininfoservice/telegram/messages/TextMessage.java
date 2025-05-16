package com.goit.fininfoservice.telegram.messages;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@RequiredArgsConstructor
public class TextMessage  implements BotMessage{

    private final Message message;

    @Override
    public Long getChatID() {

        return message.getChatId();
    }

    @Override
    public String getText() {

        return message.getText();
    }

    @Override
    public BotApiMethod<?> handle() {
        var chatID = this.getChatID();
        String responseText = "<b>Dear " + this.getUserName() + "</b>" + "\nYou have  typed text: \n" + this.getText();
        return SendMessage.builder().parseMode("HTML").text(responseText).chatId(chatID).build();
    }

    public String getUserName() {

        return message.getFrom().getUserName();
    }


}
