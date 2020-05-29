package com.dell.emc.imap.utils;

import org.openqa.selenium.WebDriver;

public class PageBase {
	WebDriver driver;
	protected String name;

	public PageBase(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public PageBase(WebDriver driver, String name) {
		super();
		this.driver = driver;
		this.name = name;
	}
	
	
}
