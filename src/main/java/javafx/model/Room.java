package javafx.model;

public class Room {

	private int number;
	private int floor;
	private String zone;
	private int capacity;
	private String type;
	private double price;
	private String status;
	
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
	}

	@Override
	public String toString() {
		return "Room [number=" + number + ", floor=" + floor + ", zone=" + zone + ", capacity=" + capacity + ", type="
				+ type + ", price=" + price + ", status=" + status + "]";
	}
	
}
