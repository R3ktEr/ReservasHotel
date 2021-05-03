package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.User;
import javafx.model.UserDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.utils.Utils;

public class LoginController implements Initializable{
	
	@FXML
	private TextField tUser;
	@FXML
	private PasswordField tPassword;
	@FXML
	private Button bLogin;
	@FXML
	private Label labSingUp;
	@FXML
	private Label labDropOut;
	
	private List<User> users;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.users=UserDAO.getUserDAO().loadUsers("users.xml");
		
		if(this.users==null) {
			this.users=new ArrayList<User>();
		}
	}

	@FXML
	public void logIn(ActionEvent event) {
		User ulogin=new User();
		
		ulogin.setUsername(tUser.getText());
		ulogin.setPassword(tPassword.getText());
		
		if(this.users.size()>0) {
			boolean found=false;
			Iterator<User> userIterator=users.iterator();
			
			while(userIterator.hasNext()&&!found) {
				if(ulogin.equals(userIterator.next())) {
					found=true;
					
					try {
						App.changeScene("rooms");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(!found) {
				Utils.popWarning("Aviso: Usuario o contraseña incorrectos");
			}
		}else {
			Utils.popError("Error: No hay usuarios registrados");
		}
	}
	
	@FXML
	public void signIn(MouseEvent event) {
		User signin=new User();
		
		signin.setUsername(tUser.getText());
		signin.setPassword(tPassword.getText());
		
		if(users!=null) {
			boolean found=false;
			
			Iterator<User> userIterator=users.iterator();
			while(userIterator.hasNext()&&!found) {
				if(userIterator.next().equals(signin)) {
					found=true;
					Utils.popError("Error: El usuario ya existe");
				}
			}
			
			if(!found) {
				users.add(signin);
				if(UserDAO.getUserDAO().saveUsers("users.xml", users)) {
					Utils.popInfo("Usuario registrado con exito");					
				}else {
					Utils.popError("Error al registrar el cliente");
				}
			}
		}
	}
	
	@FXML
	public void dropOut(MouseEvent event) {
		User dropout=new User();
		
		dropout.setUsername(tUser.getText());
		dropout.setPassword(tPassword.getText());
		
		if(users.size()>0) {
			boolean deleted=false;
			
			Iterator<User> userIterator=users.iterator();
			while(userIterator.hasNext()&&!deleted) {
				if(userIterator.next().equals(dropout)) {
					users.remove(dropout);	
					if(UserDAO.getUserDAO().saveUsers("users.xml", users)) {
						deleted=true;
						Utils.popInfo("Usuario borrado con éxito");
						tUser.setText("");
						tPassword.setText("");
					}else {
						Utils.popError("Error al borrar el usuario");
					}
				}
			}
			
			if(!deleted) {
				Utils.popInfo("No hay ningun usuario registrado con esas credenciales");
			}
			
		}else {
			Utils.popError("No hay usuarios registrados");
		}
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
