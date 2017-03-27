
package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 *
 */
public class ViewPlayerPanel extends MyPanel{
	
	private JButton btnBack;
	private List<String> mafiaMembers;
	
	private JLabel role;
	private JLabel name;
	private JLabel roleInfo;
	private JLabel goal;

	/**
	 * Create the panel.
	 */
	public ViewPlayerPanel(ActionListener actionListener, List<String> mafiaMembers) {
		this.packageListener = actionListener;
		this.mafiaMembers = mafiaMembers;
		
		displayNorth();
		displaySouth();
		displayCenter();
	}
	//displays role and name of player
	private void displayNorth(){
		role = new MyLabel("",20);
		north.add(role, "cell 0 2");
		name = new MyLabel("",20);
		north.add(name, "cell 0 1");
	}
	/**
	 * 
	 */
	private void displaySouth(){
		//Create Button using the default button presets and text Continue
		
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 0 0,alignx center");
		btnBack.setName("Back_ViewPlayerPanel");
		btnBack.addActionListener(packageListener);
	}
	//displays role info and goal of the player
	private void displayCenter() {
		roleInfo = new MyLabel("", 20);
		center.add(roleInfo, "cell 0 1");
		goal = new MyLabel("",20);
		center.add(goal, "cell 0 2");
	}
	//sets the players characteristics
	public void setPlayer(Player player){
		
		role.setText(player.getRole());
		name.setText(player.getName());
		roleInfo.setText(player.getRoleInfo());
	}
	
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}
