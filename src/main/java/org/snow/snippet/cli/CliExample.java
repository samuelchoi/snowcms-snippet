package org.snow.snippet.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Cli is Command Line master feature example
 * 
 */
public class CliExample {

	public static void main(String[] arg) throws ParseException {
		
		// Define command line options
		Options options = new Options();
		options.addOption("h",false,"list help");
		options.addOption("t",true,"set time on system");
		
		// Parse current arg value into CommandLine 
		CommandLineParser parse = new DefaultParser();
		CommandLine cmd = parse.parse(options, arg);
		
		// Define Interact when user input "t" or "h" option tack action
		if(cmd.hasOption("t")) {
			System.out.printf("system time has setted %s ",cmd.getOptionValue("t"));
			return;
		}
		
		if(cmd.hasOption("h")) {
			String formatter = "CLI Test";
			HelpFormatter hf = new HelpFormatter();
			hf.printHelp(formatter, options);
			return;
		}
	}
}
