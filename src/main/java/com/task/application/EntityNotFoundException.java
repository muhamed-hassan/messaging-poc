package com.task.application;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("entity not found");
    }

}
