package net.sonic.gsls.service;

import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by ahmadjawid on 7/17/17.
 */
public interface TransactionService {

    String sendRawTransaction(String hexValue) throws ExecutionException, InterruptedException;

    String getSocialRecord(String globalID) throws IOException, CipherException, ExecutionException, InterruptedException;
}
