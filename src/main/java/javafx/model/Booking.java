package javafx.model;

import java.time.LocalDate;

public class Booking {
	
	private Client client;
	private Room room;
	private LocalDate startDate;
	private LocalDate endDate;
	private int n_companions;
	
	public Booking(Client client, Room room, LocalDate startDate, LocalDate endDate, int n_companions) {
		super();
		this.client = client;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		this.n_companions = n_companions;
	}
	
	public Booking() {
		super();
		this.client = new Client();
		this.room = new Room();
		this.startDate = LocalDate.parse("0000/00/00");
		this.endDate = LocalDate.parse("0000/00/00");
		this.n_companions = -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Booking other = (Booking) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getN_companions() {
		return n_companions;
	}

	public void setN_companions(int n_companions) {
		this.n_companions = n_companions;
	}

	@Override
	public String toString() {
		return "Booking [client=" + client + ", room=" + room + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", n_companions=" + n_companions + "]";
	}

}
