package com.goit.fininfoservice.telegram.botcommands;

import com.goit.fininfoservice.telegram.controller.commands.CommandController;
import com.goit.fininfoservice.telegram.messages.BotMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.Optional;


public interface BotCommandHandler {
    String getCode();
    Optional<SendMessage>   handle(Update update);
    Optional<SendMessage>   handle(BotMessage botMessage);
 // this method is called by Spring to inject commandController every time after BotCommandHandler created
 // adding BotCommandHandler to   commandHandlers Map<> in Controller class
    @Autowired
    default void registerMyself(CommandController commandController) {
         commandController.registerHandler(getCode(), this);
    }
}
