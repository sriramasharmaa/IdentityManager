package main.view;

import io.DropboxFileUpload;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import commons.HTTPUtils;


import main.PasswordManager;
import main.view.helper.ListHandler;
import main.view.helper.ListMouseHandler;
import DAO.IdentityEntity;
//import main.Settings;


public class IdentityList extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasswordManager parentObj;//controller
	public final DefaultListModel<String> model = new DefaultListModel<String>();
	public JList<String> lst;
	public IdentityList(PasswordManager parentObj) {		
		super("Identity Store");
		this.parentObj = parentObj;
	}

	public void init(){
		load();
		lst.addKeyListener(new ListHandler(this,parentObj));
		lst.addMouseListener(new ListMouseHandler(this,parentObj));
		JScrollPane scrollPane = new JScrollPane(lst);
		setSize(300,300);
		setResizable(false);		
		JPanel p1 =new JPanel(new BorderLayout());
		p1.add(scrollPane,BorderLayout.CENTER);
		p1.setVisible(true);
		add(p1);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public void load(){				
		Iterator<Entry<String, IdentityEntity>> it = parentObj.entityFactory.entityList.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, IdentityEntity> e = it.next();			
			try {
				model.addElement(e.getKey());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}		
		lst = new JList<String>(model);
	}

	public void addToList(String itemToBeAdded, String itemSelected){		
		if(itemSelected == null || !parentObj.entityFactory.entityList.containsKey(itemToBeAdded)){
			model.addElement(itemToBeAdded);
		}		
	}
	
	public void refreshList(){	
		parentObj.entityFactory.DeNormalizeRecent();
	}
	

	
	



}
