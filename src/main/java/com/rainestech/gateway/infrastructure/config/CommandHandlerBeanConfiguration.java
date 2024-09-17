package com.rainestech.gateway.infrastructure.config;

import com.rainestech.gateway.application.command.*;
import com.rainestech.gateway.application.validation.UiRouteValidator;
import com.rainestech.gateway.application.validation.UpStreamRouteValidator;
import com.rainestech.gateway.domain.PrivilegeRepository;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.UpStreamRoutesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandHandlerBeanConfiguration {
    @Bean
    public CreateUiRouteCommandHandler createUiRouteCommandHandler(UiRoutesRepository repository, UiRouteValidator<CreateUiRouteCommand> validator) {
        return new CreateUiRouteCommandHandler(repository, validator);
    }

    @Bean
    public UpdateUiRouteCommandHandler updateUiRouteCommandHandler(UiRoutesRepository repository, UiRouteValidator<UpdateUiRouteCommand> validator) {
        return new UpdateUiRouteCommandHandler(repository, validator);
    }

    @Bean
    public UpdateUpStreamRouteCommandHandler updateUpStreamRouteCommandHandler(UpStreamRoutesRepository repository, UpStreamRouteValidator<UpdateUpStreamRouteCommand> validator, PrivilegeRepository privilegeRepository) {
        return new UpdateUpStreamRouteCommandHandler(repository, validator, privilegeRepository);
    }

    @Bean
    public CreateUpStreamRouteCommandHandler createUpStreamRouteCommandHandler(UpStreamRoutesRepository repository, UpStreamRouteValidator<CreateUpStreamRouteCommand> validator, PrivilegeRepository privilegeRepository) {
        return new CreateUpStreamRouteCommandHandler(repository, validator, privilegeRepository);
    }
}
