package main;
import javax.swing.*;
import main.view.PasswordDialog;


public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String settingsData = new Settings("init").getDataPath();
		if(settingsData.equals("")){
			Settings obj = new Settings();
			obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		}
		else{
			String passwd="Sard0nyx1";
			if(passwd==null || passwd.trim().length()==0){
				System.exit(0);
			}
			passwd = new PasswordDialog().init();
			new PasswordManager(passwd);			
		}
				
	}
}







