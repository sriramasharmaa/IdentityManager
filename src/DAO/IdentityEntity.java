package DAO;

import java.io.File;
import java.util.HashMap;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class IdentityEntity {
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public  String getVal() {
		return val;
	}                                                


	public void setVal(String val) {
		this.val = val;
	}


	private String key;
	private String val;
	private String label;
	private String templateName;//Catagory to view data
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}




}
