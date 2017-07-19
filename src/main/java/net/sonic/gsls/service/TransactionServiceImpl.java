package net.sonic.gsls.service;

import net.sonic.gsls.contract.SocialRecord;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by ahmadjawid on 7/17/17.
 */

@Service
public class TransactionServiceImpl implements TransactionService {


    @Autowired
    private Web3j web3j;

    @Value("${wallet-file}")
    private String walletFile;
    @Value("${wallet-password}")
    private String walletPassword;

    @Value("${contract-address}")
    private String contractAddress;


    @Override
    public String sendRawTransaction(String hexValue) {

        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (ethSendTransaction != null) {
            System.out.println(ethSendTransaction.getTransactionHash());
        }

        return null;
    }

    @Override
    public String getSocialRecord(String globalID) {

        try {
            return loadContract().getSocialRecord(new Utf8String(globalID)).get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public BigInteger getNonce(String address) {

        EthGetTransactionCount ethGetTransactionCount = null;
        try {
            ethGetTransactionCount = web3j.ethGetTransactionCount(
                    address, DefaultBlockParameterName.LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        return nonce;
    }


    public SocialRecord loadContract() {

        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials(walletPassword, walletFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }

        SocialRecord contract = SocialRecord.load(
                contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);

        return contract;
    }
}
