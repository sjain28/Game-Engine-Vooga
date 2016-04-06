package authoring.gui.items;

import BackEnd.Command;
import tools.interfaces.VoogaData;

public class NewPropertyFactory {

	public NewPropertyFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public VoogaData createNewProperty(String s) {
		VoogaData newCommand = null;
		 try {
	            Class c = Class.forName(s);
	            newCommand = (VoogaData) c.newInstance();
	        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		Class<?> cls = Class.forName(s);
		return newCommand;
	}

}
