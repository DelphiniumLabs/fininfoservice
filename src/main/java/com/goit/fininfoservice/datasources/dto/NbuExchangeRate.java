package com.goit.fininfoservice.datasources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NbuExchangeRate {
    @JsonProperty("r030")
    private int r030;

    @JsonProperty("txt")
    private String text;

    @JsonProperty("rate")
    private double rate;

    @JsonProperty("cc")
    private String currencyCode;

    @JsonProperty("exchangedate")
    private String exchangeDate;

    public String toString() {
        return "NBU Ex. rate  {" +
                "rate=" + rate +
                ",'" + currencyCode + "/UAH '" +
                '}';
    }
}
