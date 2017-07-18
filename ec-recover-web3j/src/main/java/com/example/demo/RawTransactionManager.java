package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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


    public String createRawTransaction() throws IOException, CipherException, ExecutionException, InterruptedException {

        String walletFile = "./UTC--2017-07-10T14-28-31.729000000Z--87dfd2200b2fab601fb53fb32877c352f293756e.json";

       Credentials credentials = WalletUtils.loadCredentials("test", walletFile);

        SocialRecord contract = SocialRecord.load(
                "0xe351aF6Be770014d71075101fC0Ad0c9a284d15d", web3j, credentials, GAS_PRICE, GAS_LIMIT);

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        Utf8String _globalId = new Utf8String("1");
        Utf8String _socialRecordString = new Utf8String("second version");


      //  Function function = new Function("addSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        Function function = new Function("updateSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());

       // System.out.println(FunctionEncoder.encode(function));

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, GAS_PRICE, GAS_LIMIT,
                "0xe351aF6Be770014d71075101fC0Ad0c9a284d15d",
                FunctionEncoder.encode(function));


        byte[] encodedTransaction = TransactionEncoder.encode(rawTransaction);
        Sign.SignatureData signatureData = Sign.signMessage(
                encodedTransaction, credentials.getEcKeyPair());

       // byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        byte[] signedMessage = encode(rawTransaction, signatureData);

        String hexValue = Numeric.toHexString(signedMessage);

        int v = signatureData.getV();
        String r = Numeric.toHexString(signatureData.getR());
        String s = Numeric.toHexString(signatureData.getS());


    String messageSender =  contract.verify(new Utf8String(hexValue), new Uint8(BigInteger.valueOf(v)), new Utf8String(r), new Utf8String(s)).get().getFrom();

      System.out.println(messageSender);

     // System.out.println(contract.getSocialRecord(_globalId).get().toString());

       // System.out.println("\"" + hexValue + "\"," + v + "," + "\"" + r + "\"," + "\"" + s + "\"" );

//      EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
//       System.out.println(ethSendTransaction.getTransactionHash());

        return null;
    }


    private static byte[] encode(RawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<RlpType> values = asRlpValues(rawTransaction, signatureData);
        RlpList rlpList = new RlpList(values);
        return RlpEncoder.encode(rlpList);
    }


    static List<RlpType> asRlpValues(
            RawTransaction rawTransaction, Sign.SignatureData signatureData) {
        List<RlpType> result = new ArrayList<>();

        result.add(RlpString.create(rawTransaction.getNonce()));
        result.add(RlpString.create(rawTransaction.getGasPrice()));
        result.add(RlpString.create(rawTransaction.getGasLimit()));

        // an empty to address (contract creation) should not be encoded as a numeric 0 value
        String to = rawTransaction.getTo();
        if (to != null && to.length() > 0) {
            result.add(RlpString.create(Numeric.toBigInt(to)));
        } else {
            result.add(RlpString.create(""));
        }

        result.add(RlpString.create(rawTransaction.getValue()));

        // value field will already be hex encoded, so we need to convert into binary first
        byte[] data = Numeric.hexStringToByteArray(rawTransaction.getData());
        result.add(RlpString.create(data));

        if (signatureData != null) {
            result.add(RlpString.create(signatureData.getV()));
            result.add(RlpString.create(signatureData.getR()));
            result.add(RlpString.create(signatureData.getS()));
        }

        return result;
    }


}
