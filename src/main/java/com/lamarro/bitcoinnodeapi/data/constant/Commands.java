package com.lamarro.bitcoinnodeapi.data.constant;

public interface Commands {
//  bitcoind
    String BITCOIND = "bitcoind -daemon -reindex -prune";

//  bitcoin-cli
    String BITCOIN_CLI = "bitcoin-cli ";
    String GET_BLOCKCHAIN_INFO = BITCOIN_CLI + "getblockchaininfo";
}
