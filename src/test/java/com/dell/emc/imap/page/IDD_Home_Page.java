package com.dell.emc.imap.page;


//import com.dell.devote.core.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;
import com.dell.emc.imap.utils.PageBase;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import com.dell.emc.imap.utils.generichelper.JavaScriptHelper;

public class IDD_Home_Page extends PageBase {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(IDD_Home_Page.class);
	WaitHelper waitHelper;
	public WebElement btnID4_text;
	@FindBy(xpath = "//iframe[@name='tasks']")
	public WebElement iframe_tasks;
	
	
	@FindBy(xpath= "//span[contains(text(),'No tasks to display')]")
	public WebElement textNoTasks;

	@FindBy(xpath = "//iframe[@name='dashboard']")
	public WebElement iframe_dashboard;

	@FindBy(xpath = "//span[text()='Subject Area']/following::table[@class='extdt-table-layout rich-table ' and contains(@id,'aF:tabs_panel')]/tbody")
	public WebElement Tasks_SearchResult_Table;

	@FindBy(xpath = "//td[text()='Search']/following::table[1]/tbody")
	public WebElement ReassignTask_SearchResult_Table;

	//@FindBy(xpath = "//*[@id='aF:tabs_panel_include_id:topInc:TBL3:CFC13']")
	@FindBy(xpath = "//span[text()='Title']/following::input[contains(@name,'aF:tabs_panel') and @class='inlineFilterEditor']")
	public WebElement Title_Filter;

	@FindBy(xpath = "//*[@class='inlineFilterEditor']")
	public WebElement startpage_Title_filter;

	@FindBy(xpath = "((//*[contains(text(),'Start')])//ancestor::ul)//*[contains(text(),'Data')]/../..")
	public WebElement datatab;

	@FindBy(xpath ="//span[contains(text(),'Start')]")
	public WebElement startTab;
	
	@FindBy(xpath ="//span[contains(text(),'Tasks')]")
	public WebElement taskbutton;
	
	@FindBy(xpath ="//span[contains(@class,'empty-set-text')]")
	public WebElement empty_text; 
	
	@FindBy(xpath = "//span[text()='Add Parent']")
	public WebElement AddParent;
	
	@FindBy(xpath = "//span[text()='Add Parent']/following::td[1]/input")
	public WebElement AddParent_value;
	
	@FindBy(xpath = "//span[text()='No Records ']")
	public WebElement Phone_details;
	
	public IDD_Home_Page (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void setTitle_Filter(String title) {
		log.info("entering Title Filter .." + title);
		this.Title_Filter.sendKeys(title);
	}


	@FindBy(xpath = "//*[@class='inlineFilterEditor']")
	public WebElement title_field;  
	public String getUCID(String reqID) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("dashboard");
			title_field.sendKeys("%"+reqID+"%");
			BaseUtilityHelper.wait3Seconds();
			title_field.sendKeys(Keys.ENTER);
			log.info("RequestID field should be entered as "+reqID+"" );
			BaseUtilityHelper.wait10Seconds();
			WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
			log.info("req ID"+reqID);
			String all_details = reqID_details.getText();
			System.out.println("-----"+all_details);
			int strindex = all_details.indexOf(reqID);
			String strRequestID=all_details.substring(strindex+8);
			System.out.println(strRequestID);
			String UCID=strRequestID;
			log.info("User got the UCID as"+UCID);
		return UCID;
		}
		catch (Exception e) {
			log.info("Exception:  Unable to get the UCID");
			throw e;
		}
	}
	
	public void ValidateTaskNameFormat(String Country_code, String customername, String ntID,  String reqID, String ucid) throws Exception {
		try {
			WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
			log.info("req ID"+reqID);
			String Actual_format = reqID_details.getText();
			String expected_format = "PORTAL" +"-" + Country_code +"-" + customername +"-" + ntID +"-" + reqID +"-" +ucid;
			System.out.println(Actual_format);
			System.out.println(expected_format);
			if (Actual_format.equalsIgnoreCase(expected_format)) {
				log.info("task name should be in \"PORTAL-COUNTRY CODE-Customer Name-Requestor NTID-Request ID (U%)-UCID\" format");
			}
			else {
			}			
		}
		catch (Exception e) {
			log.info("Customer name is not in required format");
			throw e;
		}
	}
	
	public void ValidateTaskNameStatusFormat(String status, String customername, String ntID,  String reqID, String ucid) throws Exception {
		try {
			WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
			log.info("req ID"+reqID);
			String Actual_format = reqID_details.getText();
			//String format_values = Country_code + ";" +  customername + ";" + ntID +";" + reqID + ";" + ucid;
			//String[] tasknamevalues_expvalues = format_values.split(";");
			String expected_format = "STATUS" +"-" + status +"-" + customername +"-" + ntID +"-" + reqID +"-" +ucid;
			System.out.println(Actual_format);
			System.out.println(expected_format);
			if (Actual_format.equalsIgnoreCase(expected_format)) {
				log.info("task name should be in STATUS-A-CustomerName-RequestorNTID-RequestID-UCID format");
			}
			else {
				log.info("task name should be in STATUS-A-CustomerName-RequestorNTID-RequestID-UCID format");
			}
			
			
		}
		catch (Exception e) {
			log.info("Customer name is not in required format");
			throw e;
		}
	}
		
	public void ValidateTaskNameRestrictedChangeRequest(String customername, String ntID,  String reqID, String ucid) throws Exception {
		try {
			WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
			log.info("req ID"+reqID);
			String Actual_format = reqID_details.getText();
			//String format_values = Country_code + ";" +  customername + ";" + ntID +";" + reqID + ";" + ucid;
			//String[] tasknamevalues_expvalues = format_values.split(";");
			String expected_format = "RESTRICTED_ACTIVATION" +"-" + "-" + customername +"-" + ntID +"-" + reqID +"-" +ucid;
			System.out.println(Actual_format);
			System.out.println(expected_format);
			if (Actual_format.equalsIgnoreCase(expected_format)) {
				
				log.info("task name should be in RESTRICTED_ACTIVATION-CustomerName-RequestorNTID-RequestID-UCID format");
			}
			else {
				log.info("task name should be in RESTRICTED_ACTIVATION-CustomerName-RequestorNTID-RequestID-UCID format"+ customername );
			}
			
		}
		catch (Exception e) {
			log.info("Customer name is not in required format");
			throw e;
		}
	}

	public void clickOnDatatab() throws Exception{
		BaseUtilityHelper.waitTime(1);
		datatab.click();
		log.info("User should be clicked on Data tab and navigated to respective tab");
	}

	public void ValidateTaskType(String reqID, String tasktype) throws Exception{
		try{
		BaseUtilityHelper.waitTime(2);
		 //driver.switchTo().defaultContent();
		//driver.switchTo().frame(0);
		WebElement tsktype = driver.findElement(By.xpath("//span[contains(text(),'"+tasktype+"')]"));
		String actual = tsktype.getText();
		System.out.println(actual);	
		if (actual.contains(tasktype)) {
			log.info("Task type '"+ tasktype +"' should be displayed for the request");
		}
		else {
			log.info("Task type '"+ tasktype +"' is not displayed");
		}
		}
		catch (Exception e) {
			log.info("Exception: task type "+ tasktype +" is not displayed");
			throw e;
		}
	}
	@FindBy (xpath = "//td[contains(text(),'Automation Testing')]")
	WebElement user_profile;
	public void logoutIDD() throws Exception{
		try{
			BaseUtilityHelper.wait3Seconds();
			Actions move = new Actions(driver);
			Robot keys = new Robot();
			BaseUtilityHelper.waitTime(2);
			move.doubleClick(user_profile);
			//move.moveToElement(user_profile).build().perform();	
			BaseUtilityHelper.waitTime(2);
			keys.keyPress(KeyEvent.VK_DOWN);
			BaseUtilityHelper.waitTime(2);
			keys.keyRelease(KeyEvent.VK_DOWN);
			keys.keyPress(KeyEvent.VK_DOWN);
			BaseUtilityHelper.waitTime(2);
			keys.keyRelease(KeyEvent.VK_DOWN);
			BaseUtilityHelper.waitTime(2);
			keys.keyPress(KeyEvent.VK_ENTER);
			BaseUtilityHelper.wait5Seconds();
			log.info("User should log out IDD");
		}catch(Exception e){
			log.info("User should log out IDD");
			throw e;
		}
	}

	public void clickStartTab() throws Exception {
		BaseUtilityHelper.waitTime(2);
		startTab.click();
		log.info("User should be able to click on Start Tab");
	}
	public void opentask(String requestID) throws Exception
	{
		BaseUtilityHelper.wait3Seconds();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		title_field.sendKeys("%"+requestID+"%");
		BaseUtilityHelper.wait3Seconds();
		title_field.sendKeys(Keys.ENTER);
		/*try
		{
			driver.findElement(By.xpath("//*[contains(text(),'"+requestID+"')]"));
			log.info("User should be able to view the task","Task was found");
		
		}
		catch(ElementNotFoundException e)
		{
			log.info("User should be able to view the task","Task Could not be found");
			//throw e;
		}
		try
		{
			BaseUtilityHelper.clickElement("td", "Open Task");
			BaseUtilityHelper.wait20Seconds();
		
		}
		catch(ElementNotFoundException e)
		{
			log.info("User should be able to click Open Task button ","Button Could not be found");
			//throw e;
		}*/
		
		
	}
			
/*		//Method to validate Task table, Title column 1st row contains desired text
		public void validateTaskTitleCellTextContains(String reqID, String desiredText) throws Exception {
			
			String strText = desiredText.trim().toUpperCase();
				BaseUtilityHelper.wait3Seconds();
				driver.switchTo().defaultContent();
				driver.switchTo().frame("tasks");
				
				WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
				log.info("req ID"+reqID);
				String Actual_Text = reqID_details.getText().trim();
				
				if (Actual_Text.toUpperCase().contains(strText.trim())) {
					log.info("Task title should contain text:- "+strText, "Task title contains text. The entire text is:- "+Actual_Text);
				}
				else {
					log.info("Task title should contain text:- "+strText, "Task title Does Not contain text. The entire text is:- "+Actual_Text);
				}			
			}
			catch (Exception e) {
				log.info("In Catch block. Task Title doesn't have required text:- " +strText,
						"''Unable to get the UCID");
				throw e;
			}
		}*/

	@FindBy(xpath ="//span[contains(text(),'Tasks')]")
	public WebElement TasksTab;
	
	//Select task tab
	public void clickOnTaskstab() throws Exception{
		BaseUtilityHelper.waitTime(10);
		TasksTab.click();
		log.info("User should be clicked on Tasks tab and navigated to respective tab");
	}
	
		
	@FindBy(xpath = "//*[@id='aF:tabs_panel_include_id:topInc:TBL3:CFC16comboboxField']")
	public WebElement taskType_Filter;
		
	//Setting filter in tasktype and task title
	public void setFilterTasksPartyTable(String FilterTaskType, String strRequestID) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tasks");
			
			//Set value in task title
			title_field.sendKeys(strRequestID);
			BaseUtilityHelper.wait3Seconds();
			title_field.sendKeys(Keys.ENTER);
			log.info("RequestID field should be entered as "+strRequestID+"");
			BaseUtilityHelper.wait10Seconds();			
			
			
			//Set value in taskType					
			log.info("Trying to set value in task type");	
			BaseUtilityHelper.wait3Seconds();
			taskType_Filter.click();
			BaseUtilityHelper.wait3Seconds();
			taskType_Filter.sendKeys(FilterTaskType);				
			BaseUtilityHelper.wait3Seconds();
			taskType_Filter.sendKeys(Keys.ENTER);					
			BaseUtilityHelper.wait20Seconds();
			log.info("Successfully set value: -" + FilterTaskType + " in task type");
					
			
		}
		catch (Exception e) {
			log.info("Exception: in catch block. Unable to set values in filters");
			throw e;
		}
	}

		
	//Method to validate Task table, Title column 1st row contains desired text
	public void validateTaskTitleCellTextContains(String reqID, String desiredText) throws Exception {
		
		String strText = desiredText.trim().toUpperCase();
		try {	
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tasks");
			
			WebElement reqID_details =driver.findElement(By.xpath("//*[contains(text(),'"+reqID+"')]"));
			log.info("req ID"+reqID);
			String Actual_Text = reqID_details.getText().trim();
			
			if (Actual_Text.toUpperCase().contains(strText.trim())) {
				log.info("Task title should contain text:- "+strText);
			}
			else {
				Assert.fail();
				log.info("Task title should contain text:- "+strText);
			}			
		}
		catch (Exception e) {
			log.info("In catch block. Task Title doesn't have required text:- " +strText);
			throw e;
		}
	}
	
	
	@FindBy(xpath ="//*[contains(text(),'Open Task')]")
	public WebElement openTasksButton;
	
	@FindBy(xpath ="//span[contains(text(),'Task Details')]")
	public WebElement taskDetailsText;
	
	@FindBy(xpath ="//*[contains(text(),'Task Actions')]")
	public WebElement taskActionDropdown;
	
	//Open task and validate that task screen opens
	public void openTaskFromTasksTab() throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tasks");
			BaseUtilityHelper.wait3Seconds();
			openTasksButton.click();
			BaseUtilityHelper.wait10Seconds();			
			BaseUtilityHelper.wait10Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("data");
			boolean taskDetailsBln = BaseUtilityHelper.IsElementPresent(taskDetailsText);
			boolean taskActionDropdownBln = BaseUtilityHelper.IsElementPresent(taskActionDropdown);
						
			if (taskDetailsBln && taskActionDropdownBln ) {
				log.info("Restricted data steward should be able to open Task");
			}
			else {
				Assert.fail();
				log.info("Restricted data steward should be able to open Task");
			}		
			BaseUtilityHelper.wait10Seconds();
			driver.switchTo().defaultContent();			
			
		}
		catch (Exception e) {
			log.info("Exception:  In catch block restricted data steward was unable to Open Task");
			throw e;
		}
	}

	@FindBy(xpath ="//*[contains(text(),'No tasks to display')]")
	public WebElement noTaskToDisplay;
	
	//Verify that there is no task with the set filter
	public void noTasksToDisplayTextValidationForFilter(String strTaskTypeFilter) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("tasks");
			BaseUtilityHelper.wait3Seconds();
			boolean taskDetailsBln = BaseUtilityHelper.IsElementPresent(noTaskToDisplay);
							
			if (taskDetailsBln) {
				log.info("Restricted data steward should not be able to see Task with filter :- "+strTaskTypeFilter);
			}
			else {
				Assert.fail();
				log.info("Restricted data steward should not be able to see Task with filter :- "+strTaskTypeFilter);				
			}		
			
			BaseUtilityHelper.wait10Seconds();
			driver.switchTo().defaultContent();			
			
		}
		catch (Exception e) {
			log.info("Restricted data steward should not be able to see Task with filter :- "+strTaskTypeFilter);
			throw e;
		}
	}

	public int title_Search(String srch) throws Exception {
		try {
			BaseUtilityHelper.wait3Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame("dashboard");
			title_field.clear();
			BaseUtilityHelper.wait3Seconds();
			title_field.sendKeys(srch);
			BaseUtilityHelper.wait3Seconds();
			title_field.sendKeys(Keys.ENTER);
			log.info("Search field should be entered as " + srch + "");
			BaseUtilityHelper.wait10Seconds();
			List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + srch + "')]"));
			if (elements.size() > 0) {
				return elements.size();
			} else {
				return 0;
			}
		} catch (Exception e) {
			log.info("Exception:  Unable to find the Search text");
			throw e;
		}
	}
	
	public void view_Details() throws Exception {
		try {
			JavaScriptHelper jss = new JavaScriptHelper(driver);
			BaseUtilityHelper.clickElement("td", "Open Task");
			BaseUtilityHelper.wait10Seconds();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			BaseUtilityHelper.wait5Seconds();
			jss.executeScript("scroll(0, 300);");
			BaseUtilityHelper.wait5Seconds();
			jss.executeScript("scroll(0, 0);");
			BaseUtilityHelper.wait5Seconds();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int task_Search(String srch) throws Exception {
		try {
			
			BaseUtilityHelper.wait3Seconds();
			title_field.sendKeys(srch);
			title_field.sendKeys(Keys.ENTER);
			log.info("Search field should be entered as " + srch + "");
			BaseUtilityHelper.wait10Seconds();
			List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(),'" + srch + "')]"));
			if (elements.size() > 0) {
				return elements.size();
			} else {
				return 0;
			}
		} catch (Exception e) {
			
			throw e;
		}
	} 
}
