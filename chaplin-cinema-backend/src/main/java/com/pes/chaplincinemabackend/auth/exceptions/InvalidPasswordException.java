package com.pes.chaplincinemabackend.auth.exceptions;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(String error, String reason) {
        super(HttpStatus.FAILED_DEPENDENCY, error, reason);
    }

    public InvalidPasswordException(String error, String reason, Throwable cause) {
        super(HttpStatus.FAILED_DEPENDENCY, error, reason, cause);
    }
}