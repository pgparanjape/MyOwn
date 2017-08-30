package JavaSources.Logging;

public class Format {
	private static	String	separator_				= "";
	
//	private static	int		width_column_1			= 0;
//	private static	int		width_column_2			= 0;
	private static	int		width_column_3			= 0;
	private static	int		width_column_4			= 0;
	private static	int		width_column_5			= 0;
//	private static	int		width_column_6			= 0;
	private static	int		width_column_7			= 0;

//	private static	String		title_column_1			= "";  	//Row
//	private static	String		title_column_2			= "";	//Op.No.
	private static	String		title_column_3			= "";	//Operation
	private static	String		title_column_4			= "";	//Time
	private static	String		title_column_5			= "";	//Exec
//	private static	String		title_column_6			= "";	//ErrorCode
	private static	String		title_column_7			= "";	//Test Data


	public	static	void	setTableConstants( String _separator, 
//						int widthColumn_1, String	titleColumn_1,
//						int widthColumn_2, String	titleColumn_2,
						int widthColumn_3, String	titleColumn_3,	
						int widthColumn_4, String	titleColumn_4,			
						int widthColumn_5, String	titleColumn_5,
//						int widthColumn_6, String	titleColumn_6,
						int widthColumn_7, String	titleColumn_7)						
		{
		separator_		= _separator;	
//		width_column_1		= widthColumn_1;	title_column_1		= titleColumn_1;
//		width_column_2		= widthColumn_2;	title_column_2		= titleColumn_2;
		width_column_3		= widthColumn_3;	title_column_3		= titleColumn_3;
		width_column_4		= widthColumn_4;	title_column_4		= titleColumn_4;
		width_column_5		= widthColumn_5;	title_column_5		= titleColumn_5;
//		width_column_6		= widthColumn_6;	title_column_6		= titleColumn_6;
		width_column_7		= widthColumn_7;	title_column_7		= titleColumn_7;		
	}

	
	public	static	String	getTableHeader()			{
//		return	colHead_1() + colHead_2() + colHead_3() + colHead_4() + colHead_5() + colHead_6() + colHead_7();
		return	 colHead_3() + colHead_4() + colHead_5() + colHead_7();
	}
	
	
	public	static	String	printTableRow(int val1, int val2, String val3, String val4, String val5, String addColumns)			{
		return	 colBody_3(val3) + colBody_4(val4) + colBody_5(val5) + 
//		return	colBody_1(val1) + colBody_2(val2) + colBody_3(val3) + colBody_4(val4) + colBody_5(val5) + 
		separator_ + addColumns;
	}
	
	public	static	String	printTableRow_left(String val3, String val4)			{
		return	colBody_3(val3) + colBody_4(val4);
//		return	colBody_1(val1) + colBody_2(val2) + colBody_3(val3) + colBody_4(val4);
	}
	
	public	static	String	printTableRow_left(int val1, int val2, String val3, String val4)			{
		return	colBody_3(val3) + colBody_4(val4);
//		return	colBody_1(val1) + colBody_2(val2) + colBody_3(val3) + colBody_4(val4);
	}
	
	public	static	String	printTableRow_right(String val5, String addColumns)			{
		return	colBody_5(val5) + separator_ + addColumns;
//		return	colBody_5(val5) + colBody_6(val6) + separator_ + addColumns;
	}
	
	public	static	String	printTableRow_right(String val5, int val6, String addColumns)			{
		return	colBody_5(val5) + separator_ + addColumns;
//		return	colBody_5(val5) + colBody_6(val6) + separator_ + addColumns;
	}

	public	static	String	additionalColumns(String colVal1, String colVal2, String colVal3, String colVal4, String colVal5, String colVal6)			{
		String addColumns	= "";
		
//		if( (colVal1 != null) && (colVal1.length() > 0) )		addColumns		= addColumns + colVal1;
//		if( (colVal2 != null) && (colVal2.length() > 0) )		addColumns		= addColumns + colVal2;
		if( (colVal3 != null) && (colVal3.length() > 0) )		addColumns		= addColumns + colVal3;
		if( (colVal4 != null) && (colVal4.length() > 0) )		addColumns		= addColumns + colVal4;
		if( (colVal5 != null) && (colVal5.length() > 0) )		addColumns		= addColumns + colVal5;
//		if( (colVal6 != null) && (colVal6.length() > 0) )		addColumns		= addColumns + colVal6;
		
		return	addColumns;
	}

	public static String getAddCol_1(int width, String val) {
		String addCol1 = "";
		if ((val != null) && (val.length() > 0))
			addCol1 = "";
		return addCol1;
	}
	
	private static String column(String separator, int width, Object value) {
		String col = null;
		int i, initLength;

		col = separator + " " + value.toString();
		initLength = col.length();

		for (i = 0; i < (width - initLength); i++)
			col = col + " ";

		return col;
	}	

	// Column Headers	
/*	private static String	colHead_1()	{
		return	column(separator_, width_column_1, title_column_1);
	}	
	
	private static String	colHead_2()	{
		return	column(separator_, width_column_2, title_column_2);
	}
*/
	private static String	colHead_3()	{
		return	column(separator_, width_column_3, title_column_3);
	}	
	
	private static String	colHead_4()	{
		return	column(separator_, width_column_4, title_column_4);
	}

	private static String	colHead_5()	{
		return	column(separator_, width_column_5, title_column_5);
	}
	
	/*private static String	colHead_6()	{
		return	column(separator_, width_column_6, title_column_6);
	}*/

	private static String	colHead_7()	{
		return	column(separator_, width_column_7, title_column_7);
	}

	// Column Bodies
	/*private static String	colBody_1(int columnValue1)	{
		return	column(separator_, width_column_1, Integer.toString(columnValue1));
	}	
	
	private static String	colBody_2(int columnValue2)	{
		return	column(separator_, width_column_2, Integer.toString(columnValue2));
	}*/

	private static String	colBody_3(String columnValue3)	{
		return	column(separator_, width_column_3, columnValue3);
	}
	
	private static String	colBody_4(String columnValue4)	{
		return	column(separator_, width_column_4, columnValue4);
	}

	private static String	colBody_5(String columnValue5)	{
		return	column(separator_, width_column_5, columnValue5);
	}

	/*private static String	colBody_6(int columnValue6)	{
		return	column(separator_, width_column_6, Integer.toString(columnValue6));
	}*/
}
