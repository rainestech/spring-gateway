package com.rainestech.gateway.domain.exception;

import lombok.Getter;

@Getter
public class UiRouteDomainException extends RuntimeException {
    private final ExceptionCode code;

    public UiRouteDomainException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

}
