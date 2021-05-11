package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.Room;
import javafx.model.RoomDAO;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RoomsController implements Initializable{
	
    @FXML
    Button bSeeAll;
    
    @FXML
    Button bSearch;

    @FXML
    Button bChangeState;
    
    @FXML
    TableView<Room> roomsTable;
    
    @FXML
    private TableColumn<Room, Integer> colNumber;
    
    @FXML
    private TableColumn<Room, Integer> colFloor;
    
    @FXML
    private TableColumn<Room, String> colZone;
    
    @FXML
    private TableColumn<Room, Integer> colCapacity;
    
    @FXML
    private TableColumn<Room, String> colType;
    
    @FXML
    private TableColumn<Room, Double> colPrice;
    
    @FXML
    private TableColumn<Room, ComboBox<String>> colStatus;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private Menu mRooms;
    
    @FXML
    private MenuItem mClients;
    
    private ObservableList<Room> roomList;
    
    public void changeToClients(ActionEvent event) {
    	try {
			App.changeScene("clients");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setTableRooms() {
    	RoomDAO r=new RoomDAO();
    	roomList=r.loadRoomList();
    	
    	this.colNumber.setCellValueFactory(new PropertyValueFactory<Room, Integer>("number"));
    	this.colFloor.setCellValueFactory(new PropertyValueFactory<Room, Integer>("floor"));
    	this.colZone.setCellValueFactory(new PropertyValueFactory<Room, String>("zone"));
    	this.colCapacity.setCellValueFactory(new PropertyValueFactory<Room, Integer>("capacity"));
    	this.colType.setCellValueFactory(new PropertyValueFactory<Room, String>("type"));
    	this.colPrice.setCellValueFactory(new PropertyValueFactory<Room, Double>("price"));
    	this.colStatus.setCellValueFactory(new PropertyValueFactory<Room, ComboBox<String>>("cbStatus"));
    	
    	//this.colStatus.getCellData(r).getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue)-> System.out.println());
    	
    	this.roomsTable.setItems(roomList);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.roomList=FXCollections.observableArrayList();
		setTableRooms();
	}
}
