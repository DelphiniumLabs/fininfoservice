package com.goit.fininfoservice.telegram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.fininfoservice.datasources.Banks;
import com.goit.fininfoservice.datasources.ExchangeRateController;
import com.goit.fininfoservice.datasources.ExchangeRateService;
import com.goit.fininfoservice.datasources.dto.MonoBankExchangeRateDTO;
import com.goit.fininfoservice.datasources.dto.NbuExchangeRateDTO;
import com.goit.fininfoservice.datasources.dto.PrivatBankExchangeRateDTO;
import com.goit.fininfoservice.telegram.keyboards.factory.InlineKeyboardFactory;
import com.goit.fininfoservice.telegram.view.impl.MonoBankExRatePrettifier;
import com.goit.fininfoservice.telegram.view.impl.PrivatBankExRatesPrettifier;
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

    // new version injection
    @Autowired
    private ExchangeRateService exchangeRateService;
    // prettifiers
    // It is used to prettify data from bank and make it more readable.
    @Autowired
    private MonoBankExRatePrettifier monoBankExRatePrettifier;
    @Autowired
    private PrivatBankExRatesPrettifier privatBankExRatesPrettifier;

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
        String userOfChat=update.getMessage().getFrom().getUserName();

        return SendMessage.builder().text("\n" + userOfChat+"\n"+Constants.GREETING+"Active Threads count="+Thread.currentThread().getId())
                .chatId(update.getMessage().getChatId())
                .replyMarkup(this.mainPageIkm)
                .build();
    }


    public EditMessageText infoPage(Update update) {
        StringBuilder infoText = new StringBuilder();
        infoText.append(
                monoBankExRatePrettifier.prettify1(exchangeRateService.
                        getExRateFromBank(Banks.MONO, MonoBankExchangeRateDTO[].class).stream()
                        .filter(rate -> List.of(840, 978, 971, 944, 156).contains(rate.getCurrencyCodeA()))
                        .toList()
                        ,"*Mono Bank exchange rates:*"
                )
        );

        return prepareEditMassage(update, infoText.toString(), this.infoPageIkm);
    }
    public SendMessage stopPage(Update update){
        String chatUser = update.getMessage().getFrom().getUserName();


        return SendMessage.builder().text("\n" + chatUser+"\n"+Constants.STOP_TEXT)
                .chatId(update.getMessage().getChatId())
                .replyMarkup(this.mainPageIkm)
                .build();
    }

    public EditMessageText updateInfoPage1(Update update){

        StringBuilder infoText = new StringBuilder();
        infoText.append(
                privatBankExRatesPrettifier.prettify1(exchangeRateService.
                                getExRateFromBank(Banks.PRIVAT, PrivatBankExchangeRateDTO[].class)
                        ,"*Privat Bank exchange rates:*"
                )
        );
        return prepareEditMassage(update, infoText.toString(), this.infoPageIkm);
    }


    public EditMessageText updateInfoPage(Update update){

        StringBuilder infoText =  new StringBuilder();

        List<NbuExchangeRateDTO> nbuBnkList= new ArrayList<>();
        List<MonoBankExchangeRateDTO> monoBankList = new ArrayList<>();
        //monobank
        if (Objects.isNull(monoMonoString)) {
            monoMonoString = erc.monoBank().log();
        }
        //national bank of Ukraine
        if (Objects.isNull(nbuMonoString)){
            nbuMonoString=erc.nbu().log().cache(Duration.ofMinutes(3));
        }

        nbuMonoString.subscribe(
                value->{
                    try {
                        nbuBnkList.addAll(Arrays.asList((new ObjectMapper()).readValue(value, NbuExchangeRateDTO[].class))) ;
                    }catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    }
                    ,error -> System.err.println("Error occurred: " + error)
                ,() -> {
                    if (!nbuBnkList.isEmpty()){
                        infoText.append(LocalDateTime.now()).append('\n')
                            .append(
                                    nbuBnkList.stream()
                                            .filter(rate->"EUR,USD,AUD,CZK,CAD".contains(rate.getCurrencyCode())).
                                            map(NbuExchangeRateDTO::toString)
                                            .collect(Collectors.joining("\n"))
                            );
                }else{
                        infoText.append("Nbu response Empty\n");
                    }
                });

        monoMonoString.subscribe(
                value->{
                    try {
                        monoBankList.addAll(Arrays.asList((new ObjectMapper()).readValue(value, MonoBankExchangeRateDTO[].class))) ;
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    }
                    ,error -> System.err.println("Error occurred: " + error)
                ,()->{
                    if (!monoBankList.isEmpty()){
                        infoText.append("\n").append(LocalDateTime.now()).append("\n")
                            .append(monoBankList.stream()
                                    .filter(rate-> List.of(840,978,971,944,156)
                                            .contains(rate.getCurrencyCodeA())
                                    )
                                    .map(MonoBankExchangeRateDTO::toString)
                                    .collect(Collectors.joining("\n"))
                            );
                }else{
                        infoText.append("Mono bank response Empty\n");
                }
                });

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
