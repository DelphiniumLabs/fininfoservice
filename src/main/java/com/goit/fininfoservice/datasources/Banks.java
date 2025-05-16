package com.goit.fininfoservice.datasources;

public enum Banks {
    PRIVAT,
    MONO,
    NBU;

        public static Banks getBank(String bankName) {
        for (Banks bank : Banks.values()) {
            if (bank.name().equals(bankName)) {
                return bank;
            }
        }
        return null;
    }
}
