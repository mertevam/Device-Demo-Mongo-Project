package com.mert.devicedemo.exception;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class NotFoundModelException extends RuntimeException {
    private String model;
    private String id;
    @Getter(AccessLevel.NONE)
    private static final String messageTemplate = "%s model [%s] not found!";

    public NotFoundModelException(String model, String id) {
        super(String.format(messageTemplate, model, id));
        this.model = model;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format(messageTemplate, model, id);
    }
}
