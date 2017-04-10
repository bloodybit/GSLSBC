package net.sonic.gsls.controller;

import net.sonic.gsls.config.Config;
import net.sonic.gsls.model.SocialRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Main class for GSLS REST interface
 * 
 * @date 18.01.2017
 * @version 1
 * @author Sebastian Göndör
 */
@RestController
@RequestMapping("/")
public class RestService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RestService.class);
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> index() throws URISyntaxException
	{
		LOGGER.info("Incoming request: GET /");
		
		JSONObject version = new JSONObject();
		version.put("version", Config.getInstance().getVersionName());
		version.put("build", Config.getInstance().getVersionNumber());
		version.put("build date", Config.getInstance().getVersionDate());
		
		JSONObject response = new JSONObject();
		response.put("status", 200);
		response.put("version", version);
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
		
	}
	
	/**
	 * retrieve a SocialRecord
	 * @param globalID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{globalID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getEntityByGlobalID(@PathVariable("globalID") String globalID)
	{
		LOGGER.info("Incoming request: GET /" + globalID);
		
		JSONObject response = new JSONObject();
		
		response.put("status", 200);
		response.put("message", "");
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
	
	/**
	 * upload a new SocialRecord
	 * @param globalID
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/{globalID}", method = RequestMethod.POST)
	public ResponseEntity<String> postDdata(@RequestBody String jwt, @PathVariable("globalID") String globalID)
	{
		LOGGER.info("Incoming request: POST /" + globalID + " - JWT: " + jwt);
		
		JSONObject response = new JSONObject();
		
		response.put("status", 200);
		response.put("message", "");
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
	
	/**
	 * edit an existing SocialRecord by overwriting with a new version
	 * 
	 * @param jwt
	 * @param globalID
	 * @return ResonseEntity
	 */
	@RequestMapping(value = "/{globalID}", method = RequestMethod.PUT)
	public ResponseEntity<String> putdata(@RequestBody String jwt, @PathVariable("globalID") String globalID)
	{
		LOGGER.info("Incoming request: PUT /" + globalID + " - JWT: " + jwt);
		
		JSONObject response = new JSONObject();
		
		response.put("status", 200);
		response.put("message", "");
		
		return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
	}
}