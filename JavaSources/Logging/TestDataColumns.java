package JavaSources.Logging;

public class TestDataColumns 												{
	public static String separator = "";

	public static void setTableConstants(String _separator) {
		separator = _separator;
	}

	private static String addColumn(String identifier, String value) {
		return identifier + "<" + value + ">" + separator;
	}

	private static String addColumn(String identifier, boolean value) {
		return identifier + "<" + value + ">" + separator;
	}

	public static String mergeTestDataColumns(String[] testDataIdentifiers, String[] testDataValues) {
		String row = "";
		int i;
		for (i = 0; i < testDataIdentifiers.length; i++) {
			row = row + addColumn(testDataIdentifiers[i], testDataValues[i]);
		}
		return row.substring(0, row.lastIndexOf("+"));
	}
	/*
	 * ----------------------------    Fixed & Build    ----------------------------
	 */
	public static	String	col_create_site(String regionValue, String siteNameValue)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue);
	}

	public static	String	col_delete_site(String regionValue, String siteNameValue)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue);
	}

	public static	String	col_create_site_alternative(String regionValue, String siteNameValue, String siteAlternativeNameValue)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("SiteAlternativeName", siteAlternativeNameValue);
	}

	public static	String	col_delete_site_alternative(String regionValue, String siteNameValue, String siteAlternativeNameValue)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("SiteAlternativeName", siteAlternativeNameValue);
	}

	public static	String	col_create_site_room(String siteNameValue, String siteRoomValue, String EVtValue)		{
		return addColumn("SiteName", siteNameValue) + addColumn("SiteRoom", siteRoomValue) + addColumn("EVt", EVtValue);
	}

	public static	String	col_deactivate_site(String regionValue, String siteNameValue)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue);
	}

	public static	String	col_create_DF(String siteNameValue, String siteRoomValue, String EVtValue, String rowValue, String plateTypeValue)	{
		return addColumn("SiteName", siteNameValue) + addColumn("SiteRoom", siteRoomValue) + addColumn("EVt", EVtValue) + 
				addColumn("Row", rowValue) + addColumn("PlateType", plateTypeValue);
	}

	public static	String	col_delete_DF(String siteNameValue, String siteRoomValue, String EVtValue, String rowValue)								{
		return addColumn("SiteName", siteNameValue) + addColumn("SiteRoom", siteRoomValue) + addColumn("EVt", EVtValue) + addColumn("Row", rowValue);
	}

	public static	String	col_create_NE(String NE_TypeValue, String regionValue, String siteNameValue, String NE_IdValue, String configValue)		{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-ID", NE_IdValue) + addColumn("Configuration", configValue);
	}

	public static	String	col_delete_NE(String NE_TypeValue, String regionValue, String siteNameValue, String NE_IdValue)		{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("NE-ID", NE_IdValue);
	}

	public static	String	col_create_project(String projectTemplate, String regionValue, String siteNameValue, String NE_TypeValue)		{
		return addColumn("ProjectTemplate", projectTemplate) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-Type", NE_TypeValue);
	}

	public static	String	col_delete_project(String projectTemplate, String regionValue, String siteNameValue, String NE_TypeValue)		{
		return addColumn("ProjectTemplate", projectTemplate) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue);
	}

	public static	String	col_create_VBZ(String NE_TypeValue, String regionValue, String siteNameValue, 
			String NE_Type2Value, String region2Value, String siteName2Value)						{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-TypeB", NE_Type2Value) + addColumn("RegionB", region2Value) + addColumn("SiteNameB", siteName2Value);
	}

	public static	String	col_delete_VBZ(String NE_TypeValue, String regionValue, String siteNameValue, 
			String NE_Type2Value, String region2Value, String siteName2Value)						{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-TypeB", NE_Type2Value) + addColumn("RegionB", region2Value) + addColumn("SiteNameB", siteName2Value);
	}

	public static	String	col_create_UDSV(String NE_TypeValue, String regionValue, String siteNameValue, String configValue,
			String NE_Type2Value, String region2Value, String siteName2Value, String config2Value)									{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("Configuration", configValue) +
				addColumn("NE-TypeB", NE_Type2Value) + addColumn("RegionB", region2Value) + addColumn("SiteNameB", siteName2Value) + 
				addColumn("ConfigurationB", config2Value);
	}

	public static	String	col_delete_UDSV(String NE_TypeValue, String regionValue, String siteNameValue, String configValue,
			String NE_Type2Value, String region2Value, String siteName2Value, String config2Value)									{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("Configuration", configValue) +
				addColumn("NE-TypeB", NE_Type2Value) + addColumn("RegionB", region2Value) + addColumn("SiteNameB", siteName2Value) + 
				addColumn("ConfigurationB", config2Value);
	}

	public static	String	col_create_Line(String regionValue, String siteNameValue, String region2Value, String siteName2Value, String lineDescription)									{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("LineDescription", lineDescription);
	}

	public static	String	col_delete_Line(String lineDescription)									{
		return addColumn("LineDescription", lineDescription);
	}

	public static	String	col_add_NE_to_NE_group(String NEgroupValue, String NEtypeValue, String configValue)						{
		return addColumn("NE-group", NEgroupValue) + addColumn("NE-Type", NEtypeValue) + addColumn("Configuration", configValue);
	}

	public static	String	col_remove_NE_from_NE_group(String NEgroupValue, String NEtypeValue)						{
		return addColumn("NE-group", NEgroupValue) + addColumn("NE-Type", NEtypeValue);
	}

	public static	String	col_attach_NE_to_DF(String NE_TypeValue, String siteNameValue, String NE_IdValue, String EVtValue, String dfTermination)						{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("SiteName", siteNameValue) + addColumn("NE-ID", NE_IdValue) + addColumn("EVt", EVtValue) + 
				addColumn("Place-Pin", dfTermination);
	}

	public static	String	col_detach_NE_from_DF(String NE_TypeValue, String siteNameValue, String NE_IdValue, String EVtValue, String dfTermination)						{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("SiteName", siteNameValue) + addColumn("NE-ID", NE_IdValue) + addColumn("EVt", EVtValue) +
				addColumn("Place-Pin", dfTermination);
	}

	public static	String	col_terminate_line_on_EP(String lineDescription, String endPoint, String regionValue, String siteNameValue, 
			String siteRoomValue, String EVtValue)						{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + addColumn("Region", regionValue) + 
				addColumn("SiteName", siteNameValue) + addColumn("SiteRoom", siteRoomValue) + addColumn("EVt", EVtValue);
	}

	public static	String	col_cancel_line_termin_on_EP(String lineDescription, String endPoint, String regionValue, String siteNameValue, 
			String siteRoomValue)	
	{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + addColumn("Region", regionValue) + 
				addColumn("SiteName", siteNameValue) + addColumn("SiteRoom", siteRoomValue);
	}

	public static	String	col_terminate_line_on_DF(String lineDescription, String endPoint, String EVtValue, String dfTermination)						{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + addColumn("EVt", EVtValue) + 
				addColumn("Place-Pin", dfTermination);
	}

	public static	String	col_cancel_line_termin_on_DF(String lineDescription, String endPoint, String dfTermination)						{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + addColumn("Place-Pin", dfTermination);
	}

	public static	String	col_terminate_line_on_NE(String lineDescription, String endPoint, String NE_TypeValue, String configValue)						{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + 
				addColumn("NE-Type", NE_TypeValue) + addColumn("Configuration", configValue);
	}

	public static	String	col_cancel_line_termin_on_NE(String lineDescription, String endPoint, String NE_TypeValue)						{
		return addColumn("LineDescription", lineDescription) + addColumn("EndPoint", endPoint) + addColumn("NE-Type", NE_TypeValue);
	}

	public static	String	col_assign_line_to_UDSV(String regionValue, String siteNameValue, String region2Value, String siteName2Value,
			String lineDescription, String cic)						{

		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("RegionB", region2Value) + 
				addColumn("SiteNameB", siteName2Value) + addColumn("LineDescription", lineDescription) + addColumn("CIC", cic);
	}

	public static	String	col_remove_line_from_UDSV(String regionValue, String siteNameValue, String region2Value, String siteName2Value,
			String lineDescription, String cic)																					{

		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("RegionB", region2Value) + 
				addColumn("SiteNameB", siteName2Value) + addColumn("LineDescription", lineDescription) + addColumn("CIC", cic);
	}

	public static	String	col_create_configuration(String basicTypeValue, String configName, String startDate)		{
		return addColumn("Basic Type", basicTypeValue) + addColumn("Configuration Name", configName) + addColumn("Start Date", startDate);
	}

	public static	String	col_extend_configuration(String basicTypeValue, String configName, String startDate)		{
		return addColumn("Basic Type", basicTypeValue) + addColumn("Configuration Name", configName) + addColumn("Start Date", startDate);
	}

	public static	String	col_configuration_addParam(String basicTypeValue, String configName, String paramName, String paramValue)		{
		return addColumn("Basic Type", basicTypeValue) + addColumn("Configuration Name", configName) + addColumn("Parameter Name", paramName) +
				addColumn("Parameter Value", paramValue);
	}

	public static	String	col_configuration_addComp(String basicTypeValue, String configName, String compName)		{
		return addColumn("Basic Type", basicTypeValue) + addColumn("Configuration Name", configName) + addColumn("Component Name", compName);
	}

	public static	String	col_create_NE_assigned_to_room(String neTypeValue, String configName, String siteName, String roomNo, String neId)		{
		return addColumn("NE-Type", neTypeValue) + addColumn("Configuration Name", configName) + addColumn("Site Name", siteName) + 
				addColumn("Room No", roomNo) + addColumn("NE-ID", neId);
	}

	public static	String	col_rack_planning(String neTypeValue, String regionValue, String siteNameValue, String roomNo, String neId)				{
		return addColumn("NE-Type", neTypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("Room No", roomNo) + addColumn("NE-ID", neId);
	}

	public static	String	col_userPref_setExportPath(String exportPath)				{
		return addColumn("export path", exportPath);
	}

	public static	String	col_im_export_IPaddresses(String exportPath, String exportFile)				{
		return addColumn("export path", exportPath) + addColumn("export file", exportFile);
	}

	public static	String	col_deleteIpAddrInNE(String NE_TypeValue, String regionValue, String siteNameValue, String NE_IdValue, String countIpAddr)		{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-ID", NE_IdValue) + addColumn("IP@ counted", countIpAddr);
	}
	public static	String	col_verifyIpAddrDeleted(boolean newState)		{
		return addColumn("check result - all IP@ are deleted", newState);
	}

	public static	String	col_verifyIpAddrImported(String exportPath, String exportFile, String numIpAddrImported)				{
		return addColumn("export path", exportPath) + addColumn("export file", exportFile) + addColumn("count imported IP@", numIpAddrImported);
	}

	public static	String	col_changeNeConf(String NE_TypeValue, String regionValue, String siteNameValue, String NE_IdValue, String configValue, String startValue)		{
		return addColumn("NE-Type", NE_TypeValue) + addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + 
				addColumn("NE-ID", NE_IdValue) + addColumn("Configuration", configValue)  + addColumn("Start", startValue);
	}

	public static	String	col_import_compInNeGroup(String exportPath, String exportFile, String neGroupName, String nodeID, String addr1, 
			String startDate)				{
		return addColumn("export path", exportPath) + addColumn("export file", exportFile) + addColumn("NE-group", neGroupName) + 
				addColumn("OSS-Node-ID", nodeID) + addColumn("address1", addr1) + addColumn("StartDate", startDate);
	}

	
	
//	 * ---------------------------------    Radio    ----------------------------------
	 
	public static	String	col_create_cell(String regionValue, String siteNameValue, String cellId, String generation)		{
		return 	addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("Cell-ID", cellId) + 
				addColumn("Generation", generation);
	}

	public static	String	col_create_order(String siteNameValue, String cellId, String generation, String configSTAA, String startDate)		{
		return 	addColumn("SiteName", siteNameValue) + addColumn("Cell-ID", cellId) + addColumn("Generation", generation) +
				addColumn("ST configuartion", configSTAA) + addColumn("Start Date", startDate);
	}

	public static	String	col_fix_ST(String siteNameValue, String neId, String neType, String config, String startDate)		{
		return 	addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("NE-Type", neType) +
				addColumn("Configuration", config) + addColumn("Start Date", startDate);
	}

	public static	String	col_fix_AS(String siteNameValue, String neId, String config, String startDate)		{
		return 	addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("Configuration", config) +
				addColumn("Start Date", startDate);
	}

	public static	String	col_unfix_ST(String siteNameValue, String neId, String neType, String config)		{
		return 	addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("NE-Type", neType) +	
				addColumn("Configuration", config);
	}

	public static	String	col_unfix_AS(String siteNameValue, String neId, String config)		{
		return addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("Configuration", config);
	}


	public static	String	col_delete_ST(String regionValue, String siteNameValue, String neId, String neType, String generation)		{
		return 	addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("NE-Type", neType) +
				addColumn("Generation", generation);
	}


	public static	String	col_delete_AS(String regionValue, String siteNameValue, String neId, String generation)		{
		return 	addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("NE-ID", neId) + addColumn("Generation", generation);
	}


	public static	String	col_cancel_orders(String regionValue, String siteNameValue, String cellId, String generation)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("Cell-ID", cellId) + 
				addColumn("Generation", generation);
	}

	public static	String	col_delete_cell(String regionValue, String siteNameValue, String cellId, String generation)		{
		return addColumn("Region", regionValue) + addColumn("SiteName", siteNameValue) + addColumn("Cell-ID", cellId) + 
				addColumn("Generation", generation);
	}

	public static	String	col_load_network(String netDefName, String cutOffDate)											{
		return addColumn("Network Definition", netDefName) + addColumn("Cutoff Date", cutOffDate);
	}

	public static	String	col_count_cells_of_ND(String netDefName, String cutOffDate, String numOfCells)											{
		return addColumn("Network Definition", netDefName) + addColumn("Cutoff Date", cutOffDate) + addColumn("no. of cells", numOfCells);
	}
}
