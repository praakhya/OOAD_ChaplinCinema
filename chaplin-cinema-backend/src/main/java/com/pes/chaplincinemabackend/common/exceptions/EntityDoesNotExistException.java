package com.pes.chaplincinemabackend.common.exceptions;

import org.springframework.http.HttpStatus;

public class EntityDoesNotExistException extends BaseException {
    public EntityDoesNotExistException(String error, String reason) {
        super(HttpStatus.FAILED_DEPENDENCY, error, reason);
    }
}
