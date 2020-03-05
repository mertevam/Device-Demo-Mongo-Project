package com.mert.devicedemo.exception;

import lombok.AccessLevel;
import lombok.Getter;

public class AlreadyCreatedModelException extends RuntimeException {
    private String model;
    private String id;
    @Getter(AccessLevel.NONE)
    private static final String messageTemplate = "%s model [%s] is already created!";

    public AlreadyCreatedModelException(String model, String id) {
        super(String.format(messageTemplate, model, id));
        this.model = model;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(messageTemplate, model, id);
    }
}