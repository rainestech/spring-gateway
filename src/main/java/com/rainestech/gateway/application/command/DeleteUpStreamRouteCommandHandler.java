package com.rainestech.gateway.application.command;

import com.rainestech.gateway.domain.UpStreamRoutes;
import com.rainestech.gateway.domain.UpStreamRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class DeleteUpStreamRouteCommandHandler {
    private final UpStreamRoutesRepository repository;

    public void handle(DeleteUpStreamRouteCommand command) {
        Optional<UpStreamRoutes> route = repository.findOneById(command.id());
        if (route.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.NOT_FOUND, "UpStreamRoute not found");
        }

        repository.delete(route.get());
    }
}
