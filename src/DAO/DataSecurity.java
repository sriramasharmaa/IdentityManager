package DAO;

import java.io.File;

import crypt.BlowfishKey;

public class DataSecurity {
	BlowfishKey encObj;
	public DataSecurity() throws Exception {
		// TODO Auto-generated constructor stub
		encObj = new BlowfishKey("Sard0nyx1");
	}

	void encrypt(File f){
		try {
			encObj.encrypt(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	void decrypt(File f){
		try {
			encObj.decrypt(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
