package com.example.demo.exception;

import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

public class HandleException extends Exception {

    Object[] arr;

    public HandleException(String message) {
        super(message);
    }

    public HandleException(String message, Object... arr) {
        super(message);
        this.arr = Arrays.stream(arr).toArray();
    }
}
