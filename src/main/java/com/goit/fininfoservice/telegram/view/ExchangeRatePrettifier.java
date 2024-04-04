package com.goit.fininfoservice.telegram.view;

import java.util.List;

public interface ExchangeRatePrettifier <T> {
    default String prettify1(List<T> listOfExRate, String header) {
        final StringBuilder prettyResult = new StringBuilder();
        prettyResult.append(header).append('\n');
        if (!listOfExRate.isEmpty()) {
            listOfExRate.stream().forEach(
                    rate -> prettyResult.append(exRateToString(rate)).append("\n")
            );
        } else {
            prettyResult.append("Data hasn't ready yet. Try again please.");
        }
        return prettyResult.toString();
    }
   // String prettify(List<T> exRatesList);
    String exRateToString(T rate);

}
