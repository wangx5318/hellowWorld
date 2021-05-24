package com.example.demo.common;

import lombok.Data;

@Data
public class BuniessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public BuniessException() {
        super();
    }

    public BuniessException(String message) {
        super(message);
        this.message = message;
    }

}