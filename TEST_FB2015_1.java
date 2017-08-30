package Tests;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import resources.Tests.TEST_FB2015_1Helper;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

import JavaSources.Logging.FormatFB;
import JavaSources.Logging.Logger;
import JavaSources.utilities.Converter;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

/**
 * Description   : Functional Test Script
 * @author earslan
 */
public class TEST_FB2015_1 extends TEST_FB2015_1Helper
{
	/**
	 * Script Name   : <b>TEST_FB2015_1</b>
	 * Generated     : <b>25.03.2015 15:36:06</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2015/03/25
	 * @author earslan
	 */
	final static boolean	rt						= true;		// TRUE := Real Test!	
	static boolean			SELECT_MODE				= false;		// TRUE := instead of the respective DELETE operation the SELECT op. will be perormed 
	final static int 		MAX_OP_NUM				= 999999;	

	static boolean 		startFlag				= true;	 	// damit nur bei der 1. Iter. START>>> ausgegeben wird!	
	/**********************************************************/

	static int 			startiter				= 0;
	static int 			iter					= 0;
	static int 			skipFirstTime			= 0;
	static int 			skipAllTime				= 0;
	static int 			maxOpNo					= MAX_OP_NUM;


    final static int 	LINE_OFFSET				= 0;	
    final static int 	START_ADD				= 20 + LINE_OFFSET;	
    final static int 	START_REMOVE			= 40 + LINE_OFFSET;	
    final static int 	START_DELETE			= 70 + LINE_OFFSET;	
	/**********************************************************/
    final static int 	SET_PREF_EXPORT_PATH	= 1;

    final static int 	GEN_SITE				= 2;
    final static int 	CHECK_SITE_IS_CREATED	= 3;
    final static int 	GEN_SITE_ALTER			= 4;
    final static int 	GEN_SITE_ROOM			= 5;
    final static int 	GEN_DF					= 6;
    final static int 	GEN_NE					= 7;
    final static int 	GEN_PROJ				= 8;
    final static int 	GEN_VBZ					= 9;
    final static int 	GEN_UDSV				= 10;
    final static int 	GEN_LINE				= 11;

    final static int 	GEN_CONFIG				= 12;
    final static int 	EXTEND_CONFIG			= 13;
    final static int 	ADD_PARAM_TO_CONFIG		= 14;
    final static int 	ADD_COMP_TO_CONFIG		= 15;
	
    final static int 	ADD_NE_TO_NE_GROUP		= 18;
    final static int 	EXPORT_IP_ADDR			= 19;
    final static int 	DEL_IP_ADDR_IN_NE		= 20;
    final static int 	VER_IP_ADDR_DELETED		= 21;
    final static int 	IMPORT_IP_ADDR			= 22;
    final static int 	VER_IP_ADDR_IMPORTED	= 23;
	
    final static int 	CHANGE_CONF				= 24;
    final static int 	IMPORT_COMP_IN_NE_GROUP	= -1; // 25;

    final static int 	ATTACH_NE_TO_DF			= -26;
    final static int 	TERMIN_LINE_ON_EP		= 27;
    final static int 	TERMIN_LINE_ON_DF		= 28;
    final static int 	TERMIN_LINE_ON_NE		= 29;
    final static int 	ASSIGN_LINE_TO_UDSV		= 30;
    final static int 	RACK_PLANNING_ASSIGN	= 31;

	final static int 	EDIT_VBZ				= 32;
	final static int 	NEW_BUNDLE				= 33;

    final static int 	RACK_PLANNING_REMOVE	= 34;
    final static int 	REMOVE_LINE_FROM_UDSV	= 35;
    final static int 	REMOVE_LINE_TERM_ON_NE  = 36;
    final static int 	REMOVE_LINE_TERM_ON_DF  = 37;
    final static int 	REMOVE_LINE_TERM_ON_EP  = 38;
    final static int 	DETACH_NE_FROM_DF		= 39;
    final static int 	REM_NE_FROM_NE_GROUP	= 40;

    final static int 	REEXCHANGE_CONF			= -1; // 41; //  muss noch implementiert werden (1:1 übernehmen, Konfigurationen vertauschen, Datensatz unter dem von REMOVE NE FROM NE_GROUP)
	
    final static int 	REMOVE_BUNDLE			= 50;

    final static int 	DEL_LINE				= 79;
    final static int 	DEL_UDSV				= 80;
    final static int 	DEL_VBZ					= 81;
    final static int 	DEL_PROJ				= 82;
    final static int 	DEL_NE					= 83;
    final static int 	DEL_DF					= -84;
    final static int 	DEL_SITE_ROOM			= 85;
    final static int 	DEL_SITE_ALTER			= 86;
    final static int 	DEACTIVATE_SITE			= 87;
    final static int 	DEL_SITE				= 88;
    final static int 	CHECK_SITE_IS_DELETED	= 89;
    final static int 	REM_PARAM_FROM_CONFIG	= 90;
    final static int 	REM_COMP_FROM_CONFIG	= 91;
    final static int 	DEL_CONFIG				= 92;
	
	/**********************************************************/
	
	Logger 		logger		= new Logger();
	FormatFB 	table		= new FormatFB();
	Converter	conv		= new Converter();
	
	// Helper for Calendar Date output
	private void printCurrentDate()
	{
		GregorianCalendar cal	= new GregorianCalendar();
		DateFormat df;
	    
		df = DateFormat.getDateInstance(DateFormat.LONG);
		logger.log("date: " + df.format(cal.getTime()));
	    cal		= null;
	}
	
	// Helper for Calendar Time output
	private String printCurrentTime()
	{
		GregorianCalendar cal	= new GregorianCalendar();
	    DateFormat df;
	    String	time			= "";
	    
	    df = DateFormat.getTimeInstance(DateFormat.LONG);
	    time			= df.format(cal.getTime());
	    cal		= null;
	    return	time;
	}

	
	// Trigger - conditional operation starter
	// 
	// firstRow: 		-1 			(1. range)
	// lastRow:  		-1 			(4. range)
	// dataCheckDefault: 	null 			(if complete data check expression in main function)
	// dataCheckDefault: 	array of checkData	(all or part of the check data for default expression)
	// dataCheckSpecific:	data check expression	(complete or part of the specific data check expression in main function)
	// dataCheckSpecific:	true			(if data check expression is complete default)	
	//
	//									[-1]		[-1]		
	private boolean		trigger( int firstRow, int lastRow, int opSkipper, int opNumber, int maxOpNum, String[] dataCheckDefault, boolean dataCheckSpecific )	
	{
		int 	i;
		boolean	condition		= false;
		
		if( (firstRow == -1) && (lastRow == START_ADD) )					{
			if( iter < lastRow )
				condition		= true;
		}
		else if( (firstRow == START_ADD) && (lastRow == START_REMOVE) )		{	
			if( (iter >= firstRow) && (iter < lastRow) )
				condition		= true;
		}
		else if( (firstRow == START_REMOVE) && (lastRow == START_DELETE) )	{	
			if( (iter >= firstRow) && (iter < lastRow) )
				condition		= true;
		}
		else if( (firstRow == START_DELETE) && (lastRow == -1) )					{
			if( iter >= firstRow )
				condition		= true;
		}
		else
			;
		
		condition	= condition && (opSkipper < opNumber) && (maxOpNum >= opNumber);
		
		if( dataCheckDefault != null )					{

			for(i=0; i<dataCheckDefault.length; i++)	{
				if( dataCheckDefault[i].substring(0, 1).equalsIgnoreCase("!") )
					condition	= condition && ( (dpString(dataCheckDefault[i].substring(1)) == null) || 
											((dpString(dataCheckDefault[i].substring(1))).length() == 0) );
				else
					condition	= condition && (dpString(dataCheckDefault[i]) != null) && ((dpString(dataCheckDefault[i])).length() > 0);
			}
		}
		condition	= condition && dataCheckSpecific;

		return condition;
	}
	
	
	
	
	// Helper for calculating Error Code
	private int getErrorCode(boolean success, int opNo)
	{
		int	retVal		= opNo * 10;
		
		switch( opNo )			{
		case GEN_SITE: 				retVal = retVal + Workflows.FB.Site.WF_CreateSite.errorCode;  										break;
		case GEN_NE: 				retVal = retVal + Workflows.FB.NE_WFs.WF_CreateNEwithRoomNo.errorCode;  							break;
		case GEN_PROJ: 				retVal = retVal + Workflows.FB.Projects.WF_CreateProject.errorCode;  								break;
		case GEN_VBZ: 				retVal = retVal + Workflows.FB.VBZ.WF_CreateVBZ.errorCode;  										break;
		case EDIT_VBZ: 				retVal = retVal + Workflows.FB.VBZ.WF_EditVBZ.errorCode;  											break;
//		case NEW_BUNDLE:			retVal = retVal + Workflows.FB.VBZ.Bundles.WF_NewBundle.errorCode;  											break;
		case GEN_UDSV: 				retVal = retVal + Workflows.FB.UDSV.WF_CreateUDSV.errorCode;  										break;
		case GEN_CONFIG: 			retVal = retVal + Workflows.FB.Administration.Config_Comp.WF_CreateConfigComp.errorCode;  			break;
		case EXTEND_CONFIG: 		retVal = retVal + Workflows.FB.Administration.Config_Comp.WF_ExtendConfigComp.errorCode;  			break;
		case ATTACH_NE_TO_DF: 		retVal = retVal + Workflows.FB.NE_WFs.AttachToComponent.WF_AttachPin.errorCode;  					break;
		case DETACH_NE_FROM_DF: 	retVal = retVal + Workflows.FB.NE_WFs.AttachToComponent.WF_DetachPin.errorCode;  					break;
		case CHECK_SITE_IS_DELETED: retVal = retVal + Workflows.FB.Site.CheckNoSiteExists.errorCode;  									break;
		case CHECK_SITE_IS_CREATED: retVal = retVal + Workflows.FB.Site.CheckSiteExists.errorCode;  									break;

		default: 
			if( !success )		retVal = retVal + 1;
		}
		
	    return	retVal;
	}
	
	// Error Code resetter (has to be performed immediately after read out error code)
	private void resetErrorCode(int opNo)
	{
		switch( opNo )			{
		case GEN_SITE: 				Workflows.FB.Site.WF_CreateSite.errorCode										= 0; break;
		case GEN_NE: 				Workflows.FB.NE_WFs.WF_CreateNEwithRoomNo.errorCode								= 0; break;
		case GEN_PROJ:				Workflows.FB.Projects.WF_CreateProject.errorCode								= 0; break;
		case GEN_VBZ: 				Workflows.FB.VBZ.WF_CreateVBZ.errorCode											= 0; break;
		case EDIT_VBZ: 				Workflows.FB.VBZ.WF_EditVBZ.errorCode											= 0; break;
//		case NEW_BUNDLE: 			Workflows.FB.VBZ.Bundles.WF_NewBundle.errorCode									= 0; break;		
		case GEN_UDSV: 				Workflows.FB.UDSV.WF_CreateUDSV.errorCode										= 0; break;
		case GEN_CONFIG:			Workflows.FB.Administration.Config_Comp.WF_CreateConfigComp.errorCode			= 0; break;
		case EXTEND_CONFIG:			Workflows.FB.Administration.Config_Comp.WF_ExtendConfigComp.errorCode			= 0; break;
		case ATTACH_NE_TO_DF:		Workflows.FB.NE_WFs.AttachToComponent.WF_AttachPin.errorCode					= 0; break;
		case DETACH_NE_FROM_DF:		Workflows.FB.NE_WFs.AttachToComponent.WF_DetachPin.errorCode					= 0; break;
		case CHECK_SITE_IS_DELETED:	Workflows.FB.Site.CheckNoSiteExists.errorCode									= 0; break;
		case CHECK_SITE_IS_CREATED:	Workflows.FB.Site.CheckSiteExists.errorCode										= 0; break;
		
		default: ;
		}
	}

	// Brute Force Close and new Start Client FB
	private void restartClient()
	{
/*		
		try { callScript("Workflows.Wrappers.FB_CommonActions.BruteForceClose"); }
		catch(Exception e)	{ logger.log("\n\n*** cannot shutdown and restart client ... terminate test robot! ***");  System.exit(0); }
*/
		Workflows.Wrappers.StartFB.pte.kill();

		sleep(2*Global.SleepTimer.factor);
		
		try { callScript("Tests.StartApp.Restart_FixedAndBuild"); }
		catch(Exception e)	{ logger.log("\n\n*** cannot restart client ... terminate test robot! ***");  System.exit(0); }	
	}
	
	/*******************************************************************************
	 * 		MAIN METHODE
	 */
	public void testMain(Object[] args) 
	{
		String	exec				= "OK";
		boolean	success				= true;
		int numFailure				= 0;
		
		table.initMainTable();
		table.initSubTable();
	
		String[]	testDataIdentifiers	= null;
		String[]	testDataValues		= null;
		
		// für jede neue Iteration wird skip auf '0' zurückgesetzt1
		int	skip				= 0;
		
		// 1. Argument auslesen (Startzeile)
		if ( (args != null) && (args.length > 0) && conv.isNumber(args[0]) ) 
			startiter		= Integer.parseInt( args[0].toString() );		// andernsfalls ist 'startiter' = 0
		
		// 2. Argument auslesen (Aktionen überspringen für die 1. Iteration)
		if ( (args != null) && (args.length > 1) && conv.isNumber(args[1]) )
			skipFirstTime	= Integer.parseInt( args[1].toString() );	// andernsfalls ist 'skip' = 0
		
		// 3. Argument auslesen (Aktionen überspringen für die alle Iteration 1+n)
		if ( (args != null) && (args.length > 2) && conv.isNumber(args[2]))
			skipAllTime		= Integer.parseInt( args[2].toString() );	// andernsfalls = 0
		
		// 4. Argument auslesen (Obergrenze für Operationen ausführen)
		if ( (args != null) && (args.length > 3) && conv.isNumber(args[3]))
			maxOpNo		= Integer.parseInt( args[3].toString() );	// andernsfalls = 999999

		
		if( (iter - startiter) == 0 ) 
			skip 	= skipFirstTime;  // nur für den 1. Durchlauf!

		if( (iter - startiter) > 0)
			skip	= skipAllTime;	 // beim 2. und nachfolgenden Durchläufen auf "Dauer-Skip" setzen 
	
		
		//	Signalisiere Script-Start
		if( startFlag )				{
/*
 * Start F&B Client
 */			
		if( rt && Global.ScriptControlFlags.enable_client_restart ){ 
			callScript("Tests.StartApp.Restart_FixedAndBuild");
		}
			
			logger.log("**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********");
			logger.log("\n>>> START: F&B client Version " + Workflows.SQL.DbInstance.currentVersion + " ***** ");
			printCurrentDate();
			logger.log(" ***** time: " + printCurrentTime());
			logger.log("\n**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\t**********\n");
			logger.log(table.printTableHeader());
			logger.log("- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\t- - - - -\n");			
	/*
	 * COMMON SETTINGS (ONE TIME OPERATIONS)
	 */			
			/*****************************************************************************************************************************************/
			/*************************************************     SET EXPORT PATH IN USER PREFERENECES     ******************************************/
			/*****************************************************************************************************************************************/			
			if( (iter >= startiter) && (skip < SET_PREF_EXPORT_PATH) ) 		{
				logInfo(" *------------------   SET EXPORT PATH IN USER PREFERENECES   ------------------* ");

				testDataIdentifiers		= new String[] { "Pref-Attr", "Pref-Value" };
				testDataValues			= new String[] { "PU-Export-Pfad", Global.Paths.ExportPath };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, SET_PREF_EXPORT_PATH, "SET EXPORT PATH IN USER PREFERENECES", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.System.Preferences.tab_FixedAndBuild.Set_exportPath"); }
					catch(Exception e)	{
						success = false;	exec = "FAILED"; numFailure++; 
						}
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, SET_PREF_EXPORT_PATH), testDataIdentifiers, testDataValues));
					this.resetErrorCode(SET_PREF_EXPORT_PATH);
					
					if( success ){
						numFailure = 0;		break; 
						}else { 
							exec = "OK";			success = true;		this.restartClient(); 
							}
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			
			startFlag		= false;
		}
		
		
		// Schleifen und Einzelaktionen
		if(iter >= startiter)	{		
/*
 *  1. 	CREATE NETWORK
 *  	AND PROJECT PLANNING
 *  	OBJECTS
 */			
			do {		
				/*****************************************************************************************************************************************/
				/********************************************************     CREATE NEW SITE      *******************************************************/
				/*****************************************************************************************************************************************/
				if( this.trigger(-1, START_ADD, skip, GEN_SITE, maxOpNo, new String[] {"IndentCode"}, true) )					{
					logInfo(" *-------------------   Create New Site   -------------------* ");
	
					testDataIdentifiers		= new String[] { "Region", "SiteName" };
					testDataValues			= new String[] { dpString("Region"), dpString("SiteName") };
	
					while (true)	{
						logger.log(table.printTableRowLeftPart(iter, GEN_SITE, "CREATE NEW SITE", printCurrentTime()));
						if( rt ) callScript("Workflows.FB.Site.WF_CreateSite", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
						if( this.getErrorCode(success, GEN_SITE) > (10*GEN_SITE) + 1 )	{ success = false;	exec = "FAILED";	numFailure++; }
						logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_SITE), testDataIdentifiers, testDataValues));
						this.resetErrorCode(GEN_SITE);
						
						if( success )			{ numFailure = 0;		break; }
						else					{ exec = "OK";			success = true;		this.restartClient(); }
		
						if( numFailure > 1 )	{ logger.log("\n\n >>> fatal error [CREATE NEW SITE]- exit:0"); System.exit(0); }
					}
					testDataIdentifiers	= null;		testDataValues = null;		
				}
				/*****************************************************************************************************************************************/
				/********************************************************    CHECK SIITE IS CREATED     **************************************************/
				/*****************************************************************************************************************************************/														
				if( this.trigger(-1, START_ADD, skip, CHECK_SITE_IS_CREATED, maxOpNo, new String[] {"IndentCode"}, true) )					{
	
					logInfo(" *-------------------   Create New Site   -------------------* ");
	
					testDataIdentifiers		= new String[] { "Region", "SiteName" };
					testDataValues			= new String[] { dpString("Region"), dpString("SiteName") };
	
					while (true)	{
						logger.log(table.printTableRowLeftPart(iter, CHECK_SITE_IS_CREATED, "CHECK SIITE IS CREATED", printCurrentTime()));
						if( rt ) callScript("Workflows.FB.Site.CheckSiteExists", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
						if( this.getErrorCode(success, CHECK_SITE_IS_CREATED) > (10*CHECK_SITE_IS_CREATED) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
						logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, CHECK_SITE_IS_CREATED), testDataIdentifiers, testDataValues));
						
						if( success )			{ numFailure = 0;		break; }
						else					{ exec = "OK";			success = true;		this.restartClient(); }
		
						if( numFailure > 1 )	{ numFailure = 0;		break; }
					}
					testDataIdentifiers	= null;		testDataValues = null;		
					if( ( this.getErrorCode(success, CHECK_SITE_IS_CREATED) > (10*CHECK_SITE_IS_CREATED) ) )	skip--;
				}
			} while( this.getErrorCode(success, CHECK_SITE_IS_CREATED) > (10*CHECK_SITE_IS_CREATED) );
			this.resetErrorCode(CHECK_SITE_IS_CREATED);
		
			/******************************************************************************************************************************************/
			/**************************************************     CREATE NEW SITE ALTERNATIVE     ***************************************************/
			/******************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_SITE_ALTER, maxOpNo, new String[] {"AlternativeSiteName"}, true) )					{
				logInfo(" *-------------------   Create Site Alternative  -------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName", "SiteAlternativeName" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("AlternativeSiteName") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_SITE_ALTER, "CREATE SITE ALTERNATIVE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.AlternativeSite.CreateAlternativeSite", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_SITE_ALTER), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_SITE_ALTER);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/********************************************************     CREATE NEW SITE ROOM     ***************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_SITE_ROOM, maxOpNo, new String[] {"RoomNo"}, 
				(dpString("RoomUsedFlag")!= null) && ((dpString("RoomUsedFlag")).toString()).equalsIgnoreCase("SELECTED")) )			{
				logInfo(" *-------------------   Create Site Room   -------------------* ");

				testDataIdentifiers		= new String[] { "SiteName", "SiteRoom" };
				testDataValues			= new String[] { dpString("SiteName"), dpString("RoomNo") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_SITE_ROOM, "CREATE SITE ROOM", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.SiteRoom.CreateRoom", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_SITE_ROOM), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_SITE_ROOM);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/******************************************************     CREATE NEW DISTRIBUTOR     ***************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_DF, maxOpNo, new String[] {"PlattenTyp"}, true) )							{			
				logInfo(" *--------------   Create New Distributor   --------------* ");

				testDataIdentifiers		= new String[] { "SiteName", "SiteRoom", "EVt", "Row" };
				testDataValues			= new String[] { dpString("SiteName"), dpString("RoomNo"), dpString("RoomEVt"), dpString("Row") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_DF, "CREATE NEW DDF", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Distributor.CreateDistributor", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**************************************************     CREATE NEW NETWORK ELEMENT     ***************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_NE, maxOpNo, new String[] {"NE_Type", "NE_Configuration", "Begin", "End"}, true) )			{			

				if( (dpString("RoomNo") != null) && ((dpString("RoomNo")).length() > 0) )						{
					logInfo(" *--------------   Create New Network Element with Room No  --------------* ");
					
					testDataIdentifiers	= new String[] { "NE_Type", "NE_Configuration", "Region", "SiteName", "RoomNo", "NE_ID" };
					testDataValues		= new String[] { dpString("NE_Type"), dpString("NE_Configuration"), dpString("Region"), dpString("SiteName"), dpString("RoomNo"), dpString("NE_ID") };

					while (true)	{
						logger.log(table.printTableRowLeftPart(iter, GEN_NE, "CREATE NETWORK ELEMENT WITH ROOM No", printCurrentTime()));
						if( rt ) callScript("Workflows.FB.NE_WFs.WF_CreateNEwithRoomNo", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
						if( this.getErrorCode(success, GEN_NE) > (10*GEN_NE) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
						logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_NE), testDataIdentifiers, testDataValues));
						this.resetErrorCode(GEN_NE);
						
						if( success )			{ numFailure = 0;		break; }
						else					{ exec = "OK";			success = true;		this.restartClient(); }
		
						if( numFailure > 1 )	{ numFailure = 0;		break; }
					}
				}
				else																					{
					logInfo(" *--------------   Create New Network Element   --------------* ");
	
					testDataIdentifiers	= new String[] { "NE_Type", "NE_Configuration", "Region", "SiteName", "NE_ID" };
					testDataValues		= new String[] { dpString("NE_Type"), dpString("NE_Configuration"), dpString("Region"), dpString("SiteName"), dpString("NE_ID") };

					while (true)	{
						logger.log(table.printTableRowLeftPart(iter, GEN_NE, "CREATE NETWORK ELEMENT", printCurrentTime()));
						if( rt ) callScript("Workflows.FB.NE_WFs.WF_CreateNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
						if( this.getErrorCode(success, GEN_NE) > (10*GEN_NE) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
						logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_NE), testDataIdentifiers, testDataValues));
						this.resetErrorCode(GEN_NE);
						
						if( success )			{ numFailure = 0;		break; }
						else					{ exec = "OK";			success = true;		this.restartClient(); }
		
						if( numFailure > 1 )	{ numFailure = 0;		break; }
					}
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*******************************************************     CREATE NEW PROJECT     ******************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_PROJ, maxOpNo, new String[] {"ProjTemplateName"}, true) )							{			
				logInfo(" *--------------   Create New Project   --------------* ");

				testDataIdentifiers		= new String[] { "ProjTemplateName", "Region", "SiteName", "NE-Type" };
				testDataValues			= new String[] { dpString("ProjTemplateName"), dpString("Region"), dpString("SiteName"), dpString("NE_Type") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_PROJ, "CREATE NEW PROJECT", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.Projects.WF_CreateProject", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
					if( this.getErrorCode(success, GEN_PROJ) > (10*GEN_PROJ) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_PROJ), testDataIdentifiers, testDataValues));
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				this.resetErrorCode(GEN_PROJ);
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     CREATE NEW VBZ     *******************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_VBZ, maxOpNo, new String[] {"VBZ_Notes"}, true) )							{			
				logInfo(" *--------------   Create New VBZ   --------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "NE_TypeB", "RegionB", "SiteNameB" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_TypeB"), dpString("RegionB"), dpString("SiteNameB") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_VBZ, "CREATE NEW VBZ", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.VBZ.WF_CreateVBZ", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
					if( this.getErrorCode(success, GEN_VBZ) > (10*GEN_VBZ) + 1 )	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_VBZ), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_VBZ);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     CREATE NEW UDSV     ******************************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, GEN_UDSV, maxOpNo, new String[] {"CIC"}, true) )							{			
				logInfo(" *--------------   Create New UDSV   --------------* ");

				testDataIdentifiers		= new String[] { "NE-Type", "Region", "SiteName", "NE-TypeB", "RegionB", "SiteNameB" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_TypeB"), dpString("RegionB"), dpString("SiteNameB") };

				Global.SleepTimer.factor	= Global.SleepTimer.factor + 2;
			
				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_UDSV, "CREATE NEW UDSV", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.UDSV.WF_CreateUDSV", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
					if( this.getErrorCode(success, GEN_UDSV) > (10*GEN_UDSV) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_UDSV), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_UDSV);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;
				
				Global.SleepTimer.factor	= Global.SleepTimer.factor - 2;
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     CREATE NEW LINE     ******************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(-1, START_ADD, skip, GEN_LINE, maxOpNo, new String[] {"LineDescription"}, true) )					{			
				logInfo(" *--------------   Create New Line   --------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName", "RegionB", "SiteNameB", "LineDescription" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("RegionB"), dpString("SiteNameB"), dpString("LineDescription") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_LINE, "CREATE NEW LINE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Create_N_Lines", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_LINE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_LINE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*****************************************************     CREATE NEW CONFIGURATION     **************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(-1, START_ADD, skip, GEN_CONFIG, maxOpNo, new String[] {"BasicType", "NE_Configuration", "Begin", "!End"}, true) )		{			

				logInfo(" *--------------   Create New Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "Begin" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("Begin") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, GEN_CONFIG, "CREATE NEW CONFIGURATION", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_CreateConfigComp", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
					if( this.getErrorCode(success, GEN_CONFIG) > (10*GEN_CONFIG) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, GEN_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(GEN_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*******************************************************     EXTEND CONFIGURATION     ****************************************************/
			/*****************************************************************************************************************************************/			
			/*****************************************************************************************************************************************/			
			if( this.trigger(-1, START_ADD, skip, EXTEND_CONFIG, maxOpNo, new String[] {"BasicType", "NE_Configuration", "Begin", "End"}, true) )		{			
				
				logInfo(" *--------------   Extend Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "Begin" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("Begin") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, EXTEND_CONFIG, "EXTEND CONFIGURATION", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_ExtendConfigComp", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
					if( this.getErrorCode(success, EXTEND_CONFIG) > (10*EXTEND_CONFIG) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, EXTEND_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(EXTEND_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/***************************************************     ADD PARAMETER TO CONF/COMP     **************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(-1, START_ADD, skip, ADD_PARAM_TO_CONFIG, maxOpNo, new String[] {"NE_Configuration", "ParamName", "ParamValue"}, true) )		{			
				logInfo(" *--------------   Add Parameters to Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "ParamName", "ParamValue" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("ParamName"), dpString("ParamValue") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, ADD_PARAM_TO_CONFIG, "ADD CONFIGURATION PARAMETERS", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_AddParamToConfigComp", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, ADD_PARAM_TO_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(ADD_PARAM_TO_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*************************************************     ADD COMPONENT TO CONFIGURATION     ***********************************************/
			/*****************************************************************************************************************************************/
			if( this.trigger(-1, START_ADD, skip, ADD_COMP_TO_CONFIG, maxOpNo, new String[] {"NE_Configuration", "ConfigCompName", "!Begin"}, true) )				{						
				logInfo(" *--------------   Add Component to Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "ConfigCompName" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("ConfigCompName") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, ADD_COMP_TO_CONFIG, "ADD COMPONENT TO CONFIGURATION", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_AddComponentToConfig", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, ADD_COMP_TO_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(ADD_COMP_TO_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}

/*
 *  2. 	CREATE OBJECT LINKS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
/***************************************************************************************************************************************
************************************************     ADD NETWORK ELEMENTS TO NE-GROUPS     *********************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, ADD_NE_TO_NE_GROUP, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   Add NE Group To NE   ------------------* ");

				testDataIdentifiers		= new String[] { "NeGroupName", "NE_Type", "NE_Configuration" };
				testDataValues			= new String[] { dpString("NeGroupName"), dpString("NE_Type"), dpString("NE_Configuration") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, ADD_NE_TO_NE_GROUP, "ADD NETWORK ELEMENT TO NE GROUP", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.AssignNE_GroupToNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, ADD_NE_TO_NE_GROUP), testDataIdentifiers, testDataValues));
					this.resetErrorCode(ADD_NE_TO_NE_GROUP);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
********************************************************     EXPORT IP ADDRESSES     *****************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, EXPORT_IP_ADDR, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   EXPORT IP ADDRESSES   ------------------* ");

				testDataIdentifiers		= new String[] { "ExportPath", "ExportFileNameIP" };
				testDataValues			= new String[] { Global.Paths.ExportPath, Global.Paths.ExportFileNameIP };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, EXPORT_IP_ADDR, "EXPORT IP ADDRESSES", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.IP_ImExport.ExportIpAddress", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, EXPORT_IP_ADDR), testDataIdentifiers, testDataValues));
					this.resetErrorCode(EXPORT_IP_ADDR);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
********************************************************     DELETE IP ADDRESSES IN NE     ***********************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, DEL_IP_ADDR_IN_NE, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   DELETE IP ADDRESSES IN NE   ------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "NE_ID", "#(deletedIP@)" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_ID"), Workflows.FB.NE_WFs.WF_DeleteIpAddrInNE.countIpAddrInNE };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_IP_ADDR_IN_NE, "DELETE IP ADDRESSES IN NE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.WF_DeleteIpAddrInNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_IP_ADDR_IN_NE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_IP_ADDR_IN_NE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*******************************************************     VERIFY IP@ ARE DELETED     ***************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, VER_IP_ADDR_DELETED, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   VERIFY IP@ ARE DELETED   ------------------* ");
				
				testDataIdentifiers		= new String[] { "NoIP@exists" };
				testDataValues			= new String[] { String.valueOf(Workflows.Wrappers.FB_IP_ImExport.Verify_NoIpAddrExists.noIpAddrExists) };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, VER_IP_ADDR_DELETED, "VERIFY IP@ ARE DELETED", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.IP_ImExport.Verify_NoIpAddrExists", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, VER_IP_ADDR_DELETED), testDataIdentifiers, testDataValues));
					this.resetErrorCode(VER_IP_ADDR_DELETED);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
********************************************************     IMPORT IP ADDRESSES     *****************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, IMPORT_IP_ADDR, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   IMPORT IP ADDRESSES   ------------------* ");
				
				testDataIdentifiers		= new String[] { "ExportPath", "ExportFileNameIP" };
				testDataValues			= new String[] { Global.Paths.ExportPath, Global.Paths.ExportFileNameIP };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, IMPORT_IP_ADDR, "EXPORT IP ADDRESSES", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.IP_ImExport.ImportIpAddress", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, IMPORT_IP_ADDR), testDataIdentifiers, testDataValues));
					this.resetErrorCode(IMPORT_IP_ADDR);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
******************************************************     VERIFY IP@ ARE IMPORTED     ***************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, VER_IP_ADDR_IMPORTED, maxOpNo, new String[] {"NeGroupName", "!NeGroupType"}, true) )				{						
				logInfo(" *------------------   VERIFY IP@ ARE IMPORTED   ------------------* ");
				
				testDataIdentifiers		= new String[] { "ExportPath", "ExportFileNameIP", "numIpAddrImported" };
				testDataValues			= new String[] { Global.Paths.ExportPath, Global.Paths.ExportFileNameIP, Workflows.FB.IP_ImExport.Verify_IpAddrImported.numIpAddrImported };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, VER_IP_ADDR_IMPORTED, "VERIFY IP@ ARE IMPORTED", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.IP_ImExport.Verify_IpAddrImported", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, VER_IP_ADDR_IMPORTED), testDataIdentifiers, testDataValues));
					this.resetErrorCode(VER_IP_ADDR_IMPORTED);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*****************************************************     CHANGE CONFIGURATION     *******************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, CHANGE_CONF, maxOpNo, new String[] {"NE_Type", "NE_Configuration", "Begin", "!End"}, true) )				{						
				logInfo(" *--------------   Change Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "NE_ID", "NE_Configuration", "Begin" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_ID"), dpString("NE_Configuration"), dpString("Begin") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, CHANGE_CONF, "CHANGE CONFIGURATION", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.ChangeConf", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, CHANGE_CONF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(CHANGE_CONF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
************************************************     IMPORT COMPONENTS IN NE-GROUP     ***************************************************

			if( (iter >= START_ADD) && (iter < START_REMOVE) && (skip < IMPORT_COMP_IN_NE_GROUP) && (dpString("NeGroupName") != null) && ((dpString("NeGroupName")).length() > 0) && 
						(dpString("NeGroupType") != null) && ((dpString("NeGroupType")).length() > 0)) 		{
				logInfo(" *--------------   IMPORT COMPONENTS IN NE-GROUPS   --------------* ");

				try { callScript("Workflows.FB.NE_Groups.ImportComponents", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
				catch(Exception e)	{ success = false;	exec = "FAILED"; }
				
				logger.log(table.importCompInNeGroup(iter, IMPORT_COMP_IN_NE_GROUP, "IMPORT COMPONENTS IN NE-GROUPS", exec, printCurrentTime(), Global.Paths.ImportPath, Global.Paths.ImportFileNameNeGroup, 
								dpString("NeGroupName"), "OSS_Node_ID", "Address 1", dpString("Begin")));					
				
				if( ! success )		{ 
					exec = "OK";	success = true; 
					
					try { callScript("Workflows.Wrappers.FB_CommonActions.BruteForceClose"); }
					catch(Exception e)	{ logger.log("\n\n*** cannot shutdown and restart client ... terminate test robot! ***");  System.exit(0); }
					
					try { callScript("Tests.StartApp.Start_FixedAndBuild"); }
					catch(Exception e)	{ logger.log("\n\n*** cannot restart client ... terminate test robot! ***");  System.exit(0); }				
				}					
			}
/*****************************************************************************************************************************************
***********************************************     ATTACH NETWORK ELEMENTS TO DISTRIBUTOR     *******************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, ATTACH_NE_TO_DF, maxOpNo, new String[] {"NE_Type", "DF_Term"}, true) )				{						
				logInfo(" *------------------   Attach NE To DF   ------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "SiteName", "NE_ID", "RoomEVt", "DF_Term" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("SiteName"), dpString("NE_ID"), dpString("RoomEVt"), dpString("DF_Term") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, ATTACH_NE_TO_DF, "ATTACH COMPONENT TO PIN", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.NE_WFs.AttachToComponent.WF_AttachPin", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
					if( this.getErrorCode(success, ATTACH_NE_TO_DF) > 10*ATTACH_NE_TO_DF )	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, ATTACH_NE_TO_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(ATTACH_NE_TO_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*******************************************************     TERMINATE LINE ON EP     *****************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, TERMIN_LINE_ON_EP, maxOpNo, new String[] {"LineDescription", "RoomEVt"}, true) )				{						
				logInfo(" *------------------   Terminate Line on Endpoint   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "RegionB", "SiteNameB", "RoomNo", "RoomEVt" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("RegionB"), dpString("SiteNameB"), dpString("RoomNo"), dpString("RoomEVt") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, TERMIN_LINE_ON_EP, "ASSIGN LINE END TO EVt/ROOM No", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.AssignLineToRoom", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, TERMIN_LINE_ON_EP), testDataIdentifiers, testDataValues));
					this.resetErrorCode(TERMIN_LINE_ON_EP);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*******************************************************     TERMINATE LINE ON DF     *****************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, TERMIN_LINE_ON_DF, maxOpNo, new String[] {"LineDescription", "DF_Term"}, true) )				{						
				logInfo(" *------------------   Terminate Line on Distributor   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "DF_Term", "RoomEVt" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("DF_Term"), dpString("RoomEVt") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, TERMIN_LINE_ON_DF, "ATTACH LINE END TO PIN", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.AttachLineToDistributor", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, TERMIN_LINE_ON_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(TERMIN_LINE_ON_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*******************************************************     TERMINATE LINE ON NE     *****************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, TERMIN_LINE_ON_NE, maxOpNo, new String[] {"LineDescription", "NE_Type"}, true) )				{						
				logInfo(" *------------------   Terminate Line on Network Element   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "NE_Type", "NE_Configuration" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("NE_Type"), dpString("NE_Configuration") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, TERMIN_LINE_ON_NE, "ATTACH LINE END TO IF", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.AttachLineToNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, TERMIN_LINE_ON_NE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(TERMIN_LINE_ON_NE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
*******************************************************     ASSIGN LINE TO UDSV     ******************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, ASSIGN_LINE_TO_UDSV, maxOpNo, new String[] {"LineDescription", "CIC"}, true) )				{						
				logInfo(" *------------------   Terminate Line on Network Element   ------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName", "RegionB", "SiteNameB", "LineDescription", "CIC" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("RegionB"), dpString("SiteNameB"), dpString("LineDescription"), dpString("CIC") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, ASSIGN_LINE_TO_UDSV, "ASSIGN LINE TO UDSV", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.AssignUdsvToLine", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, ASSIGN_LINE_TO_UDSV), testDataIdentifiers, testDataValues));
					this.resetErrorCode(ASSIGN_LINE_TO_UDSV);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
/*****************************************************************************************************************************************
******************************************************     RACK PLANNING ASSIGN RACK    **************************************************
*/
			if( this.trigger(START_ADD, START_REMOVE, skip, RACK_PLANNING_ASSIGN, maxOpNo, new String[] {"NE_Type"}, (dpString("BasicType") != null) && (dpString("BasicType")).equalsIgnoreCase("Rack")) )				{						
				logInfo(" *------------------   Rack Planning - Assign Rack  ------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "RoomNo", "NE_ID" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("RoomNo"), dpString("NE_ID") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, RACK_PLANNING_ASSIGN, "RACK PLANNING - ASSIGN RACK", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.RackPlanning.AssignRackToNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, RACK_PLANNING_ASSIGN), testDataIdentifiers, testDataValues));
					this.resetErrorCode(RACK_PLANNING_ASSIGN);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     EDIT VBZ     ***********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_ADD, START_REMOVE, skip, EDIT_VBZ, maxOpNo, new String[] {"VBZ_Notes"}, true) )							{			
				logInfo(" *-------------------   Edit VBZ   -------------------* ");

				testDataIdentifiers		= new String[] { "VBZ_Comment1", "VBZ-EndDate" };
				testDataValues			= new String[] { dpString("VBZ_Notes"), dpString("End") };

				while ( true )	{
					logger.log(table.printTableRowLeftPart(iter, EDIT_VBZ, "EDIT VBZ", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.VBZ.WF_EditVBZ", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
					if( this.getErrorCode(success, EDIT_VBZ) > (10*EDIT_VBZ) )	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, EDIT_VBZ), testDataIdentifiers, testDataValues));
					this.resetErrorCode(EDIT_VBZ);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;				
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     NEW BUNDLE     ***********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_ADD, START_REMOVE, skip, NEW_BUNDLE, maxOpNo, new String[] {"Bundles", "End"}, true) )							{			
				logInfo(" *-------------------   Edit VBZ   -------------------* ");

				testDataIdentifiers		= new String[] { "Comment1", "Bundles", "Bundle-EndDate" };
				testDataValues			= new String[] { dpString("VBZ_Notes"), dpString("Bundles"), dpString("End") };

				while ( true )	{
					logger.log(table.printTableRowLeftPart(iter, NEW_BUNDLE, "CREATE NEW BUNDLE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.VBZ.Bundles.NewBundle", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					if( this.getErrorCode(success, NEW_BUNDLE) > (10*NEW_BUNDLE) )	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, NEW_BUNDLE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(NEW_BUNDLE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;				
			}

/*
 *  3. 	DELETE OBJECT LINKS			
 */
			/*****************************************************************************************************************************************/
			/******************************************************     RACK PLANNING REMOVE RACK    *************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, RACK_PLANNING_REMOVE, maxOpNo, new String[] {"NE_Type"}, (dpString("BasicType") != null) && (dpString("BasicType")).equalsIgnoreCase("Rack")) )				{						
				logInfo(" *------------------   Rack Planning - Remove Rack  ------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "RoomNo", "NE_ID" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("RoomNo"), dpString("NE_ID") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, RACK_PLANNING_REMOVE, "RACK PLANNING - REMOVE RACK", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.RackPlanning.RemoveRackFromNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, RACK_PLANNING_REMOVE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(RACK_PLANNING_REMOVE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*********************************************************     REMOVE LINE FROM UDSV     *************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, REMOVE_LINE_FROM_UDSV, maxOpNo, new String[] {"LineDescription", "CIC"}, true) )				{						
				logInfo(" *------------------   Remove Line fron UDSV   ------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName", "RegionB", "SiteNameB", "LineDescription", "CIC" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("RegionB"), dpString("SiteNameB"), dpString("LineDescription"), dpString("CIC") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REMOVE_LINE_FROM_UDSV, "DELETE UDSV ASSIGNMENT", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.RemoveUdsvAssignment", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REMOVE_LINE_FROM_UDSV), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REMOVE_LINE_FROM_UDSV);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}				
			/*****************************************************************************************************************************************/
			/**************************************************     REMOVE LINE TERMINATION ON NE     ************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, REMOVE_LINE_TERM_ON_NE, maxOpNo, new String[] {"LineDescription", "NE_Type"}, true) )				{						
				logInfo(" *------------------   Remove Line Termination on Network Element   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "NE_Type" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("NE_Type") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REMOVE_LINE_TERM_ON_NE, "DETACH LINE END FROM IF", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.DetachLineFromNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REMOVE_LINE_TERM_ON_NE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REMOVE_LINE_TERM_ON_NE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}				
			/*****************************************************************************************************************************************/
			/**************************************************     REMOVE LINE TERMINATION ON DF     ************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, REMOVE_LINE_TERM_ON_DF, maxOpNo, new String[] {"LineDescription", "DF_Term"}, true) )				{						
				logInfo(" *------------------   Remove Line Termination on Distributor   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "DF_Term" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("DF_Term") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REMOVE_LINE_TERM_ON_DF, "DETACH LINE END FROM PIN", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.DetachFromDistributor", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REMOVE_LINE_TERM_ON_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REMOVE_LINE_TERM_ON_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}		
			/*****************************************************************************************************************************************/
			/**************************************************     REMOVE LINE TERMINATION AT EP     ************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, REMOVE_LINE_TERM_ON_EP, maxOpNo, new String[] {"LineDescription", "RoomEVt"}, true) )				{						
				logInfo(" *------------------   Remove Line Termination on Endpoint   ------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription", "LineEndPoint", "RegionB", "SiteNameB", "RoomNo", "RoomEVt" };
				testDataValues			= new String[] { dpString("LineDescription"), dpString("LineEndPoint"), dpString("RegionB"), dpString("SiteNameB"), dpString("RoomNo"), dpString("RoomEVt") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REMOVE_LINE_TERM_ON_EP, "DELETE LINE ROOM_EVt ASSIGNMENT", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Termination.RemoveLineFromRoom", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REMOVE_LINE_TERM_ON_EP), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REMOVE_LINE_TERM_ON_EP);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**********************************************     DETACH NETWORK ELEMENT FROM DISTRIBUTOR     ******************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, DETACH_NE_FROM_DF, maxOpNo, new String[] {"NE_Type", "DF_Term"}, true) )				{						
				logInfo(" *------------------   DETACH NETWORK ELEMENT FROM DISTRIBUTOR   ------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "SiteName", "NE_ID", "RoomEVt", "DF_Term" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("SiteName"), dpString("NE_ID"), dpString("RoomEVt"), dpString("DF_Term") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DETACH_NE_FROM_DF, "DETACH COMPONENT FROM DF", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.NE_WFs.AttachToComponent.WF_DetachPin", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
					if( this.getErrorCode(success, DETACH_NE_FROM_DF) > 10*DETACH_NE_FROM_DF )	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DETACH_NE_FROM_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DETACH_NE_FROM_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/************************************************     REMOVE NETWORK ELEMENT FROM NE-GROUP     *******************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_REMOVE, START_DELETE, skip, REM_NE_FROM_NE_GROUP, maxOpNo, new String[] {"NeGroupName"}, true) )				{						
				logInfo(" *------------------   Remove NE-Group From NE   ------------------* ");

				testDataIdentifiers		= new String[] { "NeGroupName", "NE_Type" };
				testDataValues			= new String[] { dpString("NeGroupName"), dpString("NE_Type") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REM_NE_FROM_NE_GROUP, "REMOVE NETWORK ELEMENT FROM NE GROUP", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.RemoveNE_GroupFromNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REM_NE_FROM_NE_GROUP), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REM_NE_FROM_NE_GROUP);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     REMOVE BUNDLE     ***********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_REMOVE, START_DELETE, skip, REMOVE_BUNDLE, maxOpNo, new String[] {"Bundles", "End"}, true) )							{			
				logInfo(" *-------------------   Edit VBZ   -------------------* ");

				testDataIdentifiers		= new String[] { "Comment1", "Bundles", "Bundle-EndDate" };
				testDataValues			= new String[] { dpString("VBZ_Notes"), dpString("Bundles"), dpString("End") };

				while ( true )	{
					logger.log(table.printTableRowLeftPart(iter, REMOVE_BUNDLE, "DELETE BUNDLE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.VBZ.Bundles.DeleteBundle", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REMOVE_BUNDLE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REMOVE_BUNDLE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;				
			}

			
/*
 *  4. 	DELETE NETWORK
 *  	AND PROJECT PLANNING
 *  	OBJECTS
 */
			/*****************************************************************************************************************************************/
			/**********************************************************     DELETE LINE     **********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_DELETE, -1, skip, DEL_LINE, maxOpNo, new String[] {"LineDescription"}, true) )					{			
				logInfo(" *-------------------   Delete Line   -------------------* ");

				testDataIdentifiers		= new String[] { "LineDescription" };
				testDataValues			= new String[] { dpString("LineDescription") };

				while ( true )	{
					logger.log(table.printTableRowLeftPart(iter, DEL_LINE, "DELETE LINE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Line.Delete_N_Lines", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_LINE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_LINE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     DELETE UDSV     **********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_DELETE, -1, skip, DEL_UDSV, maxOpNo, new String[] {"CIC"}, true) )							{			
				logInfo(" *-------------------   Delete UDSV   -------------------* ");

				testDataIdentifiers		= new String[] { "NE-Type", "Region", "SiteName", "NE-TypeB", "RegionB", "SiteNameB" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_TypeB"), dpString("RegionB"), dpString("SiteNameB") };

				while ( ! SELECT_MODE )	{
					logger.log(table.printTableRowLeftPart(iter, DEL_UDSV, "DELETE UDSV", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.UDSV.DeleteUdsv", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_UDSV), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_UDSV);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
				
				if( SELECT_MODE )	callScript("Workflows.FB.UDSV.SelectUdsv", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
			}
			/*****************************************************************************************************************************************/
			/**********************************************************     DELETE VBZ     ***********************************************************/
			/*****************************************************************************************************************************************/						
			if( this.trigger(START_DELETE, -1, skip, DEL_VBZ, maxOpNo, new String[] {"VBZ_Notes"}, true) )							{			
				logInfo(" *-------------------   Delete VBZ   -------------------* ");

				testDataIdentifiers		= new String[] { "NE_Type", "Region", "SiteName", "NE_TypeB", "RegionB", "SiteNameB" };
				testDataValues			= new String[] { dpString("NE_Type"), dpString("Region"), dpString("SiteName"), dpString("NE_TypeB"), dpString("RegionB"), dpString("SiteNameB") };

				while ( ! SELECT_MODE )	{
					logger.log(table.printTableRowLeftPart(iter, DEL_VBZ, "DELETE VBZ", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.VBZ.DeleteVBZ", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_VBZ), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_VBZ);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
				
				if( SELECT_MODE )	callScript("Workflows.FB.VBZ.SelectVBZ", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
			}
			/*****************************************************************************************************************************************/
			/*********************************************************     DELETE PROJECT     ********************************************************/
			/*****************************************************************************************************************************************/								
			if( this.trigger(START_DELETE, -1, skip, DEL_PROJ, maxOpNo, new String[] {"ProjTemplateName"}, true) )							{			
				logInfo(" *--------------   Delete Project   --------------* ");

				testDataIdentifiers		= new String[] { "ProjTemplateName", "Region", "SiteName", "NE-Type" };
				testDataValues			= new String[] { dpString("ProjTemplateName"), dpString("Region"), dpString("SiteName"), dpString("NE_Type") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_PROJ, "DELETE PROJECT", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Projects.WF_DeleteProject", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_PROJ), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_PROJ);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/****************************************************     DELETE NETWORK ELEMENT     *****************************************************/
			/*****************************************************************************************************************************************/								
			if( this.trigger(START_DELETE, -1, skip, DEL_NE, maxOpNo, new String[] {"NE_Type", "NE_Configuration"}, true) )			{			
				logInfo(" *--------------   Delete Network Element   --------------* ");

				testDataIdentifiers	= new String[] { "NE_Type", "NE_Configuration", "Region", "SiteName", "NE_ID" };
				testDataValues		= new String[] { dpString("NE_Type"), dpString("NE_Configuration"), dpString("Region"), dpString("SiteName"), dpString("NE_ID") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_NE, "DELETE NETWORK ELEMENT", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.NE_WFs.DeleteNE", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_NE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_NE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/********************************************************     DELETE DISTRIBUTOR     *****************************************************/
			/*****************************************************************************************************************************************/								
			if( this.trigger(START_DELETE, -1, skip, DEL_DF, maxOpNo, new String[] {"PlattenTyp"}, true) )							{			
				logInfo(" *-------------------   Delete Line   -------------------* ");

				testDataIdentifiers		= new String[] { "SiteName", "SiteRoom", "EVt", "Row" };
				testDataValues			= new String[] { dpString("SiteName"), dpString("RoomNo"), dpString("RoomEVt"), dpString("Row") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_DF, "DELETE DF", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Distributor.DeleteDistributor", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_DF), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_DF);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		 this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/********************************************************     DELETE ROOM EQUIPMENT     **************************************************/
			/*****************************************************************************************************************************************/								
			if( this.trigger(START_DELETE, -1, skip, DEL_SITE_ROOM, maxOpNo, new String[] {"RoomNo"}, (dpString("RoomUsedFlag")!= null) && 
					((dpString("RoomUsedFlag")).toString()).equalsIgnoreCase("SELECTED")) && ( Workflows.SQL.DbInstance.execEnv.equalsIgnoreCase("DA") || 
							(dpString("RoomEVt") == null) || ((dpString("RoomEVt")).length() == 0) ))			{
				logInfo(" *-------------------   Delete Site Room  -------------------* ");
	
				testDataIdentifiers		= new String[] { "Region", "SiteName", "SiteRoom" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("RoomNo") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_SITE_ROOM, "DELETE SITE ROOM EQUIPMENT", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.SiteRoom.DeleteRoom", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_SITE_ROOM), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_SITE_ROOM);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*******************************************************     DELETE SITE ALTERNATIVE     *************************************************/
			/*****************************************************************************************************************************************/											
			if( this.trigger(START_DELETE, -1, skip, DEL_SITE_ALTER, maxOpNo, new String[] {"AlternativeSiteName"}, true) )					{
				logInfo(" *-------------------   Delete Site Alternative  -------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName", "SiteAlternativeName" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), dpString("AlternativeSiteName") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_SITE_ALTER, "DELETE SITE ALTERNATIVE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.AlternativeSite.DeleteAlternativeSite", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_SITE_ALTER), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_SITE_ALTER);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/***********************************************************     DEACTIVATE SITE     *****************************************************/
			/*****************************************************************************************************************************************/														
			if( this.trigger(START_DELETE, -1, skip, DEACTIVATE_SITE, maxOpNo, new String[] {"SiteStatus"}, true) )					{
				logInfo(" *-------------------   Deactive Site -------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEACTIVATE_SITE, "DEACTIVATE SITE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.DeactivateSite", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEACTIVATE_SITE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEACTIVATE_SITE);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/***********************************************************     DELETE SITE     *********************************************************/
			/*****************************************************************************************************************************************/														
			if( this.trigger(START_DELETE, -1, skip, DEL_SITE, maxOpNo, new String[] {"IndentCode"}, Workflows.SQL.DbInstance.execEnv.equalsIgnoreCase("DA") || 
					(dpString("RoomEVt") == null) || ((dpString("RoomEVt")).length() == 0) ) )					{
				logInfo(" *-------------------   Delete Site   -------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_SITE, "DELETE SITE", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Site.DeleteNSites", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_SITE), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_SITE);
					
					if( success )			{ numFailure = 0;		break; }
					else					
					{ 
						// check for "funk30.nodes.nod_such_id "
						if( PB_2011_1.FB.CommonActions.ora_errors.CheckNodeSuchIDExists.existFlag )
						{
							if( Workflows.SQL.DbInstance.execEnv.equalsIgnoreCase("DA") )
								callScript("Workflows.SQL.SqlDeleteNodeSuchIDs", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD);
						}
						exec = "OK";			success = true;		this.restartClient(); 
					}
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/********************************************************    CHECK SIITE IS DELETED     **************************************************/
			/*****************************************************************************************************************************************/														
			if( this.trigger(START_DELETE, -1, skip, CHECK_SITE_IS_DELETED, maxOpNo, new String[] {"IndentCode"}, Workflows.SQL.DbInstance.execEnv.equalsIgnoreCase("DA") || 
					(dpString("RoomEVt") == null) || ((dpString("RoomEVt")).length() == 0) ) )					{
				logInfo(" *-------------------   Delete Site   -------------------* ");

				testDataIdentifiers		= new String[] { "Region", "SiteName" };
				testDataValues			= new String[] { dpString("Region"), dpString("SiteName"), };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, CHECK_SITE_IS_DELETED, "CHECK SIITE IS DELETED", printCurrentTime()));
					if( rt ) callScript("Workflows.FB.Site.CheckNoSiteExists", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); 
					if( this.getErrorCode(success, CHECK_SITE_IS_DELETED) > (10*CHECK_SITE_IS_DELETED) + 1 ){ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, CHECK_SITE_IS_DELETED), testDataIdentifiers, testDataValues));
					this.resetErrorCode(CHECK_SITE_IS_DELETED);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*************************************************     REMOVE PARAMETER FROM CONFIGURATION     *******************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_DELETE, -1, skip, REM_PARAM_FROM_CONFIG, maxOpNo, new String[] {"NE_Configuration", "ParamName", "ParamValue"}, true) )		{			
				logInfo(" *--------------   Remove Parameter From Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "ParamName", "ParamValue" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("ParamName"), dpString("ParamValue") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REM_PARAM_FROM_CONFIG, "REMOVE PARAMETER FROM CONF/COMP", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_RemoveParamFromConfigComp", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REM_PARAM_FROM_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REM_PARAM_FROM_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*************************************************     REMOVE COMPONENT FROM CONFIGURATION     ***********************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_DELETE, -1, skip, REM_COMP_FROM_CONFIG, maxOpNo, new String[] {"NE_Configuration", "ConfigCompName"}, true) )				{						
				logInfo(" *--------------   Remove Component from Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "ConfigCompName" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("ConfigCompName") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, REM_COMP_FROM_CONFIG, "REMOVE COMPONENT FROM CONFIGURATION", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_RemoveComponentFromConfig", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, REM_COMP_FROM_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(REM_COMP_FROM_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
			/*****************************************************************************************************************************************/
			/*****************************************************     DELETE CONFIGURATION     **************************************************/
			/*****************************************************************************************************************************************/			
			if( this.trigger(START_DELETE, -1, skip, DEL_CONFIG, maxOpNo, new String[] {"BasicType", "NE_Configuration", "Begin"}, true) )		{			
				logInfo(" *--------------   Create New Configuration   --------------* ");

				testDataIdentifiers		= new String[] { "BasicType", "NE_Configuration", "Begin" };
				testDataValues			= new String[] { dpString("BasicType"), dpString("NE_Configuration"), dpString("Begin") };

				while (true)	{
					logger.log(table.printTableRowLeftPart(iter, DEL_CONFIG, "DELETE CONFIGURATION", printCurrentTime()));
					try { if( rt ) callScript("Workflows.FB.Administration.Config_Comp.WF_DeleteConfigComp", DEFAULT_ARGS, DP_SHARE_CURRENT_RECORD); }
					catch(Exception e)	{ success = false;	exec = "FAILED";	numFailure++; }
					logger.log(table.printTableRowRightPart(exec, this.getErrorCode(success, DEL_CONFIG), testDataIdentifiers, testDataValues));
					this.resetErrorCode(DEL_CONFIG);
					
					if( success )			{ numFailure = 0;		break; }
					else					{ exec = "OK";			success = true;		this.restartClient(); }
	
					if( numFailure > 1 )	{ numFailure = 0;		break; }
				}
				testDataIdentifiers	= null;		testDataValues = null;		
			}
		}
	
		iter++;
	}
}

