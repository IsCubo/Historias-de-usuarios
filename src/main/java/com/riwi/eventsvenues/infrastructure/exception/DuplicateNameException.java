package com.riwi.eventsvenues.infrastructure.exception;

public class DuplicateNameException extends BusinessException{

    public DuplicateNameException(String message) {
        super(message, ErrorCode.DUPLICATE_NAME);
    }

}
