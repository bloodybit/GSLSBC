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
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import java.io.IOException;
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
    public String sendRawTransaction(String hexValue) throws ExecutionException, InterruptedException {

       EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();

       String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println(transactionHash);
        return transactionHash;
    }

    @Override
    public String getSocialRecord(String globalID) throws IOException, CipherException, ExecutionException, InterruptedException {

        SocialRecord contract = loadContract();
        return contract.getSocialRecord(new Utf8String(globalID)).get().toString();
    }


    public SocialRecord loadContract() throws IOException, CipherException {

        Credentials credentials = WalletUtils.loadCredentials(walletPassword, walletFile);

        SocialRecord contract = SocialRecord.load(
                contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);

        return contract;
    }
}
