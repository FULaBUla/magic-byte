package com.github.misterchangray.core.exception;

public class InvalidParameterException extends MagicByteException {

    public InvalidParameterException(){ }

    public InvalidParameterException(String str){
        super(str);
    }
}
