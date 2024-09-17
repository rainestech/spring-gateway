package com.rainestech.gateway.application.query;

import com.rainestech.gateway.application.models.NavItems;
import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public final class GetClientNavigationQueryHandler {

    private final UiRoutesRepository repository;

    public GetClientNavigationQueryHandler(UiRoutesRepository repository) {
        this.repository = repository;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public Iterable<NavItems> handle() {
        List<String> modules = repository.findAllActiveModules().orElse(new ArrayList<>());

        List<NavItems> navItems = new ArrayList<>();
        List<UiRoutes> privileges = repository.findAllActive().orElse(new ArrayList<>());
        for (String module : modules) {

            UiRoutes modulePrivilege = getModuleUrl(privileges, module);

            if (modulePrivilege != null) {
                NavItems item = setModuleNavItem(modulePrivilege, privileges, module);

                navItems.add(item);
            }
        }

        return navItems;
    }

    private List<NavItems> setChildren(List<UiRoutes> privileges, String module) {
        List<NavItems> children = new ArrayList<>();
        for (UiRoutes privilege : getChildrenPrivilege(privileges, module)) {
            NavItems child = new NavItems(
                    privilege.name(),
                    privilege.url(),
                    privilege.icon(),
                    new ArrayList<>(),
                    privilege.app(),
                    privilege.module(),
                    privilege.styleClass()
            );

            children.add(child);
        }

        return children;
    }

    private NavItems setModuleNavItem(UiRoutes privilege, List<UiRoutes> privileges, String module) {
        List<NavItems> children = new ArrayList<>();
        if (privilege.hasChildren()) {
            children.addAll(setChildren(privileges, module));
        }

        return new NavItems(
                privilege.name(),
                privilege.url(),
                privilege.icon(),
                children,
                privilege.app(),
                privilege.module(),
                privilege.styleClass()
        );
    }

    private UiRoutes getModuleUrl(List<UiRoutes> privileges, String module) {
        Boolean ch = true;
        return getModulePrivileges(privileges, module)
                .stream().filter(child -> ch.equals(child.hasChildren())).findFirst()
                .orElse(getSingleModulePrivilege(privileges, module));
    }

    private UiRoutes getSingleModulePrivilege(List<UiRoutes> privileges, String module) {
        return privileges.stream().filter(item -> module.equals(item.module())).findFirst().orElse(null);
    }

    private List<UiRoutes> getChildrenPrivilege(List<UiRoutes> privileges, String module) {
        Boolean ch = false;
        return getModulePrivileges(privileges, module).stream()
                .filter(child -> ch.equals(child.hasChildren())).toList();
    }

    private List<UiRoutes> getModulePrivileges(List<UiRoutes> privileges, String module) {
        return privileges.stream().filter(item -> module.equals(item.module())).toList()
                .stream().filter(distinctByKey(UiRoutes::id)).toList();
    }
}
