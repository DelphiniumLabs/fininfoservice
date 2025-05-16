package com.goit.fininfoservice.telegram.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BotUser {
    BigDecimal id;
    String firstName;
    String lastName;
    String userName;

}
