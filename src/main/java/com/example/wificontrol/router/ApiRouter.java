package com.example.wificontrol.router;

import com.example.wificontrol.handler.CommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ApiRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(CommandHandler commandHandler){
        return nest(path("/cmd"),
                route()
                        .GET("/open", commandHandler::openUrl)
                        .GET("/fullscreen",commandHandler::fullscreen)
                        .build()
                );


    }
}
