package net.sonic.gsls.service;

import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Created by ahmadjawid on 7/17/17.
 */
public interface TransactionService {

    String sendRawTransaction(String hexValue);

    String getSocialRecord(String globalID);

    BigInteger getNonce(String address);

    BigInteger getGasPrice();

    BigInteger getGasLimit();

}
