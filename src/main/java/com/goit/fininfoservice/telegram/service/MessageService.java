package com.goit.fininfoservice.telegram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.fininfoservice.datasources.ExchangeRateController;
import com.goit.fininfoservice.datasources.dto.MonoBankExchangeRate;
import com.goit.fininfoservice.datasources.dto.NbuExchangeRate;
import com.goit.fininfoservice.datasources.dto.PrivatBankExchangeRate;
import com.goit.fininfoservice.telegram.keyboards.factory.InlineKeyboardFactory;
import com.goit.fininfoservice.utils.Constants;
import com.goit.fininfoservice.utils.CurrencyCode;
import lombok.Setter;
import org.jvnet.hk2.annotations.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

//функционал генерации каждой страницы
@Service
@Setter
public class MessageService {

    @Autowired
    private InlineKeyboardMarkup mainPageIkm; // Lazy init
    @Autowired
    private InlineKeyboardMarkup infoPageIkm ;
    @Autowired
    private  InlineKeyboardMarkup settingPageIkm ;
    @Autowired
    private  InlineKeyboardMarkup pointAmountSettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup bankSettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup currencySettingPageIkm;
    @Autowired
    private  InlineKeyboardMarkup timeSettingPageIkm;

    @Autowired
    private  ExchangeRateController erc;

    private Mono<String> privatMonoString;
    private Mono<String> nbuMonoString;
    private Mono<String> monoMonoString;
    @Autowired
    private InlineKeyboardFactory inlineKeyboardFactory;
    @Autowired
    private CurrencyCode currencyCodes;

    public EditMessageText mainPage(Update update){
              return EditMessageText.builder().text("*Main page text*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.mainPageIkm)
                .build();
    }

    public SendMessage startPage(Update update){

        return SendMessage.builder().text(Constants.GREETING)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(this.mainPageIkm)
                .build();
    }

    public EditMessageText infoPage(Update update){
        StringBuilder infoText= new StringBuilder();
        if (Objects.isNull(privatMonoString)) {
                      privatMonoString=erc.privatBank();
        }

        infoText.append(privatMonoString.blockOptional().orElse("Someting goes wrong with PRIVATBANK!!!"));
        PrivatBankExchangeRate[] pvtBnk;
        try {
            pvtBnk = (new ObjectMapper()).
                    readValue( infoText.toString(), PrivatBankExchangeRate[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (Objects.nonNull(pvtBnk)) {
            // todo: replace with Logger
            System.out.println(LocalDateTime.now());
            Arrays.stream(pvtBnk).forEach(p->System.out.println(p.toString()));
        }

        return EditMessageText.builder().text(
                        LocalDateTime.now().toString() +'\n'+infoText)
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.infoPageIkm)
                .build();
    }

    public EditMessageText updateInfoPage(Update update){

        StringBuilder infoText =  new StringBuilder();
        //monobank
        if (Objects.isNull(monoMonoString)) {
            monoMonoString = erc.monoBank().log();
        }

        //national bank of Ukraine
        if (Objects.isNull(nbuMonoString)){
            nbuMonoString=erc.nbu().log().cache(Duration.ofMinutes(3));
        }

        List<NbuExchangeRate> nbuBnkList= new ArrayList<>();
        List<MonoBankExchangeRate> monoBankList = new ArrayList<>();

        nbuMonoString.subscribe(value->{
                try {

                    nbuBnkList.addAll(Arrays.asList((new ObjectMapper()).readValue(value,NbuExchangeRate[].class))) ;
                }catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
        },error -> System.err.println("Error occurred: " + error),    // Consumer для ошибки
                () -> {if (!nbuBnkList.isEmpty()){
                    infoText.append(LocalDateTime.now()).append('\n')
                            .append(
                                    nbuBnkList.stream()
                                            .filter(rate->"EUR,USD,AUD,CZK,CAD".contains(rate.getCurrencyCode())).
                                            map(NbuExchangeRate::toString)
                                            .collect(Collectors.joining("\n"))
                            );
                }else{infoText.append("Nbu response Empty\n");}
        }
        );

        monoMonoString.subscribe(value->{

                try {
                    monoBankList.addAll(Arrays.asList((new ObjectMapper()).readValue(value,MonoBankExchangeRate[].class))) ;
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                },error -> System.err.println("Error occurred: " + error),
                ()->{if (!monoBankList.isEmpty()){
                    infoText.append(LocalDateTime.now())
                            .append(monoBankList.stream()
                                    .filter(rate-> List.of(840,978,971,944,156)
                                            .contains(rate.getCurrencyCodeA())
                                    )
                                    .map(MonoBankExchangeRate::toString)
                                    .collect(Collectors.joining("\n"))
                            );
                }else{infoText.append("Mono bank response Empty\n");
                }
        });

        // --there was a nbu if (!nbuBnkList.isEmpty()){...

        // --there was a monobank if (!monoBnkList.isEmpty()){...
        infoText.append(infoText.isEmpty() ? "Data hasn't ready yet. Try again please." : "");
        return EditMessageText.builder().text(infoText.toString())
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.infoPageIkm)
                .build();
    }

    public EditMessageText settingPage(Update update){
         update.getCallbackQuery().getMessage().getReplyMarkup().getKeyboard();
        return EditMessageText.builder().text("*Change Settings*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(this.settingPageIkm)
                .build();
    }

    public EditMessageText pointAmountSettingPage(Update update){

        return EditMessageText.builder().text("*Choose amount of decimal numbers*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(pointAmountSettingPageIkm)
                .build();
    }

    public EditMessageText bankSettingPage(Update update){

        return EditMessageText.builder().text("*Choose bank :) to get rates from*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(bankSettingPageIkm)
                .build();
    }

    public EditMessageText currencySettingPage(Update update){
    InlineKeyboardMarkup ikmCurr = inlineKeyboardFactory.getDoubleLineMarkup(currencyCodes.getCodesAsMap());

        return EditMessageText.builder().text("*Choose currency exchange rates*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(ikmCurr)
                .build();
        //currencySettingPageIkm
    }


    public EditMessageText timeSettingPage(Update update){

        return EditMessageText.builder().text("*Choose time*").parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(timeSettingPageIkm)
                .build();
    }



    public  EditMessageText prepareEditMassage(Update update, String massageText, InlineKeyboardMarkup ikm){

        return  EditMessageText.builder().text(massageText).parseMode("markdown")
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(ikm)
                .build();
    }
    public SendMessage prepareSendMassage(Update update,String massageText, InlineKeyboardMarkup ikm ){
        return SendMessage.builder().text(massageText).parseMode("markdown")
                .chatId(update.getMessage().getChatId())
                .replyMarkup(ikm)
                .build();
    }

}
