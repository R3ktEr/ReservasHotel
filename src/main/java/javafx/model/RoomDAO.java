package javafx.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.utils.DBConnection;

public class RoomDAO extends Room{

	private static final String GETALLROOMS="Select * from room";
	
	private static RoomDAO _roomDAO;
	
	public ObservableList<Room> roomsList;
	
	private RoomDAO() {
		super();
		this.roomsList=FXCollections.observableArrayList();
	}
	
	public static RoomDAO getMyRoomDAO() {
		if(_roomDAO==null) {
			_roomDAO=new RoomDAO();
		}
		
		return _roomDAO;
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

	public List<Room> getRoomsList() {
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
				
				return this.roomsList;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}