
package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * The north Panel displays all of the JLabels needed for the Day Cycle
 * The center panel holds all of the player buttons - Theses are used for selecting the target of the lynching
 * @author Pierce de Jong 30006609
 *
 */
public class DayPanel extends MyPanel{
	private ActionListener globalListener;
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//These labels display the text for what to do on this screen
	private JLabel lblDescription1;
	private JLabel lblDescription2;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	
	private JButton btnHome;
	//List of buttons representing each player that is alive
	//Pressing a button will set them as the target for the day lynching
	private List<JButton> playerButtonList = new ArrayList<>();
	/**
	 * Constructor
	 * @param packageListener - Used for buttons that only want to change stuff within the package
	 * @param globalListener - Used for buttons that change the entire program from one Controller to Another, (E.g. Home buttons)
	 */
	public DayPanel(ActionListener packageListener, ActionListener globalListener) {
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		
		contentPane.setName("Day Panel");
		
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
		
		lblDayTime = new MyLabel("Day Time", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0 2 0,alignx center");
		
		String text1 = "Talk among yourselves to choose who to lynch";// There must be a 50% majority to lynch him/her";
		lblDescription1 = new MyLabel(text1, textColor, infoFont);
		north.add(lblDescription1, "cell 0 1 2 1");
		
		String text2 = "Select him/her once there is a majority vote";// There must be a 50% majority to lynch him/her";
		lblDescription2 = new MyLabel(text2, textColor, infoFont);
		north.add(lblDescription2, "cell 0 2 2 1");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 0 0, alignx center");
		btnContinue.addActionListener(packageListener);
		btnContinue.setName("Continue_DayPanel");
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	public void displayCenter(List<Player> playerInfo){
		//Loops through the list of players and create a button for each player
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				displayPlayerButton(p);
			}
		}
	}
	/**
	 * Creates a button for a player when called in displayCenter()
	 * 
	 * @param i - index value of player and location on center grid y value
	 */
	private void displayPlayerButton(Player p){
		//Create string of the players name and role (debug)
		String text = p.getName();
		//if(test) {text = text+" | "+playerInfo.get(i).getRole();}
		JButton btnPlayer = new MyButton(text);//Create a new button with passing the String text
		btnPlayer.setName("Day_"+Integer.toString(p.getPosition()));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+p.getPosition()+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(packageListener);//Add action listener 
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
	
	public void setContinueButtonVisible(boolean bool){
		btnContinue.setVisible(bool);
	}
}
