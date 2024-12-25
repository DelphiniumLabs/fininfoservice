package com.goit.fininfoservice.telegram.keyboards.exceptions;

public class EmptyButtonsMapException extends Exception{
  public   EmptyButtonsMapException(){
        super("Empty Map<> buttons. Add items or provide nonempty Map");
    }
}
