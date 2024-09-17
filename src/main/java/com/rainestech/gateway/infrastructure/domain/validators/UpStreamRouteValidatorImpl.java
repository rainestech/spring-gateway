package com.rainestech.gateway.infrastructure.domain.validators;

import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UpStreamRouteValidator;

import java.util.Map;

public class UpStreamRouteValidatorImpl<T> implements UpStreamRouteValidator<T> {
    @Override
    public Map<String, String> validate(T command, CommandValidatorType type) {
        return Map.of();
    }
}
