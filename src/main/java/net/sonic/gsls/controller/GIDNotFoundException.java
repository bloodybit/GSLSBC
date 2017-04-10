package net.sonic.gsls.controller;

/**
 * Exception class for handling integrity check failures of the dataset
 * 
 * @date 18.01.2017
 * @version 1
 * @author Sebastian Göndör
 */
public class GIDNotFoundException extends Exception
{
	private static final long serialVersionUID = 8787254843105440320L;
	
	public GIDNotFoundException(String message)
	{
		super(message);
	}
	
	public GIDNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public GIDNotFoundException(Throwable cause)
	{
		super(cause);
	}
}