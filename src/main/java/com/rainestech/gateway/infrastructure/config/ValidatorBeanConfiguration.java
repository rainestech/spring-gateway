package com.rainestech.gateway.infrastructure.config;

import com.rainestech.gateway.application.command.CreateUiRouteCommand;
import com.rainestech.gateway.application.command.CreateUpStreamRouteCommand;
import com.rainestech.gateway.application.command.UpdateUiRouteCommand;
import com.rainestech.gateway.application.command.UpdateUpStreamRouteCommand;
import com.rainestech.gateway.application.validation.UiRouteValidator;
import com.rainestech.gateway.application.validation.UpStreamRouteValidator;
import com.rainestech.gateway.infrastructure.domain.validators.UiRouteValidatorImpl;
import com.rainestech.gateway.infrastructure.domain.validators.UpStreamRouteValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorBeanConfiguration {
    @Bean
    public UiRouteValidator<CreateUiRouteCommand> uiRouteValidator() {
        return new UiRouteValidatorImpl<>();
    }

    @Bean
    public UiRouteValidator<UpdateUiRouteCommand> uiRouteUpdateValidator() {
        return new UiRouteValidatorImpl<>();
    }

    @Bean
    public UpStreamRouteValidator<CreateUpStreamRouteCommand> upStreamRouteValidator() {
        return new UpStreamRouteValidatorImpl<>();
    }

    @Bean
    public UpStreamRouteValidator<UpdateUpStreamRouteCommand> upStreamRouteUpdateValidator() {
        return new UpStreamRouteValidatorImpl<>();
    }
}
