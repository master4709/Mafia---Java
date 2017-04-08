
package displayGame;

import myJStuff.*;

import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * This class is used to display the list of possible targets of the day lynching
 * The north Panel displays all of the JLabels needed for the Day Cycle
 * The center panel holds all of the player buttons - Theses are used for selecting the target of the lynching
 * @author Pierce de Jong 30006609
 *
 */
public class DayPanel extends MyPanel{
	private ActionListener globalListener;
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//These labels display the text for what to do on this screen
	private JLabel lblDescription1;
	private JLabel lblDescription2;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	
	private JButton btnHome;
	private JButton btnViewPlayers;
	//List of buttons representing each player that is alive
	//Pressing a button will set them as the target for the day lynching
	private List<JButton> playerButtonList = new ArrayList<>();
	/**
	 * Constructor
	 * @param packageListener - Used for buttons that only want to change stuff within the package
	 * @param globalListener - Used for buttons that change the entire program from one Controller to Another, (E.g. Home buttons)
	 */
	public DayPanel(ActionListener packageListener, ActionListener globalListener) {
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		
		contentPane.setName("Day Panel");
		
		displayNorth();
		displaySouth();
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		
		lblDayTime = new MyLabel("Day Time", textColor, titleFont);
		north.add(lblDayTime, "flowy,cell 0 0 2 0,alignx center");
		
		String text1 = "Talk among yourselves to choose who to lynch";// There must be a 50% majority to lynch him/her";
		lblDescription1 = new MyLabel(text1, textColor, infoFont);
		north.add(lblDescription1, "cell 0 1 2 1");
		
		String text2 = "Select him/her once there is a majority vote";// There must be a 50% majority to lynch him/her";
		lblDescription2 = new MyLabel(text2, textColor, infoFont);
		north.add(lblDescription2, "cell 0 2 2 1");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		btnHome = new MyButton("Home",buttonFont);
		south.add(btnHome,"cell 0 0,alignx left");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");	
		
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Continue");
		south.add(btnContinue, "cell 1 0, growx");
		btnContinue.addActionListener(packageListener);
		btnContinue.setName("Continue_DayPanel");
		
		btnViewPlayers = new MyButton("<html> View<br>Players <html>",buttonFont*2/3);
		south.add(btnViewPlayers,"cell 2 0,alignx right");
		btnViewPlayers.addActionListener(packageListener);
		btnViewPlayers.setName("ViewPlayers_DayPanel");
	}
	/**
	 * Creates a button representing a player for the Center Panel
	 * @param name - String for the text displayed on the JButotn
	 * @param position - index value of player and location on center grid y value
	 */
	public void displayPlayerButton(String name, int position){
		//if(test) {text = text+" | "+playerInfo.get(i).getRole();}
		JButton btnPlayer = new MyButton(name);//Create a new button with passing the String text
		btnPlayer.setName("Day_"+Integer.toString(position));//Sets the name of the button to the index value of the player
		center.add(btnPlayer, "cell 0 "+position+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(packageListener);//Add action listener 
		btnPlayer.setFont(new MyFont(setButtonFont(name)));
		playerButtonList.add(btnPlayer);//Add to the list of player buttons
	}
	
	/**
	 * Removes the button of the the target
	 * @param target
	 */
	public void removePlayerButton(int target){
		if(target!=-1){//Error handling, Must have a valid target to remove the button
			for(JButton button: playerButtonList){//Loops through the list of player buttons
				if(button.getName().contains(Integer.toString(target))){//Finds the one with the same name as the target. THe buttons are named 0,1,2... etc
					center.remove(button);//Remove the button from the list of buttons 
				}
			}
		}
	}
	/**
	 * @param previous
	 * @param target
	 */
	public void setButtonSelected(int previous, String current){
		for(JButton button: playerButtonList){
			if(button.getName().equals(current)){
				button.setBackground(selectColor);
			}else if(button.getName().contains(Integer.toString(previous))){
				button.setBackground(btnBackgroundColor);
			}
		}
	}
	
	public void resetButtonColor(){
		for(JButton button: playerButtonList){
			button.setBackground(btnBackgroundColor);
		}
	}
	
	public void setContinueButtonVisible(boolean bool){
		btnContinue.setVisible(bool);
	}
	
	private int setButtonFont(String text){
		int font = 10;
		
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
		int textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		
		int screenWidth = 480;
		
		while(font<30 && textWidth<screenWidth-100){
			font++;
			textWidth = (int)(new MyFont(font).getStringBounds(text, frc).getWidth());
		}
		return font;
	}
}
