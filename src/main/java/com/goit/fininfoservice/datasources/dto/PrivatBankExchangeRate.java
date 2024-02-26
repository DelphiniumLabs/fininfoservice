package com.goit.fininfoservice.datasources.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class PrivatBankExchangeRate {
    @JsonProperty("ccy")
    private String currency;

    @JsonProperty("base_ccy")
    private String baseCurrency;

    @JsonProperty("buy")
    private double buyRate;

    @JsonProperty("sale")
    private double saleRate;

    @Override
    public String toString() {
        return "Privat Bank Ex. rate  {" +
                "'" + currency + '\'' +
                "/'" + baseCurrency + '\'' +
                ", Buy =" + buyRate +
                ", Sale =" + saleRate +
                '}';
    }


}
