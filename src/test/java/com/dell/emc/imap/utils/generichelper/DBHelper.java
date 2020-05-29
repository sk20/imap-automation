package com.dell.emc.imap.utils.generichelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

import com.dell.emc.imap.utils.BrowserManager;
import com.dell.emc.imap.utils.ConfigFileReader;
import com.dell.emc.imap.utils.ReadConfig;

public class DBHelper extends BrowserManager {

	private static Logger log = LoggerHelper.getLogger(DBHelper.class);

	private static Connection connection;
	private static DBHelper instance = null;

	public DBHelper(String env) {
		connection = getSingleInstanceConnection(env);
	}	
	
	private Connection getSingleInstanceConnection(String env) {
		String url = null;
		String userName = null;
		String password = null;
		String serverName = null;
		String serviceName = null;
		String port = null;
		
		ReadConfig rc = new ReadConfig();

		try {
			ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
			switch (env) {
			case "test3_ech3t":
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim(); //rc.getKeyValues("serverName_Test_03").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName_Test").trim();//rc.getKeyValues("serviceName_Test_03").trim();
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test_03").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser_Test").trim();//rc.getKeyValues("dbUser_Test_03").trim();
				password = fileReader.getProperties().getProperty("dbPass_Test").trim();//rc.getKeyValues("dbPass_Test_03").trim();
				break;
			case "test3_ibstg":
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();//rc.getKeyValues("serverName_Test_03").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName_Test");//rc.getKeyValues("serviceName_Test_03").trim();
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test_03").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("IBSTG_dbUser").trim();//rc.getKeyValues("dbUser_Test_03_IBSTG").trim();
				password = fileReader.getProperties().getProperty("IBSTG_dbPass").trim();//rc.getKeyValues("dbPass_Test_03_IBSTG").trim();
				break;
			case "test3_obstg":				
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();//rc.getKeyValues("serverName_Test_03").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName_Test");//rc.getKeyValues("serviceName_Test_03").trim();
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test_03").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser_Test_OBSTG").trim();//rc.getKeyValues("dbUser_Test_03_OBSTG").trim();
				password = fileReader.getProperties().getProperty("dbPass_Test_OBSTG").trim();//rc.getKeyValues("dbPass_Test_03_OBSTG").trim();
				break;
			case "test4_ech4t":
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName_Test").trim();
				port = fileReader.getProperties().getProperty("port_Test").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser").trim();
				password = fileReader.getProperties().getProperty("dbPass").trim();
				break;
			case "test3_webapp": // done by Biswajit
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();//rc.getKeyValues("serverName_Test");
				serviceName = fileReader.getProperties().getProperty("serviceName_Test").trim();//rc.getKeyValues("serviceName_Test");
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test");
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser_webapp_03");//rc.getKeyValues("dbUser_Test_IBSTG");
				password = fileReader.getProperties().getProperty("dbPass_webapp_03");//rc.getKeyValues("dbPass_Test_IBSTG");
				break;
			case "test4_obstg":
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();//rc.getKeyValues("serverName_Test");
				serviceName = fileReader.getProperties().getProperty("serviceName_Test").trim();//rc.getKeyValues("serviceName_Test");
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test");
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser_Test_OBSTG");//rc.getKeyValues("dbUser_Test_IBSTG");
				password = fileReader.getProperties().getProperty("dbPass_Test_OBSTG");//rc.getKeyValues("dbPass_Test_IBSTG");
				break;
			case "dev3_ech3d":
				serverName = fileReader.getProperties().getProperty("serverName").trim(); //rc.getKeyValues("serverName_Test_03").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName").trim();//rc.getKeyValues("serviceName_Test_03").trim();
				port = fileReader.getProperties().getProperty("port").trim();//rc.getKeyValues("port_Test_03").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbUser").trim();//rc.getKeyValues("dbUser_Test_03").trim();
				password = fileReader.getProperties().getProperty("dbPass").trim();//rc.getKeyValues("dbPass_Test_03").trim();
				break;
				
			case "test3_emcparty":
				serverName = fileReader.getProperties().getProperty("serverName_Test").trim();//rc.getKeyValues("serverName_Test_03").trim();
				serviceName = fileReader.getProperties().getProperty("serviceName_Test");//rc.getKeyValues("serviceName_Test_03").trim();
				port = fileReader.getProperties().getProperty("port_Test").trim();//rc.getKeyValues("port_Test_03").trim();
				url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + serviceName;
				userName = fileReader.getProperties().getProperty("dbemcpartyUsrStg").trim();//rc.getKeyValues("dbUser_Test_03_IBSTG").trim();
				password = fileReader.getProperties().getProperty("dbemcpartyPwdStg").trim();//rc.getKeyValues("dbPass_Test_03_IBSTG").trim();
				break;
			default:
				break;
			}
//			System.out.println("URL : "+url+"User: "+userName+"Pass:"+password);
			connection = DriverManager.getConnection(url.trim(), userName, password);
			if (connection != null) {
				log.info("Connected to data base.."+url+","+userName+","+password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Failed to create Data base connection.." + e);
		} catch (FileNotFoundException e) {
			log.error("Properties file could not be found", e);
		} catch (IOException e) {
			log.error("Could not access properties file", e);
		}
		return connection;
	}

	public static DBHelper getInstance(String env) {
		/*if (instance == null) {
			instance = new DBHelper(env);
		}*/
		
		return new DBHelper(env);
	}

	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String dbQuery, String env) throws SQLException, ParseException, IOException {
		instance = DBHelper.getInstance(env);
		connection = instance.getConnection();
		log.info("Executing query: " + dbQuery);

		try {
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection DBConnection(String Drivername, String Hostname, String Servicename, String Port,
			String Username, String Password) {
		try {
			String url = "jdbc:oracle:thin:@" + Hostname + ":" + Port + ":" + Servicename;
			connection = DriverManager.getConnection(url, Username, Password);
			if (connection != null) {
				log.info("Connected to data base..");
			}
		} catch (SQLException e) {
			log.error("Failed to create Data base connection.." + e);
		}
		return connection;
	}

	public static String getExistingCustomer(String query, String colName,String conn_name) throws Exception {
		try {
			String existingCustNo=null;
			List<String> firstCol = new ArrayList<String>();
			ResultSet result = DBHelper.getResultSet(query, conn_name);
			// =============> Fetch the Data from DB <==================================//
			while (result.next()) {
				firstCol.add(result.getString(colName));
			}
			Random rand = new Random();
			int list = firstCol.size();
			int randomIndex = rand.nextInt(list);
			existingCustNo = firstCol.get(randomIndex);
			return existingCustNo;
		} catch (Exception e) {
			log.info("Exception: Unable to retrieve the Ucid");
			throw e;
		}
	}
	
	public static String getMDM_ID(String query) throws Exception {
		String mdm = "";
		try {
			ResultSet result = DBHelper.getResultSet(query, "test3_ech3t");
			System.out.println(result.getFetchSize());
			// =============> Fetch the Data from DB <==================================//
			if (result.next()) {
				mdm = result.getString("UCID");
			}
		} catch (Exception e) {

			throw e;
		}
		return mdm;
	}
	
	public static String[] getvaluefromDB(String query, String[] coloumn_name) throws Exception {
		try {
			BaseUtilityHelper.wait5Seconds();	
//			Ravindra: Added below 2 lines
			ReadConfig rc = new ReadConfig();
			ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
			
//			ResultSet result = DBHelper.getResultSet(query, "stg");
//			Ravindra: Commented above line and added below 1 line only
			ResultSet result = DBHelper.getResultSet(query,fileReader.getProperties().getProperty("cmdm_conn_name"));
			String[] column_values = new String[coloumn_name.length];
			while (result.next()) {
				BaseUtilityHelper.waitTime(1);
				for (int j = 0; j < coloumn_name.length; j++) {
					String value = result.getString(coloumn_name[j]);
					column_values[j] = value;
				}
			}
			return column_values;

		} catch (Exception e) {
			log.info("value from DB should be fetched from " + coloumn_name + " coloumn");
			throw e;
		}
	}
	
	public static String[] getColumnvaluesfromDB(String query, String coloumn_name, int xth_row, int numberofvalues) throws Exception{
		try {
			 //DBHelper DBHelp = new DBHelper("test3_ech3t");
		    // BaseUtilityHelper.wait5Seconds();
		     ResultSet result = DBHelper.getResultSet(query,"dev3_ech3d");	
		    // BaseUtilityHelper.wait20Seconds();
		     String[] req_value = new String[numberofvalues];
		     int i = 0;
		     int j = 0;
		     while (result.next()) {		        	
			        //BaseUtilityHelper.waitTime(1);
			        if (i>=xth_row) {						        	
			        String value = result.getString(coloumn_name);
			        req_value[j] = value;
			        System.out.println(req_value[j]);
			        j++;	
			        if (j>=numberofvalues) {
			        	break;
			        }
			        }
			        i++;
		        }
		        log.info("value from DB should be fetched from "+coloumn_name+" coloumn");
		        return req_value;			
		}
		catch (Exception e) {
			log.info("value from DB should be fetched from "+coloumn_name+" coloumn");
			throw e;
		}
		
	}
	
	public static String getvaluefromDB(String query, String coloumn_name, int xth_row) throws Exception{
		try {
			 //DBHelper DBHelp = new DBHelper("test4");
			
//			Praveen: Added below two lines			
			ReadConfig rc = new ReadConfig();
			ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment")); 
		     BaseUtilityHelper.wait5Seconds();
//		     ResultSet result = DBHelper.getResultSet(query,"test4");	
//			Praveen: Added below one line and commented one line		    
		     ResultSet result = DBHelper.getResultSet(query,fileReader.getProperties().getProperty("imap_conn_name")); 
		     BaseUtilityHelper.wait20Seconds();
		     String req_value = "";
		     int i = 0;
		     while (result.next()) {		        	
			        BaseUtilityHelper.waitTime(1);
			        req_value = result.getString(coloumn_name);
			        System.out.println(req_value);
			        i++;
			        if (i==xth_row) {			        	
			        	 break;
			        }
		        }
		        log.info("value from DB should be fetched from "+coloumn_name+" coloumn");
		        return req_value;			
		}
		catch (Exception e) {
			log.info("value from DB should be fetched from "+coloumn_name+" coloumn");
			throw e;
		}
		
	}
	
	public static String[] getvaluefromDB(String query, String[] coloumn_name, int xth_row) throws Exception{
		try {	
//			Ravindra: Added below 2 lines only
			ReadConfig rc = new ReadConfig();
			ConfigFileReader fileReader = new ConfigFileReader(rc.getKeyValues("environment"));
			
			 //DBHelper DBHelp = new DBHelper(driver);
		     BaseUtilityHelper.wait5Seconds();
//		     ResultSet result = DBHelper.getResultSet(query, "stg");	
//			 Ravindra: Commented above line and added below 1 line only
			 ResultSet result = DBHelper.getResultSet(query,fileReader.getProperties().getProperty("cmdm_conn_name"));
		     
		     String[] column_values = new String[coloumn_name.length];
		     int count = 0;			     			    	
		     while (result.next()) {		        	
			        BaseUtilityHelper.waitTime(1);				        
			        for(int j=0; j<coloumn_name.length; j++)
			        {
			        	String value = result.getString(coloumn_name[j]);
			        	column_values[j] = value;		
			        }
			        count++;
			        if (count==xth_row) {			        	
			        	 break;
			        }				        
		        }					     
		     return column_values;			
		     
		}
		catch (Exception e) {
			log.info("value from DB should be fetched from "+coloumn_name+" coloumn");
			throw e;
		}			
	}
	
//	public String[] getvaluefromDB(String query, String[] coloumn_name) throws Exception {
//		try {
//			BaseUtilityHelper.wait5Seconds();
//			ResultSet result = DBHelper.getResultSet(query, "stg");
//			String[] column_values = new String[coloumn_name.length];
//			while (result.next()) {
//				BaseUtilityHelper.waitTime(1);
//				for (int j = 0; j < coloumn_name.length; j++) {
//					String value = result.getString(coloumn_name[j]);
//					column_values[j] = value;
//				}
//			}
//			return column_values;
//
//		} catch (Exception e) {
//			HTMLReport.updateErrorMessageintoHTMLReport(
//					"value from DB should be fetched from " + coloumn_name + " coloumn",
//					"Unable to fetch value from DB");
//			throw e;
//		}
//	}

	
	public static void disconnect() throws SQLException {
		connection.close();
	}
}