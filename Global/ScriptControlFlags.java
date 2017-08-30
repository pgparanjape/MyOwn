package Global;


public class ScriptControlFlags {
	public static boolean		NeGroup_inUseFlag			= false;
	public static boolean		NeGroup_triggAddrFlag		= false;
	public static boolean		OrderNBM_ST_Success			= true;
	public static boolean		SiteInCellScreenIsSelected	= false;
	public static boolean		MenuItemProjectWasClicked	= false;
	public static boolean		preferencesNotSet			= true;

	public static int			selectCode					= 0;     // -> BasicFunctions->Select Data for checking Existence
							/*
							 *  = 1; Data do not exist
							 *  = 2; Dara exist
							 *  = 3; error while trying to select data
							 */
	public static boolean		enable_client_restart		= true;   // FALSE: no automatical client restart! 
	public static boolean cellScreenCanBeClosed ;

}	
