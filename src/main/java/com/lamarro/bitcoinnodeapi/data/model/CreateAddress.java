package com.lamarro.bitcoinnodeapi.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAddress {
    private String privateKey;
    private String publicKey;
    private String wif;
    private String address;
}
