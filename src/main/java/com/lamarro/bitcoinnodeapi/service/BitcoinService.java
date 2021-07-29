package com.lamarro.bitcoinnodeapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lamarro.bitcoinnodeapi.data.constant.Commands;
import com.lamarro.bitcoinnodeapi.exception.AppException;
import com.lamarro.bitcoinnodeapi.exception.BitcoinNotStartedException;
import com.lamarro.bitcoinnodeapi.data.model.CreateAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BitcoinService {

    private final ExecuteService executeService;

    public String getBlochChainInfo() {
        return executeCommand(Commands.GET_BLOCKCHAIN_INFO);
    }

    public CreateAddress createAccount() {
        try {
            String address = executeCommand(Commands.CREATE_ACCOUNT);
            String addressInfoJson = executeService.executeCommand(String.format(Commands.ADDRESS_INFO_FORMAT, address));
            String privateKey = executeService.executeCommand(String.format(Commands.GET_PRIVATE_KEY_FORMAT, address));

            String publicKey = new ObjectMapper().readTree(addressInfoJson).get("pubkey").asText();
            return CreateAddress.builder()
                    .address(address)
                    .publicKey(publicKey)
                    .privateKey(privateKey)
                    .wif(privateKey)
                    .build();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }

    public BigDecimal getBalance(String address) {
        String value = executeService.executeCommand(String.format(Commands.GET_RECEIVED_BY_ACCOUNTS, address));
        log.info("Get balance value: " + value);
        return new BigDecimal(value);
    }

    public String executeCommand(String command) {
        try {
            return executeService.executeCommand(command);
        } catch (BitcoinNotStartedException e) {
            tryRefreshBitcoind();
        }
        return executeService.executeCommand(command);
    }

    public String runBitcoinDaemon() {
        return executeService.executeCommand(Commands.BITCOIND);
    }

    private void tryRefreshBitcoind() {
        log.error("Bitcoind not started, try to start");
        runBitcoinDaemon();
        waitRefresh();
    }

    private void waitRefresh() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException interruptedException) {
            throw new AppException(interruptedException);
        }
    }
}
