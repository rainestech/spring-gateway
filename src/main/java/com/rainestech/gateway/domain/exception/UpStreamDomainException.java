package com.rainestech.gateway.domain.exception;

import lombok.Getter;

@Getter
public class UpStreamDomainException extends RuntimeException {
    private final ExceptionCode code;

    public UpStreamDomainException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

}
