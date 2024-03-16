package com.pes.chaplincinemabackend.common.exceptions;

public enum ExceptionMessage {
    USER_ALREADY_EXISTS("This error occurs when you are trying to create a new user with same username as an existing user. It looks like a user with username %s already exists", "Username %s already exists"),
    ENTITY_ALREADY_EXISTS("This error occurs when you are trying to create a new entity which is same as an existing entity. It looks like a entity with id %s already exists", "Entity %s already exists"),
    ENTITY_DOES_NOT_EXIST("This error occurs when you are trying to retrieve an entity which does not exist. It looks like a entity with id %s does not exist", "Entity %s does not exist"),
    EMAIL_ALREADY_EXISTS("This error occurs when you are trying to create a new user with same email as an existing user. It looks like a user with email %s already exists", "Email %s already exists"),
    INVALID_PASSWORD("This error occurs when the password supplied with the request doesn't match the password of the user. %s","Invalid password for user %s"),
    TOKEN_SIGNING_ERROR("This error is thrown when we could not sign JWT token. This error requires administrator to fix some configuration on their end.", "Token could not be signed"),
    ID_DOESNOT_EXIT("This error is thrown when the request body doesn't contain a ID. For this request a Id is expected in request body.", "Request body doesn't contain Id"),
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