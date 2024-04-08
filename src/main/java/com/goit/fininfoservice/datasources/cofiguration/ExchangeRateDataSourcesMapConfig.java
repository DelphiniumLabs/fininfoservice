package com.goit.fininfoservice.datasources.cofiguration;

import com.goit.fininfoservice.datasources.Banks;
import com.goit.fininfoservice.datasources.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ExchangeRateDataSourcesMapConfig {
    private final DataSource<Mono<String>> privatBankReactiveDataSource;
    private final DataSource<Mono<String>> nbuBankReactiveDataSource;
    private final DataSource<Mono<String>> monoBankReactiveDataSource;
    @Autowired
        public ExchangeRateDataSourcesMapConfig(DataSource<Mono<String>> privatBankReactiveDataSource,
                                                DataSource<Mono<String>> nbuBankReactiveDataSource,
                                                DataSource<Mono<String>> monoBankReactiveDataSource) {
        this.privatBankReactiveDataSource = privatBankReactiveDataSource;
        this.nbuBankReactiveDataSource = nbuBankReactiveDataSource;
        this.monoBankReactiveDataSource = monoBankReactiveDataSource;
    }
    @Bean
    public Map<Banks, DataSource<Mono<String>>> exchangeRateDataSourcesMap() {
        Map<Banks, DataSource<Mono<String>>> dataSourceMap = new EnumMap<>(Banks.class);

        dataSourceMap.put(Banks.PRIVAT, privatBankReactiveDataSource);
        dataSourceMap.put(Banks.NBU, nbuBankReactiveDataSource);
        dataSourceMap.put(Banks.MONO,monoBankReactiveDataSource);
        return dataSourceMap;
    }




}
