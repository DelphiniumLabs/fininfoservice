package com.goit.fininfoservice.telegram.keyboards.exceptions;

public class EmptyButtonsMapException extends RuntimeException{
  public   EmptyButtonsMapException(String errorMassage){
        super(errorMassage);
    }

    public EmptyButtonsMapException(Throwable cause) {
        super(cause);
    }
}
