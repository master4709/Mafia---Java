package displayGame;

import myJStuff.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import net.miginfocom.swing.MigLayout;

/**
 * @author Pierce de Jong 30006609
 * This class creates a content pane with the current players name at the top of the screen.
 * A button is created at the bottom of the screen and when pressed goes to the night screen for the current player
 *
 *
 *
 */
public class CheckPlayerPanel{

	//All of the Color variables needed for the screen
	//Receive values in setColor()
	private Color textColor;
	private Color backgroundColor;
	
	//All of the Font variables needed for the screen
	//Receive values in setFont()
	private Font nameFont;
	private Font questionFont;

	//This is the main JPanel for this class
	//Every other JPanel gets added to this one
	//Has a getter method to be used to display the content pane to the frame
	private JPanel contentPane;
			
	//All of the panels that get displayed on the content pane
	//Every other JObject for the content pane is displayed on one of these JPanels
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	//Labels to be displayed on the JPanels
	
	private JLabel lblAreYouReally;//Displays text "Are you Really"
	private JLabel lblPlayer;////Displays the current player's name to the screen
	
	private JButton btnYes;//Button pressed to go to next screen
	
	/**
	 * Create the frame.
	 */
	public CheckPlayerPanel() {
		
		//Sets all of the needed Fonts and Colors to needed values
		setFont();
		setColor();

		//This is the pane that every other pane (north,south etc) is put on
		contentPane = new JPanel();
		//Creates a 5 pixel border around the entire screen
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Creates all panes 
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		//Expands the first x position to fill the entire JPanel
		north.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		
		//Displays all of the JLabels, JButtons etc. to all of the JPannels
		displayNorth();
		displaySouth();
		setBackground(backgroundColor);
	}
	/**
	 * Displays all of the JLabels for the north panel
	 * Adds labels to the Panel via a coord system x y a b
	 */
	private void displayNorth(){
		
		lblAreYouReally = new MyLabel("Are You Really", textColor, questionFont);
		north.add(lblAreYouReally, "cell 0 0,alignx center");
		
		lblPlayer = new MyLabel("", textColor, nameFont);
		north.add(lblPlayer, "cell 0 1,alignx center");
	}
	/**
	 * Displays all of the JObjects for the south panel
	 */
	private void displaySouth(){
		//This button displays the text yes and when pressed goes to the GameController and switches the contentPane
		//to the next player in the night cycle
		btnYes = new MyButton("Yes");
		south.add(btnYes, "cell 0 0,growx");
		btnYes.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().switchNightPlayer();
		}});
		
	}
	
	/**
	 * Sets all of the panels background to the passed Color
	 * Also creates a black border around the edge of the screen
	 * @param c
	 */
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.defaultBorderColor);
	}
	/**
	 * Sets the lblPlayer text to the current player
	 * @param text
	 */
	public void setPlayerName(String text){
		lblPlayer.setText(text);
	}
	
	private void setFont(){
		nameFont = new MyFont(100);
		questionFont = new MyFont(60);
	}
	
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
	}
	/**
	 * Get the content pane
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}

}
