import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) {
		Driver driver = new oracle.jdbc.OracleDriver();
		Connection connection = null;
		Statement stmt = null;
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.234.105:1521:tsLPXM7",
					"nedb_schema", "nepwdb");
			System.out.println("Connection");
			stmt = connection.createStatement();

//			String sql = "SELECT * FROM pu$udsvs";
//			String sqlQuery = "Select * from  ne$suppliers where Supl_Supplier_Name like 'ALCATEL'";
//			String sqlQuery = "Select pers_id from inf_personen where pers_name like 'test_admin'";
			String sqlQuery = "Update inf_personen set pers_supl_id = 20 where pers_id='165153'";
//			String sqlQuery = "Select * from inf_personen where pers_id='165153'";
			
			System.out.println(stmt.executeUpdate(sqlQuery));
//			ResultSet rs = stmt.executeQuery(sqlQuery);
			/*ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnsNumber = rsmd.getColumnCount();
			
			for(int i = 1; i <= columnsNumber; i++)
			{
			    System.out.println(rsmd.getColumnName(i));
			}

			// Iterate through the data in the result set and display it. 

			while (rs.next()) {
				// Print one row
				for (int i = 1; i <= columnsNumber; i++) {
					System.out.print(rs.getString(i) + " "); // Print one element of a row
				}
				System.out.println();// Move to the next line to print the next row.

			}*/
			// STEP 5: Extract data from result set
			/*try {
				while (rs.next()) {
					if (rs.getRow() > 8000) {
						break;
					}
					String coffeeName = rs.getString(1);
					int supplierID = rs.getInt(2);
					float price = rs.getFloat(3);
					int sales = rs.getInt(4);
//					int total = rs.getInt(5);
					System.out.println(coffeeName + "\t" + supplierID + "\t"
							+ price + "\t" + sales + "\t");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}*/
//			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.setAutoCommit(true);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					connection.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try
		System.out.println("Goodbye!");
	}// end main

}
