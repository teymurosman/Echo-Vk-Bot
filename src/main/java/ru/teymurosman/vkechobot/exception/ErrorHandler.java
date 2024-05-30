package ru.teymurosman.vkechobot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler
 */
@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({InvalidVkSecretException.class, UnsupportedCallbackTypeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidVkSecretException(final RuntimeException e) {
        log.info(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SenderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleSenderException(final SenderException e) {
        log.info(e.getMessage());

        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerError(final Throwable e) {
        log.warn(e.getMessage());

        return new ResponseEntity<>("Unexpected server failure", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
