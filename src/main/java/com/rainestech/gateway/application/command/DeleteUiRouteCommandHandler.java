package com.rainestech.gateway.application.command;

import com.rainestech.gateway.domain.UiRoutes;
import com.rainestech.gateway.domain.UiRoutesRepository;
import com.rainestech.gateway.domain.exception.ExceptionCode;
import com.rainestech.gateway.domain.exception.UpStreamDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class DeleteUiRouteCommandHandler {
    private final UiRoutesRepository repository;

    public void handle(DeleteUpStreamRouteCommand command) {
        Optional<UiRoutes> route = repository.findOneById(command.id());
        if (route.isEmpty()) {
            throw new UpStreamDomainException(ExceptionCode.NOT_FOUND, "UiRoute not found");
        }

        repository.delete(route.get());
    }
}
