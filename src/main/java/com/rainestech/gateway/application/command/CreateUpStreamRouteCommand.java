package com.rainestech.gateway.application.command;

import com.rainestech.gateway.domain.Permissions;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record CreateUpStreamRouteCommand(
        @NotNull
        String service,
        String host,
        @NotNull
        String path,
        Map<String, String> requestHeaders,
        Map<String, String> responseHeaders,
        List<String> requestFilters,
        List<String> responseFilters,
        boolean status,
        String description,
        String responseType,
        List<Permissions> permissions,
        String privilegeId
) {
}
