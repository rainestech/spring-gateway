package com.rainestech.gateway.application.models;

import java.util.List;

public record NavItems(
        String name,
        String url,
        String icon,
        List<NavItems> children,
        String app,
        String module,
        String styleClass
) {
}

