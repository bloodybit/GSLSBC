import { Wallet } from 'ethers'; // wallet utils 

function getWalletKey(walletData, walletPassword, cb) {
    console.log("Getting the private key...");
    // get the wallet
    Wallet
        .fromEncryptedWallet(JSON.stringify(walletData), walletPassword)
        .then(wallet => {
            console.log(`PRIVATE KEY: ${wallet.privateKey}`);
            wallet.password = walletPassword;
            cb(null, wallet);
        })
        .catch(error => {
            cb(error, null);
        });
}


module.exports = {
    getWalletKey
};