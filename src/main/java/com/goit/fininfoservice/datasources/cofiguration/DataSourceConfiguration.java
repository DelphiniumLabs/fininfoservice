package com.goit.fininfoservice.datasources.cofiguration;

import com.goit.fininfoservice.datasources.RestApiReactiveDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @DependsOn("webClientBuilder")
    public RestApiReactiveDataSource privatBankReactiveDataSource(
            @Value("${bank.privat.base.url}") String baseUrl,
            @Value("${bank.privat.exchange.rate.uri}")String uri,
            WebClient.Builder webClientBuilder)
    {
        return new RestApiReactiveDataSource(baseUrl,uri, webClientBuilder);
    }
}
