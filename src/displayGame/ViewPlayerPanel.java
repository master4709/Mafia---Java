package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
/**
 *@author: Ronelle Bakima
 *30005568
 *
 */
public class ViewPlayerPanel extends MyPanel{
	
	private JButton btnBack;
	private List<String> mafiaMembers;
	private JTextArea role;
	private JLabel name;
	private JTextArea roleInfo;
	private JTextArea goal;
	
	private JLabel role_title;
	private JLabel name_title;
	private JLabel roleInfo_title;
	private JLabel goal_title;

	/**
	 * Create the panel. Displays the top, south, and center of
	 * the panel.
	 */
	public ViewPlayerPanel(ActionListener actionListener, List<String> mafiaMembers) {
		this.packageListener = actionListener;
		this.mafiaMembers = mafiaMembers;
		
		displayNorth();
		displaySouth();
		displayCenter();
	}
	/**
	 * displays the name of player at the top of 
	 * the panel
	 */
	private void displayNorth(){
		name_title = new MyLabel("Name:", 30);
		north.add(name_title, "cell 0 1");
		name = new MyLabel("", 30);
		north.add(name, "cell 0 2");
	}
	/**
	 * displays the back button at the bottom of the
	 * panel
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 0 0,alignx center");
		btnBack.setName("Back_ViewPlayerPanel");
		btnBack.addActionListener(packageListener);
	}
	/**
	 * displays role, role info and the goal of the player in the 
	 * center of the panel
	 */
	private void displayCenter() {
		role_title = new MyLabel("Role: ", 30);
		center.add(role_title, "cell 0 1");
		role = new MyTextArea("", 30);
		center.add(role, "cell 0 2");
		roleInfo_title = new MyLabel("Role Info:", 30);
		center.add(roleInfo_title, "cell 0 3");
		roleInfo = new MyTextArea("", 30);
		center.add(roleInfo, "cell 0 4");
		goal_title = new MyLabel("Goal:", 30);
		center.add(goal_title, "cell 0 5");
		goal = new MyTextArea("", 30);
		center.add(goal, "cell 0 6");
	}
	/**
	 * Sets the name to JLabel and sets the role, roleInfo, and
	 * goal to text areas. Wraps the text areas in order to display
	 * them on the screen
	 * @param player, screenWidth
	 */
	public void setPlayer(Player player, int screenWidth){
		name.setText(player.getName());
		role.setText(player.getRole());
		role.setLineWrap(true);
		role.setBounds(0, 0, screenWidth-50, 100);
		roleInfo.setText(player.getAction());
		roleInfo.setLineWrap(true);
		roleInfo.setBounds(0, 0, screenWidth-50, 100);
		goal.setText(player.getGoal());
		goal.setLineWrap(true);
		goal.setBounds(0, 0, screenWidth-50, 100);
	}
}
