
package displayGame;

import myJStuff.*;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
/**
 * This class creates a JPanel to display all of the player buttons to view assignments in the ViewPlayerPanel
 * 
 * @author Pierce de Jong - 30006609
 */
public class ViewAllPlayersPanel extends MyPanel{
	
	private ActionListener globalListener;
	//This label displays the text "Day Time"
	private JLabel lblDayTime;
	//These labels display the text for what to do on this screen
	private JLabel lblDescription1;
	private JLabel lblDescription2;
	//Pressing this button goes to the next screen using the GameController
	private JButton btnContinue;
	private JButton btnHome;
	
	private List<JButton> playerButtonList = new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public ViewAllPlayersPanel(ActionListener actionListener, ActionListener globalListener) {
		this.packageListener = actionListener;
		this.globalListener = globalListener;
		
		contentPane.setName("ViewAllPlayers Panel");
		displayNorth();
		displaySouth();
	}
	/**
	 * Displays that it is Day Time and rules of the day
	 */
	private void displayNorth(){
		
		lblDayTime = new MyLabel("View Roles", textColor, titleFontSize);
		north.add(lblDayTime, "flowy,cell 0 0");
		
		String text1 = "Click on your name to check your role";
		lblDescription1 = new MyLabel(text1, textColor, textFontSize);
		north.add(lblDescription1, "cell 0 1,");
		
		String text2 = "Then, pass the device to the next player";
		lblDescription2 = new MyLabel(text2, textColor, textFontSize);
		north.add(lblDescription2, "cell 0 2,");
	
	}
	/**
	 * Creates button needed to be pressed to go to next screen
	 */
	private void displaySouth(){
		south.setLayout(new MigLayout("", "[][grow]", "[]"));
		
		btnHome = new MyButton("Home",buttonFontSize);
		south.add(btnHome,"cell 0 0,alignx left,alignx left");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");
		//New Button using the default button presets and text Continue
		btnContinue = new MyButton("Go to Day");
		south.add(btnContinue, "cell 1 0, grow x");
		btnContinue.setName("Continue_ViewAllPlayersPanel");
		btnContinue.addActionListener(packageListener);
		
	}
	/**
	 * Creates a button representing a player for the Center Panel
	 * @param name - String for the text displayed on the JButotn
	 * @param position - index value of player and location on center grid y value
	 */
	public void displayPlayerButton(String name, int position){
		//if(test) {text = text+" | "+playerInfo.get(i).getRole();}
		JButton btnPlayer = new MyButton(name);//Create a new button with passing the String text
		btnPlayer.setName("Select_"+Integer.toString(position));
		center.add(btnPlayer, "cell 0 "+position+",growx");//Add the button to the center panel
		btnPlayer.addActionListener(packageListener);//Add action listener 
		btnPlayer.setFont(new MyFont(setFontSize(name,buttonFontSize,100)));
		playerButtonList.add(btnPlayer);
	}
	/**
	 * Every time a player dies in the game,
	 * sets the text of their button to add Dead |  
	 * @param position - index value of the player that died
	 */
	public void setPlayerButtonDead(int position){
		String text = "Dead | " + playerButtonList.get(position).getText();
		playerButtonList.get(position).setText(text);
		int font = setFontSize(text,buttonFontSize,100);
		playerButtonList.get(position).setFont(new MyFont(font));
		if(font<25) playerButtonList.get(position).setBorder(new EmptyBorder(12,5,12,5));
	}
}
