package com.goit.fininfoservice.datasources.impl;

import com.goit.fininfoservice.datasources.DataSource;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class WebClientDataSourceCached implements DataSource<Mono<String>> {
    private final String uri;
    private final String baseUrl;
    private final WebClient webClient;
    // fields for  cache implementation
    private long ttl = 6; //default value
    public void setTtl (long ttl) throws IllegalArgumentException {

            if(ttl > 0) {
                this.ttl = ttl;
            } else{
                throw new IllegalArgumentException("ttl can't be <= 0");
            }

    }
    private Mono<String> monoCachedResult;
    private LocalDateTime lastUpdate;
    public LocalDateTime getLastUpdate(){
        return Objects.nonNull(this.lastUpdate)? this.lastUpdate :
                ZonedDateTime.now().toLocalDateTime() ;
    }
       public WebClientDataSourceCached(String baseUrl, String uri, WebClient.Builder webClientBuilder){
        this.uri = uri;
        this.baseUrl = baseUrl;
        this.webClient = webClientBuilder.baseUrl(this.baseUrl).build();
           // set lastUpdate as if it occurred 5 minutes ago (intial value)
        this.lastUpdate = LocalDateTime.now().minusMinutes(ttl);
    }
    @Override
    public Mono<String> fetchData() {


        if(this.lastUpdate.plusMinutes(ttl).isBefore(LocalDateTime.now())) {

            if(Objects.isNull(this.monoCachedResult))
                this.monoCachedResult = webClient.get().uri(uri)
                        .retrieve()
                    .bodyToMono(String.class)
                    .cache(Duration.ofMinutes(ttl)
                    );
            this.lastUpdate = LocalDateTime.now();
        }
        return this.monoCachedResult;
    }
}
