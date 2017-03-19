package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * NightPanel
 * @author Pierce de Jong 30006609
 *
 */
public class NightPanel extends MyPanel implements ActionListener{
	
	private Color selectColor;
	
	private JLabel lblName;
	private JLabel lblRole;
	private JLabel lblInfo;
	private JLabel lblGoal;
	private JLabel lblMafia;
	private JLabel lblDetective;
	
	private JButton btnContinue;
	private JButton btnDetective;
	
	//Current position in the list of players
	private int target = -1;
	
	private List<Player> playerInfo;
	
	private List<JButton> playerButtonList = new ArrayList<>();
	private List<String> mafiaMember = new ArrayList<>();

	/**
	 * Create the panel and all of the sub panels
	 * Displays all of the needed buttons and labels etc...
	 * @param playerInfo
	 */
	public NightPanel(ActionListener actionListener, List<Player> playerInfo, List<String> mafiaMember) {
		this.actionListener = actionListener;
		this.playerInfo = playerInfo;
		this.mafiaMember = mafiaMember;
		


		displaySouth();
		//Initializes the labels, does not fill them with anything
		displayNorth();
		displayCenter();
	}
	
	
	/**
	 * Display the name, role, info, and Mafia members (if applicable)
	 */
	private void displayNorth(){
		String text = "";
		lblName = new MyLabel(text, textColor, titleFont);
		north.add(lblName, "flowy,cell 0 0");
		lblRole = new MyLabel(text, textColor, roleFont);
		north.add(lblRole, "cell 0 1");
		lblInfo = new MyLabel(text, textColor, infoFont);
		north.add(lblInfo, "cell 0 2");
		lblGoal = new MyLabel(text, textColor, infoFont);
		north.add(lblGoal, "cell 0 3");
		lblMafia= new MyLabel(text, textColor, infoFont);
		north.add(lblMafia, "cell 0 4");
	}
	
	/**
	 * This displays all of the possible buttons that each player can press when it is his/ her turn
	 * Each button represents a player
	 */
	private void displayCenter(){
		int k=0;
		for(Player player: playerInfo){
			if(!player.isDead()){
				displayPlayerButton(player.getPlayPosition());
			}
			k = k+player.getPlayPosition();
		}
		
		btnDetective = new MyButton("Confirm Target",30);
		center.add(btnDetective, "cell 0 "+k+1+",alignx center");
		btnDetective.setName("Detective");
		btnDetective.addActionListener(actionListener);
		btnDetective.setVisible(false);
		
		lblDetective = new MyLabel("", textColor, infoFont);
		center.add(lblDetective, "cell 0 "+k+",alignx center");
		
	}
	/**
	 * Displays the button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 0 0");
		btnContinue.addActionListener(actionListener);
		btnContinue.setName("Continue_NightPanel");
	}
	/**
	 * Creates a button with the text value of a player depending on i
	 * @param i
	 */
	private void displayPlayerButton(int i){
		String text = playerInfo.get(i).getName();
		JButton btnPlayer = new MyButton(text);
		center.add(btnPlayer, "cell 0 "+i+",growx");
		btnPlayer.addActionListener(this);
		btnPlayer.setName(Integer.toString(i));
		playerButtonList.add(btnPlayer);
	}
	
	public JButton getDetectiveButton(){
		return btnDetective;
	}
	
	public void setDetectiveMessage(String text){
		btnDetective.setVisible(false);
		lblDetective.setText(text);
	}
	
	public Integer getPlayerTarget(){
		return target;
	}
	
	public void resetTarget(){
		target = -1;
	}
	
	public void removePlayerButton(int target){
		if(target!=-1){
			for(JButton button: playerButtonList){
				if(target==Integer.parseInt(button.getName())){
					center.remove(playerButtonList.get(target));
				}
			}
		}
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}
	/**
	 * Sets the display for the new player at night
	 * @param i, position in list of current player
	 * @param mafiaMember
	 */
	public void setDisplay(int i){
		//Resets the player target to -1
		if(target!=-1){
			playerButtonList.get(target).setBackground(Colors.defaultButtonBackgroundColor);
		}
		target = -1;
		//Sets the labels to the current players information
		lblName.setText(playerInfo.get(i).getName());
		lblRole.setText(playerInfo.get(i).getRole());
		lblInfo.setText(playerInfo.get(i).getRoleInfo());
		//Clears Detective Label
		lblDetective.setText("");
		//Clears the Mafia Label
		lblMafia.setText("");
		//Hides the Detective Button
		btnDetective.setVisible(false);
		
		
		//If The current player is the detective display the button to check if the target is part of the Mafia
		if(playerInfo.get(i).getRole().contains("Detective")){
			btnDetective.setVisible(true);
		}
		//if the player is part of the Mafia, display a list of all Mafia Members to the screen
		if(playerInfo.get(i).getRole().contains("Mafia")){
			lblMafia.setText("Mafia Members: "+ mafiaMember);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		//Get the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		
		//Resets the background of the previously targeted player button if the current player has changed targets
		if(target!=-1){
			playerButtonList.get(target).setBackground(Colors.defaultButtonBackgroundColor);
		}
		
		switch(name){
		case "0":
			playerButtonList.get(0).setBackground(selectColor);
			target=0;
			break;
		case "1":
			playerButtonList.get(1).setBackground(selectColor);
			target=1;
			break;
		case "2":
			playerButtonList.get(2).setBackground(selectColor);
			target=2;
			break;
		case "3":
			playerButtonList.get(3).setBackground(selectColor);
			target=3;
			break;
		case "4":
			playerButtonList.get(4).setBackground(selectColor);
			target=4;
			break;
		case "5":
			playerButtonList.get(5).setBackground(selectColor);
			target=5;
			break;
		case "6":
			playerButtonList.get(6).setBackground(selectColor);
			target=6;
			break;
		case "7":
			playerButtonList.get(7).setBackground(selectColor);
			target=7;
			break;
		case "8":
			playerButtonList.get(8).setBackground(selectColor);
			target=8;
			break;
		case "9":
			playerButtonList.get(9).setBackground(selectColor);
			target=9;
			break;
		case "10":
			playerButtonList.get(10).setBackground(selectColor);
			target=10;
			break;
		default:
			break;
		}
	}
}