package PB_2015_2.Verification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import PB_2015_2.Common.Const;
import PB_2015_2.Main.ScriptHandler;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TopLevelTestObject;
import com.rational.test.ft.vp.IFtVerificationPoint;

public class Verify {
	
	private static boolean resultFlag;
	public static boolean isResultFlag() {
		return resultFlag;
	}
	public static boolean intVerification(int actual,int expected, String operation , String message) {
		switch(operation){
		case(">"): if (actual > expected){resultFlag = true;}else{resultFlag = false;}break;
		case("<"): if (actual < expected){resultFlag = true;}else{resultFlag = false;}break;
		case("Exactmatch"): if (actual == expected){resultFlag = true;}else{resultFlag = false;}break;
		case("="): if (actual == expected){resultFlag = true;}else{resultFlag = false;}break;
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag)+"@"+actual+"@"+expected);
		ScriptHandler.vpDetails.add(message +"@"+actual+"@"+expected);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message+":"+resultFlag +" '"+actual+"' "+ operation+"  Expected: '"+expected+"' ");
		return resultFlag;
	}
	public static boolean strVerification(String actual, String expected, String operation, String message) {
		switch(operation.toLowerCase()){
		case("exactmatch"): if (actual.equals(expected)){resultFlag = true;}else{resultFlag = false;}break;
		case("contains"): if (actual.toLowerCase().contains(expected.toLowerCase())){resultFlag = true;}else{resultFlag = false;}break;
		case ("regx"):
			if (actual.toLowerCase().matches(expected.toLowerCase())) {
				resultFlag = true;
			} else {
				resultFlag = false;
			}
			break;
		case ("nomatch"):if (actual.equalsIgnoreCase(expected)){resultFlag = false;}else{resultFlag = true;}break;
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag)+"@"+actualText+"@"+expectedText);
		ScriptHandler.vpDetails.add(message +"@"+actual+"@"+expected);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message + ":" + resultFlag + " Actual Text: '"
				+ actual + "'  Expected Text: '" + expected + "'  ");
		return resultFlag;
	}
	
	public static void booleanVerification(boolean actual, boolean expected, String message) {
		if (actual == expected) {
			resultFlag = true;
		} else {
			resultFlag = false;
		}
//		ScriptHandler.vpDetails.put(message,String.valueOf(resultFlag) + "@" + actual + "@" + expected);
		ScriptHandler.vpDetails.add(message +"@"+actual+"@"+expected);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message + ":" + resultFlag + " Actual : '" + actual
				+ "'  Expected : '" + expected + "'  ");
	}
	
	public static void arrayListVerification(ArrayList<String> baseArrayList,
			ArrayList<String> actualArrayList, String message) {
		try {
			Collections.sort(baseArrayList);
			Collections.sort(actualArrayList);
		} catch (Exception e) {}
		resultFlag = baseArrayList.equals(actualArrayList);
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(message +"@"+actualArrayList+"@"+baseArrayList);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message + ": " + resultFlag);
	}
	
	public static void arrayVerification(String[] actualArray,
			String[] expectedArray, String message) {
		if (actualArray.length == expectedArray.length) {
			for (int i = 0; i < expectedArray.length; i++) {
				if (expectedArray[i].equalsIgnoreCase(actualArray[i])) {
					resultFlag = true;
				} else {
					resultFlag = false;
					break;
				}
			}
		} else {
			resultFlag = false;
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(message +"@"+actualArray+"@"+expectedArray);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message + " " + resultFlag);
	}
	
	public static void arrayListWithValue(ArrayList<String> siteNameArray, String whatNeedsToCheck, String message) {
		Iterator <?> itr = siteNameArray.iterator();
		String temp = "";
		while (itr.hasNext()) {
			temp = itr.next().toString();
			if (!temp.startsWith(whatNeedsToCheck)) {
				resultFlag = false;
				break;
			}
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag)+"@"+whatNeedsToCheck+" "+ temp);
		ScriptHandler.vpDetails.add(message +"@"+whatNeedsToCheck+" "+ temp);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println("verification of NE list valus - "+message +" "+resultFlag);
	}
	
	public static void startDateInArray(ArrayList<String> siteNameArray, String comparetoWhatString, String message) {
		Iterator <?> itr = siteNameArray.iterator();
		while (itr.hasNext()) {
			String startDateString = itr.next().toString();
			DateFormat dfStartDate = new SimpleDateFormat("yyyy-mm-dd");
			DateFormat dfcomparetoWhat = new SimpleDateFormat("dd.mm.yyyy");
			java.util.Date startDate, comparetoWhatDate;
			try {
				startDate = dfStartDate.parse(startDateString);
				comparetoWhatDate = dfcomparetoWhat.parse(comparetoWhatString);

				if (startDateString.isEmpty()&& startDate.compareTo(comparetoWhatDate) <= 0) {
					resultFlag = false;
					break;
				}
			} catch (Exception e) {}
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag)+"@"+comparetoWhatString);
		ScriptHandler.vpDetails.add(message +"@"+comparetoWhatString);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println("verification of start date NE list valus - "+message +" "+resultFlag);
	}
	
	public static void arrayListWithValue(ArrayList<String> whichArrayList,
			String expectedString, String operation, String message) {
		resultFlag = true;
		Iterator<String> itr = whichArrayList.iterator();
		if (operation.equalsIgnoreCase(Const.EXACTMATCH)) {
			while (itr.hasNext()) {
				String temp = (String)itr.next();
				if (temp != null) {
					if (!temp.equalsIgnoreCase(expectedString)) {
						System.out.println("***** Failed " + temp);
						resultFlag = false;
						break;
					}
				}
			}
		} else if (operation.equalsIgnoreCase(Const.STARTS_WITH)) {
			while (itr.hasNext()) {
				String temp = itr.next().toString().toLowerCase();
				if (temp != null) {
					if (!temp.startsWith(expectedString.toLowerCase())) {
						System.out.println("***** Failed " + temp);
						resultFlag = false;
						break;
					}
				}
			}
		} else if (operation.equalsIgnoreCase(Const.REGX)) {
			while (itr.hasNext()) {
				String temp = itr.next().toString().toLowerCase();
				if (temp != null) {
					if (!temp.matches(expectedString.toLowerCase())) {
						System.out.println("***** Failed " + temp);
						resultFlag = false;
						break;
					}
				}
			}
		}else if (operation.equalsIgnoreCase(Const.CONTAINS)) {
			while (itr.hasNext()) {
				String temp = itr.next().toString().toLowerCase();
				if (temp != null) {
					if (!temp.contains(expectedString.toLowerCase())) {
						System.out.println("***** Failed " + temp);
						resultFlag = false;
						break;
					}
				}
			}
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag)+"@"+expectedString);
		ScriptHandler.vpDetails.add(message +"@"+expectedString);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message + " : " + resultFlag);
	}
	
	public static void arrayListWithValues(ArrayList<String> whichArrayList,
			String expectedString, int howManyTimes, String message) {
		resultFlag = true;
		int counter = 0;
		Iterator<String> itr = whichArrayList.iterator();
		while (itr.hasNext()) {
			try {
				String temp = itr.next().toString();
				if (temp.equalsIgnoreCase(expectedString)) {
					counter++;
				}
			} catch (Exception e) {}
		}
		if (counter == howManyTimes) {
			resultFlag = true;
		} else {
			resultFlag = false;
		}

//		ScriptHandler.vpDetails.put(message,String.valueOf(resultFlag) + "@" + expectedString);
		ScriptHandler.vpDetails.add(message +"@" + expectedString);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message+ ":" + resultFlag + "  Expected: '" +howManyTimes+"'"+ "  Actual: '"+counter+"'");
	}

	public static void arrayContains(String what, String[] whichArray,
			int noOfTime, String message) {
		int counter = 0;
		for (int i = 0; i < whichArray.length; i++) {
			if (whichArray[i].equalsIgnoreCase(what)) {
				counter++;
			}
		}
		if (counter == noOfTime){
			resultFlag = true;
		}else{
			resultFlag = false;
		}
//		ScriptHandler.vpDetails.put(message, String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(message);
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(message+ " : " + resultFlag + "  Expected: '" +noOfTime+"'"+ "  Actual: '"+counter+"'");
	}
	
	public static void arrayContains (String date, String[] startDate) {
		for (int i = 0; i < startDate.length; i++) {
			if (!startDate[i].equalsIgnoreCase(date)) {
				resultFlag = false;
				System.out.println("verification with start date - Expected: '" +date+"'"+ "  Actual: '"+startDate[i]+"'" );
				break;
			}
		}
//		ScriptHandler.vpDetails.put("verification with start date", String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add("verification with start date");
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println("verification with start date :   " + resultFlag);
	}
	
	public static void withVP(IFtVerificationPoint vp) {
		resultFlag = vp.performTest();
//		ScriptHandler.vpDetails.put(vp.getVPName(), String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(vp.getVPName());
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(vp.getVPName() + "  " + resultFlag);
	}

	public static void withVP(GuiTestObject testObj, IFtVerificationPoint vp) {
		resultFlag = testObj.performTest(vp);
//		ScriptHandler.vpDetails.put(vp.getVPName(), String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(vp.getVPName());
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(vp.getVPName() + "  " + resultFlag);
	}
	
	public static void withVP(GuiSubitemTestObject testObj,IFtVerificationPoint vp) {
		resultFlag = testObj.performTest(vp);
//		ScriptHandler.vpDetails.put(vp.getVPName(), String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(vp.getVPName());
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(vp.getVPName() + "  " + resultFlag);
	}
	
	public static void withVP(TopLevelTestObject testObj,IFtVerificationPoint vp) {
		resultFlag = testObj.performTest(vp);
//		ScriptHandler.vpDetails.put(vp.getVPName(), String.valueOf(resultFlag));
		ScriptHandler.vpDetails.add(vp.getVPName());
		ScriptHandler.vpDetailsAssertation.add(resultFlag);
		System.out.println(vp.getVPName() + "  " + resultFlag);
	}

	public static void failDueToUnexpectedError(String errorMessage) {
		if (errorMessage.length() > 130) {
			errorMessage = errorMessage.subSequence(0, 130).toString();
		}
//		ScriptHandler.vpDetails.put(errorMessage, String.valueOf(false));
		ScriptHandler.vpDetails.add(errorMessage);
		ScriptHandler.vpDetailsAssertation.add(false);
		System.out.println("Fail : " + errorMessage);
	}

	public static void hashTables(Hashtable<String, String> actualHashTable,
			Hashtable<String, String> expectedHashTable, String message) {
		if (actualHashTable.equals(expectedHashTable)) {
			System.out.println("Both are equal ");
			/*ScriptHandler.vpDetails.put(message
					+ " Bothe valus are correct in hash Table ",
					String.valueOf(true));*/
			ScriptHandler.vpDetails.add(message+ " Bothe valus are correct in hash Table ");
			ScriptHandler.vpDetailsAssertation.add(true);
		} else{
			for (Map.Entry<String, String> entry : expectedHashTable.entrySet()) {
				String actualAnswer = actualHashTable.get(entry.getKey());
				String expectedAnswer = entry.getValue();
				if (expectedAnswer != null && actualAnswer != null
						&& expectedAnswer.equals(actualAnswer)) {

				} else {
					/*ScriptHandler.vpDetails.put(message + " "
							+ "Actual Value'" + actualAnswer
							+ "' Expected Value'" + expectedAnswer + "'",
							String.valueOf(false));*/
					ScriptHandler.vpDetails.add(message + " "
							+ "Actual Value'" + actualAnswer
							+ "' Expected Value'" + expectedAnswer + "'");
					ScriptHandler.vpDetailsAssertation.add(false);
					System.out.println("Hash Table Verification fail " + entry.getKey() + "  "
							+ actualAnswer + "  " + expectedAnswer);
				}
			}
		}
	}
	
	public static void pass(String message) {
		ScriptHandler.vpDetails.add(message);
		ScriptHandler.vpDetailsAssertation.add(true);
//		ScriptHandler.vpDetails.put(message, String.valueOf(true));		
	}
	public static void hashMap(
			HashMap<String, String> actualHashTable,
			HashMap<String, String> expectedHashTable, String message) {

		if (actualHashTable.equals(expectedHashTable)) {
			System.out.println("Both are equal ");
			/*ScriptHandler.vpDetails.put(message
					+ " Bothe valus are correct in hash Table ",
					String.valueOf(true));*/
			ScriptHandler.vpDetails.add(message+ " Bothe valus are correct in hash Table ");
			ScriptHandler.vpDetailsAssertation.add(true);
		} else{
			for (Map.Entry<String, String> entry : expectedHashTable.entrySet()) {
				String actual = actualHashTable.get(entry.getKey());
				String expected = entry.getValue();
				boolean condition = expected != null && actual != null && expected.equals(actual);
				if (!condition) {
					/*ScriptHandler.vpDetails.put(message
							+ " map Key: '" + entry.getKey() + "' "
							+ "Actual Value'" + actual + "' Expected Value'"
							+ expected + "'", String.valueOf(false));*/
					ScriptHandler.vpDetails.add(message
							+ " map Key: '" + entry.getKey() + "' "
							+ "Actual Value'" + actual + "' Expected Value'"
							+ expected + "'");
					ScriptHandler.vpDetailsAssertation.add(false);
					System.out.println("Hash Table Verification fail "
							+ message + entry.getKey() + "  '" + actual
							+ "'  '" + expected + "'");
				}
			}
		}
	}
}
