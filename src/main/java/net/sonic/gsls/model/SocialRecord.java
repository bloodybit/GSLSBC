package net.sonic.gsls.model;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.codec.binary.Base64;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONTokener;

import org.everit.json.schema.ValidationException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Data object for handling the dataset
 * 
 * @date 06.02.2017
 * @version 2
 * @author Sebastian Göndör
 */
public class SocialRecord
{
	private String _context;			// JSON-LD
	private String _type;				// JSON-LD
	private String type;
	private String globalID;			// global id
	private String displayName;			// human readable name
	private String profileLocation;		// URL
	private String platformGID;
	private String personalPublicKey;
	private String accountPublicKey;
	private String salt;				// length MUST be 16 chars
	private String datetime;			// XSD datetime format e.g. 2015-01-01T11:11:11Z
	private int active;
	private RevokedKey[] keyRevocationList;
	
	protected SocialRecord()
	{
		
	}
	
	public static SocialRecord createFromJSONObject(JSONObject json)
	{
		SocialRecord sr = new SocialRecord();
		
		sr._context = json.getString("@context");
		sr._type = json.getString("@type");
		sr.type = json.getString("type");
		sr.globalID = json.getString("globalID");
		sr.platformGID = json.getString("platformGID");
		sr.displayName = json.getString("displayName");
		sr.profileLocation = json.getString("profileLocation");
		sr.datetime = json.getString("datetime");
		sr.accountPublicKey = json.getString("accountPublicKey");
		sr.personalPublicKey = json.getString("personalPublicKey");
		sr.active = json.getInt("active");
		sr.salt = json.getString("salt");
		
		sr.keyRevocationList = new RevokedKey[json.getJSONArray("keyRevocationList").length()];
		for(int i=0; i<json.getJSONArray("keyRevocationList").length(); i++)
			sr.keyRevocationList[i] = RevokedKey.createFromJSONObject(json.getJSONArray("keyRevocationList").getJSONObject(i));
		
		return sr;
	}
	
	public JSONObject exportJSONObject()
	{
		JSONObject json = new JSONObject();
		
		json.put("@context", this._context);
		json.put("@type", this._type);
		json.put("type", this.type);
		json.put("globalID", this.globalID);
		json.put("platformGID", this.platformGID);
		json.put("displayName", this.displayName);
		json.put("profileLocation", this.profileLocation);
		json.put("datetime", this.datetime);
		json.put("accountPublicKey", this.accountPublicKey);
		json.put("personalPublicKey", this.personalPublicKey);
		json.put("active", this.active);
		json.put("salt", this.salt);
		json.put("keyRevocationList", new JSONArray(this.keyRevocationList));
		
		return json;
	}
	
	public String getAtContext()
	{
		return this._context;
	}
	
	public void setAtContext(String context)
	{
		this._context = context;
	}
	
	public String getAtType()
	{
		return this._type;
	}
	
	public void setAtType(String type)
	{
		this._type = type;
	}
	
	public String getPlatformGID()
	{
		return this.platformGID;
	}
	
	public void setPlatformGID(String platformGID)
	{
		this.platformGID = platformGID;
	}
	
	public String getGlobalID()
	{
		return this.globalID;
	}
	
	public void setGlobalID(String gid)
	{
		this.globalID = gid;
	}
	
	public String getDisplayName()
	{
		return this.displayName;
	}
	
	public void setDisplayName(String displayname)
	{
		this.displayName = displayname;
	}
	
	public String getProfileLocation()
	{
		return this.profileLocation;
	}
	
	public void setProfileLocation(String profilelocation)
	{
		this.profileLocation = profilelocation;
	}
	
	public String getPersonalPublicKey()
	{
		return this.personalPublicKey;
	}
	
	public void setPersonalPublicKey(String personalPublicKey)
	{
		this.personalPublicKey = personalPublicKey;
	}
	
	public String getAccountPublicKey()
	{
		return this.accountPublicKey;
	}
	
	public void setAccountPublicKey(String accountPublicKey)
	{
		this.accountPublicKey = accountPublicKey;
	}
	
	public String getSalt()
	{
		return this.salt;
	}
	
	public void setSalt(String salt)
	{
		this.salt = salt;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getDatetime()
	{
		return this.datetime;
	}
	
	public void setDatetime(String datetime)
	{
		this.datetime = datetime;
	}
	
	public int getActive()
	{
		return this.active;
	}
	
	public void setActive(int active)
	{
		this.active = active;
	}
	
	public RevokedKey[] getKeyRevocationList()
	{
		return this.keyRevocationList;
	}
	
	public void setKeyRevocationList(RevokedKey[] krl)
	{
		this.keyRevocationList = krl;
	}
	
	public boolean validate() throws SocialRecordIntegrityException
	{
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("schema.json"))
		{
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			Schema schema = SchemaLoader.load(rawSchema);
			schema.validate(exportJSONObject());
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new SocialRecordIntegrityException("Error while loading schema.json");
		}
		catch (ValidationException e)
		{
			throw new SocialRecordIntegrityException("SocialRecord does not validate against JSON Schema: " + e.getMessage());
		}
		
		if(getType().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'type' missing");
		if(!getType().equals("platform") && !getType().equals("user"))
			throw new SocialRecordIntegrityException("illegal parameter value for 'type'");
		
		if(getGlobalID().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'globalID' missing");
		/*if(!getGlobalID().equals(GID.createGID(this.getPersonalPublicKey(), this.getSalt())))
			throw new SocialRecordIntegrityException("illegal parameter value for 'globalID'");*/
		
		if(getDatetime().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'datetime' missing");
		if(!net.sonic.gsls.util.XSDDateTime.validateXSDDateTime(getDatetime()))
			throw new SocialRecordIntegrityException("invalid 'datetime' format...");
		
		if(getDisplayName().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'profileLocation' missing");
		
		if(getProfileLocation().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'type' missing");
		
		if(getPersonalPublicKey().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'personalPublicKey' missing");
		String stringtobechecked = getPersonalPublicKey().substring(26, getPersonalPublicKey().length()-24);
		if (!Base64.isArrayByteBase64(stringtobechecked.getBytes()))
			throw new SocialRecordIntegrityException("invalid 'personalPublicKey' characterset");
		
		if(getAccountPublicKey().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'accountPublicKey' missing");
		stringtobechecked = getAccountPublicKey().substring(26, getAccountPublicKey().length()-24);
		if (!Base64.isArrayByteBase64(stringtobechecked.getBytes()))
			throw new SocialRecordIntegrityException("invalid 'accountPublicKey' characterset");
		
		if (getSalt().isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'salt' missing");
		if (!Base64.isArrayByteBase64(getSalt().getBytes()))
			throw new SocialRecordIntegrityException("invalid 'salt' characterset");
		
		String isactive = Integer.toString(getActive());
		if(isactive.isEmpty())
			throw new SocialRecordIntegrityException("mandatory parameter 'Active' missing");
		if(getActive() != 0 && getActive() != 1 && getActive() != 2)
			throw new SocialRecordIntegrityException("invalid parameter value for 'active'");
		
		// TODO check format of KeyRevocationList
		
		return true;
	}
}