package com.pes.chaplincinemabackend.common.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String error, String reason) {
        super(HttpStatus.FAILED_DEPENDENCY, error, reason);
    }
}
