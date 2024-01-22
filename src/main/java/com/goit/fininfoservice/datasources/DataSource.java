package com.goit.fininfoservice.datasources;

import reactor.core.publisher.Mono;

public interface  DataSource <T> {
     Mono<T> fetchData();

}
