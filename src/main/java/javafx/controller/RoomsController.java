package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.Room;
import javafx.model.RoomDAO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RoomsController implements Initializable{
	
	@FXML
	AnchorPane background;
	
    @FXML
    Button bSeeAll;
    
    @FXML
    Button bSearch;
    
    @FXML
    TextField tfSearch;
    
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
    	
    	this.roomsTable.setItems(roomList);
    	this.roomsTable.getSortOrder().add(colNumber);
    	this.roomsTable.sort();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.roomList=FXCollections.observableArrayList();
		this.tfSearch.setPromptText("Ej: 8");
		setTableRooms();
	}
	
	@FXML
	public void filterByNumber(KeyEvent event) {
		String filter="-1";
		boolean valid=true;
		
		if(this.tfSearch.getText().isEmpty()==false) {
			String s=this.tfSearch.getText();
			
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i)<'0'||s.charAt(i)>'9') {
					valid=false;
				}
			}
			
			if(valid) {
				filter=this.tfSearch.getText();							
			}
		}
		
		if(filter.equals("-1")) {
			this.roomsTable.setItems(roomList);
		}else {
			ObservableList<Room> roomsFiltered=FXCollections.observableArrayList();
			roomsFiltered.clear();
			
			for (Room room : this.roomList) {
				if((room.getNumber()+"").startsWith(filter)) {
					roomsFiltered.add(room);
				}
			}	
			
			this.roomsTable.setItems(roomsFiltered);
			this.roomsTable.sort();					
		}
	}
	
	@FXML
	public void advancedSearch(ActionEvent event) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("searchRoom.fxml"));
			Parent root=loader.load();
			SearchRoomController controller=loader.getController();
			
			controller.initAttributes(this.roomList, this.roomsTable);
			
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showAllRooms() {
		this.roomsTable.setItems(roomList);
		this.roomsTable.sort();
	}
}
