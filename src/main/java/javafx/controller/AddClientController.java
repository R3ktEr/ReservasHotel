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

public class AddClientController {
	
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
		this.tfName.setPromptText("Introduzca un nombre");
		this.tfNIF.setPromptText("Ej: 24124687T");
		this.tfNationality.setPromptText("Ej: Alemania");
	}

	@FXML
	public void add(ActionEvent event) {
		boolean valid=true;
		
		client=new Client();
		//DateTimeFormatter f=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
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
}
