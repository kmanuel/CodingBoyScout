package com.klowdscy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if a scout is not found for the given search criteria
 * Created by manuel on 26.11.17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such scout")
public class UnknownScoutException extends RuntimeException {
    public UnknownScoutException(String scoutName) {
        super("could not find scout for name='" + scoutName + "'");
    }
}
