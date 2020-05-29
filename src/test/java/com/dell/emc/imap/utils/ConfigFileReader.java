package com.dell.emc.imap.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath = "Configuration//Config";
	
	public ConfigFileReader(String environment) {
		String propertiesFilePath = propertyFilePath + "." + environment + ".properties";
		try (BufferedReader reader = new BufferedReader(new FileReader(propertiesFilePath))) {
			properties = new Properties();
			properties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(environment+" not found at " + propertyFilePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		return properties;
	}
}
