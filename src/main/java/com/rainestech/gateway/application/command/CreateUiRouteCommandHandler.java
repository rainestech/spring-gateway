package com.rainestech.gateway.application.command;

import com.rainestech.gateway.application.models.UiRouteResponse;
import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UiRouteValidator;
import com.rainestech.gateway.domain.DomainId;
import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UiRouteDomainException;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class CreateUiRouteCommandHandler {
    private final UiRoutesRepository repository;
    private final UiRouteValidator<CreateUiRouteCommand> validator;

    public UiRouteResponse handle(CreateUiRouteCommand command) {
        var errors = validator.validate(command, CommandValidatorType.CREATE);

        if (!errors.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.VALIDATION_ERROR, validator.getErrors(errors));
        }

        if (repository.findOneByUrl(command.url()).isPresent()) {
            throw new UiRouteDomainException(ExceptionCode.BAD_REQUEST, "UiRoute already exists");
        }

        UiRoutes route = new UiRoutes(
                (new DomainId()).id(),
                command.app(),
                command.status(),
                command.url(),
                command.module(),
                command.name(),
                command.orderNo(),
                command.hasChildren(),
                command.icon(),
                command.privilege(),
                command.styleClass()
        );


        repository.save(route);

        return new UiRouteResponse(route);
    }
}
