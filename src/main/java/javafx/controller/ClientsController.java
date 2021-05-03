package javafx.controller;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.model.Client;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientsController {

	 @FXML
	 Button bSeeAll;
	 
	 @FXML
	 Button bSearch;
	 
	 @FXML
	 Button bAdd;
	 
	 @FXML
	 Button bModify;
	 
	 @FXML
	 Button bDelete;
	 
	 @FXML
	 TableView<Client> clientsTable;
	 
	 @FXML
	 private TableColumn<Client, Integer> colID;
	 
	 @FXML
	 private TableColumn<Client, String> colName;
	 
	 @FXML
	 private TableColumn<Client, String> colNif;
	 
	 @FXML
	 private TableColumn<Client, String> colNationality;
	 
	 @FXML
	 private TableColumn<Client, Integer> colRoom;
	 
	 @FXML
	 private TableColumn<Client, LocalDate> colStanceStartDate;
	 
	 @FXML
	 private TableColumn<Client, LocalDate> colStanceEndDate;
	 
	 @FXML
	 private TableColumn<Client, Integer> colNumCompanions;
	 
	 @FXML
	 private MenuBar menuBar;
    
	 @FXML
	 private Menu mClients;
    
	 @FXML
	 private MenuItem mRooms;
	 
	 public void changeToRooms(ActionEvent event) {
    	try {
			App.changeScene("rooms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
