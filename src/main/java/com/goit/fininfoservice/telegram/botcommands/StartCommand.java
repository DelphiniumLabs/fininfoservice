package com.goit.fininfoservice.telegram.botcommands;

import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.service.MessageService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class StartCommand implements BotCommandHandler
{
    private final MessageService messageService;


    @Override
    public Optional<SendMessage> handle(Update update) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true); // Подгоняет размер клавиатуры
        keyboard.setOneTimeKeyboard(false); // Клавиатура исчезает после нажатия

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("🔍 Найти"));
        row1.add(new KeyboardButton("📜 История"));
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("⚙ Настройки"));
        rows.add(row1);
        rows.add(row2);
        keyboard.setKeyboard(rows);

        var chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage(chatId.toString(), "Выберите действие:");
        message.setReplyMarkup(keyboard);

        log.info("\n Handle method called from {}\n", this.getClass());

        return Optional.of(message);
    }

    @Override
    public Optional<SendMessage> handle(BotMessage botMessage) {
        return Optional.empty();
    }


    @Override
    public String getCode() {
        return Commands.START.getCode();
    }

    SendMessage handleAndReturnSendMessage(Update update) {
        return messageService.startPage(update);
    }
}
