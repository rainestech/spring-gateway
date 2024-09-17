package com.rainestech.gateway.application.validation;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T command, CommandValidatorType type);

    default String getErrors(Map<String, String> errors) {
        return getErrors(errors, false);
    }

    default String getErrors(Map<String, String> errors, boolean all) {
        if (errors.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (!all) {
            errors.entrySet().stream().findFirst().ifPresent(e -> sb.append(e.getKey()).append(":").append(e.getValue()).append("\n"));
            return sb.toString();
        }

        for (Map.Entry<String, String> error : errors.entrySet()) {
            sb.append(error.getKey()).append(":").append(error.getValue()).append("\n");
        }
        return sb.toString();
    }
}
