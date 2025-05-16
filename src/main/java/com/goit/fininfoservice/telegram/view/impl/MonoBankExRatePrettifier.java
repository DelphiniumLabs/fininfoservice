package com.goit.fininfoservice.telegram.view.impl;

import com.goit.fininfoservice.datasources.dto.MonoBankExchangeRateDTO;
import com.goit.fininfoservice.telegram.view.ExchangeRatePrettifier;
import com.goit.fininfoservice.utils.CurrencyCode;
import org.springframework.stereotype.Component;

@Component
public class MonoBankExRatePrettifier implements ExchangeRatePrettifier <MonoBankExchangeRateDTO> {

    @Override
    public String exRateToString(MonoBankExchangeRateDTO rate) {
        CurrencyCode cc = new CurrencyCode();
        StringBuilder sb = new StringBuilder();
        sb.append(cc.getCode(rate.getCurrencyCodeA()))
                .append("/").append(cc.getCode(rate.getCurrencyCodeB()))
                .append(" Buy ")
                .append(rate.getRateBuy())
                .append(" Sell ")
                .append(rate.getRateSell());
        return sb.toString();
    }
}