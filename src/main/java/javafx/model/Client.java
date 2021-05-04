package javafx.model;

import java.time.LocalDate;

public class Client extends Person{

	protected int ID;
	protected String nationality;
	protected int room;
	protected LocalDate inistance;
	protected LocalDate endstance;
	protected int ncompanions;
	
	public Client(String name, String nIF, int iD, String nationality, int room, LocalDate inistance, LocalDate endstance, int ncompanions) {
		super(name, nIF);
		ID = iD;
		this.nationality = nationality;
		this.room=room;
		this.inistance=inistance;
		this.endstance=endstance;
		this.ncompanions=ncompanions;
	}
	
	public Client() {
		super();
		ID = -1;
		this.nationality = "";
		this.room=-1;
		this.inistance=LocalDate.parse("2000-01-01");
		this.endstance=LocalDate.parse("2000-01-01");
		this.ncompanions=-1;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public LocalDate getInistance() {
		return inistance;
	}

	public void setInistance(LocalDate inistance) {
		this.inistance = inistance;
	}

	public LocalDate getEndstance() {
		return endstance;
	}

	public void setEndstance(LocalDate endstance) {
		this.endstance = endstance;
	}

	public int getNcompanions() {
		return ncompanions;
	}

	public void setNcompanions(int ncompanions) {
		this.ncompanions = ncompanions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ID;
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
		Client other = (Client) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [ID=" + ID + ", nationality=" + nationality + "]";
	}
	
}
