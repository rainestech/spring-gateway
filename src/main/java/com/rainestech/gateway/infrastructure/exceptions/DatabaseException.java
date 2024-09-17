package com.rainestech.gateway.infrastructure.exceptions;

public final class DatabaseException extends RuntimeException {
    public DatabaseException(Throwable e) {
        super(e);
    }
}
