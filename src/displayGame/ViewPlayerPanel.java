package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
/**
 *@author: Ronelle Bakima
 *30005568
 *
 */
public class ViewPlayerPanel extends MyPanel{
	
	private JButton btnBack;
	private List<String> mafiaMembers;
	private JLabel name;
	private JTextArea display;
	private JLabel lblicon;
	private JLabel lblMafiaMembers;

	/**
	 * Create the panel. Displays the top, south, and center of
	 * the panel.
	 */
	public ViewPlayerPanel(ActionListener actionListener, List<String> mafiaMembers) {
		this.packageListener = actionListener;
		this.mafiaMembers = mafiaMembers;
		
		contentPane.setName("ViewPlayer Panel");
		
		displayNorth();
		displaySouth();
		displayCenter();
	}
	/**
	 * displays the name of player at the top of 
	 * the panel
	 */
	private void displayNorth(){
		name = new MyLabel("", 70);
		north.add(name, "cell 0 0");
		lblMafiaMembers = new MyLabel("Mafia Members: "+ mafiaMembers.toString());
		north.add(lblMafiaMembers,"cell 0 1");
		lblMafiaMembers.setFont(new MyFont(MyPanel.setFontSize(lblMafiaMembers.getText(), 25, 75)));
		lblMafiaMembers.setVisible(false);
	}
	/**
	 * displays the back button at the bottom of the
	 * panel
	 */
	private void displaySouth(){
		btnBack = new MyButton("Back");
		south.add(btnBack, "cell 1 0,growx");
		btnBack.setName("Back_ViewPlayerPanel");
		btnBack.addActionListener(packageListener);
		btnBack.setBorder(new EmptyBorder(20,5,20,5));
	}
	/**
	 * displays role, role info and the goal of the player in the 
	 * center of the panel
	 */
	private void displayCenter() {
		display = new MyTextArea("", 30);
		center.add(display, "cell 0 1");
		
		ImageIcon icon = new ImageIcon("src/data/pictures/rose.png");
		lblicon = new JLabel(icon);
		center.add(lblicon, "cell 0 2");
	}
	/**
	 * sets the name as a JLabel. Sets role, roleInfo, and goal as
	 * Strings. Sets JTextArea display with these strings to display
	 * @param player, screenWidth
	 */
	public void setPlayer(Player player){
		name.setText(player.getName());
		name.setFont(new MyFont(setFontSize(player.getName(),titleFontSize,50)));
		String r = "Role- " + player.getRole();
		String ri = "Night Action- " + player.getAction();
		String g = "Goal-" + player.getGoal();
		display.setEditable(false);
		String d = r + "\n" + "\n" + ri + "\n" + "\n" + g;
		display.setText(d);
		display.setLineWrap(true);
		display.setBounds(0, 0, width-50, 100);
		
		if(player.getRole().contains("Mafia")){
			lblMafiaMembers.setVisible(true);
		}else{
			lblMafiaMembers.setVisible(false);
		}
	}
}
