package com.rainestech.gateway.application.command;

import com.rainestech.gateway.application.models.UpStreamRouteResponse;
import com.rainestech.gateway.application.validation.CommandValidatorType;
import com.rainestech.gateway.application.validation.UpStreamRouteValidator;
import com.rainestech.gateway.domain.PrivilegeRepository;
import com.rainestech.gateway.domain.Privileges;
import com.rainestech.gateway.domain.UpStreamRoutes;
import com.rainestech.gateway.domain.UpStreamRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;

public final class UpdateUpStreamRouteCommandHandler {
    private final UpStreamRoutesRepository repository;
    private final UpStreamRouteValidator<UpdateUpStreamRouteCommand> validator;
    private final PrivilegeRepository privilegeRepository;

    public UpdateUpStreamRouteCommandHandler(UpStreamRoutesRepository repository, UpStreamRouteValidator<UpdateUpStreamRouteCommand> validator, PrivilegeRepository privilegeRepository) {
        this.repository = repository;
        this.validator = validator;
        this.privilegeRepository = privilegeRepository;
    }

    public UpStreamRouteResponse handle(UpdateUpStreamRouteCommand command) {
        var errors = validator.validate(command, CommandValidatorType.CREATE);

        if (!errors.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.VALIDATION_ERROR, validator.getErrors(errors));
        }

        if (repository.findOneById(command.path()).isPresent()) {
            throw new UpStreamDomainException(ExceptionCode.NOT_FOUND, "UpStreamRoute not found");
        }

        Privileges privilege = privilegeRepository.findOneById(command.privilegeId())
                .orElseThrow(() -> new UpStreamDomainException(ExceptionCode.NOT_FOUND, "Privilege not found"));

        UpStreamRoutes upStreamRoutes = new UpStreamRoutes(
                command.id(),
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
