package com.dell.emc.imap.testscripts;

import java.sql.ResultSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dell.emc.imap.page.IMAP_PageFactory;
import com.dell.emc.imap.utils.BrowserManager;
import com.dell.emc.imap.utils.ConfigFileReader;
import com.dell.emc.imap.utils.JSONReader;
import com.dell.emc.imap.utils.generichelper.BaseUtilityHelper;
import com.dell.emc.imap.utils.generichelper.DBHelper;
import com.dell.emc.imap.utils.generichelper.LoggerHelper;
import com.dell.emc.imap.utils.generichelper.WaitHelper;

public class CloneRequestSubmission extends BrowserManager{
	
	@BeforeTest
	public void beforeTest() throws Throwable {
		testBaseBeforeTest();
	}
	
	@BeforeMethod
	public void testBeforeMethod() throws Exception{
		testBaseBeforeMethod();
		ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
		String clickURL = fileReader.getProperties().getProperty("IMAP_PORTAL_URL");
		driver.manage().deleteAllCookies();
		driver.navigate().to(clickURL);
	}
	
	@Test
	public void T02_CloneRequestSubmission() throws Exception{
		Map<String, Object> dataMap = JSONReader.readJSONData(".\\Data\\CloneRequestSubmission.json");
		//Data 
		String DB_query = dataMap.get("query").toString();
		String RTM = dataMap.get("RTM").toString();
		String requestPurpose = dataMap.get("RequestPurpose").toString();
		String pleaseSpecicy = dataMap.get("PleaseSpecify").toString();
		String query = dataMap.get("query1").toString();
		
		ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
		IMAP_PageFactory obj_PageFac = PageFactory.initElements(driver, IMAP_PageFactory.class);
		final Logger log = LoggerHelper.getLogger(CloneRequestSubmission.class);
		BaseUtilityHelper.wait10Seconds();
		String existingCustomerID = DBHelper.getExistingCustomer(query, "UCID",
				fileReader.getProperties().getProperty("imap_conn_name"));
		
		// =============> Enter the ID into the Customer ID field<===========//
		obj_PageFac.IMAP_SearchPage().setCustomerID(existingCustomerID);
		obj_PageFac.IMAP_SearchPage().clickSearchButton();
		WaitHelper waithelper = new WaitHelper(driver);
		BaseUtilityHelper.wait5Seconds();
		waithelper.WaitForSearchLoading();
		
		if (obj_PageFac.IMAP_SearchPage().chkIfSearchResultisEmpty())

		{
			obj_PageFac.IMAP_SearchPage().clickOnCheckBox("Golden Records");
			BaseUtilityHelper.wait5Seconds();
			obj_PageFac.IMAP_SearchPage().clickOnCloneImg(1);
			BaseUtilityHelper.wait5Seconds();
			obj_PageFac.CustomerCloneRequestPage().verifyCustomerCloneRequestPage();
			String suit = BaseUtilityHelper.Random_NumberGenerator(3);
			obj_PageFac.CustomerCloneRequestPage().enterSuiteValue(suit);
			obj_PageFac.CustomerCloneRequestPage().selectRTM(RTM);
			obj_PageFac.CustomerCloneRequestPage().InternalCustomerType();
			obj_PageFac.CustomerCloneRequestPage().selectRequestPurpose(requestPurpose);
			BaseUtilityHelper.wait3Seconds();
			obj_PageFac.CustomerCloneRequestPage().enterValueIntoPleaseSpecify(pleaseSpecicy);
			obj_PageFac.CustomerCloneRequestPage().clickOnSubmit();
			BaseUtilityHelper.wait20Seconds();
			BaseUtilityHelper.wait10Seconds();
			if (BaseUtilityHelper.IsElementPresent(obj_PageFac.IMAP_SearchPage().submitanyway)) {
				obj_PageFac.IMAP_SearchPage().clickonSubmitAnyway();
			}
			if (waithelper.WaitForSubmitLoading()) {
				String Request_id = obj_PageFac.CustomerCloneRequestPage().getRequestId().trim();
				String dbquery_exc = DB_query.replace("req_id", Request_id);
				log.info(dbquery_exc);
				BaseUtilityHelper.waitNSeconds(120);
				ResultSet result = DBHelper.getResultSet(dbquery_exc,fileReader.getProperties().getProperty("imap_conn_name"));
				log.info(result.getFetchSize());
				String request_id = "";
				while (result.next()) {
					request_id = result.getString("REQUEST_ID");
				}
				if (request_id.equalsIgnoreCase(Request_id.trim())) {
					System.out.println("Pass");
					log.info("Request ID created successfully in DB and Request id is: " + request_id);
				} else {
					System.out.println("Fail");
					log.info("Request ID not created in DB");
					throw new Exception("Exception:Request ID not created in DB");
				}
			}
		} else {
			log.info("Exception:Data not found in iMapPortal , Please re-run the script");
			throw new Exception("Data Not found in iMapportal, Please try again...");
		}
	}
	
	@AfterMethod(description = "Executes After Each Iteration")
	public void afterMethod(ITestResult res) throws Exception {
		testBaseAfterMethod(res);
	}

	@AfterTest
	public void tearDown() throws Throwable {
		testBaseAfterTest();
	}
}
