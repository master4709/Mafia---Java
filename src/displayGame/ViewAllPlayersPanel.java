
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * 
 * @author 

 */
public class ViewAllPlayersPanel extends MyPanel{
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//This label displays the text for what to do on this screen
	private JLabel lblDiscription;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	//Stores all of the data of the players
	//Does not change the date stored in it EVER
	private List<String> playerNames;
	/**
	 * Create the panel.
	 */
	public ViewAllPlayersPanel(ActionListener actionListener, List<String> playerNames) {
		this.playerNames = playerNames;
		this.actionListener = actionListener;
		
		displayNorth();
		displaySouth();
		displayCenter();
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		lblDayTime = new MyLabel("View Players", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text = "Click a Player to view Role";
		lblDiscription = new MyLabel(text, textColor, textFont);
		north.add(lblDiscription, "cell 0 1,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Start Game");
		south.add(btnContinue, "cell 0 0, alignx center");
		btnContinue.setName("Continue_ViewAllPlayersPanel");
		btnContinue.addActionListener(actionListener);
		
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	private void displayCenter(){
		//Loops through the list of players adn create a button for each player
		for(int i =0;i<playerNames.size();i++){
			displayPlayerButton(i);
		}
	}

	/**
	 * Creates a button for a player when called in displayCenter()
	 * @param i - 
	 */
	private void displayPlayerButton(int i){
		//Create string of the players name
		String text = playerNames.get(i);
		//Create a new button with passing the String text
		JButton btnPlayer = new MyButton(text);
		btnPlayer.setName("Select_"+Integer.toString(i));
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position); 
		btnPlayer.addActionListener(actionListener);//Add action listener
	}
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}
