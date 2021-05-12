package javafx.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="settings")
@XmlAccessorType(XmlAccessType.FIELD)
public class DBConnection {
	@XmlTransient
	private static Connection con;
	@XmlTransient
	private static DBConnection _DBConection;
	
	private DBConnection() {
		server="";
		database="";
		user="";
		password="";
	}
	
	public static DBConnection getDBConnection() {
		if(_DBConection==null) {
			_DBConection=new DBConnection();
		}
		
		return _DBConection;
	}
	
	@XmlElement(name="server")
	private String server;
	@XmlElement(name="database")
	private String database;
	@XmlElement(name="user")
	private String user;
	@XmlElement(name="password")
	private String password;
	
	private void setSettings(String server, String database, String user, String password) {
		this.server=server;
		this.database=database;
		this.user=user;
		this.password=password;
	}
	
	public String getServer() {
		return server;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public static boolean conect() {
		
		try {
			//Esto pa que sirve?
			//Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(DBConnection.getDBConnection().getServer()+"/"+DBConnection.getDBConnection().getDatabase(),DBConnection.getDBConnection().getUser(),
			DBConnection.getDBConnection().getPassword());
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
	
	public boolean saveSettings()
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(DBConnection.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(DBConnection.getDBConnection(), new File("settings.xml"));
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al guardar los ajustes de conexion de la base de datos");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean loadSettings()
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(DBConnection.class);
			Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
			DBConnection conect=((DBConnection) unmarshaller.unmarshal(new File("settings.xml")));
			setSettings(conect.getServer(), conect.getDatabase(), conect.getUser(), conect.getPassword());
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al cargar los ajustes de conexion de la base de datos");
			e.printStackTrace();
		}
		
		return result;
	}
}
