package com.lamarro.bitcoinnodeapi.service;

import com.lamarro.bitcoinnodeapi.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
@Slf4j
public class ExecuteService {


    public String executeCommand(String command) {
        try {
            Process proc = Runtime.getRuntime().exec(command);
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            StringBuilder result = new StringBuilder();
            while((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }

            log.info(String.format("Execute command: %s, result: %s", command, result.toString()));
            proc.waitFor();

            return result.toString();
        } catch (InterruptedException | IOException e) {
            throw new AppException(e);
        }
    }
}
