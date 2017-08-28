package PB_2015_2.Common;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Session {

	public static void main(String[] args) {
		String sqlQuery = "SELECT v.CLIENT_INFO , v.* FROM v$session v ";
		Driver driver = new oracle.jdbc.OracleDriver();
		Connection connection = null;
		Statement stmt = null;
		String dbName = "TSLPXM10";
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.1.234.105:1521:" + dbName,
					"nedb_schema", "nepwdb");
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			System.out.println("Getting open session information ...");
			boolean found = false;

			while (rs.next()) {
				String temp = rs.getString("CLIENT_INFO");
				if (temp != null) {
					System.out.println(temp);
					found = true;
				}
			}
			if (found)
				System.out.println("");
			else 
				System.out.println("No Open sessions");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}