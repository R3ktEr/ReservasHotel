package javafx.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.controller.LoginController;
import javafx.model.User;

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

	public boolean saveUsers(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(User.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(LoginController.getMyLoginController(), new File(url));
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al guardar la base de datos de los usuarios");
			//e.printStackTrace();
		}
		
		return result;
	}

	public boolean loadUsers(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbcontent=JAXBContext.newInstance(User.class);
			Unmarshaller unmarshaller=jaxbcontent.createUnmarshaller();
			LoginController.getMyLoginController().setUsers(((LoginController) unmarshaller.unmarshal(new File(url))).getUsers());
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al cargar la base de datos de los usuarios: Fichero no encontrado!");
			//e.printStackTrace();
		}
		
		
		return result;
	}

}
