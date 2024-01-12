package com.goit.fininfoservice.telegram;


import com.goit.fininfoservice.telegram.contoller.CommandController;
import com.goit.fininfoservice.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    private final MessageService messageService;
    private final CommandController commandController;
    public Bot(@Value("${bot.token}") String botToken, MessageService messageService, CommandController commandController){
        super(botToken);
        this.messageService=messageService;
        this.commandController=commandController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            if(update.getMessage().getText().equals("/start")){
                try {
                    execute( messageService.startPage(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    execute(commandController.commandProcessing(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(update.hasCallbackQuery()){
            try {
                execute(commandController.commandProcessing(update));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "RateBot";
    }

}
