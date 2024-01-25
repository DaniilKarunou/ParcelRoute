package com.parcelroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid pickup code is provided.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidPickupCodeException extends RuntimeException {

    /**
     * Constructs an InvalidPickupCodeException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidPickupCodeException(String message) {
        super(message);
    }
}

