package ru.ahomyakov.neuro.errors;

public class IllegalDimensionException extends Exception{
    public IllegalDimensionException() {
    }

    public IllegalDimensionException(String message) {
        super(message);
    }

    public IllegalDimensionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalDimensionException(Throwable cause) {
        super(cause);
    }
}