package DAO;

import java.util.HashMap;
import java.util.Map;

public class EntityMarshaller {
	public HashMap<String,HashMap<String,String>> items ;

	/**
	 * @param args
	 */
	public EntityMarshaller(){
		items = new HashMap<String,HashMap<String,String>>();
	}

	@Override
	public String toString(){
		return null;
		//return "entityList ["+messages+"]";
	}

}
