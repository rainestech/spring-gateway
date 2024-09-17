package com.rainestech.gateway.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record UpStreamRoutes(
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
        String responseType,
        List<Permissions> permissions,
        Privileges privilege
) {
    public UpStreamRoutes(
            String service,
            String host,
            String path,
            Map<String, String> requestHeaders,
            Map<String, String> responseHeaders,
            List<String> requestFilters,
            List<String> responseFilters,
            boolean status,
            String description,
            String responseType,
            List<Permissions> permissions,
            Privileges privilege
    ) {
        this(
                new DomainId().id(),
                service,
                host,
                path,
                requestHeaders,
                responseHeaders,
                requestFilters,
                responseFilters,
                status,
                description,
                responseType,
                permissions,
                privilege
        );
    }

    public UpStreamRoutes(
            String id,
            String service,
            String host,
            String path,
            String requestHeaders,
            String responseHeaders,
            String requestFilters,
            String responseFilters,
            boolean status,
            String description,
            String responseType,
            String permissions,
            Privileges privileges
    ) throws JsonProcessingException {
        this(
                id,
                service,
                host,
                path,
                new ObjectMapper().readValue(requestHeaders, new TypeReference<HashMap<String, String>>() {
                }),
                new ObjectMapper().readValue(responseHeaders, new TypeReference<HashMap<String, String>>() {
                }),
                new ObjectMapper().readValue(requestFilters, new TypeReference<ArrayList<String>>() {
                }),
                new ObjectMapper().readValue(responseFilters, new TypeReference<ArrayList<String>>() {
                }),
                status,
                description,
                responseType,
                new ObjectMapper().readValue(permissions, new TypeReference<ArrayList<Permissions>>() {
                }),
                privileges
        );
    }
}
