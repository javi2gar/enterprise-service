package com.enterprise.service.adapter.out.persistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorCode, String message) {
        super(errorCode + ": " + message);
    }

}
