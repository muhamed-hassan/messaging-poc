package com.task.application;

public class NoResultException extends RuntimeException {

    public NoResultException() {
        super("no data found");
    }

}
