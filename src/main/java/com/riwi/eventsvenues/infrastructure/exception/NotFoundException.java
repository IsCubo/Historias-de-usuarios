package com.riwi.eventsvenues.infrastructure.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND);
    }

}
