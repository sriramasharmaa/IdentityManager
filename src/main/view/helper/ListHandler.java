package main.view.helper;

import io.DropboxFileUpload;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.PasswordManager;
import main.view.IdentityList;

public class ListHandler implements KeyListener {
	IdentityList parentObj;
	PasswordManager controller;
	public ListHandler(IdentityList p, PasswordManager controller) {
		// TODO Auto-generated constructor stub
		this.parentObj = p;
		this.controller = controller;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode()==49){//store in cloud
			try {
				new DropboxFileUpload(controller.entityFactory.sm.sourceMapper);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(arg0.getKeyCode()==127){//delete

			if(parentObj.lst.getSelectedValue() != null){
				String deleteItem = parentObj.lst.getSelectedValue().toString();
				parentObj.model.remove(parentObj.lst.getSelectedIndex());
				parentObj.refreshList();
				controller.entityFactory.Delete(deleteItem);					
			}
			
		}
		else if(arg0.getKeyCode()==67){//change password 'c'
				controller.changePassword();
		}			
		else if(arg0.getKeyCode()==61){//add '+'
			
			try {					
				controller.add();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		}
		else if(arg0.getKeyCode()==17){//'cntrl' copy paste
			String result = controller.entityFactory.Fetch(parentObj.lst.getSelectedValue().toString());
			StringSelection selection = new StringSelection(result);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);			
		}
		else if(arg0.getKeyCode()==83){//settings
			try {
				//Settings obj = new Settings();
				controller.addSettings();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}			
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}		
