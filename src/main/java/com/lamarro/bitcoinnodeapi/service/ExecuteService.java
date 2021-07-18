package com.lamarro.bitcoinnodeapi.service;

import com.lamarro.bitcoinnodeapi.exception.AppException;
import com.lamarro.bitcoinnodeapi.exception.BitcoinNotStartedException;
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

            String line = reader.readLine();
            boolean isFirstIteration = true;
            StringBuilder result = new StringBuilder();
            log.info("Result: " + line + " ,command: " + command);
            do {
                log.info("Result: " + line + " ,size: " + (line != null ? line.length() : 0));
                if ((line == null || line.isBlank()) && isFirstIteration) {
                    log.error(String.format("Incorrect result %s, try to start bitcoind", line));
                   throw new BitcoinNotStartedException();
                }
                isFirstIteration = false;
                result.append(line).append("\n");
            } while((line = reader.readLine()) != null);

            log.info(String.format("Execute command: %s, result: %s", command, result.toString()));
            proc.waitFor();

            return result.toString();
        } catch (InterruptedException | IOException e) {
            throw new AppException(e);
        }
    }
}
