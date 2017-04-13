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
		contentPane.setName("PlayerNamePanel");
		displayNorth();
		displaySouth();
	}

	/** Method displays contents in the north **/
	private void displayNorth(){
		
		lblEnterPlayers = new MyLabel("Enter Player Names", 52);
		north.add(lblEnterPlayers);
	}

	/** Method displays contents in the center **/
	public void displayCenter(int playerTotal){
        for (int count = 0; count < playerTotal; count++) {
            // this sets a default text in the textField
            JTextField playerNameInput = new MyTextField("", 30);
            center.add(playerNameInput, "cell 0 " + count + " ,growx");
            playerNameInput.setName(Integer.toString(count+1));
            textFields.add(playerNameInput);
        }
	}
	
	/** Method displays contents in the south **/
	private void displaySouth(){
		
		btnHome = new MyButton("Home",buttonFontSize);
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
			if(f.getText().equals("")||f.getText()==null){
				names.add("Player "+f.getName());
			}else{
				names.add(f.getText());
			}
		}
		return names;
	}

}