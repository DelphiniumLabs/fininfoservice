package com.goit.fininfoservice.datasources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

@Data
public class MonoBankExchangeRate {
    @JsonProperty("currencyCodeA")
    private int currencyCodeA;

    @JsonProperty("currencyCodeB")
    private int currencyCodeB;

    @JsonProperty("date")
    private long date;

    @JsonProperty("rateBuy")
    private double rateBuy;

    @JsonProperty("rateSell")
    private double rateSell;

    @JsonProperty("rateCross")
    private double rateCross;

    @Override
    public String toString() {
        return "Mono Bank Ex Rate {" +
                "CodeA=" + currencyCodeA +
                ", CodeB=" + currencyCodeB +
                ", Buy = " + rateBuy +
                ", Sell = " + rateSell +
                '}';
    }

    public static MonoBankExchangeRate fromJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, MonoBankExchangeRate.class);
    }
}
