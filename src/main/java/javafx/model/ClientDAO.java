package javafx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.utils.DBConnection;
import javafx.utils.Utils;

public class ClientDAO extends Client{
	
	private static final String SAVECLIENT="Insert into client (ID, name, NIF, nationality, room, inistance, endstance, ncompanions) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATECLIENT="Update client set name=?, NIF=?, nationality=?, room=?, inistance=?, endstance=?, ncompanions=? where ID=?";
	private static final String GETCLIENTBYNIF="Select * from client where NIF=";
	private static final String DELETEBYID="Delete from client where ID=";
	private static final String GETALLCLIENTS="Select * from client";
	
	private ObservableList<Client> clientList;
	
	public ClientDAO() {
		super();
		this.clientList=FXCollections.observableArrayList();
	}
	
	public ClientDAO(Client c) {
		super(c.name,c.NIF,c.ID,c.nationality,c.room,c.inistance,c.endstance,c.ncompanions);
		this.clientList=FXCollections.observableArrayList();
	}
	
	public ClientDAO(String name, String nIF, int iD, String nationality, int room, LocalDate inistance, LocalDate endstance, int ncompanions) {
		super(name, nIF, iD, nationality, room, inistance, endstance, ncompanions);
		this.clientList=FXCollections.observableArrayList();
	}
	
	public void iniClientList(ObservableList<Client> clientList) {
		this.clientList=clientList;
	}
	
	public ObservableList<Client> getClientList() {
		return clientList;
	}

	public ObservableList<Client> getAllClients() {
		Connection con=DBConnection.getConnection();
		Client c;
		
		if(con!=null) {
			try {
				PreparedStatement ps=con.prepareStatement(GETALLCLIENTS);
				ResultSet rs=ps.executeQuery();
				
				while(rs.next()) {
					c=new Client();
					
					c.setID(rs.getInt("ID"));
					c.setName(rs.getString("name"));
					c.setNIF(rs.getString("NIF"));
					c.setNationality(rs.getString("nationality"));
					c.setRoom(rs.getInt("room"));
					c.setInistance(Utils.dateToLocalDate(rs.getDate("inistance")));
					c.setEndstance(Utils.dateToLocalDate(rs.getDate("endstance")));
					c.setNcompanions(rs.getInt("ncompanions"));
					
					this.clientList.add(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.clientList;
	}
	
	public void saveClient() {
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				PreparedStatement ps=con.prepareStatement(SAVECLIENT);
				ps.setString(1, this.name);
				ps.setString(2, this.NIF);
				ps.setString(3, this.nationality);
				ps.setInt(4, this.room);
				ps.setDate(5, Utils.localDateToDate(this.inistance));
				ps.setDate(6, Utils.localDateToDate(this.endstance));
				ps.setInt(7, this.ncompanions);
				
				//On duplicate key
				ps.setString(8, this.name);
				ps.setString(9, this.NIF);
				ps.setString(10, this.nationality);
				ps.setInt(11, this.room);
				ps.setDate(12, Utils.localDateToDate(this.inistance));
				ps.setDate(13, Utils.localDateToDate(this.endstance));
				ps.setInt(14, this.ncompanions);
				
				ps.executeUpdate();
				getDatabaseID();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("Error al conectarse a la base de datos");
		}
	}
	
	public void updateClient() {
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				PreparedStatement ps=con.prepareStatement(UPDATECLIENT);
				ps.setString(1, this.name);
				ps.setString(2, this.NIF);
				ps.setString(3, this.nationality);
				ps.setInt(4, this.room);
				ps.setDate(5, Utils.localDateToDate(this.inistance));
				ps.setDate(6, Utils.localDateToDate(this.endstance));
				ps.setInt(7, this.ncompanions);
				
				ps.setInt(8, this.ID);
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("Error al conectarse a la base de datos");
		}
	}
	
	public void getDatabaseID() {
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(GETCLIENTBYNIF+"'"+this.NIF+"'");
				
				if(rs.next()) {
					this.ID=rs.getInt("ID");				
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("Error al conectarse a la base de datos");
		}
	}
	
	public Client getClientByNif() {
		Connection con=DBConnection.getConnection();
		Client c=null;
		
		if(con!=null) {
			try {
				c=new Client();
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(GETCLIENTBYNIF+"'"+this.NIF+"'");
				
				c.setID(rs.getInt("ID"));
				c.setName(rs.getString("name"));
				c.setNIF(rs.getString("NIF"));
				c.setNationality(rs.getString("nationality"));
				c.setRoom(rs.getInt("room"));
				c.setInistance(Utils.dateToLocalDate(rs.getDate("inistance")));
				c.setEndstance(Utils.dateToLocalDate(rs.getDate("endstance")));
				c.setNcompanions(rs.getInt("ncompanions"));	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			System.out.println("Error al conectarse a la base de datos");
		}
		
		return c;
	}
	
	public void deleteClientByID() {
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				Statement st=con.createStatement();
				st.executeUpdate(DELETEBYID+"'"+this.ID+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Error al conectarse a la base de datos");
		}
	}
}
