package ru.ahomyakov.neuro.errors;

public class NetIsNotInitalizedExceprion extends Exception{
    public NetIsNotInitalizedExceprion() {
    }

    public NetIsNotInitalizedExceprion(String message) {
        super(message);
    }

    public NetIsNotInitalizedExceprion(String message, Throwable cause) {
        super(message, cause);
    }

    public NetIsNotInitalizedExceprion(Throwable cause) {
        super(cause);
    }
}