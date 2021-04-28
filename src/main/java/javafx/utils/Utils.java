package javafx.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {
	
	private static Utils _myUtils;
	
	private Utils(){}
	
	public static Utils getMyUtils()
	{
		if(_myUtils==null)
		{
			_myUtils=new Utils();
		}
		
		return _myUtils;
	}

	public static void setUtils(Utils utils) {
		Utils._myUtils = utils;
	}
	
	public static void popInfo(String content) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void popError(String content) {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void popConfirmation(String content) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void popWarning(String content) {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Alerta");
		alert.setContentText(content);
		alert.showAndWait();
	}
}
