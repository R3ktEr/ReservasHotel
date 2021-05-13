package javafx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.Client;
import javafx.model.ClientDAO;
import javafx.model.Room;
import javafx.model.RoomDAO;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class AddClientController implements Initializable{
	
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
	Button bAdd;
	
	@FXML
	Button bExit;
	
	private Client client;
	private ObservableList<Client> clientList;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tfName.setPromptText("Introduzca un nombre");
		this.tfNIF.setPromptText("Ej: 24124687T");
		this.tfNationality.setPromptText("Ej: Alemania");
		this.tfNCompanions.setPromptText("Ej: 2");
		
		this.bAdd.setDisable(true);
	}
	
	public void initClientList(ObservableList<Client> clientList) {
		RoomDAO r=new RoomDAO();
		ObservableList<Room> roomsList=FXCollections.observableArrayList();
		ObservableList<Room> freeRoomsList=FXCollections.observableArrayList();
		roomsList=r.loadRoomList();
		
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
				freeRoomsList.add(room);
			}
		}
		
		this.clientList=clientList;
		this.dpFrom.setEditable(false);
		setDatePicker(dpFrom);
		this.dpTo.setEditable(false);
		setDatePicker(dpTo);
		this.cbRoom.setItems(freeRoomsList);;
	}

	@FXML
	public void add(ActionEvent event) {
		boolean valid=true;
		
		if(this.tfName.getText()!=null&&this.tfName.getText().isEmpty()==false&&this.tfNIF.getText()!=null&&this.tfNIF.getText().isEmpty()==false
		&&this.tfNationality.getText()!=null&&this.tfNationality.getText().isEmpty()==false&&this.cbRoom.getValue()!=null&&this.dpFrom.getValue()!=null&&
		this.dpTo.getValue()!=null&&this.tfNCompanions.getText()!=null&&this.tfNCompanions.getText().isEmpty()==false) {
			
			client=new Client();
			client.setName(tfName.getText());
			client.setNIF(tfNIF.getText());
			client.setNationality(tfNationality.getText());
			client.setRoom(cbRoom.getSelectionModel().getSelectedItem().getNumber());
			client.setInistance(dpFrom.getValue());
			client.setEndstance(dpTo.getValue());
			client.setNcompanions(Integer.parseInt(tfNCompanions.getText()));
			
			if(!client.getNIF().matches("[0-9]{8}[A-Z]")&&valid) {
				Utils.popError("Error: Introduzca un NIF con el formato indicado");
				valid=false;
			}
			
			if(this.clientList.contains(client)&&valid) {
				Utils.popError("Error, el cliente ya existe");
				valid=false;
			}
			
			if(this.cbRoom.getSelectionModel().getSelectedItem().getCapacity()<Integer.parseInt(this.tfNCompanions.getText())&&valid) {
				Utils.popError("Error: La capacidad de la habitacion no es suficiente");
				this.tfNCompanions.setText("");
				valid=false;
			}
			
			if(valid) {
				
				ClientDAO cd=new ClientDAO(client);
				cd.saveClient();
				
				client=(Client) cd;
				
				if(client!=null) {
					this.clientList.add(client);						
				}
				
				Stage stage=(Stage) this.bAdd.getScene().getWindow();
				stage.close();
			}
		}else {
			Utils.popError("Error: Uno o varios campos están vacíos");
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
	
	public void setDatePicker(DatePicker dp) {
		dp.setDayCellFactory(dia -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            LocalDate today = LocalDate.now();

	            setDisable(empty || date.compareTo(today) < 0 );
	        }
	    });
	}
	
	@FXML
	public void checkFields(KeyEvent event) {

		if(this.tfName.getText().isEmpty()==false||this.tfNIF.getText().isEmpty()==false||this.tfNationality.getText().isEmpty()==false
		||this.tfNCompanions.getText().isEmpty()==false) {
			this.bAdd.setDisable(false);				
		}else {
			this.bAdd.setDisable(true);
		}
		
	}
	
	@FXML
	public void checkComboBox(Event event) {
		if(this.cbRoom.getValue()!=null) {
			this.bAdd.setDisable(false);
		}else {
			this.bAdd.setDisable(true);
		}
	}
	
	@FXML
	public void checkDatePickers(Event event) {
		if(this.dpFrom.getValue()!=null||this.dpTo.getValue()!=null) {
			this.bAdd.setDisable(false);			
		}else {
			this.bAdd.setDisable(true);
		}
	}
}
