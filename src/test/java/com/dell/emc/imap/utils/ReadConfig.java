package com.dell.emc.imap.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ReadConfig extends BaseReadConfig{
	public ReadConfig(String path) {
		if (path.contains("bin")) {
			path = path.substring(0, path.lastIndexOf("/") - 3);
		} else {
			path = path.substring(0, path.lastIndexOf("/"));
		}

		try {
			this.config.load(new FileInputStream(path + "/Configuration/Config.properties"));
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.path = path;
		this.result = new ArrayList();
	}
	
	public ReadConfig() {
		try {
			this.config.load(new FileInputStream("Configuration\\Config.properties"));
		} catch (IOException var2) {
			var2.printStackTrace();
		}
		this.path = "Configuration\\Config.properties";
		this.result = new ArrayList();
	}
	
	public String getKeyValues(String key) throws FileNotFoundException, IOException {
		return this.getKeyValues(false, key);
	}

	public String[] getKeyValues(String key, String seperator) throws FileNotFoundException, IOException {
		return super.getKeyValues(false, key, seperator);
	}
}
