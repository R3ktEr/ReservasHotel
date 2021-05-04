package javafx.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

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
	
	public static boolean popConfirmation(String content) {
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Confirmacion");
		alert.setContentText(content);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    return true;
		} else {
		    return false;
		}
	}
	
	public static void popWarning(String content) {
		Alert alert=new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setTitle("Alerta");
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static LocalDate dateToLocalDate(Date date) {
        LocalDate localDate = date.toLocalDate();
        
        return localDate;
	}
	
	public static Date localDateToDate(LocalDate date) {
		Date sqlDate=Date.valueOf(date);
		
		return sqlDate;
	}
}
