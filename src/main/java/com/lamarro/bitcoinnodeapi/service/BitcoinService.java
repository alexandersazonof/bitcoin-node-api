package com.lamarro.bitcoinnodeapi.service;

import com.lamarro.bitcoinnodeapi.data.constant.Commands;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BitcoinService {

    private final ExecuteService executeService;

    public String getBlochChainInfo() {
        return executeService.executeCommand(Commands.GET_BLOCKCHAIN_INFO);
    }

}
