package javafx.model;

public class Client extends Person{

	private int ID;
	private String nationality;
	
	public Client(String name, String nIF, int iD, String nationality) {
		super(name, nIF);
		ID = iD;
		this.nationality = nationality;
	}
	
	public Client() {
		super();
		ID = -1;
		this.nationality = "";
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
