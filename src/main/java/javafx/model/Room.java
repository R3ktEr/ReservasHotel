package javafx.model;

import javafx.scene.control.ComboBox;

public class Room {

	protected int number;
	protected int floor;
	protected String zone;
	protected int capacity;
	protected String type;
	protected double price;
	protected String status;
	protected ComboBox<String> cbStatus;
	
	public Room(int number, int floor, String zone, int capacity, String type, double price, String status) {
		super();
		this.number = number;
		this.floor = floor;
		this.zone = zone;
		this.capacity = capacity;
		this.type = type;
		this.price = price;
		this.status = status;
	}
	
	public Room() {
		super();
		this.number = -1;
		this.floor = -1;
		this.zone = "";
		this.capacity = -1;
		this.type = "";
		this.price = -1.0;
		this.status = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (number != other.number)
			return false;
		return true;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		setCbStatus(status);
	}
	
	public ComboBox<String> getCbStatus() {
		return this.cbStatus;
	}

	public void setCbStatus(String status) {
		ComboBox<String> statusCombo=new ComboBox<String>();
    	statusCombo.getItems().add("Libre");
    	statusCombo.getItems().add("Ocupada");
    	statusCombo.getItems().add("Reservada");
    	statusCombo.getItems().add("Sucia");
    	
    	statusCombo.getSelectionModel().select(status);
    	statusCombo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue)->{
    		this.status=newValue;
    		RoomDAO r=new RoomDAO(this.number, this.floor, this.zone, this.capacity, this.type, this.price, this.status);
    		r.updateRoom();
    	});
    	
		this.cbStatus = statusCombo;
	}

	@Override
	public String toString() {
		return "Nº "+this.number+" Tipo: "+this.type+" Capacidad: "+this.capacity;
	}
	
}
