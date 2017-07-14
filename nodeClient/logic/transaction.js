import Web3 from 'web3';
import Transaction from 'ethereumjs-tx'; // raw transactions
import { Wallet } from 'ethers'; // wallet utils 
import * as http from 'http'; // http requests 
import jsonfile from 'jsonfile'; // i/o json files
import path from 'path'; // handle paths 
import * as _ from 'lodash'; // utility functions 

const SolidityFunction = require('web3/lib/web3/function'); // hex converter
const socialRecordContract = require('../build/contracts/SocialRecord.json'); // social record contract
const config = require('../resources/config');
const GSLS_ADDRESS = config.contractAddress; // SOCIAL RECORD CONTRACT GETH

const web3 = new Web3();

// TODO: REmove this
web3.setProvider(new web3.providers.HttpProvider());

function createRawTrans(socialRecord, cb) {
    console.log(`create Raw Transaction`);

    // TODO Get this variables from apis or server
    if (!window.currentWallet.address){
        cb('no address set', null);
    }
    let nonce = web3.toHex(web3.eth.getTransactionCount(window.currentWallet.address));
    let gasPrice = web3.toHex(web3.eth.gasPrice);
    let gasLimit = web3.toHex(4700000);

    if (!(nonce && gasPrice && gasLimit)) {
        cb('Parameters are missed or not generated', null);
    }

    console.log(1);
    const data = convertDataToHex('addSocialRecord', socialRecord);

    const txParams = {
        nonce,
        gasPrice,
        gasLimit,
        to: GSLS_ADDRESS,
        value: '0x00',
        data,
        // EIP 155 chainId - mainnet: 1, ropsten: 3
        chainId: 3
    }

    console.log(`txParams:`,txParams);

    let tx = new Transaction(txParams);

    let privateKey = null;
    if (window.currentWallet) {
        privateKey = Buffer.from(window.currentWallet.privateKey.substr(2), 'hex');
    }

    tx.sign(privateKey);
    let serializedTx = `0x${tx.serialize().toString('hex')}`;
    console.log(`serializedTx: ${txParams}`);

    cb(null, serializedTx);
}

function convertDataToHex(functionName, values) {
    console.log(`prepareData...`);
    let method = new SolidityFunction('', _.find(socialRecordContract.abi, { name: functionName }), '');
    let payloadData = method.toPayload(values).data;
    return payloadData;
}

function sendTransaction(transactionHash) {
    console.log(`Sending the transaction...`);

    var options = {
        host: 'localhost',
        path: '/rawtransaction/',
        //since we are listening on a custom port, we need to specify it by hand
        port: '8080',
        //This is what changes the request to a POST request
        method: 'POST'
    };

    return new Promise((resolve, reject) => {
        let req = http.request(options, (response) => {
            var str = ''
            response.on('data', function(chunk) {
                str += chunk;
            });

            response.on('end', function() {
                console.log(`Got a response!\n\n`);

                resolve(str);
            });
        });

        req.write(transactionHash);
        req.end();
    });
}

function test(e) {

    console.log('TEST');
    web3.personal.unlockAccount(web3.eth.accounts[0], 'supermario', 15000);
    web3.eth
        .contract(socialRecordContract.abi) // socialRecordContract
        .new({
            from: web3.eth.accounts[0],
            data: socialRecordContract.unlinked_binary, // socialRecordContract
            gas: '4700000'
        }, function(error, contract) {

            if (error)
                console.log(error);

            if (typeof contract.address !== 'undefined') {
                console.log(contract.address);
                config.contractAddress = contract.address;
                jsonFile.writeFileSync('./resources/config.json', config);
                console.log(contract.address);
            }
        });
}
export { createRawTrans, sendTransaction };