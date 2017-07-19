package net.sonic.gsls.contract;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.0.1.
 */
public final class SocialRecord extends Contract {
    private static final String BINARY = "6060604052341561000c57fe5b5b60008054600160a060020a03191633600160a060020a031690811782556040805160808101825260078183019081527f43726561746f720000000000000000000000000000000000000000000000000060608301528152600160208083018290529385528352922082518051919261008a928492909101906100ac565b50602091909101516001909101805460ff19169115159190911790555b61014c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100ed57805160ff191683800117855561011a565b8280016001018555821561011a579182015b8281111561011a5782518255916020019190600101906100ff565b5b5061012792915061012b565b5090565b61014991905b808211156101275760008155600101610131565b5090565b90565b610f5b8061015b6000396000f300606060405236156100755763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166341c0e1b581146100775780636383cc201461008957806393a1122e146101a8578063c57411a814610212578063d3b493b314610331578063f1835db714610407575bfe5b341561007f57fe5b610087610442565b005b341561009157fe5b61011c600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b0180359182018390048302840183019094528083529799988101979196509182019450925082915084018382808284375094965061046f95505050505050565b604080518315158152602080820183815284519383019390935283519192916060840191850190808383821561016d575b80518252602083111561016d57601f19909201916020918201910161014d565b505050905090810190601f1680156101995780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b34156101b057fe5b6101fe600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375094965061074495505050505050565b604080519115158252519081900360200190f35b341561021a57fe5b61011c600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284375050604080516020601f89358b018035918201839004830284018301909452808352979998810197919650918201945092508291508401838280828437509496506107fb95505050505050565b604080518315158152602080820183815284519383019390935283519192916060840191850190808383821561016d575b80518252602083111561016d57601f19909201916020918201910161014d565b505050905090810190601f1680156101995780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b341561033957fe5b610387600480803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843750949650610c5595505050505050565b6040805160208082528351818301528351919283929083019185019080838382156103cd575b8051825260208311156103cd57601f1990920191602091820191016103ad565b505050905090810190601f1680156103f95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561040f57fe5b61042660043560ff60243516604435606435610dc4565b60408051600160a060020a039092168252519081900360200190f35b60005433600160a060020a0390811691161461045e5760006000fd5b600054600160a060020a0316ff5b5b565b6000610479610e35565b6002846040518082805190602001908083835b602083106104ab5780518252601f19909201916020918201910161048c565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff161591506104fe9050575050604080516020810190915260008082529061073d565b604060405190810160405280848152602001600115158152506002856040518082805190602001908083835b602083106105495780518252601f19909201916020918201910161052a565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084518051919461058a94508593500190610e47565b506020918201516001918201805460ff191691151591909117905560408051808201825287815280840183905233600160a060020a03166000908152928452912081518051929391926105e09284920190610e47565b5060208201518160010160006101000a81548160ff0219169083151502179055509050507f28ba1e13b3c31eb411f244a6e1b269965b606b4befb99d94517174a24957c52b3385856040518084600160a060020a0316600160a060020a03168152602001806020018060200183810383528581815181526020019150805190602001908083836000831461068f575b80518252602083111561068f57601f19909201916020918201910161066f565b505050905090810190601f1680156106bb5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838382156106fa575b8051825260208311156106fa57601f1990920191602091820191016106da565b505050905090810190601f1680156107265780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15060019050815b9250929050565b60006002826040518082805190602001908083835b602083106107785780518252601f199092019160209182019101610759565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092209150600090506107b38282610ec6565b506001908101805460ff19169055600160a060020a033316600090815260209190915260408120906107e58282610ec6565b506001908101805460ff1916905590505b919050565b6000610805610e35565b836040518082805190602001908083835b602083106108355780518252601f199092019160209182019101610816565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019166001600033600160a060020a0316600160a060020a0316815260200190815260200160002060000160405180828054600181600116156101000203166002900480156108e95780601f106108c75761010080835404028352918201916108e9565b820191906000526020600020905b8154815290600101906020018083116108d5575b505091505060405180910390206000191614151561098f576000608060405190810160405280606081526020017f54686973206163636f756e74206973206e6f7420616c6c6f77656420746f206d81526020017f6f646966792074686520736f6369616c207265636f726420636f72726573706f81526020017f6e64696e6720746f207468652073706563696669656420676c6f62616c2069648152509150915061073d565b6002846040518082805190602001908083835b602083106109c15780518252601f1990920191602091820191016109a2565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff1615159150610a659050576000606060405190810160405280602981526020017f5468652073706563696669656420736f6369616c207265636f726420646f657381526020017f6e277420657869737400000000000000000000000000000000000000000000008152509150915061073d565b604060405190810160405280848152602001600115158152506002856040518082805190602001908083835b60208310610ab05780518252601f199092019160209182019101610a91565b51815160209384036101000a6000190180199092169116179052920194855250604051938490038101909320845180519194610af194508593500190610e47565b5060208201518160010160006101000a81548160ff0219169083151502179055509050507fcccdb45f3b5672bf7d1d195ebd44389811a7e68c2a0aa29a9d25cbd1eb2853d73385856040518084600160a060020a0316600160a060020a03168152602001806020018060200183810383528581815181526020019150805190602001908083836000831461068f575b80518252602083111561068f57601f19909201916020918201910161066f565b505050905090810190601f1680156106bb5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838382156106fa575b8051825260208311156106fa57601f1990920191602091820191016106da565b505050905090810190601f1680156107265780820380516001836020036101000a031916815260200191505b509550505050505060405180910390a15060019050815b9250929050565b610c5d610e35565b6002826040518082805190602001908083835b60208310610c8f5780518252601f199092019160209182019101610c70565b51815160209384036101000a600019018019909216911617905292019485525060405193849003019092206001015460ff1615159150610cd190505760006000fd5b6002826040518082805190602001908083835b60208310610d035780518252601f199092019160209182019101610ce4565b518151600019602094850361010090810a820192831692199390931691909117909252949092019687526040805197889003820188208054601f6002600183161590980290950116959095049283018290048202880182019052818752929450925050830182828015610db75780601f10610d8c57610100808354040283529160200191610db7565b820191906000526020600020905b815481529060010190602001808311610d9a57829003601f168201915b505050505090505b919050565b60408051600081815260208083018452918301819052825187815260ff87168184015280840186905260608101859052925190926001926080808301939192601f198301929081900390910190868661646e5a03f11515610e2157fe5b50506020604051035190505b949350505050565b60408051602081019091526000815290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610e8857805160ff1916838001178555610eb5565b82800160010185558215610eb5579182015b82811115610eb5578251825591602001919060010190610e9a565b5b50610ec2929150610f0e565b5090565b50805460018160011615610100020316600290046000825580601f10610eec5750610f0a565b601f016020900490600052602060002090810190610f0a9190610f0e565b5b50565b610f2c91905b80821115610ec25760008155600101610f14565b5090565b905600a165627a7a72305820247335beb2f402dd9a8f45db03b9408bbcf1a74c6e72c68abda656d696d51d610029";

    private SocialRecord(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private SocialRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SocialRecordAddedEventResponse> getSocialRecordAddedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialRecordAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<SocialRecordAddedEventResponse> responses = new ArrayList<SocialRecordAddedEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            SocialRecordAddedEventResponse typedResponse = new SocialRecordAddedEventResponse();
            typedResponse.user = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
            typedResponse.socialRecordString = (Utf8String)eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialRecordAddedEventResponse> socialRecordAddedEventObservable() {
        final Event event = new Event("SocialRecordAdded", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialRecordAddedEventResponse>() {
            @Override
            public SocialRecordAddedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialRecordAddedEventResponse typedResponse = new SocialRecordAddedEventResponse();
                typedResponse.user = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
                typedResponse.socialRecordString = (Utf8String)eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<SocialRecordUpdatedEventResponse> getSocialRecordUpdatedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialRecordUpdated", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<SocialRecordUpdatedEventResponse> responses = new ArrayList<SocialRecordUpdatedEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            SocialRecordUpdatedEventResponse typedResponse = new SocialRecordUpdatedEventResponse();
            typedResponse.updater = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
            typedResponse.socialRecordString = (Utf8String)eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialRecordUpdatedEventResponse> socialRecordUpdatedEventObservable() {
        final Event event = new Event("SocialRecordUpdated", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialRecordUpdatedEventResponse>() {
            @Override
            public SocialRecordUpdatedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialRecordUpdatedEventResponse typedResponse = new SocialRecordUpdatedEventResponse();
                typedResponse.updater = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
                typedResponse.socialRecordString = (Utf8String)eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public List<SocialReocordDeletedEventResponse> getSocialReocordDeletedEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("SocialReocordDeleted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<SocialReocordDeletedEventResponse> responses = new ArrayList<SocialReocordDeletedEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            SocialReocordDeletedEventResponse typedResponse = new SocialReocordDeletedEventResponse();
            typedResponse.deleter = (Address)eventValues.getNonIndexedValues().get(0);
            typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SocialReocordDeletedEventResponse> socialReocordDeletedEventObservable() {
        final Event event = new Event("SocialReocordDeleted", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST,DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SocialReocordDeletedEventResponse>() {
            @Override
            public SocialReocordDeletedEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                SocialReocordDeletedEventResponse typedResponse = new SocialReocordDeletedEventResponse();
                typedResponse.deleter = (Address)eventValues.getNonIndexedValues().get(0);
                typedResponse.globalId = (Utf8String)eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addSocialRecord(Utf8String _globalId, Utf8String _socialRecordString) {
        Function function = new Function("addSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> deleteSocialRecord(Utf8String _globalId) {
        Function function = new Function("deleteSocialRecord", Arrays.<Type>asList(_globalId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> updateSocialRecord(Utf8String _globalId, Utf8String _socialRecordString) {
        Function function = new Function("updateSocialRecord", Arrays.<Type>asList(_globalId, _socialRecordString), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getSocialRecord(Utf8String _globalId) {
        Function function = new Function("getSocialRecord", 
                Arrays.<Type>asList(_globalId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> verify(Bytes32 _hash, Uint8 _v, Bytes32 _r, Bytes32 _s) {
        Function function = new Function("verify", 
                Arrays.<Type>asList(_hash, _v, _r, _s), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<SocialRecord> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(SocialRecord.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<SocialRecord> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(SocialRecord.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static SocialRecord load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialRecord(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SocialRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SocialRecord(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SocialRecordAddedEventResponse {
        public Address user;

        public Utf8String globalId;

        public Utf8String socialRecordString;
    }

    public static class SocialRecordUpdatedEventResponse {
        public Address updater;

        public Utf8String globalId;

        public Utf8String socialRecordString;
    }

    public static class SocialReocordDeletedEventResponse {
        public Address deleter;

        public Utf8String globalId;
    }
}
