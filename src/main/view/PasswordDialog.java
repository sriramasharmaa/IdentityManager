package main.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordDialog extends JPanel{
	JLabel lbl;
	JPasswordField pwd;
	public PasswordDialog(){
		
		lbl = new JLabel("Password please...");
		pwd = new JPasswordField(10);
		pwd.requestFocusInWindow();
		pwd.requestFocus(true);
		add(lbl);
		add(pwd);
	}
	
	public String init(){
		String[] options = new String[]{"OK", "Cancel"};		
		int option = JOptionPane.showOptionDialog(null, this, "Enter password..",
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
	
		if(option==0){
			return new String(pwd.getPassword());
		}
		else{
			return "";
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new PasswordDialog().init());
	}

}
