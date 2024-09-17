package com.rainestech.gateway.infrastructure.domain.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rainestech.gateway.domain.Privileges;
import com.rainestech.gateway.domain.UpStreamRoutes;
import com.rainestech.gateway.domain.UpStreamRoutesRepository;
import com.rainestech.gateway.infrastructure.exceptions.DatabaseException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class UpStreamRoutesRepositoryImpl implements UpStreamRoutesRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String sqlWithRelations;

    public UpStreamRoutesRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        sqlWithRelations = "select r.id, r.service, r.host, r.path, r.request_headers, r.response_headers, r.request_filters, r.response_filters, r.status, r.description, r.response_type, r.permissions," +
                "p.id, p.name, p.service from up_stream_routes as r" +
                " inner join privileges p on r.privilege_id = p.id ";
    }

    @Override
    public Optional<List<UpStreamRoutes>> findAllActive() {
        String sql = sqlWithRelations + " where r.status = true";
        return Optional.of(
                jdbcTemplate.query(sql, (rs, rowNum) -> {
                    var privilege = new Privileges(
                            rs.getString("p.id"),
                            rs.getString("p.service"),
                            rs.getString("p.name")
                    );

                    try {
                        return new UpStreamRoutes(
                                rs.getString("id"),
                                rs.getString("service"),
                                rs.getString("host"),
                                rs.getString("path"),
                                rs.getString("request_headers"),
                                rs.getString("response_headers"),
                                rs.getString("request_filters"),
                                rs.getString("response_filters"),
                                rs.getBoolean("status"),
                                rs.getString("description"),
                                rs.getString("response_type"),
                                rs.getString("permissions"),
                                privilege
                        );
                    } catch (JsonProcessingException e) {
                        throw new DatabaseException(e);
                    }
                })
        );
    }

    @Override
    public Optional<UpStreamRoutes> findOneByServiceAndPath(String service, String path) {
        String sql = sqlWithRelations + "where service = ? and path = ?";
        List<UpStreamRoutes> upStreamRoutes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String id = rs.getString("id");
            String host = rs.getString("host");
            String requestHeaders = rs.getString("request_headers");
            String responseHeaders = rs.getString("response_headers");
            String requestFilters = rs.getString("request_filters");
            String responseFilters = rs.getString("response_filters");
            boolean status = rs.getBoolean("status");
            String description = rs.getString("description");
            String responseType = rs.getString("response_type");
            String permissions = rs.getString("permissions");

            var privilege = new Privileges(
                    rs.getString("p.id"),
                    rs.getString("p.service"),
                    rs.getString("p.name")
            );
            try {
                return new UpStreamRoutes(id, service, host, path, requestHeaders, responseHeaders, requestFilters, responseFilters, status, description, responseType, permissions, privilege);
            } catch (JsonProcessingException e) {
                throw new DatabaseException(e);
            }
        }, service, path);
        return Optional.of(upStreamRoutes.getFirst());
    }

    @Override
    public Optional<List<UpStreamRoutes>> getAll() {
        String sql = sqlWithRelations;
        List<UpStreamRoutes> upStreamRoutes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String id = rs.getString("id");
            String service = rs.getString("service");
            String host = rs.getString("host");
            String path = rs.getString("path");
            String requestHeaders = rs.getString("request_headers");
            String responseHeaders = rs.getString("response_headers");
            String requestFilters = rs.getString("request_filters");
            String responseFilters = rs.getString("response_filters");
            boolean status = rs.getBoolean("status");
            String description = rs.getString("description");
            String responseType = rs.getString("response_type");
            String permissions = rs.getString("permissions");

            var privilege = new Privileges(
                    rs.getString("p.id"),
                    rs.getString("p.service"),
                    rs.getString("p.name")
            );
            try {
                return new UpStreamRoutes(id, service, host, path, requestHeaders, responseHeaders, requestFilters, responseFilters, status, description, responseType, permissions, privilege);
            } catch (JsonProcessingException e) {
                throw new DatabaseException(e);
            }
        });
        return Optional.of(upStreamRoutes);
    }

    @Override
    public Optional<UpStreamRoutes> findOneById(String id) {
        String sql = sqlWithRelations + "where id = ?";
        List<UpStreamRoutes> upStreamRoutes = jdbcTemplate.query(sql, (rs, rowNum) -> {
            String service = rs.getString("service");
            String host = rs.getString("host");
            String path = rs.getString("path");
            String requestHeaders = rs.getString("request_headers");
            String responseHeaders = rs.getString("response_headers");
            String requestFilters = rs.getString("request_filters");
            String responseFilters = rs.getString("response_filters");
            boolean status = rs.getBoolean("status");
            String description = rs.getString("description");
            String responseType = rs.getString("response_type");
            String permissions = rs.getString("permissions");

            var privilege = new Privileges(
                    rs.getString("p.id"),
                    rs.getString("p.service"),
                    rs.getString("p.name")
            );
            try {
                return new UpStreamRoutes(id, service, host, path, requestHeaders, responseHeaders, requestFilters, responseFilters, status, description, responseType, permissions, privilege);
            } catch (JsonProcessingException e) {
                throw new DatabaseException(e);
            }
        }, id);
        return Optional.of(upStreamRoutes.getFirst());
    }

    @Override
    public void save(UpStreamRoutes upStreamRoutes) {
        String sql = "insert into up_stream_routes(id, service, host, path, request_headers, response_headers, request_filters, response_filters, status, description, response_type) values(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.update(sql,
                    upStreamRoutes.id(),
                    upStreamRoutes.service(),
                    upStreamRoutes.host(),
                    upStreamRoutes.path(),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.requestHeaders()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.responseHeaders()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.requestFilters()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.responseFilters()),
                    upStreamRoutes.status(),
                    upStreamRoutes.description(),
                    upStreamRoutes.responseType()
            );
        } catch (JsonProcessingException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(UpStreamRoutes upStreamRoutes) {
        String sql = "delete from up_stream_routes where id = ?";
        jdbcTemplate.update(sql, upStreamRoutes.id());
    }

    @Override
    public void deleteAll() {
        String sql = "delete from up_stream_routes";
        jdbcTemplate.update(sql);
    }

    @Override
    public void update(UpStreamRoutes upStreamRoutes) {
        String sql = "update up_stream_routes set service = ?, host = ?, path = ?, request_headers = ?, response_headers = ?, request_filters = ?, response_filters = ?, status = ?, description = ? where id = ?";
        try {
            jdbcTemplate.update(sql,
                    upStreamRoutes.service(),
                    upStreamRoutes.host(),
                    upStreamRoutes.path(),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.requestHeaders()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.responseHeaders()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.requestFilters()),
                    new ObjectMapper().writeValueAsString(upStreamRoutes.responseFilters()),
                    upStreamRoutes.status(),
                    upStreamRoutes.description(),
                    upStreamRoutes.id()
            );
        } catch (JsonProcessingException e) {
            throw new DatabaseException(e);
        }
    }
}
