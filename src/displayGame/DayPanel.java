
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
 * This class is used to display the list of possible targets of the day lynching
 * 
 * @author Pierce de Jong 30006609
 *
 */
public class DayPanel implements ActionListener{
	
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
	
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//This label displays the text for what to do on this screen
	private JLabel lblDiscription;
	
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	
	//Stores all of the data of the players
	//Does not change the date stored in it EVER
	private List<Player> playerInfo;
	
	//List of buttons representing each player that is alive
	//Pressing a button will set them as the target for the day lynching
	private List<JButton> playerButtonList = new ArrayList<>();
	
	//Stores the index value for the target of the dayLynching
	//To be used in the game class to kill the target of the day lynching
	private int target;

	/**
	 * Create the panel.
	 */
	public DayPanel(ActionListener actionListener,List<Player> playerInfo) {
		this.actionListener = actionListener;
		this.playerInfo = playerInfo;
		target = -1;
		
		setFont();
		setColor();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[][grow]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[][grow,fill]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		west.setLayout(new MigLayout("", "[]", "[]"));
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		east.setLayout(new MigLayout("", "[]", "[]"));
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		displayNorth();
		displaySouth();
		displayCenter();
		displayEast();
		displayWest();
		
		setBackground(backgroundColor);
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		lblDayTime = new MyLabel("Day Time", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text = "One player must be voted out each day.";// There must be a 50% majority to lynch him/her";
		lblDiscription = new MyLabel(text, textColor, infoFont);
		//lblDiscription.setWrapStyleWord(true);
		//lblDiscription.setLineWrap(true);
		north.add(lblDiscription, "cell 0 1,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 1 0");
		btnContinue.addActionListener(actionListener);
		btnContinue.setName("Continue_DayPanel");
	}
	/**
	 * Creates all of the buttons representing each player that is alive
	 */
	private void displayCenter(){
		//Loop through the list of players
		for(Player player: playerInfo){
			//If the player is NOT dead
			if(!player.isDead()){
				//create a button of that player
				displayPlayerButton(player.getPlayPosition());
			}
		}
	}
	
	private void displayEast(){
		
	}
	
	private void displayWest(){
		
	}
	/**
	 * Creates a button for a player when called in displayCenter()
	 * 
	 * @param i - index value of player and location on center grid y value
	 */
	private void displayPlayerButton(int i){
		//Create string of the players name and role (debug)
		String text = playerInfo.get(i).getName()+"|"+playerInfo.get(i).getRole();
		JButton btnPlayer = new MyButton(text);//Create a new button with passing the String text
		btnPlayer.setName(Integer.toString(i));
		String position = "cell 0 "+i+",growx";
		center.add(btnPlayer, position);
		btnPlayer.addActionListener(this);//Add action listener 
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
	/**
	 * sets all of the fonts of the screen to new MyFonts with int size (how big the font is)
	 */
	private void setFont(){
		titleFont = new MyFont(65);
		infoFont = new MyFont(25);
	}
	/**
	 * Sets all of the colors of the screen to custom Colors made in Colors class
	 */
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
		selectColor = Colors.blue;
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
	
	public void resetTarget(){
		target = -1;
	}
	
	public Integer getTarget(){
		return target;
	}
	
	/**
	 * Returns the contentPane with everything added to it
	 * @return contentPane
	 */
	public JPanel getContentPane(){
		return contentPane;
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
