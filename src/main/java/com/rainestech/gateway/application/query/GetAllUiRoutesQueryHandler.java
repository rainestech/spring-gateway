package com.rainestech.gateway.application.query;

import com.rainestech.gateway.application.models.UiRouteResponse;
import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;

import java.util.ArrayList;
import java.util.List;

public final class GetAllUiRoutesQueryHandler {

    private final UiRoutesRepository repository;

    public GetAllUiRoutesQueryHandler(UiRoutesRepository repository) {
        this.repository = repository;
    }

    public Iterable<UiRouteResponse> handle() {
        List<UiRoutes> routes = repository.getAll().orElse(new ArrayList<>());

        return routes.stream().map(UiRouteResponse::new).toList();
    }
}
