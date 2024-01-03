package com.goit.telegram.contoller;

import com.goit.telegram.service.MessegeService;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Controller
public class ComandController {
    private MessegeService service = new MessegeService();

    public EditMessageText commandProcessing(Update update){
        String command = update.getMessage().getText();
        switch (command){
            case "/start":
                return service.mainPageKeyboard(update);
            default:
                return service.mainPageKeyboard(update);
        }
    }
}
