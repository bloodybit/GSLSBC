pragma solidity ^0.4.0;

import "./strings.sol";

contract SonicContract {
    
    using strings for *;
    

	struct SocialRecord {
	    string publicKey; // pubkey used to decrypt the message and be sure it is from the creator
	    string data; // Whole String to store with all the social record
	    bool exists; // Needed to check if it is initialized https://ethereum.stackexchange.com/questions/11618/how-to-test-if-a-struct-state-variable-is-set
	}
	
	
	string public globalId; // Global id used as key in order to store and/or retrieve the SR
	mapping(string => SocialRecord) socialRecords; // sort of key-value dictionary where all the SR are stored
	
	function storeSocialRecord(string _globalId, string _socialRecord, string _publicKey) {
	    assert(!socialRecords[_globalId].exists);

	    socialRecords[_globalId] = SocialRecord(_publicKey, _socialRecord, true);
	}
	
	function updateSocialRecord(string _globalId, string _data) returns (string) {
	    //assert(!socialRecords[_globalId].exists);

        // need to decrypt the _data
        
        
        // _data now should be something like this "/*djdabGLOBALIDoskand---*/{json: 'json'}"
        
        // Then we split by one seq of chars
        var startComment = "\/*".toSlice();
        var endComment = "---*\/".toSlice();
        var dd = _data.toSlice();
        var toRemove = dd.split(startComment).toString();
        var glob = dd.split(endComment).toString();
        var dataToStore = dd.toString();
        
        assert(sha3(glob) == sha3(_globalId));
        socialRecords[_globalId].data = dataToStore;
        return dataToStore;
	}
	

    function getSocialRecord(string _globalId) constant returns (string) {
        return socialRecords[_globalId].data;
    }
}