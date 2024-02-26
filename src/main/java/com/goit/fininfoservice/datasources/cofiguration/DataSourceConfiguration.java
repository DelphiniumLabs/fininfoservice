package com.goit.fininfoservice.datasources.cofiguration;

import com.goit.fininfoservice.datasources.impl.WebClientDataSource;
import com.goit.fininfoservice.datasources.impl.WebClientDataSourceCached;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @DependsOn("webClientBuilder")
    public WebClientDataSource privatBankReactiveDataSource(
            @Value("${bank.privat.base.url}") String baseUrl,
            @Value("${bank.privat.exchange.rate.uri}")String uri,
            WebClient.Builder webClientBuilder)
    {
        return new WebClientDataSource(baseUrl,uri, webClientBuilder);
    }

    @Bean
    @DependsOn("webClientBuilder")
    public WebClientDataSource nbuBankReactiveDataSource(
            @Value("${bank.nbu.base.url}") String baseUrl,
            @Value("${bank.nbu.exchange.rate.uri}")String uri,
            WebClient.Builder webClientBuilder)
    {

        return new WebClientDataSource(baseUrl,uri, webClientBuilder);
    }
    @Value("${bank.mono.request.delay}")
    private long ttl;
    @Bean
    @DependsOn("webClientBuilder")
    public WebClientDataSourceCached monoBankReactiveDataSource(
            @Value("${bank.mono.base.url}") String baseUrl,
            @Value("${bank.mono.exchange.rate.uri}")String uri,
            WebClient.Builder webClientBuilder)  {
        WebClientDataSourceCached wcdsc=new WebClientDataSourceCached(baseUrl, uri, webClientBuilder);
        wcdsc.setTtl(this.ttl);
           return wcdsc;
    }

}
