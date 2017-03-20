package displaySetUp;

import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * This class creates the Panel for selecting how many
 * players you want in the game. This preceeds the
 * PlayerNamePanel and supercedes the Main Panel
 * @author Elvin Limpin 30018832
 * 
 *
 */
public class PlayerCountPanel extends MyPanel implements ActionListener{
	
	//Labels for the north panel
	private JLabel lblText;
	private JLabel lblText2;
	
	private JButton btnContinue;
	
	private int playerTotal = -1;
	
	private List<JButton> buttonList = new ArrayList<>();

	/**
	 * Constructor initalizes the view and creates an actionListener
	 * @param actionListener
	 */
	public PlayerCountPanel(ActionListener actionListener) {
		this.actionListener = actionListener;
		displayNorth();
		displaySouth();
		displayCenter();
	}
	
	private void displayNorth(){
		lblText = new MyLabel("How Many", textColor, titleFont);
		north.add(lblText, "cell 0 0,alignx center");
		
		lblText2 = new MyLabel("Players?", textColor, titleFont);
		north.add(lblText2, "cell 0 1,alignx center");
	}

	private void displayCenter(){
		//Loops to create a button for each amount of players aloud 
		for(int i=5;i<=10;i++){
			//Create each button at location i
			displayPlayerButton(i);
		}
	}
	private void displaySouth(){
		//Create the button and pass it values for text, foreground and background color, and font
		btnContinue = new MyButton("Continue");
		btnContinue.setName("Continue_PlayerCountPanel");
		//Add the button to the south panel, button will fill width of screen 
		south.add(btnContinue, "cell 0 0,growx");
		//Add action listener for when the button is pressed
		btnContinue.addActionListener(actionListener);
		
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
		btnPlayer.setName(Integer.toString(i));
		btnPlayer.addActionListener(this);
		buttonList.add(btnPlayer);
		
	}
	
	public Integer getPlayerTotal(){
		return playerTotal;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		System.out.println(name);
		if(playerTotal!=-1){
			buttonList.get(playerTotal-5).setBackground(btnBackgroundColor);
		}
		//Needs the other switch statements.
		switch(name){
		case "5":
			playerTotal = 5;
			buttonList.get(0).setBackground(selectColor);
			break;
		case "6":
			playerTotal = 6;
			buttonList.get(1).setBackground(selectColor);
			break;
		case "7":
			playerTotal = 7;
			buttonList.get(2).setBackground(selectColor);
			break;
		case "8":
			playerTotal = 8;
			buttonList.get(3).setBackground(selectColor);
			break;
		case "9":
			playerTotal = 9;
			buttonList.get(4).setBackground(selectColor);
			break;
		case "10":
			playerTotal = 10;
			buttonList.get(5).setBackground(selectColor);
			break;
		
		}
	}
}