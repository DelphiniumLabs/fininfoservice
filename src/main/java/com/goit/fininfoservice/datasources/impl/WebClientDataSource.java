package com.goit.fininfoservice.datasources.impl;

import com.goit.fininfoservice.datasources.DataSource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * This is another implementation of DataSource interface
 * that describe reactive REST API datasource
 * You can create instantiates of this class passing different args to constructor
 */
public class WebClientDataSource implements DataSource<Mono<String>> {
    private final String uri;
    private final String baseUrl;
    private final WebClient webClient;

    public WebClientDataSource(String baseUrl, String uri, WebClient.Builder webClientBuilder) {
        this.uri = uri;
        this.baseUrl = baseUrl;
        this.webClient = webClientBuilder.
                baseUrl(this.baseUrl).build();
    }
    @Override
    public Mono<String> fetchData() {
        return webClient.get().uri(uri).retrieve().bodyToMono(String.class);
    }
}
