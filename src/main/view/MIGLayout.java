package main.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class MIGLayout extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MIGLayout() {
		String[] labels = {"Name: ", "Password: ", "Url: ", "Note: "};
		int numPairs = labels.length;

		//Create and populate the panel.
		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
		    JLabel l = new JLabel(labels[i], JLabel.TRAILING);		    
		    p.add(l);
		    JTextField textField = new JTextField(10);
		    l.setLabelFor(textField);
		    p.add(textField);
		}
		JButton bt = new JButton("Save");
		p.add(bt);
		SpringUtilities.makeCompactGrid(p,
		                                numPairs, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);
		add(p);
		setSize(350,400);

		setVisible(true);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MIGLayout();
	}

}
