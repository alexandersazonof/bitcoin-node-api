package com.lamarro.bitcoinnodeapi.conotrller;

import com.lamarro.bitcoinnodeapi.data.model.CreateAddress;
import com.lamarro.bitcoinnodeapi.service.BitcoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class ExecuteController {

    private final BitcoinService bitcoinService;


    @GetMapping
    private String execute() throws IOException, InterruptedException {
        System.out.println('a');
//        String command = "ping -c 3 www.google.com";
//
//        Process proc = Runtime.getRuntime().exec(command);
//
//        // Read the output
//
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//        String line = "";
//        StringBuilder doc = new StringBuilder();
//        while((line = reader.readLine()) != null) {
//            doc.append(line).append("\n");
//            System.out.print(line + "\n");
//        }
//
//        proc.waitFor();
//
//        return doc.toString();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("https://chain.so/api/v2/address/BTC/1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", String.class).getBody();

//        return restTemplate.getForEntity("https://chain.so/api/v2/get_info/DOGE", String.class).getBody();
    }

    @GetMapping("/getblockchaininfo")
    public String getBlochChainInfo() {
        return bitcoinService.getBlochChainInfo();
    }

    @GetMapping("/craeteaddress")
    public CreateAddress createAddress() {
        return bitcoinService.createAccount();
    }

    @GetMapping("/getbalance/{address}")
    public BigDecimal getBalance(@PathVariable String address) {
        return bitcoinService.getBalance(address);
    }
}
