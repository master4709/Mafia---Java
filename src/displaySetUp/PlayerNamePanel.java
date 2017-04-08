package displaySetUp;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;
import myJStuff.MyTextField;
import net.miginfocom.swing.MigLayout;
/**
 * This class creates the Panel for selecting how many
 * players you want in the game
 * @author Elvin Limpin 30018832
 * 
 *
 */
public class PlayerNamePanel extends MyPanel{
	
	private ActionListener globalListener;
	
    private ArrayList<JTextField> textFields = new ArrayList<>();
	
	JLabel lblEnterPlayers;
	
    JButton btnContinue;
    JButton btnHome;

	/**
	 * Method displays the panels for inputting the player name
	 * @param packageListener
	 * @param globalListener
	 */
	public PlayerNamePanel(ActionListener packageListener, ActionListener globalListener) {
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		displayNorth();
		displaySouth();
	}

	/** Method displays contents in the north **/
	private void displayNorth(){
		
		lblEnterPlayers = new MyLabel("Enter Player Names", roleFont);
		north.add(lblEnterPlayers);
	}

	/** Method displays contents in the center **/
	public void displayCenter(int playerTotal){
        for (int count = 0; count < playerTotal; count++) {
            // this sets a default text in the textField
            JTextField playerNameInput = new MyTextField("Player " + String.valueOf(count+1), 30);
            center.add(playerNameInput, "cell 0 " + count + " ,growx");
            textFields.add(playerNameInput);
        }
	}
	
	/** Method displays contents in the south **/
	private void displaySouth(){
		south.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		btnHome = new MyButton("Home",buttonFont);
		south.add(btnHome,"cell 0 0,alignx left");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");
		
		btnContinue = new MyButton("Continue");
		btnContinue.setName("Continue_PlayerName");
		south.add(btnContinue, "cell 1 0,growx");
		btnContinue.addActionListener(packageListener);
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