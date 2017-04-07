package displayGame;

import myJStuff.MyPanel;
import playerInfo.Player;
import myJStuff.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;

public class VictoryPanel extends MyPanel {
	private JLabel winner_title;
	private JLabel names_roles;
	private JLabel winner_name;
	private String text;
	private JLabel content;
	private JButton btnHome;
	
	public VictoryPanel() {
		initialize();
	}
	//public VictoryPanel(ActionListener actionListener){
		//this.packageListener = actionListener;
		//initialize();
	//}
	public JPanel getContentPane(){
		return contentPane;
	}
	public void initialize() {
		displayNorth();
		displayBottom();
		displayCenter();
	}
	public void displayNorth() {
		winner_title = new MyLabel("The Winner is:", 20);
		north.add(winner_title, "cell 0 1");
		winner_name = new MyLabel("", 20);
		north.add(winner_name, "cell 0 2");
	}
	public void displayCenter() {
		names_roles = new MyLabel("Names and Roles:", 20);
		center.add(names_roles, "cell 0 1");
		content = new MyLabel("", 20);
		center.add(content, "cell 0 2");
	}
	private void displayBottom() {
		btnHome = new MyButton("Home");
		south.add(btnHome, "cell 1 0");
		btnHome.addActionListener(packageListener);
		btnHome.setName("Home");
	}
	public void setPlayerInfo(List<String> nameAndRole) {
		//Maybe make a new JLabel for each player and change the y by one and then you have a list of JLabels
		//Instead of using one JLbael
		for (String name: nameAndRole) {
			text += '\n';
			text += name;
		}
		content.setText(text);
	}
	
	public void setWinner(String name){
		winner_name.setText(name);
	}

}
