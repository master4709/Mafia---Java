package displayMain;

import myJStuff.*;

import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import javax.swing.JButton;

/**
 * This class creates About panel for Mafia game by creating 
 * different panels and putting them together.
 * The panels are : North panel, Center panel and South panel. 
 * Each panel contains button or label or text field. 
 * 
 * @author Mahsa Lotfi 10072013
 * 
 */
public class AboutPanel extends MyPanel{
	/**
	 * Instance variables.
	 */	
	//north panel label
	private JLabel lblAbout;
	
	//south panel back button
	private JButton btnBack;
	
	//textPane for center panel
	private JTextArea myTxtPane;

	/**
	 * Constructor with one argument of ActionListener actionListener.
	 * This constructor will initialize the actionListener and call 
	 * other methods of this class for display.
	 * @param actionListener
	 */
	public AboutPanel(ActionListener actionListener) {
		this.packageListener = actionListener;
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	/**
	 * Method to display content of north panel, which is label.
	 */
	private void displayNorth(){
		lblAbout = new MyLabel("About", textColor, titleFont);
		north.add(lblAbout, "cell 0 0");
	}
	
	/**
	 * Method to display contents of south panel. This panel contains 
	 * the back button which goes back to main menu.
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back", btnTxtColor, btnBackgroundColor, buttonFont);
		south.add(btnBack, "cell 0 0");
		// setting an action for back button
		btnBack.addActionListener(packageListener);
		btnBack.setName("Back_AboutPanel");
		
		
	}
	
	/**
	 * Method to display content of center panel. It will set up the text pane.
	 */
	private void displayCenter(){
		myTxtPane = new MyTextArea("", textColor, backgroundColor, textFont);
		myTxtPane.setText("\r\nVersion 1.11\r\nComputer Science 233: Final Project"
				+ "\r\n\r\nAuthors:\r\nChristilyn Arjona\r\nRonelle Bakima\r\nPierce De Jong"
				+ "\r\nElvin Limpin\r\nMahsa Lofti Gaskarimahalleh\r\n\r\n\r\nCopyright 2017. All Rights Reserved.");
				
		center.add(myTxtPane);
	}
	
	
	/**
	 * Getter method for the content pane.
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}

}
