package DAO;

import io.FileOperations;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;
import org.codehaus.jackson.JsonFactory;

import org.codehaus.jackson.map.ObjectMapper;

import crypt.BlowfishKey;


public class SourceParser {
	public String sourceMapper="e:/learners-studio/java/IdentityManager/pass2.log";
	BlowfishKey encObj;
	ArrayList<IdentityEntity> pwdEntity;
	public EntityMarshaller readObj;
	public SourceParser() throws Exception{
		//encObj = new BlowfishKey("ubutzukubutzu");		
		//used of unit test of this class, this will not be called from outside
		encObj = new BlowfishKey("Sard0nyx1");
	}
	
	public SourceParser(String pass) throws Exception{
		sourceMapper = getDataPath();
		sourceMapper = sourceMapper.trim();
		System.out.println(sourceMapper);
		if(!new File(sourceMapper).isFile()){
			sourceMapper = System.getProperty("user.dir")+"/"+sourceMapper;			
		}		
		encObj = new BlowfishKey(pass);		
	}
	
	public void ChangePassword(String pass){
		encObj.SetPassPhrase(pass);
	}
	
	public String getDataPath() throws Exception{
		String file = System.getProperty("user.dir")+"/settings.txt";		
		byte[] temp = FileOperations.read(new File(file));		
		return new String(temp);
	}
	
	/**
	 * @param args
	 * @return 
	 */
	public HashMap<String, HashMap<String, String>> load() {			
		pwdEntity = new ArrayList<IdentityEntity>();
		try {			
			encObj.decrypt(new File(sourceMapper));			
					
			ObjectMapper mapper = new ObjectMapper();			
			readObj = mapper.readValue(encObj.plainText, EntityMarshaller.class);			
			return readObj.items;
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Good bye");
			System.exit(1);
		}
		return null;
		//return pwdEntity;
	}
	
	public boolean Save(HashMap<String,HashMap<String,String>> entity){
		try {
			EntityMarshaller saveObj = new EntityMarshaller();
			saveObj.items.putAll(entity);
			ObjectMapper mapper = new ObjectMapper();
			String out = mapper.writeValueAsString(saveObj);	
			encObj.encrypt(out);
			FileOperations.write(new File(sourceMapper),encObj.cipherPhrase);			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	public boolean persist(HashMap<String,HashMap<String,String>> entity){
		try {
			EntityMarshaller saveObj = new EntityMarshaller();
			saveObj.items.putAll(entity);
			ObjectMapper mapper = new ObjectMapper();
			String out = mapper.writeValueAsString(saveObj);			
			encObj.encrypt(out);
			FileOperations.write(new File(sourceMapper),encObj.cipherPhrase);			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return true;
	}
	
	public void createBootFile(){
		if(!new File(sourceMapper).exists()){
			System.out.println("Creating boot file");
			HashMap<String,HashMap<String,String>> temp = new HashMap<String,HashMap<String,String>>();
			HashMap<String,String> e = new HashMap<String,String>();
			e.put("displayName", "PAN");
			e.put("value", "AJWPG4074H");
			e.put("quickRef", "value");
			temp.put("PAN", e);			
			Save(temp);			
		}		
	}
	
	public static void main(String[] a){
		try {		
			SourceParser p = new SourceParser();
			p.createBootFile();
			p.load();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

}
