package com.rainestech.gateway.application.command;

import jakarta.validation.constraints.NotNull;

public record DeleteUiRouteCommand(
        @NotNull
        String id
) {
}
