package com.rainestech.gateway.infrastructure.delivery.api.v1;

import com.rainestech.gateway.application.command.CreateUiRouteCommand;
import com.rainestech.gateway.application.command.CreateUiRouteCommandHandler;
import com.rainestech.gateway.application.models.UiRouteResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ui-routes")
@ResponseBody
public class CreateUiRouteAction {
    private final CreateUiRouteCommandHandler handler;

    public CreateUiRouteAction(CreateUiRouteCommandHandler handler) {
        this.handler = handler;
    }

    @PostMapping
    public ResponseEntity<UiRouteResponse> execute(@Valid @RequestBody CreateUiRouteCommand command) {
        return ResponseEntity.ok(handler.handle(command));
    }
}
