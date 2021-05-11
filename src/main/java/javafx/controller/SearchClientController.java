package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.Client;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class SearchClientController implements Initializable{

	@FXML
	private AnchorPane background;
	
    @FXML
    private TextField tfID;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfNIF;

    @FXML
    private TextField tfNationality;

    @FXML
    private TextField tfRoom;

    @FXML
    private TextField tfCompanions;

    @FXML
    private Button bSearch;

    @FXML
    private Button bExit;

    @FXML
    private DatePicker dpFrom;

    @FXML
    private DatePicker dpTo;
    
    private ObservableList<Client> clientList;
    private TableView<Client> tableClient;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tfID.setPromptText("Ej: 13");
		this.tfName.setPromptText("Ej: Juan");
		this.tfNIF.setPromptText("Ej: 23418755N");
		this.tfNationality.setPromptText("Ej: Inglaterra");
		this.tfRoom.setPromptText("Ej: 7");
		this.tfCompanions.setPromptText("Ej: 2");
		
		this.bSearch.setDisable(true);
	}
	
	public void initAtributtes(ObservableList<Client> clientList, TableView<Client> tableClient) {
		this.clientList=clientList;
		this.tableClient=tableClient;
	}
	
	@FXML
	public void searchClient() {
		ObservableList<Client> filteredClientList=FXCollections.observableArrayList(this.clientList);
		boolean valid=false;
		
		if(onlyNumbers(this.tfID, "Error: Formato de ID incorrecto")) {
			if(onlyNumbers(this.tfRoom, "Error: Numero de habitacion incorrecto")) {
				if(onlyNumbers(this.tfCompanions, "Error: Numero de acompañantes incorrecto")) {
					if(checkNIF(this.tfNIF)) {
						valid=true;
					}
				}
			}
		}
		
		
		if(valid) {
			for (Client client : clientList) {
				if((this.tfID.getText()!=null&&this.tfID.getText().isEmpty()==false)&&(client.getID()!=Integer.parseInt(this.tfID.getText()))) {
					filteredClientList.remove(client);
				}else if(!(client.getName().equals(this.tfName.getText()))&&(this.tfName.getText()!=null&&this.tfName.getText().isEmpty()==false)) {
					filteredClientList.remove(client);
				}else if(!(client.getNIF().equals(this.tfNIF.getText()))&&(this.tfNIF.getText()!=null&&this.tfNIF.getText().isEmpty()==false)) {
					filteredClientList.remove(client);
				}else if(!(client.getNationality().equals(this.tfNationality.getText()))&&(this.tfNationality.getText()!=null&&this.tfNationality.getText().isEmpty()==false)) {
					filteredClientList.remove(client);
				}else if((this.tfRoom.getText()!=null&&this.tfRoom.getText().isEmpty()==false)&&(client.getRoom()!=Integer.parseInt(this.tfRoom.getText()))) {
					filteredClientList.remove(client);
				}else if((this.tfCompanions.getText()!=null&&this.tfCompanions.getText().isEmpty()==false)&&(client.getNcompanions()!=Integer.parseInt(this.tfCompanions.getText()))) {
					filteredClientList.remove(client);
				}else if((this.dpFrom.getValue()!=null)&&(!client.getInistance().isEqual(this.dpFrom.getValue()))) {
					filteredClientList.remove(client);
				}else if((this.dpTo.getValue()!=null)&&(!client.getEndstance().isEqual(this.dpTo.getValue()))) {
					filteredClientList.remove(client);
				}
			}		
			
			this.tableClient.setItems(filteredClientList);
			
			Stage stage=(Stage) this.bSearch.getScene().getWindow();
			stage.close();
		}	
	}

	@FXML
	public void checkFields(KeyEvent event) {

		if(this.tfID.getText().isEmpty()==false||this.tfName.getText().isEmpty()==false||this.tfNIF.getText().isEmpty()==false||this.tfNationality.getText().isEmpty()==false
			||this.tfRoom.getText().isEmpty()==false||this.tfCompanions.getText().isEmpty()==false) {
			this.bSearch.setDisable(false);				
		}else {
			this.bSearch.setDisable(true);
		}
		
	}
	
	@FXML
	public void checkDatePickers(Event event) {
		if(this.dpFrom.getValue()!=null||this.dpTo.getValue()!=null) {
			this.bSearch.setDisable(false);			
		}else {
			this.bSearch.setDisable(true);
		}
	}
	
	@FXML
	public boolean onlyNumbers(TextField field, String message) {
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
	
	@FXML
	public boolean checkNIF(TextField nif) {
		if(nif.getText()!=null&&nif.getText().isEmpty()==false) {
			if(!nif.getText().matches("\\d{8}\\w")) {
				Utils.popError("Error: Formato de NIF incorrecto");
				return false;
			}			
		}
		
		return true;
	}
	
	@FXML
	public void exit(ActionEvent event) {
		Stage stage=(Stage) this.bExit.getScene().getWindow();
		stage.close();
	}
}

