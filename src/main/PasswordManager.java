package main;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Iterator;
import java.util.Map.Entry;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import main.design.IdentityManager;
import main.view.ChangePassword;
import main.view.IdentityEdit;
import main.view.IdentityList;
import DAO.IdentityEntityFactory;

public class PasswordManager  implements IdentityManager{
	private PasswordManager currentObj ;
	public IdentityEntityFactory entityFactory;//Data model
	public IdentityList listObject;
	public JList<String> lst;
	public final DefaultListModel<String> model = new DefaultListModel<String>();
	
	public PasswordManager(String pass) throws Exception{		
		entityFactory = new IdentityEntityFactory(pass);// load data model
		currentObj = this;
		listObject = new IdentityList(this);
		listObject.init();
	}
	

	@Override
	public void add() {
		// TODO Auto-generated method stub
		IdentityEdit obj;
		try {
			obj = new IdentityEdit(this);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void edit(String key) {
		// TODO Auto-generated method stub
		IdentityEdit obj;
		try {
			obj = new IdentityEdit(this, key);			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	@Override
	public void delete() {
		if(currentObj.lst.getSelectedValue() != null){
			String deleteItem = currentObj.lst.getSelectedValue().toString();
			model.remove(currentObj.lst.getSelectedIndex());
			entityFactory.Delete(deleteItem);
		}
	}
	
	public void addSettings(){
		
	
	}
	
	public void copy(){
		String result = entityFactory.sm.readObj.items.get(currentObj.lst.getSelectedValue().toString()).get("pass");
		StringSelection selection = new StringSelection(result);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
				
	}
	
	public void changePassword(){
		//ChangePassword obj;
		try {
			new ChangePassword(entityFactory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}



	
	@Override
	public void list() {
		// TODO Auto-generated method stub
		
	}
	
	public void persistOnCloud(){
		
	}
}
