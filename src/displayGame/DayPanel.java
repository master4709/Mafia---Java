
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class DayPanel extends MyPanel{
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//This label displays the text for what to do on this screen
	private JLabel lblDiscription;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	//Stores all of the data of the players
	//Does not change the date stored in it EVER
	private List<Player> playerInfo;
	//List of buttons representing each player that is alive
	//Pressing a button will set them as the target for the day lynching
	private List<JButton> playerButtonList = new ArrayList<>();
	//If the game is in test mode
	private boolean test;
	/**
	 * Constructor
	 */
	public DayPanel(ActionListener actionListener,List<Player> playerInfo, boolean test) {
		this.actionListener = actionListener;
		this.playerInfo = playerInfo;
		this.test=test;
		//Create all of the labels and buttons etc needed for the Panel
		displayNorth();
		displaySouth();
		displayCenter();
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		lblDayTime = new MyLabel("Day Time", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text = "One player must be voted out each day.";// There must be a 50% majority to lynch him/her";
		lblDiscription = new MyLabel(text, textColor, infoFont);
		//lblDiscription.setWrapStyleWord(true);
		//lblDiscription.setLineWrap(true);
		north.add(lblDiscription, "cell 0 1,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 1 0");
		btnContinue.addActionListener(actionListener);
		btnContinue.setName("Continue_DayPanel");
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	private void displayCenter(){
		//Loop through the list of players
		for(Player player: playerInfo){
			//If the player is NOT dead
			if(!player.isDead()){
				//create a button of that player
				displayPlayerButton(player.getPlayPosition());
			}
		}
	}
	/**
	 * Creates a button for a player when called in displayCenter()
	 * 
	 * @param i - index value of player and location on center grid y value
	 */
	private void displayPlayerButton(int i){
		//Create string of the players name and role (debug)
		String text = playerInfo.get(i).getName();
		if(test) {text = text+" | "+playerInfo.get(i).getRole();}
		JButton btnPlayer = new MyButton(text);//Create a new button with passing the String text
		btnPlayer.setName("Day_"+Integer.toString(i));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+i+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(actionListener);//Add action listener 
		playerButtonList.add(btnPlayer);//Add to the list of player buttons
	}
	
	/**
	 * Removes the button of the the target
	 * @param target
	 */
	public void removePlayerButton(int target){
		if(target!=-1){//Error handling, Must have a valid target to remove the button
			for(JButton button: playerButtonList){//Loops through the list of player buttons
				if(button.getName().contains(Integer.toString(target))){//Finds the one with the same name as the target. THe buttons are named 0,1,2... etc
					center.remove(button);//Remove the button from the list of buttons 
				}
			}
		}
	}
	/**
	 * 
	 * @param previous
	 * @param target
	 */
	public void setButtonSelected(int previous,int target){
		for(JButton button: playerButtonList){
			if(button.getName().contains(Integer.toString(target))){
				button.setBackground(selectColor);
			}else if(button.getName().contains(Integer.toString(previous))){
				button.setBackground(btnBackgroundColor);
			}
		}
	}

	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}
