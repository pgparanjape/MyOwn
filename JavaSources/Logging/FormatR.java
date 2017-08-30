package JavaSources.Logging;

public class FormatR {
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

	public	FormatR()	{}
	
	public void 	initMainTable()		{
		Format.setTableConstants(SEPARATOR1, 
//				WIDTH_COL_1, TITLE_COLUMN_1, WIDTH_COL_2, TITLE_COLUMN_2, 
				WIDTH_COL_3, TITLE_COLUMN_3, WIDTH_COL_4, TITLE_COLUMN_4, WIDTH_COL_5, TITLE_COLUMN_5, 
//				WIDTH_COL_6, 
//				TITLE_COLUMN_6, 
				WIDTH_COL_7, TITLE_COLUMN_7);
	}
	
	public void 	initSubTable()		{
		TestDataColumns.setTableConstants(SEPARATOR2);
	}
	
	public String	printTableHeader()	{
		return	Format.getTableHeader() + "\n";
	}
	
	/****************************************************************************************************************************************/
	public String		create_site(int row, int opNo, String opName, String exec, String time,	String region, String siteName)			{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_site(region, siteName)) + "\n";
	}

	public String		delete_site(int row, int opNo, String opName, String exec, String time,	String region, String siteName)			{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_delete_site(region, siteName)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_cell(int row, int opNo, String opName, String exec, String time,	String region, String siteName, String cellId, String generation)			{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_cell(region, siteName, cellId, generation)) + "\n";
	}

	public String		delete_cell(int row, int opNo, String opName, String exec, String time,	String region, String siteName, String cellId, String generation)			{		
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_delete_cell(region, siteName, cellId, generation)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_ST_order(int row, int opNo, String opName, String exec, String time, String region, String siteName,
					String cellId, String generation, String configST, String startDate)						{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_order(siteName, cellId, generation, configST, startDate)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		create_AS_order(int row, int opNo, String opName, String exec, String time,	String region, String siteName,
					String cellId, String generation, String configAS, String startDate)						{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_create_order(siteName, cellId, generation, configAS, startDate)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		fixST(int row, int opNo, String opName, String exec, String time, String neType, String siteName, String neId, String config, String startDate)		{
		return		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_fix_ST(siteName, neId, neType, config, startDate)) + "\n";
	}
	
	public String		unfixST(int row, int opNo, String opName, String exec, String time, String neType, String siteName, String neId, String config)			{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_unfix_ST(siteName, neId, neType, config)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		fixAS(int row, int opNo, String opName, String exec, String time, String siteName, String neId, String config, String startDate)		{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_fix_AS(siteName, neId, config, startDate)) + "\n";
	}
	
	public String		unfixAS(int row, int opNo, String opName, String exec, String time, String siteName, String neId, String config)			{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_unfix_AS(siteName, neId, config)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		deleteST(int row, int opNo, String opName, String exec, String time, String neType, String region, String siteName, String neId,
					String generation)														{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_ST(neType, region, siteName, neId, generation)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		deleteAS(int row, int opNo, String opName, String exec, String time, String region, String siteName, String neId, String generation)	{
		return		Format.printTableRow(row, opNo, opName, exec, time,	TestDataColumns.col_delete_AS(region, siteName, neId, generation)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		cancel_orders(int row, int opNo, String opName, String exec, String time,	String region, String siteName,
					String cellId, String generation)											{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_cancel_orders(region, siteName, cellId, generation)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		load_ND(int row, int opNo, String opName, String exec, String time, String netDefName, String cutOffDate)											{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_load_network(netDefName, cutOffDate)) + "\n";
	}
	/****************************************************************************************************************************************/
	public String		count_cells_of_ND(int row, int opNo, String opName, String exec, String time, String netDefName, String cutOffDate, String numOfCells)											{
		return 		Format.printTableRow(row, opNo, opName, exec, time, TestDataColumns.col_count_cells_of_ND(netDefName, cutOffDate, numOfCells)) + "\n";
	}
	/****************************************************************************************************************************************/

}
