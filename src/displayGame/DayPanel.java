
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionEvent;
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
public class DayPanel extends MyPanel implements ActionListener{
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
	//Stores the index value for the target of the dayLynching
	//To be used in the game class to kill the target of the day lynching
	private int target;
	/**
	 * Constructor
	 */
	public DayPanel(ActionListener actionListener,List<Player> playerInfo) {
		this.actionListener = actionListener;
		this.playerInfo = playerInfo;
		target = -1;
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
		JButton btnPlayer = new MyButton(text);//Create a new button with passing the String text
		btnPlayer.setName(Integer.toString(i));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+i+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(this);//Add action listener 
		playerButtonList.add(btnPlayer);//Add to the list of player buttons
	}
	
	/**
	 * Removes the button of the the target
	 * @param target
	 */
	public void removePlayerButton(int target){
		if(target!=-1){//Error handling, Must have a valid target to remove the button
			for(JButton button: playerButtonList){//Loops through the list of player buttons
				if(target==Integer.parseInt(button.getName())){//Finds the one with the same name as the target. THe buttons are named 0,1,2... etc
					center.remove(button);//Remove the button from the list of buttons 
				}
			}
		}
	}
	/**
	 * Resets the target to -1 so the panel is ready for the next Day
	 */
	public void resetTarget(){
		target = -1;
	}
	/**
	 * Get the target for who was voted out during the day
	 * @return int target
	 */
	public Integer getTarget(){
		return target;
	}
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	/**
	 * This listens for if one of the buttons in the center panel is pressed
	 * If it is pressed sets target to index value of button and sets the background color to selectColor
	 */
	public void actionPerformed(ActionEvent e){
		//Get the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		//Resets the background of the previously targeted player button if the current player has changed targets
		if(target!=-1){
			playerButtonList.get(target).setBackground(btnBackgroundColor);
		}
		//Finds the button that was pressed
		//Sets the background of the button to the select Color
		//Sets the target for the day lynching to the index value of the button
		switch(name){
		case "0":
			playerButtonList.get(0).setBackground(selectColor);
			target=0;
			break;
		case "1":
			playerButtonList.get(1).setBackground(selectColor);
			target=1;
			break;
		case "2":
			playerButtonList.get(2).setBackground(selectColor);
			target=2;
			break;
		case "3":
			playerButtonList.get(3).setBackground(selectColor);
			target=3;
			break;
		case "4":
			playerButtonList.get(4).setBackground(selectColor);
			target=4;
			break;
		case "5":
			playerButtonList.get(5).setBackground(selectColor);
			target=5;
			break;
		case "6":
			playerButtonList.get(6).setBackground(selectColor);
			target=6;
			break;
		case "7":
			playerButtonList.get(7).setBackground(selectColor);
			target=7;
			break;
		case "8":
			playerButtonList.get(8).setBackground(selectColor);
			target=8;
			break;
		case "9":
			playerButtonList.get(9).setBackground(selectColor);
			target=9;
			break;
		case "10":
			playerButtonList.get(10).setBackground(selectColor);
			target=10;
			break;
		default:
			break;
		}
	}
}
