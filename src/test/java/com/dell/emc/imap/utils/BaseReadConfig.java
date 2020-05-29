package com.dell.emc.imap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class BaseReadConfig {
	Properties config = new Properties();
	String path = null;
	String configPath = null;
	String suiteName = null;
	String tfsServer = null;
	String tfsCollection = null;
	String tfsTeamProject = null;
	String tfsSuiteId = null;
	String tfsTeastcaseId = null;
	String testRunId = null;
	String testconfigId = null;
	String testPlanId = null;
	String buildName = null;
	String buildVersion = null;
	String testSettings = null;
	String controller = null;
	String agent = null;
	String configName = null;
	String requestedBy = null;
	ArrayList<String> result;
	public Properties getConfig() {
		return config;
	}
	public void setConfig(Properties config) {
		this.config = config;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getConfigPath() {
		return configPath;
	}
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	public String getSuiteName() {
		return suiteName;
	}
	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}
	public String getTfsServer() {
		return tfsServer;
	}
	public void setTfsServer(String tfsServer) {
		this.tfsServer = tfsServer;
	}
	public String getTfsCollection() {
		return tfsCollection;
	}
	public void setTfsCollection(String tfsCollection) {
		this.tfsCollection = tfsCollection;
	}
	public String getTfsTeamProject() {
		return tfsTeamProject;
	}
	public void setTfsTeamProject(String tfsTeamProject) {
		this.tfsTeamProject = tfsTeamProject;
	}
	public String getTfsSuiteId() {
		return tfsSuiteId;
	}
	public void setTfsSuiteId(String tfsSuiteId) {
		this.tfsSuiteId = tfsSuiteId;
	}
	public String getTfsTeastcaseId() {
		return tfsTeastcaseId;
	}
	public void setTfsTeastcaseId(String tfsTeastcaseId) {
		this.tfsTeastcaseId = tfsTeastcaseId;
	}
	public String getTestRunId() {
		return testRunId;
	}
	public void setTestRunId(String testRunId) {
		this.testRunId = testRunId;
	}
	public String getTestconfigId() {
		return testconfigId;
	}
	public void setTestconfigId(String testconfigId) {
		this.testconfigId = testconfigId;
	}
	public String getTestPlanId() {
		return testPlanId;
	}
	public void setTestPlanId(String testPlanId) {
		this.testPlanId = testPlanId;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getBuildVersion() {
		return buildVersion;
	}
	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}
	public String getTestSettings() {
		return testSettings;
	}
	public void setTestSettings(String testSettings) {
		this.testSettings = testSettings;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public ArrayList<String> getResult() {
		return result;
	}
	public void setResult(ArrayList<String> result) {
		this.result = result;
	}
	
	public String getKeyValues(boolean flag, String key) throws FileNotFoundException, IOException {
		String keyValue = null;
		if (flag) {
			this.config.load(new FileInputStream(String.valueOf(this.configPath) + "/Config.properties"));
			keyValue = this.config.getProperty(key);
		} else {
			this.config.load(new FileInputStream("Configuration" + File.separator + "Config.properties"));
			keyValue = this.config.getProperty(key);
		}

		return keyValue;
	}
	
	public String[] getKeyValues(boolean flag, String key, String seperator) throws FileNotFoundException, IOException {
		String keyValue = null;
		String[] keyValues = null;
		if (flag) {
			this.config.load(new FileInputStream(String.valueOf(this.configPath) + "/Config.properties"));
			keyValue = this.config.getProperty(key);
			keyValues = keyValue.split(seperator);
		} else {
			this.config.load(new FileInputStream("Configuration" + File.separator + "Config.properties"));
			keyValue = this.config.getProperty(key);
			keyValues = keyValue.split(seperator);
		}

		return keyValues;
	}
	
	/*
	 * public String updateExecutionStatus(ArrayList<String> result) throws
	 * XMLStreamException, InterruptedException, IOException, ParseException {
	 * String finalOutcome = null; if (this.tfsServer != null && this.tfsCollection
	 * != null && this.tfsTeamProject != null && this.tfsSuiteId != null &&
	 * this.tfsTeastcaseId != null && this.testRunId != null) { if
	 * (result.toString().toLowerCase().contains("aborted")) { finalOutcome =
	 * "aborted"; } else { for (Iterator itr = result.iterator(); itr.hasNext();
	 * finalOutcome = "pass") { String outcome = (String) itr.next(); if
	 * (outcome.trim().equalsIgnoreCase("Fail")) { finalOutcome = "fail"; break; } }
	 * }
	 * 
	 * if (finalOutcome == "") { finalOutcome = "fail"; }
	 * 
	 * System.out.println("final outcome...." + finalOutcome); }
	 * 
	 * return finalOutcome; }
	 */

	public boolean isClassExists(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException var3) {
			return false;
		}
	}
}
