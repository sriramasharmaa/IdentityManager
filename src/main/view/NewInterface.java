package main.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;

public class NewInterface extends JFrame{

	public NewInterface(){
		super("Identify Manager");
		Container container = getContentPane();
		
		init();
		setVisible(true);
		setResizable(false);
		setSize(400,400);
	}
	
	public void init(){
		JPanel cell1 = new JPanel();
		cell1.setLayout(new GridBagLayout());
		JButton btn1 = new JButton("Fetch");
		Document doc = new Document() {
			
			@Override
			public void render(Runnable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeUndoableEditListener(UndoableEditListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeDocumentListener(DocumentListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void remove(int arg0, int arg1) throws BadLocationException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putProperty(Object arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertString(int arg0, String arg1, AttributeSet arg2)
					throws BadLocationException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void getText(int arg0, int arg1, Segment arg2)
					throws BadLocationException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getText(int arg0, int arg1) throws BadLocationException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Position getStartPosition() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Element[] getRootElements() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getProperty(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Position getEndPosition() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Element getDefaultRootElement() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Position createPosition(int arg0) throws BadLocationException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void addUndoableEditListener(UndoableEditListener arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addDocumentListener(DocumentListener arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		JTextArea browserBody = new JTextArea("hii");
		browserBody.setSize(400, 200);
		browserBody.setEditable(true);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		//c.gridwidth = GridBagConstraints.NONE;
		c.fill = GridBagConstraints.HORIZONTAL;
		cell1.add(btn1,c);
		c.gridx=1;
		//c.gridy=1;
		cell1.add(browserBody,c);
		c.gridx=2;
		cell1.add(new JLabel("sdfldjf"));
		cell1.setVisible(true);
		add(cell1);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NewInterface();

	}

}
