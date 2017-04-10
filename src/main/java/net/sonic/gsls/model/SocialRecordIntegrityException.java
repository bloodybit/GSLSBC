package net.sonic.gsls.model;

/**
 * Exception class for handling integrity check failures of the dataset
 * 
 * @date 12.01.2017
 * @version 1
 * @author Sebastian Göndör
 */
public class SocialRecordIntegrityException extends Exception
{
	private static final long serialVersionUID = 8787254843105440320L;
	
	public SocialRecordIntegrityException(String message)
	{
		super(message);
	}
	
	public SocialRecordIntegrityException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public SocialRecordIntegrityException(Throwable cause)
	{
		super(cause);
	}
}