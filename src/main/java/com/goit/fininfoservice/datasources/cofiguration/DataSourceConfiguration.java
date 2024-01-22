package com.goit.fininfoservice.datasources.cofiguration;

import com.goit.fininfoservice.datasources.impl.PrivatBankDataSourceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DataSourceConfiguration {
    @Bean
    @DependsOn("webClientBuilder")
    public PrivatBankDataSourceImpl privatBankDataSource(WebClient.Builder webClientBuilder){

        return new PrivatBankDataSourceImpl(webClientBuilder);
    }
}
