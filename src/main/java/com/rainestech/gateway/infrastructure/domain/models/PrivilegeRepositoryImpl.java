package com.rainestech.gateway.infrastructure.domain.models;

import com.rainestech.gateway.domain.PrivilegeRepository;
import com.rainestech.gateway.domain.Privileges;
import com.rainestech.gateway.domain.UiRoutes;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class PrivilegeRepositoryImpl implements PrivilegeRepository {
    private final JdbcTemplate jdbcTemplate;

    public PrivilegeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<UiRoutes>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Privileges>> getAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Privileges> findOneById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Privileges privileges) {
    }

    @Override
    public void delete(Privileges privileges) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(Privileges privileges) {

    }
}
