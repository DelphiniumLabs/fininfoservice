package com.goit.fininfoservice.datasources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateService {
    private final Map<Banks, DataSource<Mono<String>>> exchangeRateDataSourcesMap; //datasources
    private final Map<Banks, Mono<String>> monoExchangeRateByBankMap; //Mono results from datasources
    @Autowired
    public ExchangeRateService(@Qualifier("exchangeRateDataSourcesMap")
                               Map<Banks, DataSource<Mono<String>>> exchangeRateDataSourcesMap
            , @Qualifier("monoExchangeRateByBankMap") Map<Banks, Mono<String>> monoExchangeRateByBankMap) {
        this.exchangeRateDataSourcesMap = exchangeRateDataSourcesMap;
        this.monoExchangeRateByBankMap = monoExchangeRateByBankMap;
    }

    // Generic method to get exchange rate DTO List from bank by bank name.
    // It returns List<T> where T is class of exchange rate DTO.
    // For example, if bank is PrivatBank, it returns List<PrivatBankExchangeRateDTO>
    // and if bank is MonoBank, it returns List<MonoBankExchangeRateDTO>
    public <T> List<T> getExRateFromBank(Banks bankName, Class<T[]> targetType) {

        //monoExchangeRateByBankMap.computeIfAbsent(bankName, this::getMonoExchangeRateByBank); - very interesting method to key existence
        if (monoExchangeRateByBankMap.get(bankName) == null) {
            monoExchangeRateByBankMap.put(bankName,getMonoExchangeRateByBank(bankName));
        }
        List<T> resultExchangeRateList = new ArrayList<>();
        monoExchangeRateByBankMap.get(bankName).subscribe(
                value -> {
                    try {
                        resultExchangeRateList.addAll(Arrays.asList((new ObjectMapper()).readValue(value, targetType)));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> System.err.printf("Error occurred: %s", error)

        );
        return resultExchangeRateList;
    }

    private Mono<String> getMonoExchangeRateByBank(Banks bankName) throws IllegalArgumentException {
        if (!exchangeRateDataSourcesMap.containsKey(bankName)) {
            throw new IllegalArgumentException("Bank name %s is not valid".formatted(bankName));
        }
        return exchangeRateDataSourcesMap.get(bankName).fetchData().log().cache(Duration.ofMinutes(6));
        //added .cache(Duration.ofMinutes(6)); to test influence to result
    }
}
