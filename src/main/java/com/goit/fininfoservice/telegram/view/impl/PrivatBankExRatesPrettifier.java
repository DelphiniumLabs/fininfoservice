package com.goit.fininfoservice.telegram.view.impl;

import com.goit.fininfoservice.datasources.dto.PrivatBankExchangeRateDTO;
import com.goit.fininfoservice.telegram.view.ExchangeRatePrettifier;
import org.springframework.stereotype.Component;

@Component
//("privatBankExRatesPrettifier")
public class PrivatBankExRatesPrettifier implements ExchangeRatePrettifier<PrivatBankExchangeRateDTO> {


    @Override
    public String exRateToString(PrivatBankExchangeRateDTO rate) {
        StringBuilder sb = new StringBuilder();
        sb.append(rate.getCurrency())
                .append("/").append(rate.getBaseCurrency())
                .append(" Buy ")
                .append(rate.getBuyRate())
                .append(" Sell ")
                .append(rate.getSaleRate());
        return sb.toString();
    }


}
