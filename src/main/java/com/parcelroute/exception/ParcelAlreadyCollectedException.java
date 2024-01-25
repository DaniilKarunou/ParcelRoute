package com.parcelroute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to collect a parcel that has already been collected.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ParcelAlreadyCollectedException extends RuntimeException {

    /**
     * Constructs a ParcelAlreadyCollectedException with the specified detail message.
     *
     * @param message the detail message
     */
    public ParcelAlreadyCollectedException(String message) {
        super(message);
    }
}





