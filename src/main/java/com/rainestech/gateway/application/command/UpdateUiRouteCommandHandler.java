package com.rainestech.gateway.application.command;

import com.rainestech.gateway.application.models.UiRouteResponse;
import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UiRouteValidator;
import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UpdateUiRouteCommandHandler {
    private final UiRoutesRepository repository;
    private final UiRouteValidator<UpdateUiRouteCommand> validator;

    public UiRouteResponse handle(UpdateUiRouteCommand command) {
        var errors = validator.validate(command, CommandValidatorType.CREATE);

        if (!errors.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.VALIDATION_ERROR, validator.getErrors(errors));
        }

        if (repository.findOneById(command.url()).isPresent()) {
            throw new UpStreamDomainException(ExceptionCode.NOT_FOUND, "UiRoute not found");
        }

        UiRoutes route = new UiRoutes(
                command.id(),
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
