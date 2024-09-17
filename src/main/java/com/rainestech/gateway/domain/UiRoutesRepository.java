package com.rainestech.gateway.domain;

import java.util.List;
import java.util.Optional;

public interface UiRoutesRepository extends BaseRepository<UiRoutes> {
    Optional<List<UiRoutes>> findAllActive();

    Optional<List<String>> findAllActiveModules();

    Optional<UiRoutes> findOneByUrl(String url);
}
