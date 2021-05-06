package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.Client;
import javafx.model.ClientDAO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.utils.Utils;

public class ClientsController implements Initializable{

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
	 
	private ObservableList<Client> clientList;
	private Client c;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.clientList=FXCollections.observableArrayList();
		setTableClients();
	}
	
	@FXML
	public void setTableClients() {
		ClientDAO cd=new ClientDAO();
		
		this.colID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("ID"));
		this.colName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		this.colNif.setCellValueFactory(new PropertyValueFactory<Client, String>("NIF"));
		this.colNationality.setCellValueFactory(new PropertyValueFactory<Client, String>("nationality"));
		this.colRoom.setCellValueFactory(new PropertyValueFactory<Client, Integer>("room"));
		this.colStanceStartDate.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("inistance"));
		this.colStanceEndDate.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("endstance"));
		this.colNumCompanions.setCellValueFactory(new PropertyValueFactory<Client, Integer>("ncompanions"));
		
		this.clientList=cd.getAllClients();
		
		this.clientsTable.setItems(clientList);
	}
	
	@FXML
	public void changeToRooms(ActionEvent event) {
		try {
			App.changeScene("rooms");
	} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addClient(ActionEvent event) {
		
	try {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("addClient.fxml"));
		Parent root=loader.load();
		AddClientController controller=loader.getController();
		
		controller.initClientList(clientList);
		
		Scene scene=new Scene(root);
		Stage stage=new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
		
		this.clientsTable.refresh();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void deleteClient(ActionEvent event) {
		this.c=this.clientsTable.getSelectionModel().getSelectedItem();
		
		if(this.c==null) {
			Utils.popError("Error: Debes selecionar un cliente");
		}else if(Utils.popConfirmation("¿Estas seguro de que quieres borrar este cliente?")){
			ClientDAO c=new ClientDAO(this.c);
			c.deleteClientByID();
			this.clientList.remove(this.c);
			Utils.popInfo("Cliente borrado con exito");
		}
	}
	
	@FXML
	public void modifyClient(ActionEvent event) {
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("modifyClient.fxml"));
			Parent root=loader.load();
			ModifyClientController controller=loader.getController();
			
			this.c=this.clientsTable.getSelectionModel().getSelectedItem();
			controller.initWindow(clientList, this.c);
			
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			
			this.clientsTable.refresh();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
