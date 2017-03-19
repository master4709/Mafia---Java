
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * 
 * @author Pierce de Jong 30006609
 *
 *
 *
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
	private List<Player> playerInfo;
	/**
	 * Create the panel.
	 */
	public ViewAllPlayersPanel(ActionListener actionListener, List<Player> playerInfo) {
		this.playerInfo = playerInfo;
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
		lblDiscription = new MyLabel(text, textColor, infoFont);
		north.add(lblDiscription, "cell 0 1,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 0 0");
		btnContinue.setName("Continue_ViewAllPlayersPanel");
		btnContinue.addActionListener(actionListener);
		
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	private void displayCenter(){
		//Loop through the list of players
		for(int i=0;i<playerInfo.size();i++){
			//If the player is NOT dead
			if(!playerInfo.get(i).isDead()){
				//create a button of that player
				displayPlayerButton(i);
			}
		}
	}

	/**
	 * Creates a button for a player when called in displayCenter()
	 * 
	 * @param i - index value of player and location on center grid y value
	 */
	private void displayPlayerButton(int i){
		//Create string of the players name
		String text = playerInfo.get(i).getName();
		//Create a new button with passing the String text
		JButton btnPlayer = new MyButton(text);
		//Location on the grid, width of the button will be from screen edge to screen edge
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position);
		//Add action listener 
		btnPlayer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			GameController.getInstance().switchViewPlayer(i);
		}});
	}
	
	/**
	 * Sets all of the panels background to the passed Color
	 * Also creates a black border around the edge of the screen
	 * @param c
	 */
	
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	
	public void actionPerformed(ActionEvent e){
		//Get the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
        String name = source.getName();
        
        switch(name){
        case "0":
        	GameController.getInstance().switchViewPlayer(0);
        	break;
        case "1":
        	GameController.getInstance().switchViewPlayer(1);
        	break;
        case "2":
        	GameController.getInstance().switchViewPlayer(2);
        	break;
        case "3":
        	GameController.getInstance().switchViewPlayer(3);
        	break;
        case "4":
        	GameController.getInstance().switchViewPlayer(4);
        	break;
        case "5":
        	GameController.getInstance().switchViewPlayer(5);
        	break;
        case "6":
        	GameController.getInstance().switchViewPlayer(6);
        	break;
        case "7":
        	GameController.getInstance().switchViewPlayer(7);
        	break;
        case "8":
        	GameController.getInstance().switchViewPlayer(8);
        	break;
        case "9":
        	GameController.getInstance().switchViewPlayer(9);
        	break;
        case "10":
        	GameController.getInstance().switchViewPlayer(10);
        	break;
        default:
			break;
		}
	}
}
