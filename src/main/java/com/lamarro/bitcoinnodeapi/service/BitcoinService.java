package com.lamarro.bitcoinnodeapi.service;

import com.lamarro.bitcoinnodeapi.data.constant.Commands;
import com.lamarro.bitcoinnodeapi.exception.BitcoinNotStartedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BitcoinService {

    private final ExecuteService executeService;

    public String getBlochChainInfo() {
        try {
            return executeService.executeCommand(Commands.GET_BLOCKCHAIN_INFO);
        } catch (BitcoinNotStartedException e) {
            log.error("Bitcoind not started, try to start");
            runBitcoinDaemon();
        }
        return executeService.executeCommand(Commands.GET_BLOCKCHAIN_INFO);
    }

    public String runBitcoinDaemon() {
        return executeService.executeCommand(Commands.BITCOIND);
    }
}
