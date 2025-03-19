package com.goit.fininfoservice.telegram.keyboards.buttons;

import lombok.Data;
import lombok.RequiredArgsConstructor;

///***
/// represents Button fields
/// ***
@RequiredArgsConstructor
@Data
public  class ButtonDTO {
    private String label;
    private String data;
    private boolean on;
}