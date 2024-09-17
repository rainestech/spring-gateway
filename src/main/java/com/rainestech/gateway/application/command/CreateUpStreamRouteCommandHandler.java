package com.rainestech.gateway.application.command;

import com.rainestech.gateway.application.models.UpStreamRouteResponse;
import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UpStreamRouteValidator;
import com.rainestech.gateway.domain.*;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;

public final class CreateUpStreamRouteCommandHandler {
    private final UpStreamRoutesRepository repository;
    private final UpStreamRouteValidator<CreateUpStreamRouteCommand> validator;
    private final PrivilegeRepository privilegeRepository;

    public CreateUpStreamRouteCommandHandler(UpStreamRoutesRepository repository,
                                             UpStreamRouteValidator<CreateUpStreamRouteCommand> validator,
                                             PrivilegeRepository privilegeRepository) {
        this.repository = repository;
        this.validator = validator;
        this.privilegeRepository = privilegeRepository;
    }

    public UpStreamRouteResponse handle(CreateUpStreamRouteCommand command) {
        var errors = validator.validate(command, CommandValidatorType.CREATE);

        if (!errors.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.VALIDATION_ERROR, validator.getErrors(errors));
        }

        if (repository.findOneByServiceAndPath(command.service(), command.path()).isPresent()) {
            throw new IllegalArgumentException("UpStreamRoute already exists");
        }

        Privileges privilege = privilegeRepository.findOneById(command.privilegeId())
                .orElseThrow(() -> new UpStreamDomainException(ExceptionCode.NOT_FOUND, "Privilege not found"));

        UpStreamRoutes upStreamRoutes = new UpStreamRoutes(
                (new DomainId()).id(),
                command.service(),
                command.host(),
                command.path(),
                command.requestHeaders(),
                command.responseHeaders(),
                command.requestFilters(),
                command.responseFilters(),
                command.status(),
                command.description(),
                command.responseType(),
                command.permissions(),
                privilege
        );

        repository.save(upStreamRoutes);

        return new UpStreamRouteResponse(upStreamRoutes);
    }
}
