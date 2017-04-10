package net.sonic.gsls.config;

/**
 * Configuration class of the GSLS. Using singleton pattern.
 * 
 * @author Sebastian Göndör
 * @version 4
 * @date 06.04.2017
 */
public class Config
{
	private static Config		_singleton				= null;
	
	private static final String	versionName				= "0.0.1";
	private static final int	versionNumber			= 0000;
	private static final String	versionCode				= "";
	private static final String	versionDate				= "2017-04-19";
	private static final String	productName				= "Sonic Global Social Lookup System Blockchained";
	private static final String	productNameShort		= "gslsbc";
	
	private static final int	versionDatasetSchema	= 1;
	private static final int	versionRESTAPI			= 1;
	
	private static final String	logPathDefault			= "logs";
	private static final int	portRESTDefault			= 2002;
	
	private String				logPath;
	private int					portREST;
	
	private Config()
	{
		setDefaultValues();
	}
	
	public static Config getInstance()
	{
		if(_singleton == null)
		{
			_singleton = new Config();
		}
		return _singleton;
	}
	
	private void setDefaultValues()
	{
		this.logPath = logPathDefault; // TODO check docker and os compatibility
		this.portREST = portRESTDefault;
	}
	
	public String getLogPath()
	{
		return logPath;
	}
	
	public void setLogPath(String logPath)
	{
		this.logPath = logPath;
	}
	
	public int getPortREST()
	{
		return portREST;
	}
	
	public void setPortREST(int portREST)
	{
		this.portREST = portREST;
	}
	
	/**
	 * retrieves the product name as a String
	 *
	 * @return String
	 */
	public String getProductName()
	{
		return productName;
	}
	
	/**
	 * retrieves the product name short as a String
	 *
	 * @return String
	 */
	public String getProductNameShort()
	{
		return productNameShort;
	}
	
	/**
	 * retrieves the version number as a String, e.g. "0.1.2"
	 *
	 * @return String
	 */
	public String getVersionName()
	{
		return versionName;
	}
	
	/**
	 * retrieves the date of the build, e.g. 2014-08-22
	 *
	 * @return String
	 */
	public String getVersionDate()
	{
		return versionDate;
	}
	
	public int getVersionNumber()
	{
		return versionNumber;
	}
	
	public String getVersionCode()
	{
		return versionCode;
	}
	
	public int getVersionDatasetSchema()
	{
		return versionDatasetSchema;
	}
	
	public int getVersionRESTAPI()
	{
		return versionRESTAPI;
	}
}