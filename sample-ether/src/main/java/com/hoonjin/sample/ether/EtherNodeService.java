package com.hoonjin.sample.ether;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class EtherNodeService {

    private final Web3j client;

    public EtherNodeService(Environment environment) {
        String nodeUrl = environment.getProperty("eth.node.url");
        this.client = Web3j.build(new HttpService(nodeUrl));
    }

    @PostConstruct
    void init() throws IOException, ExecutionException, InterruptedException {
        Request<?, EthBlockNumber> req = client.ethBlockNumber();

        EthBlock ethBlock = req.sendAsync().thenApply(e -> {
            System.out.println("e = " + e);
            System.out.println("e.getJsonrpc() = " + e.getJsonrpc());
            System.out.println("e.getRawResponse() = " + e.getRawResponse());
            System.out.println("e.getResult() = " + e.getResult());
            System.out.println("e.getBlockNumber() = " + e.getBlockNumber());

            try {
                return client.ethGetBlockByNumber(DefaultBlockParameter.valueOf(e.getBlockNumber()), false).send();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return null;
            }
        }).get();

        for (EthBlock.TransactionResult transaction : ethBlock.getBlock().getTransactions()) {
            Object o = transaction.get();
            System.out.println("o = " + o);
        }

        EthBlock send = client.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send();

        for (EthBlock.TransactionResult transaction : send.getBlock().getTransactions()) {
            EthBlock.TransactionObject o = (EthBlock.TransactionObject) transaction.get();
            System.out.println("fullObject = " + o.getHash());
            System.out.println("fullObject = " + o.getFrom());
            System.out.println("fullObject = " + o.getTo());
        }

//        EthBlockNumber send = req.send();
//        System.out.println("send = " + send);
//        BigInteger blockNumber = send.getBlockNumber();
//        System.out.println("blockNumber = " + blockNumber);
    }
}
