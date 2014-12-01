package main.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import DAO.IdentityEntityFactory;

public class ChangePassword extends JFrame{

		//Class Declarations
		JPasswordField jtfText1;
		TextHandler handler = null;
		ChangePassword currentObj ;	
		IdentityEntityFactory entityList;
		//Constructor
		public ChangePassword(IdentityEntityFactory entityList) throws Exception {
			super("Change Password");
			this.entityList = entityList;
			currentObj = this;
			Container container = getContentPane();			
			container.setLayout(new FlowLayout());			
			container.add(new JLabel("New Password"));
			jtfText1 = new JPasswordField(10);
			container.add(jtfText1);			
			JButton submit = new JButton("Save");
			handler = new TextHandler();
			submit.addActionListener(handler);			
			container.add(submit);		
			setSize(325, 100);
			setVisible(true);
			setResizable(false);
		}

		//Inner Class TextHandler
		private class TextHandler implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				entityList.sm.ChangePassword(jtfText1.getText());
				entityList.Persist();
				currentObj.setVisible(false);					
			}
		}
	
}
