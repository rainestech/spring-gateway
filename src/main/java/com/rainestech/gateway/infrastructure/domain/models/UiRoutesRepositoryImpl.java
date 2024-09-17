package com.rainestech.gateway.infrastructure.domain.models;

import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UiRoutesRepositoryImpl implements UiRoutesRepository {
    private final JdbcTemplate jdbcTemplate;

    public UiRoutesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<List<UiRoutes>> findAllActive() {
        String sql = "select * from ui_routes where status = ?";

        List<UiRoutes> uiRoutes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UiRoutes.class), true);
        return Optional.of(uiRoutes);
    }

    @Override
    public Optional<List<String>> findAllActiveModules() {
        String sql = "select distinct module from ui_routes where status = ?";
        List<String> modules = jdbcTemplate.queryForList(sql, String.class, true);
        return Optional.of(modules);
    }

    @Override
    public Optional<UiRoutes> findOneByUrl(String url) {
        String sql = "select * from gateway.ui_routes where url = ?";
        List<UiRoutes> uiRoutes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UiRoutes.class), url);
        if (uiRoutes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(uiRoutes.getFirst());
    }

    @Override
    public Optional<List<UiRoutes>> getAll() {
        String sql = "select * from ui_routes";

        List<UiRoutes> uiRoutes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UiRoutes.class));
        return Optional.of(uiRoutes);
    }

    @Override
    public Optional<UiRoutes> findOneById(String id) {
        String sql = "select * from ui_routes where id = ?";
        List<UiRoutes> uiRoutes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UiRoutes.class), id);
        return Optional.of(uiRoutes.getFirst());
    }

    @Override
    public void save(UiRoutes uiRoutes) {
        String sql = "insert into gateway.ui_routes(id, app, status, url, module, name, order_no, has_children, icon, privilege, style_class) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                uiRoutes.id(),
                uiRoutes.app(),
                uiRoutes.status(),
                uiRoutes.url(),
                uiRoutes.module(),
                uiRoutes.name(),
                uiRoutes.orderNo(),
                uiRoutes.hasChildren(),
                uiRoutes.icon(),
                uiRoutes.privilege(),
                uiRoutes.styleClass()
        );
    }

    @Override
    public void delete(UiRoutes uiRoutes) {
        String sql = "delete from ui_routes where id = ?";
        jdbcTemplate.update(sql, uiRoutes.id());
    }

    @Override
    public void deleteAll() {
        String sql = "delete from ui_routes";
        jdbcTemplate.update(sql);
    }

    @Override
    public void update(UiRoutes uiRoutes) {
        String sql = "update ui_routes set app = ?, status = ?, url = ?, module = ?, name = ?, order_no = ?, has_children = ?, icon = ?, privilege = ?, style_class = ? where id = ?";
        jdbcTemplate.update(sql,
                uiRoutes.app(),
                uiRoutes.status(),
                uiRoutes.url(),
                uiRoutes.module(),
                uiRoutes.name(),
                uiRoutes.orderNo(),
                uiRoutes.hasChildren(),
                uiRoutes.icon(),
                uiRoutes.privilege(),
                uiRoutes.styleClass(),
                uiRoutes.id()
        );
    }
}
