package displayGame;

import myJStuff.*;
import playerInfo.Player;

import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
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

	/**
	 * Create the panel and all of the sub panels
	 * Displays all of the needed buttons and labels etc...
	 * @param playerInfo
	 */
	public NightPanel(ActionListener packageListener, List<String> mafiaMember) {
		this.packageListener = packageListener;
		contentPane.setName("Night Panel");
		//Create all of the needed buttons and labels and adds them to the panel
		displaySouth();
		displayNorth(mafiaMember);
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
		lblMafia= new MyLabel("Mafia Members: "+mafiaMembers, textColor, infoFont);
		north.add(lblMafia, "cell 0 4");
		lblMafia.setVisible(false);
		setFontMafia("Mafia Members: "+mafiaMembers);
	}
	
	/**
	 * This displays all of the possible buttons that each player can press when it is his/ her turn
	 * Each button represents a player
	 */
	public void displayCenter(List<Player> playerInfo){
		//The y position 
		int k=0;
		//Loops through the list of players adn create a button for each player
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				displayPlayerButton(p);
			}
			//Add one to the y position
			k = k+1;
		}
		//Create the detective button
		btnDetective = new MyButton("Confirm Target", textFont);
		center.add(btnDetective, "cell 0 "+k+1+",alignx center");
		btnDetective.setName("Detective");
		btnDetective.addActionListener(packageListener);
		btnDetective.setVisible(false);
		
		lblDetective = new MyLabel("", textColor, textFont);
		center.add(lblDetective, "cell 0 "+k+",alignx center");
		
	}
	/**
	 * Displays the button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 0 0,alignx center");
		btnContinue.addActionListener(packageListener);
		btnContinue.setName("Continue_NightPanel");
	}
	/**
	 * Creates a button with the text value of a player depending on i
	 * @param i
	 */
	private void displayPlayerButton(Player p){
		//Create string of the players name
		String text = p.getName();
		JButton btnPlayer = new MyButton(text, textFont);//Create a new button with passing the String text
		btnPlayer.setName("Night_"+Integer.toString(p.getPosition()));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+p.getPosition()+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(packageListener);//Add action listener 
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
				if(button.getName().contains(Integer.toString(target))){//Finds the one with the same name as the target. THe buttons are named 0,1,2... etc
					center.remove(button);//Remove the button from the list of buttons 
				}
			}
		}
	}
	
	public void setButtonSelected(int previous,int target){
		for(JButton button: playerButtonList){
			if(button.getName().contains(Integer.toString(target))){
				button.setBackground(selectColor);
			}else if(button.getName().contains(Integer.toString(previous))){
				button.setBackground(btnBackgroundColor);
			}
		}
	}
	/**
	 * Sets the display for the new player at night
	 * @param i, position in list of current player
	 * @param mafiaMember
	 */
	public void setDisplay(Player player){
		//Resets the player target to -1
		for(JButton button:playerButtonList){
			button.setBackground(btnBackgroundColor);
		}
		//Sets the labels to the current players information
		lblName.setText(player.getName());
		lblRole.setText(player.getRole());
		lblInfo.setText(player.getRoleInfo());
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
			lblMafia.setVisible(true);
		}else{
			lblMafia.setVisible(false);
		}
		
		setFontPlayer(player.getName());
		setFontInfo(player.getRoleInfo());
	}
	
	public void setFontPlayer(String name){
		int font = 10;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(name, frc).getWidth());
		int textHeight = (int)(new MyFont(font).getStringBounds(name, frc).getHeight());
		
		int screenWidth = 480;
		int screenHeight = 850;
		while(textWidth<screenWidth-25 && textHeight<screenHeight/11){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(name, frc).getWidth());
			textHeight = (int)(new MyFont(font).getStringBounds(name, frc).getHeight());
		}
		lblName.setFont(new MyFont(font));
	}
	
	public void setFontInfo(String info){
		int font = 10;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(info, frc).getWidth());
		
		int screenWidth = 480;
		while(textWidth<screenWidth-50 && font < 25){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(info, frc).getWidth());
		}
		lblInfo.setFont(new MyFont(font));
	}
	
	public void setFontMafia(String members){
		int font = 10;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(members, frc).getWidth());
		
		int screenWidth = 480;
		while(textWidth<screenWidth-50 && font < 20){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(members, frc).getWidth());
		}
		lblMafia.setFont(new MyFont(font));
	}
}