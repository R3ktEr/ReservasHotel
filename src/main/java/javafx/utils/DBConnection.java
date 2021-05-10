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
	
	public static boolean conect() {
		
		try {
			//Esto pa que sirve?
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(SERVER+"/"+DATABASE,USER,PASSWORD);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Utils.popError("Error: El servidor no responde\n\nDebido a la severidad de este error, la aplicacion se cerrará");
			//e.printStackTrace();
			return false;
		} 
	}
	
	public static Connection getConnection() {
		if(con==null) {
			if(conect()) {
				return con;
			}else {
				return null;
			}
		}
		
		return con;
	}
}
