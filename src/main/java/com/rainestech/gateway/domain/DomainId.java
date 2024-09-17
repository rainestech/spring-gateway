package com.rainestech.gateway.domain;

import com.github.f4b6a3.uuid.UuidCreator;

public record DomainId(String id) {
    public DomainId() {
        this(UuidCreator.getTimeOrderedEpoch().toString());
    }
}
