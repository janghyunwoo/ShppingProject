package util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;


public class DBConnectionPool extends Object {

	private static DBConnectionPool instance;
	private static DataSource ds = null;
	private static final String dbName = "jdbc/oracleDS";

	DBConnectionPool() throws SQLException {
		init();
	}

	public static DBConnectionPool getInstance() throws SQLException {
		if (instance == null) {
			instance = new DBConnectionPool();
		}
		return instance;
	}

	

	public void init() throws SQLException {
		try {
			Context intContext = new InitialContext();
			Context envContext = (Context) intContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup(dbName);
		} catch (NamingException e) {
			System.err.println("Problem looking up " + dbName + ": " + e);
		}
	}

	public static Connection getPoolConnection() throws SQLException {

/*		Connection conn = null;
		String driverName = "oracle.jdbc.OracleDriver";
		Class.forName(driverName);
		String serverName = "localhost";
		String serverPort = "1521";
		String sid = "orcl";
		String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
		String username = "shoppingproject";
		String password = "password";
		conn = DriverManager.getConnection(url, username, password);*/

	 
		instance = DBConnectionPool.getInstance();
		Connection conn =ds.getConnection();
		if (conn == null) {
			throw new SQLException("Maximum number ofconnections in pool exceeded.");
		}
		return conn;
	}
}