package displayMain;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import myJStuff.*;
/**
 * This class creates panel for Main menu of Mafia game by creating 
 * different panels and putting them together. 
 * The panels are : North panel, Center panel and South panel. 
 * Each panel contains buttons or labels. Each button is assigned to an action.
 * There is a "New Game" button which will change the panel to the displaySetUp package. 
 * The "Continue Game" button will change the panel to the same 
 * stage that the game was closed before.
 * The "Rule" and "About" buttons will change the panel to the RulePanel and AboutPanel.
 * 
 * @author Mahsa Lotfi 10072013
 * 
 */
public class MainPanel extends MyPanel{
	/**
	 * Instance variables.
	 */	
	
	private ActionListener globalListener;
	//buttons for center panel
	private JButton btnNewGame;	
	private JButton btnContinueGame;
	private JButton btnAbout;
	private JButton btnRules;
	
	//JLabel that contains background image
	private JLabel lblMan;
	
	//button for south panel
	private JButton btnTest;
	
	/**
	 * Constructor with one argument of ActionListener actionListener.
	 * This constructor will initialize the actionListener and call 
	 * other methods of this class for display.
	 * @param actionListener
	 */
	
	public MainPanel(ActionListener packageListener, ActionListener globalListener) {
		this.packageListener = packageListener;
		this.globalListener = globalListener;
		
		contentPane.setName("Main Panel");
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
	}

	/**
	 * Method to display content of north panel, which is an image.
	 */
	
	private void displayNorth(){

		ImageIcon icon = new ImageIcon("src/data/pictures/mafia.png");
		lblMan = new JLabel(icon);
		north.add(lblMan, "center");
	}

	
	/**
	 * Method to display contents of south panel. This panel contains 
	 * the testing button.
	 */
	private void displaySouth(){
		
		btnTest = new MyButton("Test Game");
		south.add(btnTest,"cell 1 0,growx");
		btnTest.addActionListener(globalListener);
		btnTest.setName("Testing_MainPanel");
	}
	
	/**
	 * Method to display content of center panel. 
	 * The buttons will be set up in this method.
	 * Each button will be assigned to a name so 
	 * the ActionListener can perform specific action
	 * when the button is pressed.
	 */
	private void displayCenter(){
		btnNewGame = new MyButton("New Game");
		center.add(btnNewGame, "cell 0 1,growx");
		btnNewGame.addActionListener(globalListener);
		btnNewGame.setName("NewGame_MainPanel");
		
		
		btnContinueGame = new MyButton("Continue Game");
		center.add(btnContinueGame, "cell 0 2,growx");
		// setting the action for continue game button
		btnContinueGame.addActionListener(globalListener);
		btnContinueGame.setName("ContinueGame_MainPanel");
		
		btnRules = new MyButton("Rules");
		center.add(btnRules, "cell 0 3,growx");
		btnRules.addActionListener(packageListener);
		btnRules.setName("Rule_MainPanel");
		
		btnAbout = new MyButton("About");
		center.add(btnAbout, "cell 0 4,growx");
		// setting the action for about button
		btnAbout.addActionListener(packageListener);
		btnAbout.setName("About_MainPanel");
		
	}
	/**
	 * Hides the test button
	 */
	public void hideTestButton(){
		btnTest.setVisible(false);
	}
}
