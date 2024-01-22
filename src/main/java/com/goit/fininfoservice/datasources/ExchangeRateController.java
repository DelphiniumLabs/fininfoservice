package com.goit.fininfoservice.datasources;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Setter
@Getter
public class ExchangeRateController {

    private DataSource<String> dataSource;

    Mono<String> fetchData(DataSource<String> dataSource){
             return dataSource.fetchData();
    }

}
