package javafx.controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

	private static LoginController _myLoginController;

	@FXML
	private TextField tUser;
	@FXML
	private TextField tPassword;
	@FXML
	private Button bLogin;
	@FXML
	private Label labSingUp;
	@FXML
	private Label labDropOut;
	
	private List<User> users;
	
	private LoginController() {
		this.users=new ArrayList<User>();
	}
	
	public static LoginController getMyLoginController() {
		if(_myLoginController==null) {
			_myLoginController=new LoginController();
		}
		
		return _myLoginController;
	}
	
	public static void setMyLoginController(LoginController login) {
		_myLoginController=login;
	}

	@FXML
	public void logIn(ActionEvent event) {
		
	}
	
	@FXML
	public void signIn(ActionEvent event) {
		
	}
	
	@FXML
	public void dropOut(ActionEvent event) {
		
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
