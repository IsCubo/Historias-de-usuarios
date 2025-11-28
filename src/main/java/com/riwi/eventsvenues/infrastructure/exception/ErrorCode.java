package com.riwi.eventsvenues.infrastructure.exception;

import javax.print.attribute.standard.Severity;

public enum ErrorCode {

    NOT_FOUND(404, "Resource not found", Severity.ERROR),
    INVALID_REQUEST(400, "Invalid request", Severity.ERROR),
    INTERNAL_SERVER_ERROR(500, "Internal server error", Severity.ERROR),
    UNAUTHORIZED(401, "Unauthorized access", Severity.ERROR),
    DUPLICATE_NAME(409, "Duplicate name", Severity.ERROR);

    private final int code;
    private final String message;
    private final Severity severity;

    ErrorCode(int code, String message, Severity severity) {
        this.code = code;
        this.message = message;
        this.severity = severity;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Severity getSeverity() {
        return severity;
    }
}
