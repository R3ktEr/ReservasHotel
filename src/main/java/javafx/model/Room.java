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

	@Override
	public String toString() {
		return "Room [number=" + number + ", floor=" + floor + ", zone=" + zone + ", capacity=" + capacity + ", type="
				+ type + ", price=" + price + ", status=" + status + "]";
	}
	
}
