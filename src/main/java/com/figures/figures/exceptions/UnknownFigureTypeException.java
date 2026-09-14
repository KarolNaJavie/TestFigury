package com.figures.figures.exceptions;

public class UnknownFigureTypeException extends RuntimeException {
    public UnknownFigureTypeException(String message) {
        super(message);
    }
}
