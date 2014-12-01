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
import DAO.IdentityEntity;
//import main.Settings;


public class IdentityList extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PasswordManager parentObj;//controller
	public final DefaultListModel<String> model = new DefaultListModel<String>();
	public JList<String> lst;
	public IdentityList(PasswordManager parentObj) {		
		super("Identity Store");
		this.parentObj = parentObj;
	}

	public void init(){
		load();
		lst.addKeyListener(new ListHandler());
		lst.addMouseListener(new listMouseHandler());
		JScrollPane scrollPane = new JScrollPane(lst);
		setSize(300,300);
		setResizable(false);		
		JPanel p1 =new JPanel(new BorderLayout());
		p1.add(scrollPane,BorderLayout.CENTER);
		p1.setVisible(true);
		add(p1);
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
	
	private class listMouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getClickCount()==2){
				
				try {
					//obj = new PassInputAdd(parentObj.entityFactory,parentObj.lst.getSelectedValue().toString());
					parentObj.edit(lst.getSelectedValue());
					//obj.SetParentObject(currentObj);
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
	
	
	private class ListHandler implements KeyListener {

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
					new DropboxFileUpload(parentObj.entityFactory.sm.sourceMapper);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else if(arg0.getKeyCode()==127){//delete

				if(lst.getSelectedValue() != null){
					String deleteItem = lst.getSelectedValue().toString();
					model.remove(lst.getSelectedIndex());
					refreshList();
					parentObj.entityFactory.Delete(deleteItem);					
				}
				
			}
			else if(arg0.getKeyCode()==67){//change password 'c'
				ChangePassword obj;
				try {
					obj = new ChangePassword(parentObj.entityFactory);
					//obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}			
			else if(arg0.getKeyCode()==61){//add '+'
				
				try {					
					parentObj.add();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			else if(arg0.getKeyCode()==17){//'cntrl' copy paste
				String result = parentObj.entityFactory.Fetch(parentObj.lst.getSelectedValue().toString());
				System.out.println(result);
				StringSelection selection = new StringSelection(result);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, selection);			
			}
			else if(arg0.getKeyCode()==83){//settings
				try {
					//Settings obj = new Settings();
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


}
