package com.rainestech.gateway.infrastructure.config;

import com.rainestech.gateway.application.query.GetAllUiRoutesQueryHandler;
import com.rainestech.gateway.application.query.GetClientNavigationQueryHandler;
import com.rainestech.gateway.application.query.GetUiRouteQueryHandler;
import com.rainestech.gateway.domain.UiRoutesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryHandlerBeanConfiguration {

    @Bean
    public GetUiRouteQueryHandler getUiRouteQueryHandler(UiRoutesRepository repository) {
        return new GetUiRouteQueryHandler(repository);
    }

    @Bean
    public GetAllUiRoutesQueryHandler getAllUiRoutesQueryHandler(UiRoutesRepository repository) {
        return new GetAllUiRoutesQueryHandler(repository);
    }

    @Bean
    public GetClientNavigationQueryHandler getClientNavigationQueryHandler(UiRoutesRepository repository) {
        return new GetClientNavigationQueryHandler(repository);
    }
}
