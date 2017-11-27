package com.klowdscy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by manuel on 26.11.17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No such scout")
public class UnknownScoutException extends Throwable {
    public UnknownScoutException(String s) {
        super(s);
    }
}
