package com.klowdscy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such scout")
public class UnknownScoutException extends RuntimeException {
    public UnknownScoutException(String scoutName) {
        super("could not find scout for name='" + scoutName + "'");
    }
}
