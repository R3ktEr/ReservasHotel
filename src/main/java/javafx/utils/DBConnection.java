package javafx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection con;
	
	private static final String SERVER="jdbc:mysql://localhost";
	private static final String DATABASE="hotel";
	private static final String USER="root";
	private static final String PASSWORD="";
	
	public static void conect() {
		
		try {
			//Esto pa que sirve?
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(SERVER+"/"+DATABASE,USER,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static Connection getConnection() {
		if(con==null) {
			conect();
		}
		
		return con;
	}
}
