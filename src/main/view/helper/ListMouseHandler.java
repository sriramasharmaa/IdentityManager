package main.view.helper;

import io.DropboxFileUpload;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.PasswordManager;
import main.view.IdentityList;

public class ListMouseHandler  implements MouseListener{
	IdentityList parentObj;
	PasswordManager controller;
	public ListMouseHandler(IdentityList p, PasswordManager controller) {
		// TODO Auto-generated constructor stub
		this.parentObj = p;
		this.controller = controller;

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getClickCount()==2){
			
			try {					
				controller.edit(parentObj.lst.getSelectedValue());					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
