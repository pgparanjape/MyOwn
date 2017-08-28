package PB_2015_2.Main;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import JavaSources.Logging.FormatFB;
import JavaSources.Logging.Logger;

public class ScriptHandlerData {
	private Logger logger;
	private FormatFB table;
	private String[] testDataIdentifiers, testDataValues;
	private boolean success;
	private String exec;
	private String methodName;
	private List<Object> myParamList;
	List<String> myList = new ArrayList<String>();

/*	public ScriptHandlerData (Logger logger, FormatFB table,
			Converter conv, String[] testDataIdentifiers,
			String[] testDataValues, boolean success, String exec,
			int numFailure, int skip, boolean whileBreak) {
*/	
	public ScriptHandlerData(Logger logger, FormatFB table,
			String[] testDataIdentifiers, String[] testDataValues,
			boolean success, String exec) {
		this.logger = logger;
		this.table = table;
		this.testDataIdentifiers = testDataIdentifiers;
		this.testDataValues = testDataValues;
		this.success = success;
		this.exec = exec;
	}

	public ScriptHandlerData() {
	}

	public Logger getLogger() {
		return logger;
	}

	public FormatFB getTable() {
		return table;
	}

	public void setTable(FormatFB table) {
		this.table = table;
	}

	public String[] getTestDataIdentifiers() {
		return testDataIdentifiers;
	}

	public void setTestDataIdentifiers(String[] testDataIdentifiers) {
		this.testDataIdentifiers = testDataIdentifiers;
	}

	public String[] getTestDataValues() {
		return testDataValues;
	}

	public void setTestDataValues(String[] testDataValues) {
		this.testDataValues = testDataValues;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getExec() {
		return exec;
	}

	public void setExec(String exec) {
		this.exec = exec;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<Object> getMyParamList() {
		return myParamList;
	}

	public void setMyParamList(List<Object> myParamList) {
		this.myParamList = myParamList;
	}
	
	// Helper for Calendar Date output
	String getCurrentDate() {
		GregorianCalendar cal = new GregorianCalendar();
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

		String dateTime = df.format(cal.getTime());
		cal = null;
		return dateTime;
	}

	// Helper for Calendar Time output
	String getCurrentTime() {
		GregorianCalendar cal = new GregorianCalendar();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);
		String dateTime = df.format(cal.getTime());
		cal = null;
		return dateTime;
	}
}
