package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * NightPanel
 * This class displays the current players name, role, goal
 * and a list of buttons of all the players that when clicked set the target to the index value
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class NightPanel extends MyPanel{
	
	//Create all of the labels for the NorthPanel
	private JLabel lblName;
	private JLabel lblRole;
	private JLabel lblInfo;
	private JLabel lblGoal;
	private JLabel lblMafia;
	private JLabel lblDetective;
	
	//JButton when pressed goes to the next screen
	private JButton btnContinue;
	//jButton that is only visible to detective. when pressed reveals 
	private JButton btnDetective;
	//List of all of the buttons representing each of the players
	private List<JButton> playerButtonList = new ArrayList<>();
	//List of all of the Mafia Members
	
	private String lynchTarget;

	/**
	 * Create the panel and all of the sub panels
	 * Displays all of the needed buttons and labels etc...
	 * @param playerInfo
	 */
	public NightPanel(ActionListener packageListener, List<String> mafiaMember, String lynchTarget) {
		this.packageListener = packageListener;
		this.lynchTarget = lynchTarget;
		contentPane.setName("Night Panel");
		//Create all of the needed buttons and labels and adds them to the panel
		displaySouth();
		displayNorth(mafiaMember);
		displayCenter();
	}
	/**
	 * Creates the name, role, info, and mafia labels and adds them to the north Panel
	 */
	private void displayNorth(List<String> mafiaMembers){
		String text = "";
		lblName = new MyLabel(text, textColor, titleFont);
		north.add(lblName, "flowy,cell 0 0");
		lblRole = new MyLabel(text, textColor, roleFont);
		north.add(lblRole, "cell 0 1");
		lblInfo = new MyLabel(text, textColor, infoFont);
		north.add(lblInfo, "cell 0 2");
		lblGoal = new MyLabel(text, textColor, infoFont);
		north.add(lblGoal, "cell 0 3");
		lblMafia= new MyLabel("Mafia Members: "+mafiaMembers.toString(), textColor, infoFont);
		lblMafia.setFont(new MyFont(setFont("Mafia Members: "+mafiaMembers,50,20)));
	}
	
	/**
	 * This displays all of the possible buttons that each player can press when it is his/ her turn
	 * Each button represents a player
	 */
	private void displayCenter(){
		//Create the detective button
		btnDetective = new MyButton("Confirm Target", textFont);
		center.add(btnDetective, "cell 0 14,alignx center");
		btnDetective.setName("Detective");
		btnDetective.addActionListener(packageListener);
		btnDetective.setVisible(false);
		
		lblDetective = new MyLabel("", textColor, textFont);
		center.add(lblDetective, "cell 0 13,alignx center");
		
	}
	/**
	 * Displays the button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnContinue = new MyButton("Continue",28);
		south.add(btnContinue, "cell 0 0,alignx center");
		btnContinue.addActionListener(packageListener);
		btnContinue.setName("Continue_NightPanel");
	}
	/**
	 * Creates a button representing a player for the Center Panel
	 * @param name - String for the text displayed on the JButotn
	 * @param position - index value of player and location on center grid y value
	 */
	public void displayPlayerButton(String name, int position){
		//if(test) {text = text+" | "+playerInfo.get(i).getRole();}
		JButton btnPlayer = new MyButton(name);//Create a new button with passing the String text
		btnPlayer.setName("Night_"+Integer.toString(position));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+position+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(packageListener);//Add action listener  
		btnPlayer.setFont(new MyFont(setFont(name,100,28)));
		playerButtonList.add(btnPlayer);//Add to the list of player buttons
	}
	
	public JButton getDetectiveButton(){
		return btnDetective;
	}
	
	public void setDetectiveMessage(String text){
		btnDetective.setVisible(false);
		lblDetective.setText(text);
	}
	
	public void removePlayerButton(int target){
		if(target!=-1){//Error handling, Must have a valid target to remove the button
			for(JButton button: playerButtonList){//Loops through the list of player buttons
				String number = button.getName().substring(6, button.getName().length());
				if(number.equals(Integer.toString(target))){//Finds the one with the same name as the target. THe buttons are named 0,1,2... etc
					center.remove(button);//Remove the button from the list of buttons 
				}
			}
		}
	}
	
	public void setButtonSelected(int previous,String current){
		for(JButton button: playerButtonList){
			if(button.getName().equals(current)){
				button.setBackground(selectColor);
			}else if(button.getName().contains(Integer.toString(previous))){
				button.setBackground(btnBackgroundColor);
			}
		}
	}
	/**
	 * Sets the display for the new player at night
	 * @param player - Player, the 
	 */
	public void setDisplay(Player player){
		//Resets the player target to -1
		for(JButton button:playerButtonList){
			button.setBackground(btnBackgroundColor);
		}
		//Sets the labels to the current players information
		lblName.setText(player.getName());
		lblRole.setText(player.getRole());
		lblInfo.setText(player.getAction());
		lblGoal.setText(player.getGoal());
		//Clears Detective Label
		lblDetective.setText("");
		//If The current player is the detective display the button to check if the target is part of the Mafia
		if(player.getRole().contains("Detective")){
			btnDetective.setVisible(true);
		}else{
			btnDetective.setVisible(false);
		}
		//if the player is part of the Mafia, display a list of all Mafia Members to the screen
		if(player.getRole().contains("Mafia")){
			north.add(lblMafia, "cell 0 4");
		}else if(player.getRole().contains("Lyncher")){
			lblGoal.setText(lblGoal.getText()+" "+lynchTarget);
		}else{
			north.remove(lblMafia);
		}
								  //method in MyPanel
		lblName.setFont(new MyFont(setFont(lblName.getText(),25,70)));
		lblInfo.setFont(new MyFont(setFont(lblInfo.getText(),50,25)));
		lblGoal.setFont(new MyFont(setFont(lblGoal.getText(),50,20)));
	}
}