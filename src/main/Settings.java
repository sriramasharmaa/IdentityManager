package main;

import io.FileOperations;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



class Settings extends JFrame{
	//Class Declarations
	JTextField dataPath;
	JButton submit;
	String disp = "";
	TextHandler handler = null;
	Settings currentObj ;
	JFileChooser fc;
	JButton browse;
	
	//Constructor
	public Settings(String init){
		
	}
	
	public Settings() throws Exception {
		super("Settings");
		currentObj = this;
		Container container = getContentPane();
		container.setLayout(new GridLayout(2,2));
		dataPath = new JTextField(10);
		dataPath.setText(getDataPath());
		container.add(new JLabel("Password file path"));
		container.add(dataPath);
		submit = new JButton("Save");
		handler = new TextHandler();
		submit.addActionListener(handler);
		container.add(submit);
		browse = new JButton("Browse");
		browse.addActionListener(new FileHandler());		
		container.add(browse);
		setSize(525, 100);
		setVisible(true);
		setResizable(false);
	}
	
	public String getDataPath() throws Exception{
		String file = System.getProperty("user.dir")+"/settings.txt";
		if(new File(file).isFile()){
			byte[] temp = FileOperations.read(new File(file));
			return new String(temp);
		}
		else{
			return "";
		}
	}
	
	private class FileHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			fc = new JFileChooser();
			fc.setCurrentDirectory(new File("."));
			fc.setDialogTitle("Choose the password file");
			if(fc.showOpenDialog(currentObj)==JFileChooser.APPROVE_OPTION){
				currentObj.dataPath.setText(fc.getSelectedFile().toString());
			}
		}
		
	}
	//Inner Class TextHandler
	private class TextHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submit) {				
				String file = System.getProperty("user.dir")+"/settings.txt";
				byte[] cont = currentObj.dataPath.getText().getBytes();
				try {
					FileOperations.write(new File(file), cont);
				} catch (Exception e1) {					
					e1.printStackTrace();
				}
			} 			
		}
	}		
}