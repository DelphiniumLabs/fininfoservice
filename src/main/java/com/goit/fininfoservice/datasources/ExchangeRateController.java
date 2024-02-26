package com.goit.fininfoservice.datasources;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;


/**
 *  Controller that provides exchange rates from banks
 *  use appropriate method
 **/
@Component
public class ExchangeRateController {
    private final DataSource<Mono<String>> privatBankReactiveDataSource;
    private final DataSource<Mono<String>> nbuBankReactiveDataSource;
    private final DataSource<Mono<String>> monoBankReactiveDataSource;
    @Autowired
    public ExchangeRateController(@NotNull DataSource<Mono<String>> privatBankReactiveDataSource,
                                  @NotNull DataSource<Mono<String>> nbuBankReactiveDataSource,
                                  @NotNull DataSource<Mono<String>> monoBankReactiveDataSource) {
        this.privatBankReactiveDataSource = privatBankReactiveDataSource;
        this.nbuBankReactiveDataSource = nbuBankReactiveDataSource;
        this.monoBankReactiveDataSource = monoBankReactiveDataSource;
        System.out.println("ExchangeRateController--------created");
    }
    public  Mono<String> nbu(){
        return nbuBankReactiveDataSource.fetchData();
    }
    public  Mono<String> privatBank(){
        return privatBankReactiveDataSource.fetchData();
    }
    public Mono<String> monoBank() {

        return monoBankReactiveDataSource.fetchData();
    }

}


