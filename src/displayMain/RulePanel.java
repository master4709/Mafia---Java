package displayMain;

import myJStuff.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.ScrollPane;
import java.awt.Point;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;

/**
 * This class creates panel for Rules of Mafia game.
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
		
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
		
		//setting background of each panel. 
		setBackground(backgroundColor);
	}
	
	/**
	 * Method to display content of center panel. It will set up the text and scroll panes.
	 */
	private void displayCenter(){
		//textField set up.
		myTxtPane = new MyTextArea("text", textColor, backgroundColor, textFont);
		myTxtPane.setText("The Mafia party game presents a \r\nconflict between the Mafia "
				+ "(the \r\ninformed minority) and the \r\nInnocents (the uninformed \r\nmajority). "
				+ "The game has two \r\nphases; \"night\", when the Mafia \r\nmight secretly murder an innocent,"
				+ "\r\nand \"day\" when Innocents vote\r\nto eliminate a Mafiosi suspect. \r\n\r\nThe game ends "
				+ "when all the Mafia \r\nmembers are eliminated or there \r\nare more Mafia members than \r\n"
				+ "Innocents.\r\n\r\nHow to play:\r\n\r\n1-Choose Players.\r\n\r\n2-Each player is going to be "
				+ "\r\nassigned for a role in Mafia's \r\nworld. \r\n\r\nThe roles are:\r\n-Townie:Do nothing at "
				+ "night.\r\n-Detective:Reveals the team for \r\none player per night.\r\n-Mafia(Hitman):May kill "
				+ "someone \r\neach night.\r\n-Doctor:May heal one player each \r\nnight (Cant go same person two "
				+ "\r\nnights in a row).\r\n-Survivor:Do nothing at night \r\n-Mafia(Barman):May stop the \r\naction "
				+ "of another player each \r\nnight (Can't go same person \r\neach night).[6+ players]\r\n"
				+ "-Bodyguard:May save another \r\nperson by stepping infront of \r\nthe bullet per night. "
				+ "(You will die \r\nin their place).[7+ players]\r\n-Lyncher:Do nothing at night.\r\n[8+ players]"
				+ "\r\n-Mafiaboss(GodFather):Hidden \r\nfrom the Detective. Can send a \r\nmessage to another Mafia "
				+ "\r\nmemeber each night. [9+ players]\r\n-Vigilante:May kill new person each \r\nnight, but is "
				+ "trying to kill Mafia. \r\nNote: Do not have to kill someone \r\neach night. [10 players].\r\n\r\n"
				+ "3-Play through the day and night \r\ncycles to see which side is going \r\nto win.\r\n\r\n");
		
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
		buttonFont = new MyButtonFont();
		textFont = new MyFont(30);
	}
	
	/**
	 * Method to set the text and background colors.
	 */
	private void setColor(){
		textColor = Colors.white;
		btnBackgroundColor = Colors.grey;
		backgroundColor = Colors.black;
		btnTxtColor = Color.black;
	}
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	

}