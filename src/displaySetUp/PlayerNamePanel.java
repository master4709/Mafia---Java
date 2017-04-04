package displaySetUp;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTextField;

import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;
import myJStuff.MyTextField;
/**
 * This class creates the Panel for selecting how many
 * players you want in the game
 * @author Elvin Limpin 30018832
 * 
 *
 */
public class PlayerNamePanel extends MyPanel{
	
	//
    private ArrayList<JTextField> textFields = new ArrayList<>();
	
	MyLabel enterPlayers = new MyLabel("Enter Player Names", 40);
    JButton continueButton = new MyButton("Continue", Colors.white, Colors.black, 25);

	public PlayerNamePanel(ActionListener packageListener) {
		this.packageListener = packageListener;
		displayNorth();
		displaySouth();
	}
	
	private void displayNorth(){
		north.add(enterPlayers);
	}

	public void displayCenter(int playerTotal){
        for (int count = 0; count < playerTotal; count++) {
            // this sets a default text in the textField
            JTextField playerNameInput = new MyTextField("Player " + String.valueOf(count+1), 0);
            center.add(playerNameInput, "cell 0 " + count + " ,growx");
            textFields.add(playerNameInput);
        }

	}
	
	private void displaySouth(){
		south.add(continueButton, "cell 0 0,growx");
        continueButton.addActionListener(packageListener);
        continueButton.setName("Continue_PlayerName");
	}
	
	/**
	 * Returns a List of Strings of all of the player names
	 * @return
	 */
	public List<String> getPlayerNames(){
		List<String> names = new ArrayList<>();
		
		for(JTextField f: textFields){
			names.add(f.getText());
		}
		return names;
	}

}