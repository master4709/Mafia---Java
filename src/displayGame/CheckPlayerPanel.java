package displayGame;

import myJStuff.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Pierce de Jong 30006609
 * This class creates a content pane with the current players name at the top of the screen.
 * A button is created at the bottom of the screen and when pressed goes to the night screen for the current player
 *
 *
 *
 */
public class CheckPlayerPanel extends MyPanel{

	//Labels to be displayed on the JPanels
	private JLabel lblAreYouReally;//Displays text "Are you Really"
	private JLabel lblPlayer;////Displays the current player's name to the screen
	private JButton btnYes;//Button pressed to go to next screen
	
	/**
	 * Create the frame.
	 */
	public CheckPlayerPanel(ActionListener packageListener) {
		this.packageListener = packageListener;
		
		contentPane.setName("CheckPlayer Panel");
		//Displays all of the JLabels, JButtons etc. to all of the JPannels
		displayNorth();
		displaySouth();
	}
	/**
	 * Displays all of the JLabels for the north panel
	 * Adds labels to the Panel via a coord system x y a b
	 */
	private void displayNorth(){
		
		lblAreYouReally = new MyLabel("Pass device to:", textColor, roleFont);
		north.add(lblAreYouReally, "cell 0 0,alignx center");
		
		lblPlayer = new MyLabel("", textColor, titleFont);
		north.add(lblPlayer, "cell 0 1,alignx center");
	}
	/**
	 * Displays all of the JObjects for the south panel
	 */
	private void displaySouth(){
		//This button displays the text yes and when pressed goes to the GameController and switches the contentPane
		//to the next player in the night cycle
		btnYes = new MyButton("That's me",45);
		south.add(btnYes, "cell 0 0,growx");
		btnYes.addActionListener(packageListener);
		btnYes.setName("Continue_CheckPlayerPanel");
		
	}
	/**
	 * Sets the lblPlayer text to the current player
	 * @param text
	 */
	public void setPlayerName(String text){
		lblPlayer.setText(text);
	}

}
