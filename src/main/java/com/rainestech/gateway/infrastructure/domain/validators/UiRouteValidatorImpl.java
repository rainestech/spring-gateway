package com.rainestech.gateway.infrastructure.domain.validators;

import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UiRouteValidator;

import java.util.Map;

public class UiRouteValidatorImpl<T> implements UiRouteValidator<T> {
    @Override
    public Map<String, String> validate(T command, CommandValidatorType type) {
        return Map.of();
    }
}
