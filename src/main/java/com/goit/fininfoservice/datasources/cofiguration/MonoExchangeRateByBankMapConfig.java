package com.goit.fininfoservice.datasources.cofiguration;

import com.goit.fininfoservice.datasources.Banks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class MonoExchangeRateByBankMapConfig {
    @Bean(name = "monoExchangeRateByBankMap")
    public Map<Banks, Mono<String>> monoExchangeRateByBankMap(){
        return new EnumMap<>(Banks.class);
    }

}
