package displayMain;

import myJStuff.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Component;

/**
 * This class creates About panel for Mafia game.
 * @author
 * 
 *
 */
public class AboutPanel{
	//colors that will be used for backgrounds and texts.
	private Color textColor;
	private Color btnBackgroundColor;
	private Color backgroundColor;
	private Color btnTxtColor;
	
	// Fonts that will be used in text field, label and button text.
	private Font titleFont;
	private Font buttonFont;
	private Font textFont;

	//Panel that gets set to the frame and displays the contents of this class
	private JPanel contentPane;
	
	//Panels that are added to the content pane. All JObjects get added to these panels
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	//north panel label
	private JLabel lblAbout;
	
	//south panel back button
	private JButton btnBack;
	
	//textPane for center panel
	private MyTextArea myTxtPane;

	/**
	 * Create the frame.
	 */
	public AboutPanel() {
		//setting font and color
		setFont();
		setColor();
		
		//creating content pane which holds all the panels together.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//creating layout for each panels
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
		
		//setting background of each panel. 
		setBackground(backgroundColor);
	}
	
	/**
	 * Method to display content of north panel, which is label.
	 */
	private void displayNorth(){
		lblAbout = new MyLabel("About", textColor, titleFont);
		north.add(lblAbout, "cell 0 0");
	}
	
	/**
	 * Method to display contents of south panel. This panel contains the back button which goes back to main menu.
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back", btnTxtColor, btnBackgroundColor, buttonFont);
		south.add(btnBack, "cell 0 0");
		// setting an action for back button
		btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchMain();
		}});
		
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
	 * Method to set the background colors for each panels.
	 */
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
	}
	
	/**
	 * Method to set the font for label, button and textField.
	 */
	private void setFont(){
		titleFont = new MyFont(50);
		buttonFont = new MyFont(25);
		textFont =new MyFont(25);
	}
	
	/**
	 * Method to set the text and background colors.
	 */
	private void setColor(){
		textColor = Colors.white;
		btnBackgroundColor = Colors.grey;
		backgroundColor = Colors.black;
		btnTxtColor = Colors.black;
	}
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}

}
