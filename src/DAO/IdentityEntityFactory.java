package DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import commons.HTTPUtils;


public class IdentityEntityFactory {	
	//contains the data required for displaying
	public HashMap<String,IdentityEntity> entityList = new HashMap<String,IdentityEntity>();
	//contains raw data loaded from the storage
	public HashMap<String,HashMap<String,String>> identityListFromStorage = new HashMap<String,HashMap<String,String>>();
	public SourceParser sm;

	public IdentityEntityFactory() throws Exception{
		sm = new SourceParser();
		LoadEntity();
		DeNormalizeRecent();
	}
	public IdentityEntityFactory(String pass) throws Exception{		
		sm = new SourceParser(pass);
		LoadEntity();
		DeNormalizeRecent();		
	}	
	
	public void loadListModel(){
		
		
	}
	
	public void CreateEntity(){
		
	}
	
	public void Dump(){
		
	}
	
	public String Fetch(String key){
		return sm.readObj.items.get(key).get("value");
	}
	
	public void Delete(String key){
		identityListFromStorage.remove(key);
		Persist();
	}
	public boolean Store(String key, HashMap<String,String> val){
		identityListFromStorage.put(key, val);
		Persist();
		DeNormalizeRecent();
		return true;
	}
	/*
	 * 
	 * This creates the list of items to be diplayed in the list box
	 */
	public void DeNormalizeRecent(){		
		Iterator<Entry<String, HashMap<String, String>>> it =  identityListFromStorage.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, HashMap<String, String>> pairs = (Map.Entry<String, HashMap<String, String>>)it.next();			
			String key="";
			try {
				key = pairs.getKey();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String val = pairs.getValue().get("displayName");
			IdentityEntity t = new IdentityEntity();
			t.setKey(key);t.setVal(val);			
			entityList.put(key, t);
		}
		
	}
	
	public void Persist(){		
		sm.Save(identityListFromStorage);
	}
	
	public void LoadEntity() throws Exception{			
		identityListFromStorage =sm.load();		
	}

}
