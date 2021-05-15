package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.Room;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class SearchRoomController implements Initializable{

    @FXML
    private TextField tfNumber;

    @FXML
    private ComboBox<Integer> cbFloor;

    @FXML
    private ComboBox<String> cbZone;

    @FXML
    private ComboBox<Integer> cbCapacity;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TextField tfFrom;

    @FXML
    private TextField tfTo;

    @FXML
    private ComboBox<String> cbState;

    @FXML
    private Button bSearch;

    @FXML
    private Button bExit;

    private ObservableList<Room> roomList;
    private TableView<Room> roomsTable;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tfNumber.setPromptText("Ej: 2");
		this.tfFrom.setPromptText("Ej: 80");
		this.tfTo.setPromptText("Ej: 160");
		setComboBoxes();
		this.bSearch.setDisable(true);
	}

	public void initAttributes(ObservableList<Room> list, TableView<Room> roomsTable) {
		this.roomList=list;
		this.roomsTable=roomsTable;
	}
	
	@FXML
	public void search(ActionEvent event) {
		ObservableList<Room> filteredRoomList=FXCollections.observableArrayList(this.roomList);
		boolean valid=false;
		
		if(onlyNumbers(this.tfNumber, "Error: Formato de ID incorrecto")) {
			if(onlyNumbers(this.tfFrom, "Error: Rango de precio incorrecto")&&onlyNumbers(this.tfTo, "Error: Rango de precio incorrecto")) {
				valid=true;
			}else {
				this.tfFrom.clear();
				this.tfTo.clear();
			}
		}
		
		if(valid) {
			for (int i=0; i<roomList.size()&&valid; i++) {
				if((this.tfNumber.getText()!=null&&this.tfNumber.getText().isEmpty()==false)&&(roomList.get(i).getNumber()!=Integer.parseInt(this.tfNumber.getText()))) {
					filteredRoomList.remove(roomList.get(i));
				}else if(this.cbFloor.getValue()!=null&&roomList.get(i).getFloor()!=this.cbFloor.getValue()) {
					filteredRoomList.remove(roomList.get(i));
				}else if(this.cbZone.getValue()!=null&&(roomList.get(i).getZone().equals(this.cbZone.getValue())==false)) {
					filteredRoomList.remove(roomList.get(i));
				}else if(this.cbCapacity.getValue()!=null&&roomList.get(i).getCapacity()!=this.cbCapacity.getValue()) {
					filteredRoomList.remove(roomList.get(i));
				}else if(this.cbType.getValue()!=null&&(roomList.get(i).getType().equals(this.cbType.getValue())==false)) {
					filteredRoomList.remove(roomList.get(i));
				}else if(this.tfFrom.getText().isEmpty()==false||this.tfTo.getText().isEmpty()==false) {
					if(checkRange()) {
						if(checkRangeOrder(Double.parseDouble(this.tfFrom.getText()), Double.parseDouble(this.tfTo.getText()))){
							if((checkPrices(roomList.get(i), Double.parseDouble(this.tfFrom.getText()), Double.parseDouble(this.tfTo.getText())))==false) {
								filteredRoomList.remove(roomList.get(i));							
							}							
						}else {
							valid=false;
						}
					}else {
						valid=false;
					}
				}else if(this.cbState.getValue()!=null&&(roomList.get(i).getStatus().equals(this.cbState.getValue())==false)) {
					filteredRoomList.remove(roomList.get(i));
				}
			}
			
			if(valid) {
				this.roomsTable.setItems(filteredRoomList);
				this.roomsTable.sort();
				
				Stage stage=(Stage) this.bSearch.getScene().getWindow();
				stage.close();				
			}
		}
	}
	
	private void setComboBoxes() {
		this.cbFloor.getItems().add(1);
		this.cbFloor.getItems().add(2);
		
		this.cbZone.getItems().add("Norte");
		this.cbZone.getItems().add("Sur");
		this.cbZone.getItems().add("Este");
		this.cbZone.getItems().add("Oeste");
		
		this.cbCapacity.getItems().add(2);
		this.cbCapacity.getItems().add(3);
		this.cbCapacity.getItems().add(4);
		this.cbCapacity.getItems().add(5);
		this.cbCapacity.getItems().add(6);
		
    	this.cbState.getItems().add("Libre");
    	this.cbState.getItems().add("Ocupada");
    	this.cbState.getItems().add("Reservada");
    	this.cbState.getItems().add("Sucia");
    	
    	this.cbType.getItems().add("Economica");
    	this.cbType.getItems().add("Normal");
    	this.cbType.getItems().add("Lujo");
    	this.cbType.getItems().add("Suit");
	}
	
	private boolean onlyNumbers(TextField field, String message) {
		for(int i=0; i<field.getText().length(); i++) {
			if(field.getText().charAt(i)<'0'||field.getText().charAt(i)>'9') {
				Utils.popError(message);
				i=field.getText().length();
				field.clear();
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkRange() {
		if(this.tfFrom!=null&&this.tfFrom.getText().isEmpty()==false&&this.tfTo.getText()!=null&&this.tfTo.getText().isEmpty()==false) {
			return true;	
		}else {
			Utils.popError("Error: Ambos campos de comparar precios deben estar rellenos");
			return false;
		}
	}
	
	private boolean checkRangeOrder(double lower, double higher) {
		if(lower<=higher) {
			return true;
		}else {
			Utils.popError("Error: El rango va desde un precio mayor a un precio menor");
			return false;
		}
	}
	
	private boolean checkPrices(Room r, double lower, double higher) {
		if(r.getPrice()>=lower&&r.getPrice()<=higher) {
			return true;
		}else {
			return false;
		}						
	}
	
	@FXML
	public void checkComboBoxes(Event event) {
		if(this.cbFloor.getValue()!=null||this.cbZone.getValue()!=null||this.cbCapacity.getValue()!=null||this.cbType.getValue()!=null||
			this.cbState.getValue()!=null) {
			this.bSearch.setDisable(false);				
		}else {
			this.bSearch.setDisable(true);
		}	
	}
	
	@FXML
	public void checkFields(KeyEvent event) {
		if(this.tfNumber.getText().isEmpty()==false||this.tfFrom.getText().isEmpty()==false||this.tfTo.getText().isEmpty()==false) {
			this.bSearch.setDisable(false);				
		}else {
			this.bSearch.setDisable(true);
		}	
	}
	
	@FXML
	public void exit(ActionEvent event) {
		Stage stage=(Stage) this.bExit.getScene().getWindow();
		stage.close();
	}
}
