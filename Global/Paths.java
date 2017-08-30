package Global;

import PB_2015_2.Common.Utils;


public class Paths {

	static String currentTime = Utils.getCurrentDate("HH_mm");
	static String currentDate = Utils.getCurrentDate("dd-MM-yy");

	static String masterExcelSheetName = "TestCases.xls";
	public static String baseFilePath = "C:\\PegaBase\\Workspace";
	public static String logFilePath = baseFilePath + "\\LOG\\" + currentDate;
	
	//New Framework support
	public static String	DetailedLogFullFileName	= logFilePath + "\\detailed_"+currentTime+".log";
	public static String	LogFullFileName			= logFilePath + "\\ExecutionReport.log";
	public static String 	KeywordMapPropPath 		= baseFilePath + "\\PB_Project_New\\keywordMapper.properties";
	public static String 	masterExcelSheetPath 	= baseFilePath + "\\PB_Project_New\\"+masterExcelSheetName;
	public static String 	screenGrabFolderPath 	= logFilePath + "\\ScreenGrab\\";

	// Old Framework support
	public static String	LogFullFileNameRadio	= logFilePath + "\\testBN_R2015.1_OC4J.log"; 
	public static String 	ScriptPath				= baseFilePath + "\\ext\\sql_sources\\";
	public static String	ExportPath				= baseFilePath + "\\PB_Project_New\\ImportExport";   
	// Preferences->tab "Fixed&Build"->PU-Expotz-Pfad
	public static String	ExportFileNameIP		= "WLAN_IP.csv";   
	public static String	ImportPath				= baseFilePath + "\\PB_Project_New\\ImportExport";
	public static String	ImportFileNameNeGroup	= "importCompInNeGroup.csv";
}
