package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by ahmadjawid on 7/16/17.
 */

@Service
public class RawTransactionManager {


    @Autowired
    private Web3j web3j;

    @Value("${wallet-file}")
    private String walletFile;

    @Value("${wallet-password}")
    private String walletPassword;

    @Value("${contract-address}")
    private String contractAddress;


    public String createRawTransaction() throws IOException, CipherException, ExecutionException, InterruptedException {

       Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletFile);

        Utf8String _globalId = new Utf8String("1");
        Utf8String _socialRecordString =  new Utf8String("social record updated7_" + _globalId);

        //Function function = new Function("addSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        Function function = new Function("updateSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());


        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, GAS_PRICE, GAS_LIMIT,
                "0x81De991ef8637fE8dEcB0384B386feAD10c21C88",
                FunctionEncoder.encode(function));

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        return hexValue;
    }
}
