package com.goit.fininfoservice.telegram.messages.updateresoving;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.fininfoservice.telegram.messages.BotMessage;
import com.goit.fininfoservice.telegram.messages.CallbackQueryMessage;
import com.goit.fininfoservice.telegram.messages.CommandMessage;
import com.goit.fininfoservice.telegram.messages.TextMessage;
import com.goit.fininfoservice.telegram.messages.factory.MessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.goit.fininfoservice.telegram.messages.factory.MessageFactory.*;

/**
 * This class resolve type of update and return Message or CallBackQuery in other case throws
 * UnsupportedOperationException
 */

@Slf4j
@Service
public class  UpdateTypeResolver {
    /***
     * This method implements message type resolving logic analysing update and creating
     * our custom types that implements interface BotMessage
     * @param update
     * @return
     */


    public BotMessage resolve(Update update){
        ObjectMapper upadteMapper = new ObjectMapper();
        final Update updateCopy = upadteMapper.convertValue(update,Update.class );

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.info("\n resolve Resolved type {}\n", update.getMessage().getText());
           return update.getMessage().getText().startsWith("/") ?
                   (COMMAND_MESSAGE.get(update) ):
                   ( TEXT_MESSAGE.get(update) );
        }
        if (update.hasCallbackQuery()) {
            log.info("\n resolve  Resolved type  {}\n", update.getCallbackQuery().getData());
            return CALLBACK_QUERY_MESSAGE.get(update);
        }
        throw new UnsupportedOperationException("UpdateTypeResolver: Unsupported Update type.");
    }
}
