package com.example.wificontrol.handler;

import com.example.wificontrol.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class CommandHandler {

    private final CommandService commandService;

    public Mono<ServerResponse> openUrl(ServerRequest request) {
        String url = request.queryParam("url").orElse("");
        return commandService.openUrl(url)
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }

    public Mono<ServerResponse> mute(ServerRequest request) {
        return commandService.mute()
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }

    public Mono<ServerResponse> shutdown(ServerRequest request) {
        return commandService.shutdown()
                .flatMap(result -> ServerResponse.ok().bodyValue(result));
    }

}
