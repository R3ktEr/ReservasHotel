package javafx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name="users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserList implements Serializable{

	@XmlElement(name="user")
	private List<User> userList;

	public UserList() {
		super();
		this.userList = new ArrayList<User>();
	}
	
	public UserList(List<User> userList) {
		super();
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
