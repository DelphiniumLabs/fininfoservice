package com.goit.fininfoservice.datasources;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;


/**
 *  Controller that provides exchange rates from banks
 *  use appropriate method
 *  we will use this controller to get rates from ExchangeRateService
 **/
@Component
public class ExchangeRateController {

    private final DataSource<Mono<String>> privatBankReactiveDataSource;
    private final DataSource<Mono<String>> nbuBankReactiveDataSource;
    private final DataSource<Mono<String>> monoBankReactiveDataSource;

    // todo: inject ExchangeRateService and use it to get rates from banks instead of DataSources
    //  use appropriate method
    //  we will use this controller to get rates from ExchangeRateService

    @Autowired
    public ExchangeRateController(@NotNull DataSource<Mono<String>> privatBankReactiveDataSource,
                                  @NotNull DataSource<Mono<String>> nbuBankReactiveDataSource,
                                  @NotNull DataSource<Mono<String>> monoBankReactiveDataSource) {
        this.privatBankReactiveDataSource = privatBankReactiveDataSource;
        this.nbuBankReactiveDataSource = nbuBankReactiveDataSource;
        this.monoBankReactiveDataSource = monoBankReactiveDataSource;
    }
    public Mono<String> getMono(String bankName){
        return privatBankReactiveDataSource.fetchData();
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


