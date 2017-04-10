package net.sonic.gsls.model;

import org.json.JSONObject;

public class RevokedKey
{
	private String revokedPublicKey;
	private String revocationDate;
	private int revocationReason;
	private String signature;
	
	protected RevokedKey()
	{
		
	}
	
	protected static RevokedKey createFromJSONObject(JSONObject json)
	{
		RevokedKey rk = new RevokedKey();
		
		rk.revokedPublicKey = json.getString("revokedPublicKey");
		rk.revocationDate = json.getString("revocationDate");
		rk.revocationReason = json.getInt("revocationReason");
		rk.signature = json.getString("signature");
		
		return rk;
	}
	
	public JSONObject exportJSONObject()
	{
		JSONObject json = new JSONObject();
		
		json.put("revokedPublicKey", this.revokedPublicKey);
		json.put("revocationDate", this.revocationDate);
		json.put("revocationReason", this.revocationReason);
		json.put("signature", this.signature);
		
		return json;
	}
	
	public String getRevokedPublicKey()
	{
		return revokedPublicKey;
	}
	
	public void setRevokedPublicKey(String revokedPublicKey)
	{
		this.revokedPublicKey = revokedPublicKey;
	}
	
	public String getRevocationDate() {
		return revocationDate;
	}
	
	public void setRevocationDate(String revocationDate)
	{
		this.revocationDate = revocationDate;
	}
	
	public int getRevocationReason()
	{
		return revocationReason;
	}
	
	public void setRevocationReason(int revocationReason)
	{
		this.revocationReason = revocationReason;
	}
	
	public String getSignature()
	{
		return signature;
	}
	
	public void setSignature(String signature)
	{
		this.signature = signature;
	}
}