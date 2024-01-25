package com.goit.fininfoservice.datasources.privatbank.dto;

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
}
