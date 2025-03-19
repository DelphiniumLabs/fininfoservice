package com.goit.fininfoservice.telegram.botcommands;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class InfoPageCommand implements BotCommandHandler {

    private final MessageService messageService;

    @Override
    public String getCode() {
        return Commands.INFO.getCode();
    }

    @Override
    public Optional<SendMessage> handle(Update update) {
        // test of method
        log.info("\n Handle method called from {}\n", getCode());
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
                buttonsRow.add(InlineKeyboardButton.builder().text("Update")
                .callbackData("/update").build());
        rowList.add(buttonsRow);
        markup.setKeyboard(rowList);
        return Optional.of(SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId())).text("Info page message").replyMarkup(markup).build());
    }

    @Override
    public Optional<SendMessage> handle(BotMessage botMessage) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(InlineKeyboardButton.builder().text("Update")
                .callbackData("/update").build());
        rowList.add(buttonsRow);
        markup.setKeyboard(rowList);
        return Optional.of(SendMessage.builder().chatId(String.valueOf(botMessage.getChatID())).text("Info page message").replyMarkup(markup).build());

    }

}
