package com.lamarro.bitcoinnodeapi.conotrller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class ExecuteController {

    @GetMapping
    private String execute() throws IOException, InterruptedException {
        String command = "ping -c 3 www.google.com";

        Process proc = Runtime.getRuntime().exec(command);

        // Read the output

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        String line = "";
        StringBuilder doc = new StringBuilder();
        while((line = reader.readLine()) != null) {
            doc.append(line).append("\n");
            System.out.print(line + "\n");
        }

        proc.waitFor();

        return doc.toString();

//        RestTemplate restTemplate = new RestTemplate();

//        return restTemplate.getForEntity("https://chain.so/api/v2/get_info/DOGE", String.class).getBody();
    }
}
