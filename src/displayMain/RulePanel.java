package displayMain;

import myJStuff.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;

/**
 * This class creates panel for Rules of Mafia game by creating different panels and putting them together. 
 * Each panel contains button or label or text field. The back button is assigned to the action of 
 * going back to the main menu. 
 * 
 * @author Mahsa Lotfi
 *
 */
public class RulePanel extends MyPanel{
	//north panel label
	private JLabel lblAbout;
	
	//south panel back button
	private JButton btnBack;
	
	//Panes for center panel
	private MyTextArea myTxtPane;
	private JScrollPane scrollPane;
	
	
	private String textFromFile = "";
	
	/**
	 * Create the frame.
	 */
	public RulePanel() {
		
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	/**
	 * Method to display content of center panel. It will set up the text and scroll panes.
	 */
	private void displayCenter(){
		//Reading text from a file in data folder and store it as a string.
		try {
			String fileName = "data/ruleTxt.txt";
			Scanner inputStream = new Scanner (new File(fileName));        	
			while (inputStream.hasNextLine() == true) {
				textFromFile += "\n";
				textFromFile += inputStream.nextLine();
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found");
	    }	
		
		//textField set up.	
		myTxtPane = new MyTextArea(textFromFile, textColor, backgroundColor, textFont);
		
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
		lblAbout = new MyLabel("Rules", textColor, titleFont);
		north.add(lblAbout, "flowx,cell 0 0");
	}
	
	/**
	 * Method to display contents of south panel. This panel contains the back button which goes back to main menu.
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back", btnTxtColor, btnBackgroundColor, buttonFont);
		south.add(btnBack, "cell 0 0");
		// setting the action for back button
		btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchMain();
    			
		}});
		
	}
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	

}