package displaySetUp;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;
import myJStuff.MyTextField;
/**
 * This class creates the Panel for selecting how many players you want in the game
 * @author Elvin Limpin 30018832
 * 
 *
 */
public class PlayerNamePanel extends MyPanel{
	
	private int numOfPlayers;
	
	//
    private ArrayList<JTextField> textFields = new ArrayList<>();
	
	MyLabel enterPlayers = new MyLabel("Enter Player Names", 40);
    JButton continueButton = new MyButton("Continue", Colors.white, Colors.black, 25);

	public PlayerNamePanel(int num) {
		this.numOfPlayers = num;
		displayNorth();
		displayCenter();
		displaySouth();
	}
	
	private void displayNorth(){
		north.add(enterPlayers);
	}

	private void displayCenter(){
        for (int count = 0; count < numOfPlayers; count++) {
            // this sets a default text in the textField
            MyTextField playerNameInput = new MyTextField("Player " + String.valueOf(count+1), 0);
            center.add(playerNameInput, "cell 0 " + count + " ,growx");
            textFields.add(playerNameInput);
        }

	}
	
	private void displaySouth(){
		south.add(continueButton, "cell 0 2,growx");
        continueButton.addActionListener(e -> {
            List<String> name = new ArrayList<>();
            for (int i = 0; i < textFields.size();i++) {
            	String text = textFields.get(i).getText();
                if(!text.isEmpty()){
                	name.add(text);
                }
            }
            if(name.size() <numOfPlayers) name = autoPopulate(name);
            System.out.println(name);
			SetUpController.getInstance().switchToRoleSelection(name);
        });
	}
	
	private List<String> autoPopulate(List<String> name) {
		for(int i = name.size() + 1; i < numOfPlayers + 1; i++){
			name.add("player_" + i);
		}
		return name;
	}

	public JPanel getContentPane(){
		return contentPane;
	}
}