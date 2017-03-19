package displayGame;

import myJStuff.*;
import logic.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 * NightPanel
 * @author Pierce de Jong 30006609
 *
 */
public class NightPanel implements ActionListener{
	
	private ActionListener actionListener;
	//All of the Color variables needed for the screen
	//Receive values in setColor()
	private Color textColor;
	private Color backgroundColor;
	private Color selectColor;
	
	//All of the Font variables needed for the screen
	//Receive values in setFont()
	private Font titleFont;
	private Font infoFont;
	private Font roleFont;

	//This is the main JPanel for this class
	//Every other JPanel gets added to this one
	//Has a getter method to be used to display the content pane to the frame
	private JPanel contentPane;
			
	//All of the panels that get displayed on the content pane
	//Every other JObject for the content pane is displayed on one of these JPanels
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
	
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
		
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[][]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow,fill]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));

		displaySouth();
		//Initializes the labels, does not fill them with anything
		displayNorth();
		displayCenter();
		displayEast();
		displayWest();
		setBackground(backgroundColor);
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
		for(int i=0;i<playerInfo.size();i++){
			if(!playerInfo.get(i).isDead()){
				displayPlayerButton(i);
			}
			k = i+1;
		}
		
		btnDetective = new MyButton("Confirm Target");
		center.add(btnDetective, "cell 0 "+k+1+",alignx center");
		btnDetective.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			if(target!=-1){
    				btnDetective.setVisible(false);
    				checkMafia();
    			}
		}});
		btnDetective.setVisible(false);
		lblDetective = new MyLabel("", textColor, new MyFont(40));
		center.add(lblDetective, "cell 0 "+k+",alignx center");
		
	}
	
	private void displayEast(){
		
	}
	
	private void displayWest(){
		
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
	
	/**
	 * Sets all of the panels background to the passed Color
	 * Also creates a black border around the edge of the screen
	 * @param c
	 */
	private void setBackground(Color c){
		north.setBackground(c);
		south.setBackground(c);
		east.setBackground(c);
		west.setBackground(c);
		center.setBackground(c);
		//Creates a black border on the screen
		contentPane.setBackground(Colors.defaultBorderColor);
	}
	
	private void setFont(){
		titleFont = new MyFont(50);
		infoFont = new MyFont(20);
		roleFont = new MyFont(25);
	}
	
	private void setColor(){
		selectColor = Colors.blue;
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
	}
	
	private void checkMafia(){
		if(playerInfo.get(target).isMafia()){
			lblDetective.setText("Part of the Mafia");
		}else{
			lblDetective.setText("Not Part of the Mafia");
		}
	}
	
	public Integer getPlayerTarget(){
		return target;
	}
	
	public void resetTarget(){
		target = -1;
	}
	
	public void removePlayerButton(int target){
		if(target!=-1){
			for(int i=0;i<playerButtonList.size();i++){
				if(target==Integer.parseInt(playerButtonList.get(i).getName())){
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