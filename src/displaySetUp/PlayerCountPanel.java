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
public class PlayerCountPanel{
	
	private Color textColor;
	private Color backgroundColor;
	private Color selectColor;
	
	private Font titleFont;

	//Panel that gets set to the frame and displays the contents of this class
	private JPanel contentPane;
	
	//Panels that are added to the content pane. All JObjects get added to these panels
	private JPanel north;
	private JPanel south;
	private JPanel west;
	private JPanel east;
	private JPanel center;
	
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
		//Sets the playerTotal to the minimum amount of players 
		playerTotal = 5;
		setFont();
		setColor();

		//Everything gets displayed on this panel 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//These panels are what all JLabels, buttons etc. are added to
		north = new JPanel();
		contentPane.add(north, BorderLayout.NORTH);
		north.setLayout(new MigLayout("", "[grow,center]", "[]"));
		
		south = new JPanel();
		contentPane.add(south, BorderLayout.SOUTH);
		south.setLayout(new MigLayout("", "[grow]", "[]"));
		
		west = new JPanel();
		contentPane.add(west, BorderLayout.WEST);
		
		east = new JPanel();
		contentPane.add(east, BorderLayout.EAST);
		
		center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		center.setLayout(new MigLayout("", "[grow]", "[]"));
		
		displayNorth();
		displaySouth();
		displayCenter();
		
		setBackground(backgroundColor);
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
    			SetUpController.getInstance().switchToGame(playerTotal);
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
		titleFont = new MyFont(80);
	}
	
	private void setColor(){
		textColor = Colors.black;
		backgroundColor = Colors.defaultBackgroundColor;
		selectColor = Colors.blue;
	}
	
	public JPanel getContentPane(){
		return contentPane;
	}

}