package ru.teymurosman.vkechobot.exception;

public class InvalidVkSecretException extends RuntimeException {
    public InvalidVkSecretException(String message) {
        super(message);
    }
}
