package com.rainestech.gateway.infrastructure.config;

import com.rainestech.gateway.domain.PrivilegeRepository;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.UpStreamRoutesRepository;
import com.rainestech.gateway.infrastructure.domain.models.PrivilegeRepositoryImpl;
import com.rainestech.gateway.infrastructure.domain.models.UiRoutesRepositoryImpl;
import com.rainestech.gateway.infrastructure.domain.models.UpStreamRoutesRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryBeanConfiguration {
    @Bean
    public UiRoutesRepository uiRoutesRepository(JdbcTemplate jdbcTemplate) {
        return new UiRoutesRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public UpStreamRoutesRepository upStreamRoutesRepository(JdbcTemplate jdbcTemplate) {
        return new UpStreamRoutesRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public PrivilegeRepository privilegeRepository(JdbcTemplate jdbcTemplate) {
        return new PrivilegeRepositoryImpl(jdbcTemplate);
    }
}
