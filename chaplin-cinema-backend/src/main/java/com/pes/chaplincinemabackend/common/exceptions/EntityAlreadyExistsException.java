package com.pes.chaplincinemabackend.common.exceptions;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {
    public EntityAlreadyExistsException(String error, String reason) {
        super(HttpStatus.FAILED_DEPENDENCY, error, reason);
    }
}
