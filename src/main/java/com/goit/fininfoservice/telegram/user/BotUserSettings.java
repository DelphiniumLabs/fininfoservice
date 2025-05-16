package com.goit.fininfoservice.telegram.user;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BotUserSettings {
    BigDecimal id;
    BotUser botUser;
    List<String> dataSources;


}
