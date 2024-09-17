package com.rainestech.gateway.domain;

public record UiRoutes(
        String id,
        String app,
        boolean status,
        String url,
        String module,
        String name,
        int orderNo,
        boolean hasChildren,
        String icon,
        String privilege,
        String styleClass
) {
    public UiRoutes(String app,
                    boolean status,
                    String url,
                    String module,
                    String name,
                    int orderNo,
                    boolean hasChildren,
                    String icon,
                    String privilege,
                    String styleClass
    ) {
        this(new DomainId().id(), app, status, url, module, name, orderNo, hasChildren, icon, privilege, styleClass);
    }
}