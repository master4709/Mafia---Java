package displayGame;

import myJStuff.MyPanel;
import myJStuff.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;

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
	
	private String text;
	private JLabel display;
	
	/**
	 * Constructor method that takes a listener as a 
	 * parameter, calls initialize();
	 * @param listener
	 */
	public VictoryPanel(ActionListener listener) {
		globalListener = listener;
		initialize();
	}
	
	/**
	 * returns contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
	/**
	 * calls north, bottom, and center displays
	 */
	public void initialize() {
		displayNorth();
		displayBottom();
		displayCenter();
	}
	/**
	 * displays the north of the panel:
	 * who the winner is
	 */
	public void displayNorth() {
		winner_title = new MyLabel("The Winner is:", 20);
		north.add(winner_title, "cell 0 1");
		winner_name = new MyLabel("", 20);
		north.add(winner_name, "cell 0 2");
	}
	/**
	 * displays the center of the panel:
	 * everybody's names and their roles
	 */
	public void displayCenter() {
		names_roles = new MyLabel("Names and Roles:", 20);
		center.add(names_roles, "cell 0 1");
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
	public void setPlayerInfo(List<String> nameAndRole) {
		for (int i = 0; i < nameAndRole.size(); i++) {
			text = nameAndRole.get(i);
			display.setText(text);
			display = new MyLabel("", 20);
			center.add(display, "cell 0 2");
		}
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
