package PB_2015_2.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBConnection {
	static private final String host = "10.1.234.105";
	static private final String port = "1521";
	static private final String dbname = "tslpxm9";
	static private final String user = "nedb_schema";
	static private final String pswd = "nepwdb";
	static Connection connection;

	public static void main(String[] args) throws SQLException {
		/*
		 * Driver driver = new oracle.jdbc.OracleDriver();
		 * DriverManager.registerDriver(driver); connection =
		 * DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":" + port
		 * + ":" + dbname, user, pswd); connection.setAutoCommit(true);
		 * query("SELECT * FROM ne_sequences ;"); connection.close();
		 */

		testDB();
	}

	static private Connection getConnection() {
		try {
			// Load Oracle JDBC driver.
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			// Define and initialize a JDBC connection.
			return DriverManager.getConnection("jdbc:oracle:thin:@" + host
					+ ":" + port + ":" + dbname, user, pswd);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	private static boolean testDB() {
		boolean ok = false;
		Connection conn = getConnection();
		if (conn == null)
			System.out.println("No connection");
		else {
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				pst = conn
						.prepareStatement("commit");
/*				pst = conn
						.prepareStatement("SELECT * FROM ne_sequences WHERE nesq_name = 'RNC'");
*/				
				/*pst = conn
						.prepareStatement("UPDATE ne_sequences ne  SET ne.nesq_max = 200 WHERE ne.nesq_name = 'RNC'");*/
				rs = pst.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rsmd.getColumnCount();
				while (rs.next()) {

					for (int i = 0; i < columns; i++) {
						System.out.print(rs.getObject(i + 1) + " ");
					} // for

					System.out.println();
				} // while

			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			} finally {
				terminate(conn, pst, rs);
			}
		}
		return ok;
	}

	static private void terminate(Connection conn, PreparedStatement pst,
			ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException ex) {
			System.out.println("Close RS: " + ex.getMessage());
		}
		try {
			if (pst != null)
				pst.close();
		} catch (SQLException ex) {
			System.out.println("Close PST: " + ex.getMessage());
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException ex) {
			System.out.println("Close Conn: " + ex.getMessage());
		}
	}
}
