package javafx.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class UserDAO extends User{

	private static UserDAO _userDAO;
	
	private UserList userList;
	
	private UserDAO() {
		super();
	}
	
	public static UserDAO getUserDAO() {
		if(_userDAO==null) {
			_userDAO=new UserDAO();
		}
		
		return _userDAO;
	}
	
	public boolean saveUsers(String url, List<User> users)
	{
		boolean result=false;
		this.userList=new UserList(users);
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(UserList.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this.userList, new File(url));
			result=true;
		} catch (JAXBException e) {
			System.out.println("Error al guardar la base de datos de los usuarios");
			e.printStackTrace();
		}
		
		return result;
	}

	public List<User> loadUsers(String url)
	{	
		try {
			JAXBContext jaxbcontent=JAXBContext.newInstance(UserList.class);
			Unmarshaller unmarshaller=jaxbcontent.createUnmarshaller();
			this.userList=((UserList) unmarshaller.unmarshal(new File(url)));		
		} catch (JAXBException e) {
			System.out.println("Error al cargar la base de datos de los usuarios: Fichero no encontrado!");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Archivo no encontrado");
		}
		
		if(this.userList!=null) {
			return this.userList.getUserList();			
		}else {
			return new ArrayList<User>();
		}
	}
}
