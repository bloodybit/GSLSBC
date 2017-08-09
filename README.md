# IOSL SS 2017: BCIDM

## Identity Management via Blockchain
The aim of this project is to build a blockchain-based, distributed system for self-asserted identities for Distributed Online Social Networks (DOSN) in the Sonic ecosystem.

## Project structure
The project has to main folders: 
- **nodeClient**: contains the code of the client
- **src**: contains the code of the GSLS server

## How to run the project
The steps in order to run the project are described in the list below: 
- Run the blockchain client (geth)
- Run the GSLS server 
- Run the desktop client

### 1. Run the blockchain client
In order to run the client, ```geth``` is needed.

The command is the following: 

```geth --fast --cache=512 --rpcapi personal,db,eth,net,web3 --rpc --testnet console```

This command should be specified in the terminal and connects to an Ethereum blockchain test node. The command opens also a console to interact directily with the blockchain client. 

### 2. Run the server 

1. Open the project on an editor such as Intellij IDEA or Eclipse.
2. Run the project inside the editor

### 3. Run the desktop client 

1. From the terminal, go to the ```nodeClient``` repository. 
2. Run the following: 

    ```npm install```
3. Then run: 

    ```npm start```

## How to test the application

Once you run ```npm start``` a window will open. 

You will asked to provide a keystore.json file and a password.

You can use the default keystore called ```default_key_store.json``` inside the ```nodeClient/test/client``` folder. 

The password is ```12345678```