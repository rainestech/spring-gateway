package com.rainestech.gateway.domain;

import java.util.List;
import java.util.Optional;

public interface UpStreamRoutesRepository extends BaseRepository<UpStreamRoutes> {
    Optional<List<UpStreamRoutes>> findAllActive();

    Optional<UpStreamRoutes> findOneByServiceAndPath(String service, String path);
}
