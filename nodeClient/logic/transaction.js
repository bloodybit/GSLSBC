import Web3 from 'web3'; // web3 main library
import Transaction from 'ethereumjs-tx'; // raw transactions
import { Wallet } from 'ethers'; // wallet utils 
import * as http from 'http'; // http requests 
import jsonFile from 'jsonfile'; // i/o json files
import path from 'path'; // handle paths 
import * as _ from 'lodash'; // utility functions 
import { Api } from './api';

// es5 import
const SolidityFunction = require('web3/lib/web3/function'); // hex converter
const socialRecordContract = require('../build/contracts/SocialRecord.json'); // social record contract
const config = require('../resources/config');
const GSLS_ADDRESS = config.contractAddress; // SOCIAL RECORD CONTRACT GETH

// web3 initialisation
const web3 = new Web3();
web3.setProvider(new web3.providers.HttpProvider()); // should be deleted

// get the social record from the GSLS
function getSocialRecord(globalID) {
    // call the api
    return Api.get(`${config.gsls}/socialrecord/${globalID}`);
}

// creates the raw transaction starting from the function name and the parameters
function createRawTrans(functionName, socialRecordData) {
    return new Promise((resolve, reject) => {
        console.info("INFO: ", createRawTrans.name);

        if (!window.currentWallet) {
            reject('ERROR: No wallet set');
        }

        let nonce = ''; //web3.toHex(web3.eth.getTransactionCount(window.currentWallet.address));
        let gasPrice = config.gasPrice.hex; //web3.toHex(web3.eth.gasPrice);
        let gasLimit = config.gasLimit.hex; //web3.toHex(4700000);

        // get the account nonce from the GSLS
        Api
            .get(`${config.gsls}/account/${window.currentWallet.address}/txninfo`)
            .then(txInfo => {
                console.info(`INFO: Parameters retrived from GSLS`, txInfo);

                nonce = web3.toHex(txInfo.nonce);

                if (config.demo) {
                    gasPrice = config.gasPrice.hex;
                } else {
                    gasPrice = web3.toHex(txInfo.price) || config.gasPrice.hex;
                }

                gasLimit = web3.toHex(txInfo.limit) || config.gasLimit.hex;

                if (!(nonce || gasPrice || gasLimit)) {
                    reject('ERROR: Parameters are missing or not generated');
                }

                // get function call data field in hex
                const transactionPayload = convertDataToHex(functionName, socialRecordData);

                // raw transaction params
                const txParams = {
                    nonce,
                    gasPrice,
                    gasLimit,
                    to: GSLS_ADDRESS,
                    value: '0x00',
                    data: transactionPayload,
                    // EIP 155 chainId - mainnet: 1, ropsten: 3
                    chainId: 3
                }

                console.info(`INFO: `, txParams);

                // create transaction and sign it
                let tx = new Transaction(txParams);
                let privateKey = Buffer.from(window.currentWallet.privateKey.substr(2), 'hex');
                tx.sign(privateKey);

                // serialise it 
                let serializedTx = `0x${tx.serialize().toString('hex')}`;
                console.info(`INFO: ${serializedTx}`);

                resolve(serializedTx);

            })
            .catch(error => {
                console.error(`ERROR: `, error);
                reject(`ERROR: The GSLS wasn't ablet to return the nonce`)
            });
    });
}

// converts the method definition into an hex interface (for raw transactions)
// the data field is composed by:
//  - methodId
//  - parameter hashes 
function convertDataToHex(functionName, values) {
    console.log(`prepareData...`);
    // get the function definition 
    let method = new SolidityFunction('', _.find(socialRecordContract.abi, { name: functionName }), '');
    // create the transaction payload 
    let payloadData = method.toPayload(values).data;
    return payloadData;
}

// send the raw transaction to the GSLS
function sendTransaction(transactionHex) {
    return Api.put(`${config.gsls}/socialrecord`, transactionHex);
}

/* TEST FUNCTIONS
 *  these functions are directly connecting to a blockchain node, therefore are used only for testings 
 */

// TEST: unlock the user account (test purposes)
function unlockAccount() {
    web3.personal.unlockAccount(window.currentWallet.address, window.currentWallet.password);
}

// TEST: get the GSLS contract address (the one that holds all the Social Records)
function getGSLS() {
    return web3.eth.contract(socialRecordContract.abi).at(config.contractAddress);
}

// TEST: add the social calling the blockchain client directly (test purposes)
function addSocialRecord(globalID, socialRecordBody) {
    console.info("INFO: ", addSocialRecord.name);

    // unlock the account 
    unlockAccount();

    return new Promise(function(resolve, reject) {

        // get the contract and call add on it
        getGSLS().addSocialRecord(globalID, socialRecordBody, { from: window.currentWallet.address, gas: config.gasLimit.wei },
            function(error, txAddr) {
                if (error) {
                    console.log(error);
                    reject(error);
                } else {
                    console.log(txAddr);
                    resolve(txAddr);
                }
            })
    });
}

// TEST: update the social calling the blockchain client directly (test purposes)
function updateSocialRecord(globalID, socialRecordBody) {
    console.info("INFO: ", updateSocialRecord.name);

    // unlock the account
    unlockAccount();
    return new Promise(function(resolve, reject) {

        // set up the event
        const SocialRecordUpdated = getGSLS().SocialRecordUpdated({ fromBlock: 0, toBlock: 'latest' });

        // get the contract and call update on it
        getGSLS().updateSocialRecord(globalID, socialRecordBody, { from: window.currentWallet.address, gas: config.gasLimit.wei },
            function(error, txAddr) {
                if (error)
                    reject(error);
            });

        // watch for events 
        SocialRecordUpdated.watch((error, event) => {
            if (error) {
                console.error("ERROR: ", error);
                reject(error);
            } else {
                console.log("RESULT: ", event.args);
                resolve(event.args)
            }
        });

    });
}

// TEST: get the social record calling the blockchain client directly
function getSocialRecrdTest(globalID) {
    return new Promise(function(resolve, reject) {
        getGSLS().getSocialRecord(globalID, { from: window.currentWallet.address }, function(error, socialRecord) {
            if (error) {
                console.log(error);
                reject(error);
            } else {
                console.log(socialRecord);
                resolve(JSON.parse(socialRecord));
            }
        })
    });
}

export {
    addSocialRecord,
    createRawTrans,
    getSocialRecord,
    sendTransaction,
    updateSocialRecord
};