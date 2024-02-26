package com.goit.fininfoservice.utils;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyCode {
    private  final Map<String, Integer> codes = new HashMap<>();
    public CurrencyCode(){
        codes.put("EUR",978);codes.put("GBP",826);codes.put("CAD",124);codes.put("CNY",156);codes.put("CHF",756);
        codes.put("AED",784);codes.put("SGD",702);codes.put("USD",840);codes.put("THB",764);codes.put("TRY",949);
        codes.put("CZK",203);codes.put("SEK",752);codes.put("JPY",392);codes.put("XAU",959);
    }
    public Integer getCode(String stringCode){
        return Optional.ofNullable(codes.get(stringCode)).orElse(999);
    }

    public String getCode(Integer digitalCode){
        return codes.entrySet().stream().filter(e->e.getValue()
                        .equals(digitalCode)).map(Map.Entry::getKey).findFirst().orElse("XXX");
    }

    public Set<String> getStringCodes(){
        return codes.keySet();
    }
    public Set<Integer> getDigitalCodes(){

        return new HashSet<>(codes.values());
    }
    public Map<String,String> getCodesAsMap(){
        Map<String,String> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry: codes.entrySet()) {
            result.put(entry.getKey(),entry.getValue().toString());
        }
        return result;
    }
}
