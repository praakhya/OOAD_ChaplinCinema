package com.pes.chaplincinemabackend.common.exceptions;

public enum ExceptionMessage {
    USER_ALREADY_EXISTS("This error occurs when you are trying to create a new user with same username as an existing user. It looks like a user with username %s already exists", "Username %s already exists"),
    EMAIL_ALREADY_EXISTS("This error occurs when you are trying to create a new user with same email as an existing user. It looks like a user with email %s already exists", "Email %s already exists"),
    INVALID_PASSWORD("This error occurs when the password supplied with the request doesn't match the password of the user. %s","Invalid password for user %s"),
    TOKEN_SIGNING_ERROR("This error is thrown when we could not sign JWT token. This error requires administrator to fix some configuration on their end.", "Token could not be signed"),
    CONTENT_WRITING_FAILED_ERROR("This error occors when our attempt to write the file on to the storage failes for some reason. We were trying to write file to %s","File writing failed at path %s"),

    ;

    ExceptionMessage(String reason, String error) {
        this.reason = reason;
        this.error = error;
    }

    public String getReason() {
        return reason;
    }

    public String getError() {
        return error;
    }

    private String reason;
    private String error;
}