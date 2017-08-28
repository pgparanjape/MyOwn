package PB_2015_2.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import resources.PB_2015_2.Main.ScriptHandlerHelper;
import Global.Paths;
import JavaSources.Logging.FormatFB;
import JavaSources.Logging.Logger;
import PB_2015_2.Common.Const;
import PB_2015_2.Common.Utils;

import com.rational.test.ft.script.RunException;

public class ScriptHandler extends ScriptHandlerHelper {

	public static Hashtable<String, String> paraTab = new Hashtable<String, String>();
//	public static Hashtable<String, String> paramsTableREQ = new Hashtable<String, String>();
	public static Hashtable<String, String> scriptParamsTable = new Hashtable<String, String>();

//	public static Hashtable<String, String> vpDetails = new Hashtable<String, String>();
	public static ArrayList<String> vpDetails = new ArrayList<String>();
	public static ArrayList<Boolean> vpDetailsAssertation = new ArrayList<Boolean>();

	private String stepExecute = "Y"; 
//	public static boolean verificationflag = true;

	public static ScriptHandlerData data = new ScriptHandlerData(new Logger(),
			new FormatFB(), null, null, true, "OK");

	private boolean myTrigger(String[] reqParam) {
		boolean condition = true;
		for (int j = 0; j < reqParam.length; j++) {
			if (paraTab.containsKey(reqParam[j]) != true) {
				condition = false;
				break;
			}
		}
		return condition;
	}

	/*******************************************************************************
	 * MAIN METHODE
	 */
	
	public void testMain(Object[] args) {
		try {
			Utils.createDir(Paths.screenGrabFolderPath);
			System.setOut(new PrintStream(new FileOutputStream(Paths.DetailedLogFullFileName)));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		data.getTable().initMainTable();
		data.getTable().initSubTable();

		data.getLogger().log("**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********");
		data.getLogger().log("\n>>> START: Automation " + " ***** " + "date: "+data.getCurrentDate() );
		data.getLogger().log(" ***** time: " + data.getCurrentTime());
		data.getLogger().log("\n**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\n");
		data.getLogger().log(data.getTable().printTableHeader());
		data.getLogger().log("- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\n");

		try {
			File keywordMapPropertyFile = new File(Paths.KeywordMapPropPath);
			FileInputStream fileInput = new FileInputStream(keywordMapPropertyFile);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Workbook testCaseWorkBook = null;
			testCaseWorkBook = WorkbookFactory.create(new File(Paths.masterExcelSheetPath));
			Sheet driverSheet = testCaseWorkBook.getSheet("Execution");

			for (int i = 1; i<=driverSheet.getLastRowNum(); i++){
				Row row = driverSheet.getRow(i);
				Cell testCaseSheetNameCell = row.getCell(1);
				Cell testCaseSheetExecutionCell = row.getCell(2);
				if (testCaseSheetExecutionCell != null && testCaseSheetNameCell != null){
					if (testCaseSheetNameCell.getCellType() == Cell.CELL_TYPE_STRING && testCaseSheetExecutionCell.getCellType() == Cell.CELL_TYPE_STRING){
						data.myList.add(testCaseSheetNameCell.getStringCellValue()+"@"+testCaseSheetExecutionCell.getStringCellValue());
					}
				}
			}

			String[] paramKey = new String[40];
			String[] paramValue = new String[40];

			for (String sheetName : data.myList) {
				String [] SheetNameValues = sheetName.trim().split("@");
				if (SheetNameValues[1].equalsIgnoreCase("Y")){
					System.out.println("****   " + sheetName + "   is executing");
					data.getLogger().log("****   " + sheetName.replace("@Y","") + "   is executing \n");
					boolean sheetExists;
					int index = testCaseWorkBook.getSheetIndex(SheetNameValues[0]);
					if (index == -1) {
						index = testCaseWorkBook.getSheetIndex(sheetName.toUpperCase());
						if (index == -1)
							sheetExists = false;
						else
							sheetExists = true;
					} else
						sheetExists = true;

					if (sheetExists){ 
						data.setMyParamList(new ArrayList<Object>());
						driverSheet = testCaseWorkBook.getSheet(SheetNameValues[0]);

						for (int row = 1; row <= driverSheet.getLastRowNum(); row++) {
							if (driverSheet.getRow(row).getCell(1) != null){
								stepExecute = driverSheet.getRow(row).getCell(0).getStringCellValue();
								if(stepExecute.isEmpty()){
									stepExecute = "Y";
								}
								String temp = driverSheet.getRow(row).getCell(1).getStringCellValue();
								if (temp != ""){
									data.setMethodName(driverSheet.getRow(row).getCell(1).getStringCellValue());
									System.out.println("      * Test Step --  "+data.getMethodName());
								}else{
									stepExecute = "BLANK";
								}
							}
							
							int lastColumnNo = driverSheet.getRow(row).getLastCellNum();
							for (int col = 2; col <= lastColumnNo ; col++) {
								if(driverSheet.getRow(row).getCell(col) != null){
									if (driverSheet.getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_STRING
											|| driverSheet.getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_FORMULA) {
										String params = driverSheet.getRow(row).getCell(col).getStringCellValue();
										String [] paramsSplit = params.split(":");
										if (paramsSplit.length == 2) {
											System.out.println(paramsSplit[0] + " : " + paramsSplit[1]);
											paraTab.put(paramsSplit[0],paramsSplit[1]) ;
											if(data.getMethodName().toUpperCase().equals("SCRIPT_PARAM")){
												scriptParamsTable.put(paramsSplit[0],paramsSplit[1]) ;
											}
											paramKey[col-2] = paramsSplit[0];
											paramValue[col-2] = paramsSplit[1];
										}
									}
								}
							}
							
							Object[] paramListObject = new String[data.getMyParamList().size()];
							paramListObject = data.getMyParamList().toArray(paramListObject);

							try {
								String value = properties.getProperty(data.getMethodName());
							//	value == GEN_SITE; IndentCode; Region, SiteName; CREATE NEW SITE; Workflows.FB.Site.WF_CreateSite
							/*	myValues = { 	GEN_SITE; 0
												IndentCode, Region, SiteName; 1 
												CREATE NEW SITE;  2
												Workflows.FB.Site.WF_CreateSite 3
											}
							*/					 
								if (value != null) {
									String myValues[] = value.split(";");
									String reqParam[] = myValues[0].split(",");
									for (int i = 0; i < reqParam.length; i++) {
										reqParam[i] = reqParam[i].trim();
									}
									this.executeKeyword(reqParam,
											myValues[1].trim(),
											myValues[2].trim());
								}else if(stepExecute.equalsIgnoreCase("Y")) {
									this.executeKeyword();
								}else if (stepExecute.equalsIgnoreCase("BLANK")){
									System.out.println("This step is BLANK");
								}else {
									System.out.println("This step is N");
								}
							} catch (Exception e) {
								System.out.println(e.fillInStackTrace());
								failResultUpdate();
								statusUpdate();
								data.getLogger().log(data.getTable().printTableRowLeftPart(data.getMethodName(), data.getCurrentTime()));
								data.getLogger().log(data.getTable().printTableRowRightPart("FAIL", paramKey , paramValue));
							} finally {
								paramValue = new String [50];
								paramKey = new String [50];
								paraTab.putAll(scriptParamsTable);
							}
						}
					}else {
						data.getLogger().log(sheetName + "  is missing in testcase excel file...");
					}
				}
				paraTab.clear();
				scriptParamsTable.clear();
			}

		} catch (FileNotFoundException e) {
			data.getLogger().log("Test Case file not fould at " + Paths.masterExcelSheetPath);
			System.out.println(e.fillInStackTrace());
		} catch (IOException e) {
			data.getLogger().log("Test Case file IO Exception  " + Paths.masterExcelSheetPath);
			System.out.println(e.fillInStackTrace());
		} catch (InvalidFormatException e) {
			data.getLogger().log("Test Case file InvalidFormat Exception  " + Paths.masterExcelSheetPath);
			System.out.println(e.fillInStackTrace());
		} catch (NullPointerException e) {
			data.getLogger().log("Something Went wrong please check the stacktrace.... " + "\n");
			data.getLogger().log("TestCase file ( "+Paths.masterExcelSheetPath + " ) or the Execution sheet is missing.. ");
			System.out.println(e.fillInStackTrace());
		}
	}

	private void statusUpdate() {
		try {
			vpDetails.clear();
			vpDetailsAssertation.clear();
		} catch (Exception ex) {}
		if (!data.isSuccess()) {
			data.setExec("OK");
			data.setSuccess(true);
		}
	}

	void failResultUpdate() {
		data.setSuccess(false);
		data.setExec("FAILED");
	}

	private void resetTestDataIdentifiersAndValue() {
		data.setTestDataIdentifiers(null);
		data.setTestDataValues(null);
//		verificationflag = true;
		try {
			unregisterAll();
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
		}
	}	
	
	public void executeKeyword(String[] reqParam, String logModuleHeaderName, String mainExeScriptName) {
		String[] mytestDataValues = new String[reqParam.length];
		for (int i = 0; i < reqParam.length; i++) {
			mytestDataValues[i] = paraTab.get(reqParam[i].trim());
		}

		if (this.myTrigger(reqParam)) {
			Object[] objdataToPass = new Object[1];
			objdataToPass[0] = new String(logModuleHeaderName);
			logInfo(" *--------------   " + logModuleHeaderName + "   --------------* ");
			if (!logModuleHeaderName.contains("APPLICATION")) {
				data.getLogger().log(data.getTable().printTableRowLeftPart(logModuleHeaderName, data.getCurrentTime()));
			}
			if (stepExecute.equalsIgnoreCase("Y")) {
				try {
					callScript(mainExeScriptName, DEFAULT_ARGS,DP_SHARE_CURRENT_RECORD);
					if(vpDetailsAssertation.contains(false)){						
						data.getLogger().log(data.getTable().printTableRowRightPart("WAR", reqParam, mytestDataValues));
						for(int i = 0; i < vpDetailsAssertation.size();i++){
							if(!vpDetailsAssertation.get(i)){
								data.getLogger().log("		" + vpDetails.get(i) + ":"+ vpDetailsAssertation.get(i) +"\n");
							}
						}
					} else {
						data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), reqParam, mytestDataValues));
					}
				} catch (RunException ex) {
					failResultUpdate();
					logTestResult(logModuleHeaderName, false);
					System.out.println(ex.fillInStackTrace()+"\n");
					callScript("PB_2015_2.UnexpectedWindow.UnexpectedWindow", objdataToPass);
					data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), reqParam, mytestDataValues));
					for(int i = 0; i < vpDetailsAssertation.size();i++){
						if(!vpDetailsAssertation.get(i)){
							data.getLogger().log("		" + vpDetails.get(i) + ":"+ vpDetailsAssertation.get(i) +"\n");
						}
					}
					data.getLogger().log("		"+ex.fillInStackTrace().toString()+"\n");
					System.exit(0);

				} catch (Exception e) {
					failResultUpdate();
					logTestResult(logModuleHeaderName, false);
					System.out.println(e.fillInStackTrace()+"\n");
					callScript("PB_2015_2.UnexpectedWindow.UnexpectedWindow", objdataToPass);
					data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), reqParam, mytestDataValues));
					for(int i = 0; i < vpDetailsAssertation.size();i++){
						if(!vpDetailsAssertation.get(i)){
							data.getLogger().log("		" + vpDetails.get(i) + ":"+ vpDetailsAssertation.get(i) +"\n");
						}
					}
					data.getLogger().log("		"+e.fillInStackTrace().toString()+"\n");
				}
				
				/*for (String key : vpDetails.keySet()) {
				if (vpDetails.get(key).startsWith("false")){
					verificationflag = false;
					break;
				}
				for (String key : vpDetails.keySet()) {
					String value = vpDetails.get(key);
					if (value.startsWith("false")) {
						data.getLogger().log("		" +key + ":"+ vpDetails.get(key) +"\n");
					}
				}*/
				statusUpdate();
			} else {
				data.getLogger().log(data.getTable().printTableRowRightPart("SKIP", reqParam, mytestDataValues));
				statusUpdate();
			}
			resetTestDataIdentifiersAndValue();
		}else {
			data.getLogger().log("Missing required params");
			data.setExec("FAILED");
			data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), reqParam, mytestDataValues));
		}
	}

	public void openApplication() {
//		paramsTableREQ.put("AppName", paraTab.get("AppName"));
		Object[] objdataToPass = new Object[1];
		objdataToPass[0] = paraTab.get("AppName");

		try {
			callScript("PB_2015_2.StartApplications.StartOC4J", objdataToPass);
		} catch (Exception e) {
			e.printStackTrace();
			data.getLogger().log(
					"Unable to launch the application "
							+ paraTab.get("AppName")
							+ ", stoppting execution .. \n");
			data.getLogger().log(e.toString()+"\n");
			System.exit(0);
		}
	}

	protected void closeApp() {
		try {
			callScript("PB_2015_2.UnexpectedWindow.UnexpectedWindow");
			callScript("PB_2015_2.UnexpectedWindow.CloseMe");
			if (Const.pte != null) {
				if (Const.pte.isAlive()) {
					Const.pte.kill();
					sleep(20);
				}
			}
		} catch (NullPointerException ex) {
			System.out.println("Null Pointer exception in Close K/W");
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			sleep(20);
		}
	}

	public void executeKeyword() {
		try {
			Methods currentKeyword = Methods.valueOf(data.getMethodName().toUpperCase());
			switch (currentKeyword) {
			case OPEN:
				this.openApplication();
				break;

			case DEL_UDSV:
				this.executeKeyword(
						new String[] { "CIC", "NE_Type", "Region", "SiteName", "NE_TypeB", "RegionB", "SiteNameB" },
						"DELETE UDSV", "Workflows.FB.UDSV.DeleteUdsv");
//				if( SELECT_MODE )	callScript("Workflows.FB.UDSV.SelectUdsv", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
				break;

			case DEL_VBZ:
				this.executeKeyword(
						new String[] {"VBZ_Notes", "NE_Type", "Region", "SiteName", "NE_TypeB", "RegionB", "SiteNameB" },
						"DELETE VBZ", "Workflows.FB.VBZ.DeleteVBZ");
//				if( SELECT_MODE )	callScript("Workflows.FB.VBZ.SelectVBZ", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
				break;

			case DEL_SITE_ROOM:
				if ((dpString("RoomUsedFlag")!= null) && ((dpString("RoomUsedFlag")).toString()).equalsIgnoreCase("SELECTED") && 
						( Workflows.SQL.DbInstance.execEnv.equalsIgnoreCase("DA") || (dpString("RoomEVt") == null) || ((dpString("RoomEVt")).length() == 0) )){
					this.executeKeyword(
							new String[] {"RoomNo", "Region", "SiteName", "SiteRoom" },
							"DELETE SITE ROOM EQUIPMENT", "Workflows.FB.Site.SiteRoom.DeleteRoom" );
				}
				break;

			case CLOSE:
				this.closeApp();
				break;

			case SCRIPT_PARAM:
				data.setExec("OK");			
				data.setSuccess(true);
				String[] valus = scriptParamsTable.values().toArray(new String[scriptParamsTable.size()]);
				String[] keyz = scriptParamsTable.keySet().toArray(new String[scriptParamsTable.size()]);
				data.getLogger().log(data.getTable().printTableRowLeftPart(data.getMethodName(), data.getCurrentTime()));
				data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), keyz , valus));
				break;

			default: 
				System.out.println("in Default");
				failResultUpdate();
				statusUpdate();
				data.getLogger().log(data.getTable().printTableRowLeftPart(data.getMethodName(), data.getCurrentTime()));
				data.getLogger().log(data.getTable().printTableRowRightPart(data.getExec(), data.getTestDataIdentifiers(), data.getTestDataValues()));
			}
		} catch (Exception e) {
			callScript("PB_2015_2.UnexpectedWindow.UnexpectedWindow");
		}
	}	
}
