package javafx.model;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends Client{
	
	List<Client> clientList;
	
	public ClientDAO() {
		super();
		this.clientList=new ArrayList<Client>();
	}
	
	public ClientDAO(String name, String nIF, int iD, String nationality, List<Client> clientList) {
		super(name, nIF, iD, nationality);
		this.clientList=new ArrayList<Client>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientList == null) ? 0 : clientList.hashCode());
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
		ClientDAO other = (ClientDAO) obj;
		if (clientList == null) {
			if (other.clientList != null)
				return false;
		} else if (!clientList.equals(other.clientList))
			return false;
		return true;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}
	
}
