
package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * 
 * @author 

 */
public class ViewAllPlayersPanel extends MyPanel{
	
	private ActionListener globalListener;
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//These labels display the text for what to do on this screen
	private JLabel lblDescription1;
	private JLabel lblDescription2;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	private JButton btnHome;
	/**
	 * Create the panel.
	 */
	public ViewAllPlayersPanel(ActionListener actionListener, ActionListener globalListener) {
		this.packageListener = actionListener;
		this.globalListener = globalListener;
		
		contentPane.setName("ViewAllPlayers Panel");
		displayNorth();
		displaySouth();
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		
		btnHome = new MyButton("Home",buttonFont);
		north.add(btnHome,"cell 0 0,alignx left,aligny top");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");
		
		lblDayTime = new MyLabel("View Roles", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text1 = "Click on your name to check your role";
		lblDescription1 = new MyLabel(text1, textColor, textFont);
		north.add(lblDescription1, "cell 0 1,");
		
		String text2 = "Then, pass the device to the next player";
		lblDescription2 = new MyLabel(text2, textColor, textFont);
		north.add(lblDescription2, "cell 0 2,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Start Game");
		south.add(btnContinue, "cell 0 0, alignx center");
		btnContinue.setName("Continue_ViewAllPlayersPanel");
		btnContinue.addActionListener(packageListener);
		
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	public void displayCenter(List<Player> playerInfo){
		//Loops through the list of players adn create a button for each player
		for(Player p: playerInfo){
			displayPlayerButton(p);
		}
	}

	/**
	 * Creates a button for a player when called in displayCenter()
	 * @param i - 
	 */
	private void displayPlayerButton(Player p){
		//Create string of the players name
		String text = p.getName();
		//Create a new button with passing the String text
		JButton btnPlayer = new MyButton(text);
		btnPlayer.setName("Select_"+Integer.toString(p.getPosition()));
		String position = "cell 0 "+p.getPosition()+",growx";
		center.add(btnPlayer, position); 
		btnPlayer.addActionListener(packageListener);//Add action listener
	}
}
