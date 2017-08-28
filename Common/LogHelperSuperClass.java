package PB_2015_2.Common;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import PB_2015_2.Verification.Verify;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.ITestObjectMethodState;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.vp.IFtVerificationPoint;
import com.rational.test.ft.vp.ITestData;

/**
 * Description   : This helper super class is an exmaple of using log4j to log the results of a test script.  
 * Log4j is initialized from config\log4jExample2.properties.  All log messages are written to the file
 * logs\log.txt.  An overall test script result is written to the file logs\result.txt.
 * 
 * @author Larry Quesada
 * @since  September 28, 2008
 */

public abstract class LogHelperSuperClass extends RationalTestScript
{
	/**
	 * The following set of constants are used for tracking the overall 
	 * verdict of a test script.
	 */
	private static final int PASS_INT = 0;
	private static final int FAIL_INT = 1;
	private static final int WARN_INT = 2;	
	private static String PASS_STRING = "PASS";
	private static String FAIL_STRING = "FAIL";
	private static String WARN_STRING = "WARN";

	/**
	 * The overall verdict of the test script.
	 */
	public int verdict = PASS_INT;

	/**
	 * Used to log the build of the application under test.  
	 * Set from the first passed in script argument.
	 */
	private static String build = "unspecified";
	

	/**
	 * When false, log4j has not been initialized.
	 */
	private  static boolean log4jInitialized = false;
	
	/**
	 * Description	:	This method is called automatically at the start of every script.  Initialize log4j,
	 * if not already completed.  Log the start of the script.  Set the build, if it was passed into the 
	 * script (first argument). 
	 */
	public void onInitialize()
	{
		//Read log4j property file, if not already completed.
		if(!log4jInitialized)
		{
			log4jInitialized = true; 
			DOMConfigurator.configure("config\\log4jExample4.xml");
		}
		
		//Log the start of the test script.
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Starting Script!");
		
		//Set the build to be the first parameter.  If no parameter, use the default (unspecified)
		Object [] args = getScriptArgs();
		if(args.length > 0)
			build = (String) args[0];
	}

	/**
	 * Description: This method is called automatically at the end of every script.  Log the test result and
	 * log the end of the script.
	 */
	public void onTerminate()
	{
		//Get the results Logger.
		Logger resultsLog = Logger.getLogger("testResult." + getScriptName());
		
		//Log the result based upon the verdict.
		if(verdict == PASS_INT)
		{
			if(Verify.isResultFlag()){
			resultsLog.info(build + "," + PASS_STRING);
			}else {
				resultsLog.error(build + "," + FAIL_STRING);			
			}
			
		}
		else if(verdict == WARN_INT)
		{
			resultsLog.warn(build + "," + WARN_STRING);						
		}
		else
		{
			resultsLog.error(build + "," + FAIL_STRING);			
		}
		
		//Log the end of the script.
		Logger logger = Logger.getLogger(getScriptName());		
		logger.info("Script Ended!");
	}

	/**
	 * Description:  Called when a verification point fails to complete successfully.  Log a failure and set
	 * the verdict of the test script to FAIL_INT.
	 */
	public void onVpFailure(IFtVerificationPoint vp) 
	{
		//Set verdict.
		verdict = FAIL_INT;
		
		//Get logger.  
		Logger logger = Logger.getLogger(getScriptName() + "." + vp.getVPName());
				

		//Get the expected data.
		Object expected =  vp.getExpectedData();
		String expectedString;

		//The expected data may be a string if the VP is from vpManual.
		if(expected.getClass().toString().contains("String"))
			expectedString = (String) expected;
		else
		{
			//Extract expected data.
			ITestData expectedTestData = (ITestData) expected;
			expectedString = expectedTestData.getData().toString();
		}

		//Get actual data.
		Object actual =  vp.getActualData();		
		String actualString;

		//Actual data may be a string if the VP is from vpManual.
		if(actual.getClass().toString().contains("String"))
			actualString = (String) actual;
		else
		{
			//Extract actual data.
			ITestData actualTestData = (ITestData) actual;
			actualString = actualTestData.getData().toString();
		}

		//Log VP failure.
		logger.error("FAIL on line " + getLineNumber());
		logger.error("expected [" + 
				expectedString + "]");
		logger.error("actual [" + 
				actualString + "]");
		
		super.onVpFailure(vp);
	}

	/** 
	 * Description: This method is called whenever an object is found, but the recognition score is higher
	 * than the warning threshold.  Set the verdict to WARN_INT and log the error.
	 */
	public void onRecognitionWarning(ITestObjectMethodState testObjectMethodState, TestObject foundObject, int score)
	{
		//Do not reset verdict, if test script already has a failure.
		if (verdict == PASS_INT)
			verdict = WARN_INT;
		
		Logger logger = Logger.getLogger(getScriptName());
		logger.warn("WARNING on line " + getLineNumber());
		logger.warn("Weak recognition for [" + foundObject.getProperty("name") + "] with a score of [" + score + "]");
		
		super.onRecognitionWarning(testObjectMethodState, foundObject, score);
	}

	/**
	 * Description: This method is called when a test object can not be found.  Set the verdict of the test
	 * script to FAIL_INT and log the error.
	 */
	public void onObjectNotFound(ITestObjectMethodState testObjectMethodState)
	{
		verdict = FAIL_INT;
		Logger logger = Logger.getLogger(getScriptName());
		logger.error("Object not found at line " + getLineNumber() + ".  ");		
		
		super.onObjectNotFound(testObjectMethodState);
	}
	
	/** 
	 * Description: This method is called whenever an exception is thrown from testMain.  Set the verdict of the test
	 * script to FAIL_INT and log the error.
	 */
	public boolean onUnhandledException(java.lang.Throwable e)
	{
		verdict = FAIL_INT;
		Logger logger = Logger.getLogger(getScriptName());
		logger.error("Call Script exception at line " + getLineNumber() + ": " + e.getMessage());
		return super.onUnhandledException(e);
	}
	
	/**
	 * Description: This method is called whenever RFT is looking for an object and finds 2 that match.  Set
	 * the verdict to FAIL_INT and log the error.
	 */
	public void onAmbiguousRecognition(ITestObjectMethodState testObjectMethodState, TestObject[] choices, int[] scores)
	{
		verdict = FAIL_INT;
		
		Logger logger = Logger.getLogger(getScriptName());
		logger.error("Ambigious Recognition at line " + getLineNumber());
		for(int i=0;i<choices.length;i++)
			logger.error("Test Object " + i + ": " + choices[i].getProperty("name") + " score: " + scores[i]);
		
		super.onAmbiguousRecognition(testObjectMethodState, choices, scores);
	}

	/** 
	 * Description: This method is called whenever an exception from callScript.  Set the verdict of the test
	 * script to FAIL_INT and log the error.
	 */
	public boolean onCallScriptException(java.lang.RuntimeException e) 
	{
		verdict = FAIL_INT;
		Logger logger = Logger.getLogger(getScriptName());
		logger.error("Call Script exception at line " + getLineNumber() + ": " + e.getMessage());
		
		return super.onCallScriptException(e);
	}
	
	/**
	 * Description: Log the start of a verification point, and then pass to the API.
	 */
	protected  IFtVerificationPoint vp(java.lang.String vpName, TestObject anchor)
	{
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Testing vp [" + vpName + "]");
		
		return super.vp(vpName,anchor);
	}

	/**
	 * Description: Log the start of a verification point, and then pass to the API.
	 */
	protected  IFtVerificationPoint vpDynamic(java.lang.String vpName)
	{
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Testing vp [" + vpName + "]");
		
		return super.vpDynamic(vpName);
	}

	/**
	 * Description: Log the start of a verification point, and then pass to the API.
	 */
	protected  IFtVerificationPoint vpDynamic(java.lang.String vpName, TestObject objectUnderTest)
	{
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Testing vp [" + vpName + "]");
		
		return super.vpDynamic(vpName,objectUnderTest);
	}
	
	/**
	 * Description: Log the start of a verification point, and then pass to the API.
	 */	
	protected  IFtVerificationPoint vpManual(java.lang.String vpName, java.lang.Object actual)
	{
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Testing vp [" + vpName + "]");
		
		return super.vpManual(vpName, actual);
	}

	/**
	 * Description: Log the start of a verification point, and then pass to the API.
	 */
	protected  IFtVerificationPoint vpManual(java.lang.String vpName, java.lang.Object expected, java.lang.Object actual)
	{
		Logger logger = Logger.getLogger(getScriptName());
		logger.info("Testing vp [" + vpName + "]");
		
		return super.vpManual(vpName,expected,actual);
	}
}