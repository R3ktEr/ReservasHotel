package javafx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.utils.DBConnection;
import javafx.utils.Utils;

public class RoomDAO extends Room{

	private static final String GETALLROOMS="Select * from room";
	private static final String UPDATEROOMS="Update room set state=? where number=?";
	
	public ObservableList<Room> roomsList;
	
	public RoomDAO() {
		super();
		this.roomsList=FXCollections.observableArrayList();
	}
	
	public RoomDAO(int number, int floor, String zone, int capacity, String type, double price, String status) {
		super(number, floor, zone, capacity, type, price, status);
		this.roomsList=FXCollections.observableArrayList();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((roomsList == null) ? 0 : roomsList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomDAO other = (RoomDAO) obj;
		if (roomsList == null) {
			if (other.roomsList != null)
				return false;
		} else if (!roomsList.equals(other.roomsList))
			return false;
		return true;
	}

	public ObservableList<Room> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(ObservableList<Room> roomsList) {
		this.roomsList = roomsList;
	}
	
	public ObservableList<Room> loadRoomList() {	
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				PreparedStatement q=con.prepareStatement(GETALLROOMS);
				ResultSet rs=q.executeQuery();
				
				while(rs.next()) {
					Room r=new Room();
					r.setNumber(rs.getInt("number"));
					r.setFloor(rs.getInt("floor"));
					r.setZone(rs.getString("zone"));
					r.setCapacity(rs.getInt("capacity"));
					r.setType(rs.getString("type"));
					r.setPrice(rs.getDouble("price"));
					r.setStatus(rs.getString("state"));
					
					if(!this.roomsList.contains(r)) {
						this.roomsList.add(r);						
					}
				}
				
			} catch (SQLException e) {
				Utils.popError("Error al cargar la base de datos: Se recomienda reiniciar la aplicacion");
				e.printStackTrace();
			}
		}
		
		return this.roomsList;
	}
	
	public void updateRoom() {
		Connection con=DBConnection.getConnection();
		
		if(con!=null) {
			try {
				PreparedStatement ps=con.prepareStatement(UPDATEROOMS);
				
				ps.setString(1, this.status);
				ps.setInt(2, this.number);
			
				ps.executeUpdate();
				
			} catch (SQLException e) {
				Utils.popError("Error al cargar la base de datos: Se recomienda reiniciar la aplicacion");
				e.printStackTrace();
			}
		}
	}
}