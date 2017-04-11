package displayGame;

import myJStuff.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * @author Pierce de Jong 30006609
 * This class creates a content pane with the current players name at the top of the screen.
 * A button is created at the bottom of the screen and when pressed goes to the night screen for the current player
 */
public class CheckPlayerPanel extends MyPanel{

	private JLabel lblAreYouReally;
	private JLabel lblPlayer;
	
	private JButton btnYes;
	
	/**
	 * Constructor
	 * @param packageListener - Used for buttons that only want to change stuff within the package
	 */
	public CheckPlayerPanel(ActionListener packageListener) {
		this.packageListener = packageListener;
		
		contentPane.setName("CheckPlayer Panel");
		
		displayNorth();
		displaySouth();
	}
	/**
	 * Creates the Labels for the north Panel
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
		btnYes = new MyButton("That's me",roleFont);
		south.add(btnYes, "cell 1 0,growx");
		btnYes.addActionListener(packageListener);
		btnYes.setName("Continue_CheckPlayerPanel");
		
	}
	/**
	 * Sets the lblPlayer text to the current player
	 * @param text
	 */
	public void setPlayerName(String text){
		lblPlayer.setText(text);
		lblPlayer.setFont(new MyFont(setFont(text,75,100)));
	}

}
