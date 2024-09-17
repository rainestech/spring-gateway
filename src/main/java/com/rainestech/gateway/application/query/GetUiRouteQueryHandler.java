package com.rainestech.gateway.application.query;

import com.rainestech.gateway.application.models.UiRouteResponse;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UiRouteDomainException;

public final class GetUiRouteQueryHandler {
    private final UiRoutesRepository repository;

    public GetUiRouteQueryHandler(UiRoutesRepository repository) {
        this.repository = repository;
    }

    public UiRouteResponse handle(GetUiRouteQuery query) {
        return repository.findOneById(query.id()).map(UiRouteResponse::new)
                .orElseThrow(() ->
                        new UiRouteDomainException(ExceptionCode.NOT_FOUND, "UiRoute not found")
                );
    }
}
