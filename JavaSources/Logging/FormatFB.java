package JavaSources.Logging;

public class FormatFB {
	private final	String	SEPARATOR1								= ":";
	private final	String	SEPARATOR2								= " + ";
	
	/* --- MAIN TABLE SETTINGS --- */
//	private final	int		WIDTH_COL_1								= 7;
//	private final	int		WIDTH_COL_2								= 7;
	private final	int		WIDTH_COL_3								= 40;
	private final	int		WIDTH_COL_4								= 18;
	private final	int		WIDTH_COL_5								= 8;
//	private final	int		WIDTH_COL_6								= 9;
	private final	int		WIDTH_COL_7								= 40;

//	private final	String	TITLE_COLUMN_1							= "Row";
//	private final	String	TITLE_COLUMN_2							= "OpNo";
	private final	String	TITLE_COLUMN_3							= "Operation Name";
	private final	String	TITLE_COLUMN_4							= "Time";
	private final	String	TITLE_COLUMN_5							= "Exec";
//	private final	String	TITLE_COLUMN_6							= "ErrCode";
	private final	String	TITLE_COLUMN_7							= "Test Data [identifier<value>,...]";

	public	FormatFB()	{}
	
	public void 	initMainTable()		{
		Format.setTableConstants(SEPARATOR1, 
//				WIDTH_COL_1, TITLE_COLUMN_1, WIDTH_COL_2, TITLE_COLUMN_2, 
				WIDTH_COL_3, TITLE_COLUMN_3, WIDTH_COL_4, TITLE_COLUMN_4, WIDTH_COL_5, TITLE_COLUMN_5, 
//				WIDTH_COL_6, 
				WIDTH_COL_7, TITLE_COLUMN_7);
	}
	
	public void initSubTable() {
		TestDataColumns.setTableConstants(SEPARATOR2);
	}

	public String printTableHeader() {
		return Format.getTableHeader() + "\n";
	}
	
	public String		printTableRowLeftPart(int row, int opNo, String opName, String time)									{
		return		Format.printTableRow_left(row, opNo, opName, time);
	}
	public String		printTableRowLeftPart(String opName, String time)									{
		return		Format.printTableRow_left(opName, time);
	}
	
	public String		printTableRowRightPart(String exec, String[] testDataIdentifiers, String[] testDataValues)									{
		return		Format.printTableRow_right(exec,	TestDataColumns.mergeTestDataColumns(testDataIdentifiers, testDataValues)) + "\n";
	}	
	public String		printTableRowRightPart(String exec, int errorCode, String[] testDataIdentifiers, String[] testDataValues)									{
		return		Format.printTableRow_right(exec, errorCode,	TestDataColumns.mergeTestDataColumns(testDataIdentifiers, testDataValues)) + "\n";
	}	
	

	/****************************************************************************************************************************************/
	public String		create_site(int row, int opNo, String opName, String exec, String time,	String region, String siteName)																						{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site(region, siteName)) + "\n";
	}

	public String		create_site_1(int row, int opNo, String opName, String exec, String time,	String region, String siteName)																						{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site(region, siteName)) + "\n";
	}

	public String		create_site_2(int row, int opNo, String opName, String exec, String time,	String region, String siteName)																						{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site(region, siteName)) + "\n";
	}

	public String		delete_site(int row, int opNo, String opName, String exec, String time,	String region, String siteName)																						{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_delete_site(region, siteName)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_siteAlternative(int row, int opNo, String opName, String exec, String time, String region, String siteName, String siteAlternativeName)															{	
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site_alternative(region, siteName, siteAlternativeName)) + "\n";
	}

	public String		delete_siteAlternative(int row, int opNo, String opName, String exec, String time, String region, String siteName, String siteAlternativeName)															{	
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site_alternative(region, siteName, siteAlternativeName)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_siteRoom(int row, int opNo, String opName, String exec, String time, String region, String siteName, String siteRoom, String EVt)														{		
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_site_room(siteName, siteRoom, EVt)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		deactivate_site(int row, int opNo, String opName, String exec, String time,	String region, String siteName)														{		
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_deactivate_site(region, siteName)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_DF(int row, int opNo, String opName, String exec, String time, 
					String siteName, String siteRoom, String EVt, String row_, String plateType)										{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_DF(siteName, siteRoom, EVt, row_, plateType)) + "\n";
	}

	public String		delete_DF(int row, int opNo, String opName, String exec, String time, String siteName, String siteRoom, String EVt, String row_)										{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_DF(siteName, siteRoom, EVt, row_)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_NE(int row, int opNo, String opName, String exec, String time, 
			String neType, String region, String siteName, String neId, String config)													{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_NE(neType, region, siteName, neId, config)) + "\n";
	}
	
	public String		delete_NE(int row, int opNo, String opName, String exec, String time, String neType, String region, String siteName, String neId)													{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_NE(neType, region, siteName, neId)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_project(int row, int opNo, String opName, String exec, String time, 
			String projTemplate, String region, String siteName, String neType)															{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_project(projTemplate, region, siteName, neType)) + "\n";
	}
	
	public String		delete_project(int row, int opNo, String opName, String exec, String time, 
			String projTemplate, String region, String siteName, String neType)															{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_project(projTemplate, region, siteName, neType)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_VBZ(int row, int opNo, String opName, String exec, String time, 
			String neTypeA, String regionA, String siteNameA, String neTypeB, String regionB, String siteNameB)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_create_VBZ(neTypeA, regionA, siteNameA, neTypeB, regionB, siteNameB)) + "\n";
	}
	
	public String		delete_VBZ(int row, int opNo, String opName, String exec, String time, 
			String neTypeA, String regionA, String siteNameA, String neTypeB, String regionB, String siteNameB)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_delete_VBZ(neTypeA, regionA, siteNameA, neTypeB, regionB, siteNameB)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_UDSV(int row, int opNo, String opName, String exec, String time, 
			String neTypeA, String regionA, String siteNameA, String configA, 
			String neTypeB, String regionB, String siteNameB, String configB)															{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_create_UDSV(neTypeA, regionA, siteNameA, configA, neTypeB, regionB, siteNameB, configB)) + "\n";
	}
	
	public String		delete_UDSV(int row, int opNo, String opName, String exec, String time, 
			String neTypeA, String regionA, String siteNameA, String configA, 
			String neTypeB, String regionB, String siteNameB, String configB)															{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_delete_UDSV(neTypeA, regionA, siteNameA, configA, neTypeB, regionB, siteNameB, configB)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_line(int row, int opNo, String opName, String exec, String time, 
			String regionA, String siteNameA, String regionB, String siteNameB, String lineDescription)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_create_Line(regionA, siteNameA, regionB, siteNameB, lineDescription)) + "\n";
	}
	
	public String		delete_line(int row, int opNo, String opName, String exec, String time, String lineDescription)									{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_Line(lineDescription)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		add_NE_to_NE_group(int row, int opNo, String opName, String exec, String time, String neGroup, String neType, String config)																				{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_add_NE_to_NE_group(neGroup, neType, config)) + "\n";
	}
	
	public String		remove_NE_from_NE_group(int row, int opNo, String opName, String exec, String time, String neGroup, String neType)																				{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_remove_NE_from_NE_group(neGroup, neType)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		attach_NE_to_DF(int row, int opNo, String opName, String exec, String time,
			String neType, String siteName, String neId, String EVt, String placePin)																				{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_attach_NE_to_DF(neType, siteName, neId, EVt, placePin)) + "\n";
	}
	
	public String		detach_NE_from_DF(int row, int opNo, String opName, String exec, String time,
			String neType, String siteName, String neId, String EVt, String placePin)																				{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_detach_NE_from_DF(neType, siteName, neId, EVt, placePin)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		terminate_line_on_EP(int row, int opNo, String opName, String exec, String time, 
			String lineDescription, String endPoint, String region, String siteName, String siteRoom, String EVt)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_terminate_line_on_EP(lineDescription, endPoint, region, siteName, siteRoom, EVt)) + "\n";
	}
	
	public String		cancel_line_termin_on_EP(int row, int opNo, String opName, String exec, String time, 
			String lineDescription, String endPoint, String region, String siteName, String siteRoom, String EVt)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_cancel_line_termin_on_EP(lineDescription, endPoint, region, siteName, siteRoom)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		terminate_line_on_DF(int row, int opNo, String opName, String exec, String time, 
			String lineDescription, String endPoint, String placePin, String EVt)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_terminate_line_on_DF(lineDescription, endPoint, EVt, placePin)) + "\n";
	}
	
	public String		cancel_line_termin_on_DF(int row, int opNo, String opName, String exec, String time, String lineDescription, String endPoint, String placePin)									{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_cancel_line_termin_on_DF(lineDescription, endPoint, placePin)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		terminate_line_on_NE(int row, int opNo, String opName, String exec, String time, 
			String lineDescription, String endPoint, String neType, String neConfig)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_terminate_line_on_NE(lineDescription, endPoint, neType, neConfig)) + "\n";
	}
	
	public String		cancel_line_termin_on_NE(int row, int opNo, String opName, String exec, String time, String lineDescription, String endPoint, String neType)									{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_cancel_line_termin_on_NE(lineDescription, endPoint, neType)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		assign_line_to_UDSV(int row, int opNo, String opName, String exec, String time, 
			String regionA, String siteNameA, String regionB, String siteNameB, String lineDescription, String cic)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_assign_line_to_UDSV(regionA, siteNameA, regionB, siteNameB, lineDescription, cic)) + "\n";
	}
	
	public String		remove_line_from_UDSV(int row, int opNo, String opName, String exec, String time, 
			String regionA, String siteNameA, String regionB, String siteNameB, String lineDescription, String cic)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	
						TestDataColumns.col_remove_line_from_UDSV(regionA, siteNameA, regionB, siteNameB, lineDescription, cic)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_configuration(int row, int opNo, String opName, String exec, String time, String basicType, String configName, String startDate)													{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_configuration(basicType, configName, startDate)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		extend_configuration(int row, int opNo, String opName, String exec, String time, String basicType, String configName, String startDate)													{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_extend_configuration(basicType, configName, startDate)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		configuration_addParam(int row, int opNo, String opName, String exec, String time, String basicType, String configName, 
							String paramName, String paramValue)													{
		return		Format.printTableRow(row, opNo, opName, exec, time,	
								TestDataColumns.col_configuration_addParam(basicType, configName, paramName, paramValue)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		configuration_addComp(int row, int opNo, String opName, String exec, String time, String basicType, String configName, String compName)													{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_configuration_addComp(basicType, configName, compName)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		createNE_assigned_to_room(int row, int opNo, String opName, String exec, String time, 
			String neType, String config, String siteName, String roomNo, String neId)													{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_create_NE_assigned_to_room(neType, config, siteName, roomNo, neId)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		rack_planning(int row, int opNo, String opName, String exec, String time, String neType, String region, String siteName, String roomNo, String neId)									{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_rack_planning(neType, region, siteName, roomNo, neId)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		exportIpAddresses(int row, int opNo, String opName, String exec, String time, String exportPath, String exportFile)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_im_export_IPaddresses(exportPath, exportFile)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		deleteIpAddrInNE(int row, int opNo, String opName, String exec, String time, String neType, String region, String siteName, 
							String neId, String countIpAddr)	{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_deleteIpAddrInNE(neType, region, siteName, neId, countIpAddr)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		verifyIpAddrDeleted(int row, int opNo, String opName, String exec, String time, boolean checkResult)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_verifyIpAddrDeleted(checkResult)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		importIpAddresses(int row, int opNo, String opName, String exec, String time, String exportPath, String exportFile)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_im_export_IPaddresses(exportPath, exportFile)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		verifyIpAddrImported(int row, int opNo, String opName, String exec, String time, String exportPath, String exportFile,
							String countImportedIpAddr)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_verifyIpAddrImported(exportPath, exportFile, countImportedIpAddr)) + "\n";
	}	
	/****************************************************************************************************************************************/
	public String		changeNeConf(int row, int opNo, String opName, String exec, String time, 
			String neType, String region, String siteName, String neId, String config, String start)													{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_changeNeConf(neType, region, siteName, neId, config, start)) + "\n";
	}
	
	//****************************************************************************************************************************************//*
	public String		importCompInNeGroup(int row, int opNo, String opName, String exec, String time, String exportPath, String exportFile,
												String neGroupName, String nodeID, String addr1, String startDate)							{

		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_import_compInNeGroup(exportPath, exportFile, 
											neGroupName, nodeID, addr1, startDate)) + "\n";
	}

}
