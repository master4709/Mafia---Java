package displaySetUp;

import displayMain.MainController;
import myJStuff.*;

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
 * This class creates the Panel for selecting how many players you want in the game
 * @author
 * 
 *
 */
public class PlayerCountPanel extends MyPanel{
	
	//Labels for the north panel
	private JLabel lblText;
	private JLabel lblText2;
	
	private JButton btnContinue;
	private JButton btnBack;
	
	private int playerMinimum = 5;
	private int playerTotal;
	
	private List<JButton> buttonList = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public PlayerCountPanel() {

		
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	private void displayNorth(){
		//Create the label and pass it text color and font
		lblText = new MyLabel("How Many", textColor, titleFont);
		//Add the label to the north panel at grid box 0,0 centering the text in the middle of the box
		north.add(lblText, "cell 0 0,alignx center");
		
		lblText2 = new MyLabel("Players?", textColor, titleFont);
		north.add(lblText2, "cell 0 1,alignx center");
	}

	private void displayCenter(){
		//Loops to creat a button for each amount of players aloud 
		for(int i=playerMinimum;i<11;i++){
			//Create each button at location i
			displayPlayerButton(i);
		}
	}
	private void displaySouth(){
		btnBack = new MyButton("Back");
		//south.add(btnBack, "cell 0 0");
		btnBack.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			MainController.getInstance().switchMain();
		}});
		//Create the button and pass it values for text, foreground and background color, and font
		btnContinue = new MyButton("Continue");
		//Add the button to the south panel, button will fill width of screen 
		south.add(btnContinue, "cell 0 0,growx");
		//Add action listener for when the button is pressed
		btnContinue.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			//Switches the current panel to the game panel
    			SetUpController.getInstance().switchToInputPlayer(playerTotal);
		}});
		
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
		
		btnPlayer.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			//Sets background of all colors to default
    			for(int m=0;m<buttonList.size();m++){
    				buttonList.get(m).setBackground(Colors.defaultButtonBackgroundColor);
    			}
    			//Sets color of the current button to blue when pressed 
    			btnPlayer.setBackground(selectColor);
    			//Sets player total to value of button text
    			playerTotal = i;
		}});
		buttonList.add(btnPlayer);
		
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}

}