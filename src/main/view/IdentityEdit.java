package main.view;

import io.FileOperations;

import java.awt.Container;

import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.PasswordManager;

import DAO.IdentityEntityFactory;




public class IdentityEdit extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtfText1; //Identity Name
	JTextField jtfText2; //Identity Username
	JPasswordField jtfText3; // Identity password
	JTextField jtfText4; //Identity url
	JTextField jtfText5; //Identity notes
	JButton jButton;
	String disp = "";
	TextHandler handler = null;
	OnSubmit handlerSubmit = null;
	IdentityEntityFactory entityFactory;//Data model
	IdentityEdit currentObj ;
	PasswordManager parentObj;//controller
	public String addedKey;
	String inpKey;
	
	public IdentityEdit(PasswordManager parentObj) {
		super("Edit Identity");		
		this.parentObj = parentObj;
		init(null);
	}
	
	public IdentityEdit(PasswordManager parentObj, String key) {
		super("Edit Identity");		
		this.parentObj = parentObj;
		inpKey = key;
		init(key);
	}
	
	public void init(String key){

		currentObj = this;		
		this.entityFactory = parentObj.entityFactory;		
		Container container = getContentPane();
		container.setLayout(new GridLayout(6,2));
		jtfText1 = new JTextField(10);
		jtfText2 = new JTextField(10);
		jtfText3 = new JPasswordField(10);
		jtfText4 = new JTextField(10);
		jtfText5 = new JTextField(10);
		jButton = new JButton("Submit");
		container.add(new JLabel("Identity Name"));
		container.add(jtfText1);
		container.add(new JLabel("Username"));
		container.add(jtfText2);
		container.add(new JLabel("Password"));
		container.add(jtfText3);
		container.add(new JLabel("URL"));
		container.add(jtfText4);
		container.add(new JLabel("Notes"));
		container.add(jtfText5);
		
		container.add(jButton);
		handler = new TextHandler();
		handlerSubmit = new OnSubmit();
		jtfText1.addActionListener(handler);	
		jtfText2.addActionListener(handler);
		jButton.addActionListener(handlerSubmit);
		
		setSize(325, 300);
		setVisible(true);
		if(key != null){
			load(key);			
		}		
	}
	
	public void load(String key){
		jtfText1.setText(parentObj.entityFactory.identityListFromStorage.get(key).get("displayName"));
		jtfText2.setText(parentObj.entityFactory.identityListFromStorage.get(key).get("username"));
	}

	private class TextHandler implements ActionListener,Transferable {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == jtfText1 || e.getSource() == jtfText2) {
				disp = "text1 : " + e.getActionCommand();
				String key = e.getActionCommand().trim();
				if(key.length()==0){
					JOptionPane.showMessageDialog(null, "Enter a valid input");
				}
				String result = entityFactory.Fetch(key);
			} 
			//JOptionPane.showMessageDialog(null, disp);
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			// TODO Auto-generated method stub
			return false;
		}
	}
    private String toUnicode(String s){
    	String temp="";
    	for(int i=0;i<s.length();i++){
    		temp = temp + String.format("\\u%04x",(int)s.charAt(i));
    	}
    	return temp;
    }	
    
	private class OnSubmit implements ActionListener {
		public void actionPerformed(ActionEvent e) {				
			currentObj.addedKey = currentObj.jtfText1.getText();
			entityFactory.Store(currentObj.addedKey, serializeForm());				
			parentObj.listObject.addToList(currentObj.addedKey, currentObj.inpKey);				
			currentObj.setVisible(false);			
		} 
			
		
		HashMap<String,String> serializeForm(){
			HashMap<String,String> ret = new HashMap<String,String>();
			ret.put("name", currentObj.jtfText1.getText());
			ret.put("displayName", currentObj.jtfText1.getText());
			ret.put("username", currentObj.jtfText2.getText());
			ret.put("password", currentObj.jtfText3.getText());
			ret.put("url", currentObj.jtfText4.getText());
			ret.put("notes", currentObj.jtfText5.getText());
			return ret;			
		}
	}




}
