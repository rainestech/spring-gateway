package com.rainestech.gateway.application.models;

import com.rainestech.gateway.domain.UpStreamRoutes;

import java.util.List;
import java.util.Map;

public record UpStreamRouteResponse(
        String id,
        String service,
        String host,
        String path,
        Map<String, String> requestHeaders,
        Map<String, String> responseHeaders,
        List<String> requestFilters,
        List<String> responseFilters,
        boolean status,
        String description,
        String responseType
) {
    public UpStreamRouteResponse(UpStreamRoutes data) {
        this(
                data.id(),
                data.service(),
                data.host(),
                data.path(),
                data.requestHeaders(),
                data.responseHeaders(),
                data.requestFilters(),
                data.responseFilters(),
                data.status(),
                data.description(),
                data.responseType()
        );
    }
}
