package displayMain;

import myJStuff.*;

import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.Dimension;

/**
 * This class creates panel for Rules of Mafia game by creating 
 * different panels and putting them together. 
 * The panels are : North panel, Center panel and South panel. 
 * Each panel contains button or label or text field. 
 * The back button is assigned to the action of going back to the main menu.  
 * 
 * @author Mahsa Lotfi 10072013
 *
 */
public class RulePanel extends MyPanel{
	/**
	 * Instance variables.
	 */	
	//north panel label
	private JLabel lblAbout;
	
	//south panel back button
	private JButton btnBack;
	
	//Panes for center panel
	private JTextArea myTxtPane;
	private JScrollPane scrollPane;
	
	
	private String textFromFile = "";
	
	/**
	 * Constructor with one argument of ActionListener actionListener.
	 * This constructor will initialize the actionListener and call 
	 * other methods of this class for display.
	 * @param actionListener
	 */
	public RulePanel(ActionListener actionListener) {
		this.packageListener = actionListener;
		
		contentPane.setName("Rule Panel");
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	/**
	 * Method to display content of center panel. It will set up the text and scroll panes.
	 * This method will read the text from a file and present it on the panel.
	 */
	private void displayCenter(){
		//Reading text from a file in data folder and store it as a string.
		try {
			String fileName = "src/resources/rules.txt";
			Scanner inputStream = new Scanner (new File(fileName));        	
			while (inputStream.hasNextLine() == true) {
				textFromFile += "\n";
				textFromFile += inputStream.nextLine();
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
	    	return;
	    }	
		
		//textField set up.	
		myTxtPane = new MyTextArea(textFromFile, textColor, backgroundColor, textFontSize);
		
		//scrollPane set up.
		scrollPane = new JScrollPane(myTxtPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(500, 750));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		center.add(scrollPane);
	}
	
	/**
	 * Method to display content of north panel, which is label.
	 */
	private void displayNorth(){
		lblAbout = new MyLabel("Rules", titleFontSize);
		north.add(lblAbout, "flowx,cell 0 0");
	}
	
	/**
	 * Method to display contents of south panel. This panel contains 
	 * the back button which goes back to main menu.
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 1 0");
		btnBack.addActionListener(packageListener);
		btnBack.setName("Back_RulePanel");			
	}
}