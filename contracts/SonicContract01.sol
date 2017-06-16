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
	    assert(socialRecords[_globalId].exists);

	    socialRecords[_globalId] = SocialRecord(_publicKey, _socialRecord, true);
	}
	
	function updateSocialRecord(string _globalId, string _data) {
	    assert(socialRecords[_globalId].exists);

        // need to decrypt the _data
        
        
        // _data now should be something like this "/*djdabGLOBALIDoskand---*/{json: 'json'}"
        
        // Then we split by one seq of chars
        var startComment = "\/*".toSlice();
        var endComment = "---*\/".toSlice();
        string[] memory data;
        var dd = _data.toSlice();
        var toRemove = dd.split(startComment);
        for(uint i = 0; i<2; i++){
            data[i] = dd.split(endComment).toString();
        }
       
       assert(sha3(data[i]) != sha3(_globalId));
        
        // store the new Json encrypted
	    socialRecords[_globalId].data = data[1];
	}
    

    function getSocialRecord(string _globalId) constant returns (string) {
        return socialRecords[_globalId].data;
    }
}