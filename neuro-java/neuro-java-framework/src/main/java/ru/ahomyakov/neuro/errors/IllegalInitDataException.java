package ru.ahomyakov.neuro.errors;

public class IllegalInitDataException extends Exception{
    public IllegalInitDataException() {
    }

    public IllegalInitDataException(String message) {
        super(message);
    }

    public IllegalInitDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInitDataException(Throwable cause) {
        super(cause);
    }
}
