package com.rainestech.gateway.application.command;

import jakarta.validation.constraints.NotNull;

public record CreateUiRouteCommand(
        String app,
        boolean status,
        @NotNull
        String url,
        @NotNull
        String module,
        @NotNull
        String name,
        @NotNull
        int orderNo,
        boolean hasChildren,
        String icon,
        @NotNull
        String privilege,
        String styleClass
) {
}
