package com.rainestech.gateway.domain;

import java.util.List;
import java.util.Optional;

public interface PrivilegeRepository extends BaseRepository<Privileges> {
    Optional<List<UiRoutes>> findAll();
}
