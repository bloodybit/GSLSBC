package net.sonic.gsls.util;

/**
 * Integrity exception
 * 
 * @date 18.01.2017
 * @version 1
 * @author Sebastian Göndör
 */
public class IntegrityException extends Exception
{
	private static final long serialVersionUID = -1592414652284347551L;
	
	public IntegrityException(String msg)
	{
		super(msg);
	}
}