package displayGame;

import myJStuff.MyPanel;
import playerInfo.Player;
import myJStuff.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

/**
 * @author: Ronelle Bakima
 * 30005568
 * 
 * */

public class VictoryPanel extends MyPanel {
	/**
	 * declaration of variables
	 */
	private JLabel winner_title;
	private JLabel names_roles;
	private JLabel winner_name;
	private JButton btnHome;
	
	private ActionListener globalListener;
	private JLabel status;
	private String str;
	
	/**
	 * Constructor method that takes a listener as a 
	 * parameter, calls initialize();
	 * @param listener
	 */
	public VictoryPanel(ActionListener listener) {
		globalListener = listener;
		
		contentPane.setName("Victory Panel");
		
		initialize();
	}
	/**
	 * calls north, bottom, and center displays
	 */
	public void initialize() {
		displayNorth();
		displayBottom();
	}
	/**
	 * displays the north of the panel:
	 * who the winner is
	 */
	public void displayNorth() {
		winner_title = new MyLabel("The Winner is:", 30);
		north.add(winner_title, "cell 0 1");
		winner_name = new MyLabel("", 60);
		north.add(winner_name, "cell 0 2");
		names_roles = new MyLabel("Status, Names and Roles:", 30);
		north.add(names_roles, "cell 0 3");
	}
	/**
	 * displays the bottom of the panel:
	 * the home button
	 */
	private void displayBottom() {
		btnHome = new MyButton("Home");
		south.add(btnHome, "cell 1 0");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");
	}
	/**
	 * Sets all the names and roles in order to be 
	 * displayed. Takes the list of the player info
	 * as an argument
	 * @param nameAndRole
	 */
	public void setPlayerInfo(Player p) {		
		if(p.getStatus()!=0) {
			str = "Alive- ";
		}else{
			if(p.isLynched()){
				str = "Lycnhed- ";
			}else{
				str = "Murdered- ";
			}
		}
		status = new MyLabel(str+p.toString());
		status.setFont(new MyFont(setFontSize(status.getText(),buttonFontSize,75)));
		center.add(status,"cell 0 " + p.getPosition());
	}
	/**
	 * Sets the winner of the game
	 * takes name as an argument from game
	 * controller
	 * @param name
	 */
	public void setWinner(String name){
		winner_name.setText(name);
	}

}
