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
 * This class creates panel for Rules of Mafia game.
 * 
 * @author
 *
 */
public class RulePanel{
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
	private JPanel east;
	private JPanel center;
	private JPanel west;
	
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
		south.setLayout(new MigLayout("", "[][][][][][][][][][]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
		
		//setting background of each panel. 
		setBackground(backgroundColor);
	}
	
	/**
	 * Method to display content of center panel. It will set up the text and scroll panes.
	 * @throws  
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
		// setting an action for back button
		btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchMain();
    			
		}});
		
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
		textFont = new MyFont(25);
	}
	
	/**
	 * Method to set the text and background colors.
	 */
	private void setColor(){
		textColor = Colors.white;
		btnBackgroundColor = Colors.grey;
		backgroundColor = Colors.black;
		btnTxtColor = Color.white;
	}
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	

}