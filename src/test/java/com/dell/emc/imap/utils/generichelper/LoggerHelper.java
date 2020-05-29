package com.dell.emc.imap.utils.generichelper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
/**
 * 
 * @author Nimesh Patel
 *
 */
public class LoggerHelper {

	private static boolean root=false;
	static WebDriver driver;
	
	public static Logger getLogger(Class<?> cls){
		if(root){
			return Logger.getLogger(cls);
		}
		//PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/java/com/dell/emc/IMAPortal/Config/log4j.properties"));
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("Configuration/log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}
	
	public static void main(String[] args) {
		Logger log = LoggerHelper.getLogger(LoggerHelper.class);
		log.info("logger is configured");
		}
}