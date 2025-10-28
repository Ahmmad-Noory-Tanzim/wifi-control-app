package com.example.wificontrol.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class CommandService {


    public Mono<String> openUrl(String url){

        try {
            new ProcessBuilder(
                    "node",
                    "C:\\Users\\tanzi\\OneDrive\\Documents\\phone app\\Automation-Server\\youtubeAutomation.js",
                    url
            ).start();

            return Mono.just("YouTube automation started: " + url);
        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }

        /*
        try {
            new ProcessBuilder(
                    "powershell",
                    "-command",
                    "$wshell = New-Object -ComObject wscript.shell; " +
                            "$wshell.AppActivate('msedge'); " +   // focus Edge window
                            "Start-Sleep -Milliseconds 500; " +
                            "$wshell.SendKeys('%{F4}')"
            ).start();

            try {
                Thread.sleep(5000); // wait 1 second
            } catch (InterruptedException ignored) {
            }

            new ProcessBuilder(
                    "powershell","-command",
                    "Start-Process 'msedge.exe' '" + url + "'; " +
                            "Start-Sleep -Seconds 2; " +
                            "$wshell = New-Object -ComObject wscript.shell; " +
                            "$wshell.AppActivate('msedge'); " +
                            "Start-Sleep -Milliseconds 500; " +
                            "$wshell.SendKeys('{F11}'); " +
                            "Start-Sleep -Milliseconds 5000; " +
                            "$wshell.SendKeys('f')"
            ).start();

            return Mono.just("Reopened: " + url);

        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }
*/
    }

    public Mono<String> mute() {

        try {
            new ProcessBuilder("powershell", "-command",
                    "Add-Type -AssemblyName System.Windows.Forms; " +
                            "$wshell.AppActivate('msedge'); " +  // focus Edge window
                            "[System.Windows.Forms.SendKeys]::SendWait('{M}')").start();
            return Mono.just("Fullscreen triggered");
        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }
    }

    public Mono<String> shutdown() {

        try {
            new ProcessBuilder("cmd", "/c", "taskkill /IM msedge.exe /F").start();
            return Mono.just("Fullscreen triggered");
        } catch (IOException e) {
            return Mono.just("Error: " + e.getMessage());
        }
    }

}
