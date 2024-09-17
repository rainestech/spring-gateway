package com.rainestech.gateway.domain;

public record Privileges(
        String id,
        String service,
        String name
) {
    public Privileges(String service, String name) {
        this(new DomainId().id(), service, name);
    }
}
