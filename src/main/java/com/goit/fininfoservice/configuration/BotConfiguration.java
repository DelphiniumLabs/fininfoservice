package com.goit.fininfoservice.configuration;

import com.goit.fininfoservice.telegram.Bot;
import com.goit.fininfoservice.telegram.contoller.CommandController;
import com.goit.fininfoservice.telegram.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@Configuration
public class BotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(Bot bot) throws TelegramApiException {
        var api= new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(bot);
        return api;
    }
    @Bean
    public MessageService messageService(){
        return new MessageService();

    }
    @Bean
    public CommandController commandController(){
        return new CommandController();
    }
}
