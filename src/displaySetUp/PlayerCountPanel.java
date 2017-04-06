package displaySetUp;

import myJStuff.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;


/**
 * This class creates the Panel for selecting how many
 * players you want in the game. This precedes the
 * PlayerNamePanel and supersedes the Main Panel
 * @author Elvin Limpin 30018832
 * 
 *
 */
public class PlayerCountPanel extends MyPanel{
	
	private ActionListener globalListener;
	
	//Labels for the north panel
	private JLabel lblText;
	private JLabel lblText2;
	
	private JButton btnContinue;
	private JButton btnHome;
	
	private List<JButton> buttonList = new ArrayList<>();

	/**
	 * Constructor initalizes the view and creates an actionListener
	 * @param actionListener
	 */
	public PlayerCountPanel(ActionListener packageListener, ActionListener globalListener) {
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		contentPane.setName("PlayerCount Panel");
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	private void displayNorth(){
		btnHome = new MyButton("Home",buttonFont);
		north.add(btnHome,"cell 0 0,alignx left,aligny top");
		btnHome.addActionListener(globalListener);
		btnHome.setName("Home");
		btnHome.setBorder(emptyBorder);
		
		lblText = new MyLabel("How Many", textColor, titleFont);
		north.add(lblText, "cell 0 0,alignx left");
		
		lblText2 = new MyLabel("  Players?", textColor, titleFont);
		north.add(lblText2, "cell 0 1,alignx center");
	}

	private void displayCenter(){
		//Loops to create a button for each amount of players aloud 
		for(int i=5;i<=12;i++){
			//Create each button at location i
			displayPlayerButton(i);
		}
	}
	private void displaySouth(){
		//Create the button and pass it values for text, foreground and background color, and font
		btnContinue = new MyButton("Continue");
		btnContinue.setName("Continue_PlayerCount");
		//Add the button to the south panel, button will fill width of screen 
		south.add(btnContinue, "cell 0 0,growx");
		//Add action listener for when the button is pressed
		btnContinue.addActionListener(packageListener);
		
	}
	/**
	 * Creates a button displaying option to pick amount of players
	 * Button will be highlighted (blue) when pressed until another button is pressed
	 * Sets the playerTotal to the value of i (amount of players)
	 * @param i
	 */
	private void displayPlayerButton(int i){
		JButton btnPlayer = new MyButton(Integer.toString(i));
		center.add(btnPlayer, "cell 0 "+i+",growx");
		btnPlayer.setName("PlayerCount "+Integer.toString(i));
		btnPlayer.addActionListener(packageListener);
		buttonList.add(btnPlayer);
	}
	
	public void changeButtonSelected(int position){
		for(JButton btn: buttonList){
			if(btn.getName().contains(Integer.toString(position))){
				btn.setBackground(Colors.select);
			}else{
				btn.setBackground(Colors.defaultButtonBackgroundColor);
			}
		}
	}
}