package PB_2015_2.Common;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.InternalFrameTestObject;
import com.rational.test.ft.object.interfaces.RootTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TextSelectGuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.value.RegularExpression;
import com.rational.test.ft.*;
import com.rational.test.ft.script.*;

public class Utils {

	static Random rand = new Random();
	static int i = 0;
	static int wait = Const.waitForElementDuration;
	static RootTestObject root = RootTestObject.getRootTestObject() ;
	
	static String[] regExp = { ".*.ProDialog", ".*.FreeResourcesInternalFrame",
			".*.GuiDialog", ".*.GuiInternalFrame", ".*.swing.JDialog",
			".*.fixedbuild.menu.BetrachtungsDatum",
			".*.presentation.WorkingDialog" ,".*.view.DialogLogin", ".*.view.mainframe.MainFrame"};

	public static String getCurrentDate(String dateFormatPattern) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	@SuppressWarnings("deprecation")
	public static String getDate(String dateFormatPattern, int plusDays) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
		Date date = new Date();
		date.setDate(date.getDate()+ plusDays);
		return dateFormat.format(date);
	}

	public static String createDir(String path) {
		try {
			(new File(path)).mkdirs();
		} catch (Exception e) {
		}
		return "";
	}
	
	public static String getRandomNoStr(int digits) {
		return Integer.toString(rand.nextInt(digits));
	}

	public static int getRandomNo(int digits) {
		return rand.nextInt(digits);
	}

	private static void waitForElement(GuiTestObject whichElement) {
		RationalTestScript.sleep(2);
		if (whichElement.exists()) {
			for (i = 0; i < wait && !whichElement.exists(); i++) {
				RationalTestScript.sleep(1);
			}
		}
	}

	public static void waitForElement(GuiTestObject whichElement, int myWait) {
		wait = myWait;
		waitForElement(whichElement);
	}
	
	public static String removeHtml(String message) {
		if (message.contains("<html>")) {
			message = message.replaceAll("<html>", "")
					.replaceAll("</html>", "");
		}
		return message;
	}
	
	public static String setAppVersion() {
		try {
			RegularExpression mainFrameClassRE = new RegularExpression(".*mainframe.MainFrame", false);
			RegularExpression mainFrameCaptionTextRE = new RegularExpression("PegaBase.*", false);
			TestObject[] dialog = root.find(SubitemFactory.atDescendant(".captionText",
					mainFrameCaptionTextRE, ".class", mainFrameClassRE));
			Const.APPVERSION = (String) dialog[0].getProperty(".captionText").toString();
			return Const.APPVERSION;
		} catch (Exception e) {
			return Const.APPVERSION;
		}
	}

	public static TestObject getObj(String prop1Key, String prop1Value,
			String prop2Key, String prop2Value) {
		TestObject[] allObj;
		if (prop2Key.isEmpty() && prop2Value.isEmpty()) {
			allObj = root.find(SubitemFactory
					.atDescendant(prop1Key, prop1Value));
		} else {
			allObj = root.find(SubitemFactory.atDescendant(prop1Key,
					prop1Value, prop2Key, prop2Value));
		}
		for (int i = 0; i < allObj.length; i++) {
			GuiTestObject firstObj = (GuiTestObject) allObj[i];
			try {
				if (firstObj.isShowing() && firstObj.exists()) {
					RationalTestScript.sleep(2);
					return firstObj;
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	public static TestObject getObj(String prop1Key, String prop1Value) {
		return getObj(prop1Key, prop1Value, "", "");
	}
	
	public static GuiTestObject getGuiTestObjByName(String prop1Value) {
		TestObject obj = getObj("name", prop1Value, "", "");
		if (obj != null)
			return (GuiTestObject) obj;
		return null;
	}

	public static GuiTestObject getGuiTestObjByToolTip(String prop1Value) {
		TestObject obj = getObj("toolTipText", prop1Value, "", "");
		if (obj != null)
			return (GuiTestObject) obj;
		return null;
	}
	
	public static TextGuiSubitemTestObject getTextGuiSubitemByToolTip(
			String prop1Value) {
		TestObject obj = getObj("toolTipText", prop1Value, "", "");
		if (obj != null)
			return (TextGuiSubitemTestObject) obj;
		return null;
	}
	
	public static TextGuiSubitemTestObject getTextGuiSubitemByName(String nameValue) {
		TestObject obj = getObj("name", nameValue, "", "");
		if (obj != null)
			return (TextGuiSubitemTestObject) obj;
		return null;
	}
	
	public static TextSelectGuiSubitemTestObject getListBoxByName(String nameValue) {
		TestObject obj = getObj("name", nameValue, "", "");
		if (obj != null)
			return (TextSelectGuiSubitemTestObject) obj;
		return null;
	}

	public static TestObject getObj(String prop1Key, String prop1Value,
			boolean isEnabled) {
		TestObject[] allObj = RationalTestScript.find(SubitemFactory
				.atDescendant(prop1Key, prop1Value));
		for (int i = 0; i < allObj.length; i++) {
			GuiTestObject firstObj = (GuiTestObject) allObj[i];
			try {
				if (isEnabled) {
					if (firstObj.isEnabled() && firstObj.isShowing()
							&& firstObj.exists()) {
						RationalTestScript.sleep(2);
						return firstObj;
					}
				} else {
					if (firstObj.isShowing() && firstObj.exists()) {
						RationalTestScript.sleep(2);
						return firstObj;
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	public static boolean waitForScreenToLoad(String whichScreen, int maxTimeinSec) {
		RationalTestScript.sleep(2);
//		System.out.println("Screen: '"+ whichScreen + "'");
		for (int i = 0; i < maxTimeinSec; i = i+5) {
			ArrayList<String> dialogTitles = getDialogTitles();
			for (int j = 0; j < dialogTitles.size(); j++) {
				if (dialogTitles.get(j).contains(whichScreen.toLowerCase())) {
//					System.out.println("Found:  '" + dialogTitles.get(j)+ "'");
					return true;
				}
			}
			RationalTestScript.sleep(5);
		}
		return false;
	}
	
	public static boolean waitForScreenToClose(String whichScreen, int maxTimeinSec) {
		RationalTestScript.sleep(5);
		boolean screenVisible = false;
		for (int i = 0; i < maxTimeinSec; i = i + 5) {
			ArrayList<String> dialogTitles = getDialogTitles();
			screenVisible = false;
			for (int j = 0; j < dialogTitles.size(); j++) {
				if (dialogTitles.get(j).contains(whichScreen.trim().toLowerCase())) {
//					System.out.println("Visible : "+dialogTitles.get(j));
					screenVisible = true;
					break;
				}
			}
			if (screenVisible) {
				RationalTestScript.sleep(5);
			} else {
				break;
			}
		}
		return screenVisible;
	}

	public static ArrayList<String> getDialogTitles() {
		ArrayList<String> dialogTitles = new ArrayList<>();
		for (int i = 0; i < regExp.length; i++) {
			RegularExpression dialogClassRE = new RegularExpression(regExp[i],
					false);
			TestObject[] dialogs = RationalTestScript.find(SubitemFactory
					.atDescendant(".class", dialogClassRE));
			for (int j = 0; j < dialogs.length; j++) {
				try {
					String temp = (String) dialogs[j].getProperty("title");
					dialogTitles.add(temp.trim().toLowerCase());
				} catch (Exception e) {
				}
			}
		}
		return dialogTitles;
	}

	public static void closeAll() {
		TestObject[] closeButtons = RationalTestScript.find(SubitemFactory
				.atDescendant("toolTipText", "Close [Alt+L]"));
		TestObject[] okButtons = RationalTestScript.find(SubitemFactory
				.atDescendant("accessibleContext.accessibleName", "OK", "name",
						"OptionPane.button"));
		if (okButtons.length >= 1) {
			for (int i = 0; i < okButtons.length; i++) {
				if (((GuiTestObject) okButtons[i]).isEnabled()
						&& ((GuiTestObject) okButtons[i]).isShowing()) {
					((GuiTestObject) okButtons[i]).click();
					RationalTestScript.sleep(2);
				}
				RationalTestScript.sleep(2);
			}
		}
		
		for (int i = 0; i < closeButtons.length; i++) {
			GuiTestObject abc = (GuiTestObject) closeButtons[i];
			if (abc.isEnabled() && abc.isShowing() && abc.exists()) {
				abc.click();
				RationalTestScript.sleep(2);
				TestObject[] noBtn = RationalTestScript.find(SubitemFactory
						.atDescendant("accessibleContext.accessibleName", "No",
								"name", "OptionPane.button"));
				if (noBtn.length >= 1) {
					((GuiTestObject) noBtn[0]).click();
				}
				RationalTestScript.sleep(2);
			}
		}
		for (int i = 0; i < regExp.length; i++) {
			RegularExpression dialogClassRE = new RegularExpression(regExp[i],
					false);
			TestObject[] dialogs = RationalTestScript.find(SubitemFactory
					.atDescendant(".class", dialogClassRE));
			for (int j = 0; j < dialogs.length; j++) {
				try {
					((InternalFrameTestObject) dialogs[j]).maximize();
				} catch (Exception e) {
				}
			}
		}
	}

	public static void inputKeys(String keys) {
		RegularExpression mainFrameClassRE = new RegularExpression(
				".*mainframe.MainFrame", false);
		RegularExpression mainFrameCaptionTextRE = new RegularExpression(
				"PegaBase /fixed & /build.*", false);
		TestObject[] dialog = root.find(SubitemFactory.atDescendant(
				".captionText", mainFrameCaptionTextRE, ".class",
				mainFrameClassRE));
		// "%{F4}"
		((TopLevelTestObject) dialog[0]).inputKeys(keys);
		RationalTestScript.sleep(2);
	}

	public static String executeUpdateQuery(String sqlQuery) {
		Driver driver = new oracle.jdbc.OracleDriver();
		Connection connection = null;
		Statement stmt = null;
		String dbName = getDBName();
		System.out.println(dbName);
		String retMessage = Const.SUCCESSMSG;
		boolean autoCommitStatus = false;
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.234.105:1521:" + dbName,
					"nedb_schema", "nepwdb");
			autoCommitStatus = connection.getAutoCommit();
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			int result = stmt.executeUpdate(sqlQuery);
			connection.commit();
			connection.setAutoCommit(autoCommitStatus);
			retMessage = result + " Rows updated";
			System.out.println("Update query executed properly Goodbye!");
		} catch (SQLException e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					retMessage = "Transaction is being rolled back";
					connection.rollback();
				} catch (SQLException excep) {
				}
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null) {
					connection.setAutoCommit(autoCommitStatus);
					connection.close();
				}
			} catch (SQLException se) {
				retMessage = Const.NOTSUCCESS;
			}
		}
		return retMessage;
	}

	public static String getDBName() {
		String testString = setAppVersion();
		return testString.substring(testString.indexOf("(") + 1,
				testString.indexOf(")"));
	}
	
	public static ResultSet executeSelectQuery(String sqlQuery) {
		Driver driver = new oracle.jdbc.OracleDriver();
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultset = null;

		// String dbName = "TSLPXM7";
		String dbName = getDBName();

		boolean autoCommitStatus = false;
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.234.105:1521:" + dbName,
					"nedb_schema", "nepwdb");
			autoCommitStatus = connection.getAutoCommit();
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(sqlQuery);
			connection.commit();
			connection.setAutoCommit(autoCommitStatus);
		} catch (SQLException e) {
			if (connection != null) {
				try {
					Verify.failDueToUnexpectedError("Transaction is being rolled back");
					connection.rollback();
				} catch (SQLException excep) {
				}
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null) {
					connection.setAutoCommit(autoCommitStatus);
					connection.close();
				}
			} catch (SQLException se) {
				Verify.failDueToUnexpectedError(Const.NOTSUCCESS);
			}
		}
		System.out.println("Update query executed properly Goodbye!");
		return resultset;
	}
}
