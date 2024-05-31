package integracion.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String bd = "tienda-is2";
	private static final String login = "root";
	private static final String password = "";
	private static final String url = "jdbc:mysql://localhost/" + bd;
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, login, password);
			} catch (SQLException e) {
				System.out.println(e);
			} catch (ClassNotFoundException e) {
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return connection;
	}
	
	public static void desconectar() {
		connection = null;
	}
}
