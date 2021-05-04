package javafx.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.model.Client;
import javafx.model.ClientDAO;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class AddClientController {
	
	@FXML
	TextField tfName;
	
	@FXML
	TextField tfNIF;
	
	@FXML
	TextField tfNationality;
	
	@FXML
	TextField tfRoom;
	
	@FXML
	TextField tfNCompanions;
	
	@FXML
	DatePicker dpFrom;
	
	@FXML
	DatePicker dpTo;
	
	@FXML
	Button bAdd;
	
	@FXML
	Button bExit;
	
	private Client client;
	private ObservableList<Client> clientList;
	
	public void initClientList(ObservableList<Client> clientList) {
		this.clientList=clientList;
		this.dpFrom.setEditable(false);
		this.dpTo.setEditable(false);
		this.tfName.setPromptText("Introduzca un nombre");
	}

	@FXML
	public void add(ActionEvent event) {
		client=new Client();
		//DateTimeFormatter f=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		client.setName(tfName.getText());
		client.setNIF(tfNIF.getText());
		client.setNationality(tfNationality.getText());
		client.setRoom(Integer.parseInt(tfRoom.getText()));
		client.setInistance(dpFrom.getValue());
		client.setEndstance(dpTo.getValue());
		client.setNcompanions(Integer.parseInt(tfNCompanions.getText()));
		
		if(!this.clientList.contains(client)) {
			
			ClientDAO cd=new ClientDAO(client);
			cd.saveClient();
			
			client=(Client) cd;
			
			if(client!=null) {
				this.clientList.add(client);						
			}
			
			Stage stage=(Stage) this.bAdd.getScene().getWindow();
			stage.close();
		}else {
			Utils.popError("Error, el cliente ya existe");
		}
	}
	
	@FXML
	public void exit(ActionEvent event) {
		client=null;
		
		Stage stage=(Stage) this.bAdd.getScene().getWindow();
		stage.close();
	}

	public Client getClient() {
		return client;
	}
	
}
