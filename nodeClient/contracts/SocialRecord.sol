pragma solidity ^0.4.0;

// solc FirstContract.sol --bin --abi --optimize -o ./
// web3j solidity generate /path/to/<smart-contract>.bin /path/to/<smart-contract>.abi -o /path/to/src/main/java -p com.your.organisation.name
// Inside the resource folder
// web3j solidity generate FirstContract.bin FirstContract.abi -o ../ -p hello


// PROBLEM: Struct SR has a lot of values and solidity is not able to return all of them as strings
// SOLUTION 1: convert string to bytes32 - but I don't now how to do it
// SOLUTION 2: save the SR as stringified JSON <<<<< TAKE THIS INTO CONSIDERATION

// SocialRecord contract must be mortal and owned
contract SocialRecord {
    
    struct User {
        string globalId;
        bool exists;
    }
    
    // struct containing the Social Record
    struct SR {
        string socialRecord; 
        bool exists; 
    }

    address owner;
    mapping (address => User) users;
    // State variable
    mapping (string => SR) srs; // maps GIDs to SocialRecords 
    
    // Only the owner can call the function
    modifier onlyOwner {
        require(msg.sender == owner);
        _;
    }

    // Only saved users can call the function
    modifier onlyUser {
        if(users[msg.sender].exists) {
            _;
        }
    }

    modifier onlyNotUser {
       if(!users[msg.sender].exists) {
            _;
        }
    }
    
    // Constructor
    function SocialRecord(){
        owner = msg.sender;
        // set the contract creator
        users[msg.sender] = User("Creator", true);
    }

    // Events
    event SocialRecordAdded(address user, string globalId);
    event SocialRecordUpdated(address updater, string globalId);
    event SocialReocordDeleted(address deleter, string globalId);

    // add a Social Record: only a non-user can create it and the globalId has to be new. 
    function addSocialRecord(string _globalId, string _socialRecordString) returns (bool, string) {
        
        if (srs[_globalId].exists) {
            return (false, ""); 
        }

        srs[_globalId] = SR(_socialRecordString, true);
        users[msg.sender] = User(_globalId, true);
        SocialRecordAdded(msg.sender, _globalId);
        
        return(true, _socialRecordString);
    }

    // get a Social Record
    function getSocialRecord(string _globalId) constant returns (string) {
        
        if (!srs[_globalId].exists) {
           throw; 
        }

        return srs[_globalId].socialRecord;
    }

    // update a Social Record 
    // TODO: import library to compare strings 
    function updateSocialRecord(string _globalId, string _socialRecordString) returns (bool, string){
        
        // the user is the owner of the social record 
        // if (sha3(users[msg.sender].globalId) != sha3(_globalId)) {
        //     return false; 
        // }

        // the Social Record must exist
        if (!srs[_globalId].exists)  {
            return (false, ""); 
        }

        srs[_globalId] = SR(_socialRecordString, true);
        // Trigger the event
        SocialRecordUpdated(msg.sender, _globalId);
        return (true, _socialRecordString); 
    }

    // delete a Social Record
    function deleteSocialRecord(string _globalId) returns (bool) {
        delete srs[_globalId];
        delete users[msg.sender];
        return true; 
    }
    
    // Kill the contract (only the owner can)
    function kill() onlyOwner {
        selfdestruct(owner);
    }
}