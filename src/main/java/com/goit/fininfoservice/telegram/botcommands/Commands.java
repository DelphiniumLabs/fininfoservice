package com.goit.fininfoservice.telegram.botcommands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Commands {
    START("/start"),
    INFO("/info"),
    SETTINGS("/settings"),
    UPDATE("/updateInfoPage"),
    BACK("/back");

    private final String code; // command code that used in callbackData property


}
