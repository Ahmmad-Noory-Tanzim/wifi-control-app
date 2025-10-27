package com.example.wificontrol.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class CommandService {


    public Mono<String> openUrl(String url){

        try {
            new ProcessBuilder("cmd", "/c", "taskkill /IM msedge.exe /F").start();

            try {
                Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException ignored) {
            }

            new ProcessBuilder("cmd", "/c", "start", url).start();
            return Mono.just("Reopened: " + url);

        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }

    }

    public Mono<String> fullscreen() {

        try {
            new ProcessBuilder("powershell", "-command",
                    "Add-Type -AssemblyName System.Windows.Forms; " +
                            "[System.Windows.Forms.SendKeys]::SendWait('{F}')").start();
            return Mono.just("Fullscreen triggered");
        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }
    }

}
