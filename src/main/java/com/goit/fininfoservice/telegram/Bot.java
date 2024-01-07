package com.goit.fininfoservice.telegram;


import com.goit.fininfoservice.telegram.contoller.ComandController;
import com.goit.fininfoservice.telegram.service.MessegeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    public Bot(@Value("${bot.token}") String botToken){
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            if(update.getMessage().getText().equals("/start")){
                try {
                    execute(new MessegeService().startPage(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    execute(new ComandController().commandProcessing(update));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(update.hasCallbackQuery()){
            try {
                execute(new ComandController().commandProcessing(update));
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
