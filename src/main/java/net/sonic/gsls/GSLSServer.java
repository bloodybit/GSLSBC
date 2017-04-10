package net.sonic.gsls;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.sonic.gsls.config.Config;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * Main class for GSLS daemon
 * 
 * @date 17.01.2017
 * @version 1
 * @author Sebastian Göndör
 */
@SpringBootApplication
@EnableScheduling
public class GSLSServer implements Daemon
{
	private static Logger LOGGER;
	
	public static void main(String[] args)
	{
		System.out.println("Initializing GSLS");
		
		Config config = Config.getInstance();
		
		Options options = new Options();
		
		Option helpOption = Option.builder("h")
				.longOpt("help")
				.desc("displays help on cli parameters")
				.build();
		
		Option portRESTOption = Option.builder("p")
				.longOpt("port_rest")
				.desc("sets the port for the REST interface [" + config.getPortREST() + "]")
				.hasArg()
				.build();
		
		Option logPathOption = Option.builder("l")
				.longOpt("log_path")
				.desc("sets the directory for the log files [" + config.getLogPath() + "]")
				.hasArg()
				.build();
		
		options.addOption(helpOption);
		options.addOption(portRESTOption);
		options.addOption(logPathOption);
		
		// parse common line parameters
		CommandLineParser parser = new DefaultParser();
		
		try
		{
			CommandLine cmd = parser.parse(options, args);
			if(cmd.hasOption("h"))
			{
				HelpFormatter formater = new HelpFormatter();
				formater.printHelp("GReg help", options);
				System.exit(0);
			}
			
			if(cmd.hasOption("p"))
			{
				config.setPortREST(Integer.parseInt(cmd.getOptionValue("p"))); // TODO check for valid values
			}
			if(cmd.hasOption("l"))
			{
				config.setLogPath(cmd.getOptionValue("l")); // TODO check for valid values
			}
			
			System.out.println("-----Configuration: ");
			System.out.println("portREST: " + config.getPortREST());
			System.out.println("logPath: " + config.getLogPath() + "\n-----");
			
			// setup logging
			System.setProperty("loginfofile", config.getLogPath() + "log-info.log");
			System.setProperty("logdebugfile", config.getLogPath() + "log-debug.log");
			
			LOGGER = LoggerFactory.getLogger(GSLSServer.class);
			
			LOGGER.info(config.getProductName() + " " + config.getVersionName() + " " + config.getVersionCode());
			LOGGER.info("Build #" + config.getVersionNumber() + " (" + config.getVersionDate() + ")\n");
		}
		catch (ParseException e)
		{
			System.out.println("Wrong parameter. Error: " + e.getMessage());
		}
		
		try
		{
			LOGGER.info("initializing GSLS server... ");
			
			// Registering the port for the REST interface to listen on 
			System.getProperties().put("server.port", config.getPortREST());
			LOGGER.info("REST interface listening on  " + config.getPortREST());
			
			SpringApplication.run(GSLSServer.class, args);
		}
		catch (Exception e)
		{
			LOGGER.info("failed! Service terminated!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(DaemonContext daemonContext) throws DaemonInitException, Exception
	{
		String arguments[] = daemonContext.getArguments();
		System.out.println(arguments);
		GSLSServer.main(arguments);
	}
	
	@Override
	public void start() throws Exception
	{
		
	}
	
	@Override
	public void stop() throws Exception
	{
		
	}
	
	@Override
	public void destroy()
	{
		
	}
}