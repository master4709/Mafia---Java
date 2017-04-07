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
	private String text;
	private JLabel content;
	private JButton btnHome;
	
	public VictoryPanel() {

	}
	//public VictoryPanel(ActionListener actionListener){
		//this.packageListener = actionListener;
		//initialize();
	//}
	public JPanel getContentPane(){
		return contentPane;
	}
	public void initialize() {
		displayBottom();
		displayCenter();
	}
	public void displayTop(JLabel winner) {
		winner_title = new MyLabel("The Winner is:", 20);
		north.add(winner_title, "cell 0 1");
		winner = new MyLabel("", 20);
		north.add(winner, "cell 0 2");
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
	public void setPlayerInfo(List<Player> playerInfo) {
		for (int i = 0; i < playerInfo.size(); i++) {
			text += '\n';
			text += playerInfo.get(i);
		}
		content.setText(text);
	}

}
