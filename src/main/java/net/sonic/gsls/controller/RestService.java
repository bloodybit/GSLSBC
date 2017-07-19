package net.sonic.gsls.controller;

import net.sonic.gsls.config.Config;
import net.sonic.gsls.service.TransactionService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URISyntaxException;

/**
 * Main class for GSLS REST interface
 *
 * @author Sebastian Göndör
 * @version 1
 * @date 18.01.2017
 */
@RestController
@RequestMapping("/")
public class RestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestService.class);

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> index() throws URISyntaxException {
        LOGGER.info("Incoming request: GET /");

        JSONObject version = new JSONObject();
        version.put("version", Config.getInstance().getVersionName());
        version.put("build", Config.getInstance().getVersionNumber());
        version.put("build date", Config.getInstance().getVersionDate());

        JSONObject response = new JSONObject();
        response.put("status", 200);
        response.put("version", version);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);

    }

    /**
     * retrieve a SocialRecord
     *
     * @param globalID
     * @return ResponseEntity
     */
    @RequestMapping(value = "socialrecord/{globalID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getEntityByGlobalID(@PathVariable("globalID") String globalID) {

        LOGGER.info("Incoming request: GET /" + globalID);
        String socialRecord = transactionService.getSocialRecord(globalID);

        JSONObject response = new JSONObject();

        response.put("status", 200);
        response.put("message", socialRecord);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    /**
     * upload a new SocialRecord
     * @param globalID
     * @return ResponseEntity
     */
//	@RequestMapping(value = "/{globalID}", method = RequestMethod.POST)
//	public ResponseEntity<String> postData(@RequestBody String jwt, @PathVariable("globalID") String globalID)
//	{
//		LOGGER.info("Incoming request: POST /" + globalID + " - JWT: " + jwt);
//
//		JSONObject response = new JSONObject();
//
//		response.put("status", 200);
//		response.put("message", "");
//
//		return new ResponseEntity<>(response.toString(), HttpStatus.OK);
//	}

    /**
     * Creating a new social record or editing an existing one by overwriting with a new version
     *
     * @param hexValue
     * @return ResponseEntity
     */
    @RequestMapping(value = "/socialrecord", method = RequestMethod.PUT)
    public ResponseEntity<String> putData(@RequestBody String hexValue) {
        LOGGER.info("Incoming request: PUT /socialrecord - Raw Transaction Hex Value: " + hexValue);

        String transactionHash = transactionService.sendRawTransaction(hexValue);

        JSONObject response = new JSONObject();

        response.put("status", 200);
        response.put("message", transactionHash);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    /**
     * retrieve the nonce for an account
     *
     * @param address
     * @return ResponseEntity
     */
    @RequestMapping(value = "/account/{address}/nonce", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getNonceByAccountAddress(@PathVariable("address") String address) {

        LOGGER.info("Incoming request: GET /" + "account/" + address + "/nonce");
        BigInteger nonce = transactionService.getNonce(address);

        JSONObject response = new JSONObject();

        response.put("status", 200);
        response.put("nonce", nonce);

        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}