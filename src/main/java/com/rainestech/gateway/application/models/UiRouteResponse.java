package com.rainestech.gateway.application.models;

import com.rainestech.gateway.domain.UiRoutes;

public record UiRouteResponse(
        String id,
        String app,
        boolean status,
        String url,
        String module,
        String name,
        int orderNo,
        boolean hasChildren,
        String icon,
        String privilege
) {
    public UiRouteResponse(UiRoutes data) {
        this(
                data.id(),
                data.app(),
                data.status(),
                data.url(),
                data.module(),
                data.name(),
                data.orderNo(),
                data.hasChildren(),
                data.icon(),
                data.privilege()
        );
    }
}