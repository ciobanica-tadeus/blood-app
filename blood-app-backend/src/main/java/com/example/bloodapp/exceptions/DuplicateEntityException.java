package com.example.bloodapp.exceptions;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String s) {
        super(s);
    }
}
