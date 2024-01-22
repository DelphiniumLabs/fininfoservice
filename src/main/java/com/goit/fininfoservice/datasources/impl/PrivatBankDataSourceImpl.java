package com.goit.fininfoservice.datasources.impl;

import com.goit.fininfoservice.datasources.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class PrivatBankDataSourceImpl implements DataSource <String> {
    String apiExchangeRate ="/p24api/pubinfo?json&exchange&coursid=5";

    private final WebClient webClient;

    public PrivatBankDataSourceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.
                      baseUrl("https://api.privatbank.ua").build();
    }

    @Override
    public Mono<String> fetchData() {
        return webClient.get().uri(apiExchangeRate).retrieve().bodyToMono(String.class);
    }

}
