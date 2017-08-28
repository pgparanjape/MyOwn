package PB_2015_2.Common;

import com.rational.test.ft.object.interfaces.ProcessTestObject;

public final class Const {
	
	public static ProcessTestObject pte = null;
	public static String	defaultPLD						= "01.01.2008";
	
	public static String genGSM = "GSM";
	public static String genUMTS = "UMTS";
	public static String genLTE = "LTE";
	
//	public static String INDENTCODE = "KY0604";
	public static String INDENTCODE = "ky2151";
	public static String REGION_PREFIX_KLN = "KLN";
	public static String SITE = "Siegen 41";
	public static String SITE_CITY = "Siegen";
	public static String SITE_EXT_INDENT_CODE= "1520791";
	public static String SITE_VPSZ= "49/271/81";
//	public static String planningType = "004V020H020_K";
	public static String planningType = "DX6FV_A";
	public static String rollOutPlanDate = "2015";
	public static String BLANK = "";
//	public static String _JDialogclassName = "javax.swing.JDialog";
//	public static String buttonClassName = "com.tmobile.itnetdev.guicomponents.components.PButton";
	public static String APPVERSION = "2015.2";
	public static String deleteTVSuccessful = "Time varient deletion succesful";
	public static String PLANNINGSTARTDATE = "06072015";
	public static int 	 NE_ROWNO = 2;
	public static int 	 waitForElementDuration = 20;

//	Radio
	public static String bandGSM900 = "GSM900";
	public static String bandLTE900 = "LTE900";
	public static String bandLTE800 = "LTE800";
	public static String LINK_TYPE_ANTENNA = "Antenna";
	public static String tvDeletationErrorMessage = "Antenna Assignment exists for this time variant, so the time variant cannot be deleted.";

// generic	
	public static String SUCCESSMSG = "Operation Successful";
	public static String NOTSUCCESS = "Operation NOT Succesful";
	public static boolean suppressError = false;
	public static final String EXACTMATCH = "Exactmatch";
	public static final String NOMATCH = "NoMatch";
	public static String CONTAINS = "Contains";
	public static String REGX = "Regx";
	public static final String STARTS_WITH = "StartsWith";
	public static String CHANGESSAVED = "Changes saved!";

	public static final String USERNAME = "test_admin";
	public static final String PASSWORD = "MyPw4Env";
	public static final String DURATION = "Dauer:";
}
