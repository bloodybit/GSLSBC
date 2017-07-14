import { Wallet } from 'ethers'; // wallet utils 
import jsonFile from 'jsonfile';

function getWalletKey(walletData, cb) {
    console.log("Getting the private key...");
    // get the wallet
    jsonFile.readFile(walletData.walletUrl, function(err, walletFile) {
        Wallet
            .fromEncryptedWallet(JSON.stringify(walletFile), walletData.password)
            .then(wallet => {
                console.log(`PRIVATE KEY: ${wallet.privateKey}`);
                cb(wallet);
            })
            .catch(error => console.log(error));
    });
}


module.exports = {
    getWalletKey
};