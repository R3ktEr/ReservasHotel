package javafx.controller;

import java.time.LocalDate;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.model.Client;
import javafx.model.ClientDAO;
import javafx.model.Room;
import javafx.model.RoomDAO;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class ModifyClientController {
	@FXML
	TextField tfName;
	
	@FXML
	TextField tfNIF;
	
	@FXML
	TextField tfNationality;
	
	@FXML
	ComboBox<Room> cbRoom; 
	
	@FXML
	TextField tfNCompanions;
	
	@FXML
	DatePicker dpFrom;
	
	@FXML
	DatePicker dpTo;
	
	@FXML
	Button bModify;
	
	@FXML
	Button bExit;
	
	private Client client;
	private ObservableList<Client> clientList=FXCollections.observableArrayList();
	private ObservableList<Room> roomsList=FXCollections.observableArrayList();
	private ObservableList<Room> freeRoomsList=FXCollections.observableArrayList();
	
	public void initWindow(ObservableList<Client> clientList, Client client) {
		this.clientList=clientList;
		RoomDAO r=new RoomDAO();
		this.client=client;

		this.roomsList=r.loadRoomList();
		
		//Implementar la lista de habitaciones libres
		Iterator<Room> roomIterator=roomsList.iterator();
		
		while(roomIterator.hasNext()) {
			Room room=new Room();
			room=roomIterator.next();
			boolean found=false;
			
			for(int i=0; i<clientList.size()&&!found; i++) {
				if(clientList.get(i).getRoom()==room.getNumber()) {
					found=true;
				}
			}
			
			if(!found) {
				this.freeRoomsList.add(room);
			}
		}
		
		this.dpFrom.setEditable(false);
		setDatePicker(dpFrom, this.client.getInistance());
		this.dpTo.setEditable(false);
		setDatePicker(dpTo, this.client.getEndstance());
		this.cbRoom.setItems(freeRoomsList);;
		this.tfName.setPromptText("Introduzca un nombre");
		this.tfNIF.setPromptText("Ej: 24124687T");
		this.tfNationality.setPromptText("Ej: Alemania");
		
		tfName.setText(this.client.getName());
		tfNIF.setText(this.client.getNIF());
		tfNationality.setText(this.client.getNationality());
		cbRoom.setValue(getClientRoom(this.client));
		dpFrom.setValue(this.client.getInistance());
		dpTo.setValue(this.client.getEndstance());
		tfNCompanions.setText(this.client.getNcompanions()+"");
	}

	@FXML
	public void modify(ActionEvent event) {
		boolean valid=true;
		Client client2=new Client();
		
		//DateTimeFormatter f=DateTimeFormatter.ofPattern("dd-MM-yyyy");		
		client2.setID(client.getID());
		client2.setName(tfName.getText());
		client2.setNIF(tfNIF.getText());
		client2.setNationality(tfNationality.getText());
		client2.setRoom(cbRoom.getSelectionModel().getSelectedItem().getNumber());
		client2.setInistance(dpFrom.getValue());
		client2.setEndstance(dpTo.getValue());
		if(!this.tfNCompanions.getText().isEmpty()) {
			client2.setNcompanions(Integer.parseInt(tfNCompanions.getText()));			
			if(this.cbRoom.getSelectionModel().getSelectedItem().getCapacity()<Integer.parseInt(this.tfNCompanions.getText())&&valid) {
				Utils.popError("Error: La capacidad de la habitacion no es suficiente");
				this.tfNCompanions.setText("");
				valid=false;
			}
		}
		
		if(!client2.getNIF().matches("[0-9]{8}[A-Z]")&&valid) {
			Utils.popError("Error: Introduzca un NIF con el formato indicado");
			this.tfNIF.setText(this.client.getNIF());
			valid=false;
		}
		
		if(this.tfName.getText().isEmpty()||this.tfNationality.getText().isEmpty()||this.tfNCompanions.getText().isEmpty()) {
			Utils.popError("Error: Uno o varios campos estan vacíos");
			this.tfName.setText(this.client.getName());
			this.tfNationality.setText(this.client.getNationality());
			this.tfNCompanions.setText(this.client.getNcompanions()+"");
			valid=false;
		}
		
		if(valid) {
			
			ClientDAO cd=new ClientDAO(client2);
			cd.updateClient();
			
			if(client2!=null) {
				this.clientList.remove(this.client);
				this.clientList.add(client2);						
			}
			
			Stage stage=(Stage) this.bModify.getScene().getWindow();
			stage.close();
		}
	}
	
	@FXML
	public void exit(ActionEvent event) {
		client=null;
		
		Stage stage=(Stage) this.bModify.getScene().getWindow();
		stage.close();
	}

	public Client getClient() {
		return client;
	}
	
	public void setDatePicker(DatePicker dp, LocalDate stanceDate) {
		dp.setDayCellFactory(picker -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
		
		dp.setValue(stanceDate);
	}
	
	public Room getClientRoom(Client c) {
		Iterator<Room> roomIterator=this.roomsList.iterator();
		Room r=new Room();
		
		while(roomIterator.hasNext()) {
			r=roomIterator.next();
			
			if(r.getNumber()==c.getRoom()) {
				return r;
			}
		}
		
		return r;
	}
}
