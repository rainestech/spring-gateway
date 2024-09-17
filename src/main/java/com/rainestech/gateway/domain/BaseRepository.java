package com.rainestech.gateway.domain;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    Optional<List<T>> getAll();

    Optional<T> findOneById(String id);

    void save(T t);

    void delete(T t);

    void deleteAll();

    void update(T t);
}
