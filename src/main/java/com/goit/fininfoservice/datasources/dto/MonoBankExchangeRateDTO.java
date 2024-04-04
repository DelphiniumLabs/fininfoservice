package com.goit.fininfoservice.datasources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MonoBankExchangeRateDTO {
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
        return
                currencyCodeA +
                "/" + currencyCodeB +
                ", Buy = " + rateBuy +
                ", Sell = " + rateSell;
    }

}
