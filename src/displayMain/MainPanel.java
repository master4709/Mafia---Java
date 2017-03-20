package displayMain;

import myJStuff.*;
import logic.Debug;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * This class creates panel for Main menu of Mafia game by creating different panels and putting them together. 
 * Each panel contains buttons or labels. Each button is assigned to an action.
 * @author Mahsa Lotfi
 *
 */
public class MainPanel extends MyPanel{
	
	//buttons for center panel
	private JButton btnNewGame;	
	private JButton btnContinueGame;
	private JButton btnAbout;
	private JButton btnRules;
	
	//Jlabel that contains background image
	private JLabel lblMan;
	
	//button for south panel
	private JButton btnDebug;
	private MyButton btnTest;

	/**
	 * Create the main panel frame.
	 */
	public MainPanel(ActionListener actionListener) {

		this.actionListener = actionListener;
		//displaying contents of each panels
		displayNorth();
		displaySouth();
		displayCenter();
	}

	/**
	 * Method to display content of north panel, which is an image.
	 */
	
	private void displayNorth(){

		ImageIcon icon = new ImageIcon("data/pictures/mafia.png");
		lblMan = new JLabel(icon);

		north.add(lblMan, "cell 0 0, alignx center");
		north.add(lblMan, "center");
	}

	
	/**
	 * Method to display contents of south panel. This panel contains the debug button.
	 */
	private void displaySouth(){
		btnDebug = new MyButton("Debug is " + Debug.amOn());
		south.add(btnDebug, "cell 0 0 ,growx");
		// setting the action for the button
		btnDebug.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Debug.toggle();
				btnDebug.setText("Debug is " + Debug.amOn());
			}
		});
		
		btnTest = new MyButton("Test Game");
		south.add(btnTest, "cell 0 0 ,growx");
		btnTest.addActionListener(actionListener);
		btnTest.setName("Test_MainPanel");
	}
	
	/**
	 * Method to display content of center panel. It will set up buttons. 
	 */
	private void displayCenter(){
		btnNewGame = new MyButton("New Game");
		center.add(btnNewGame, "cell 0 1,growx");
		btnNewGame.addActionListener(actionListener);
		btnNewGame.setName("NewGame_MainPanel");
		
		
		btnContinueGame = new MyButton("Continue Game");
		center.add(btnContinueGame, "cell 0 2,growx");
		// setting the action for continue game button
		btnContinueGame.addActionListener(actionListener);
		btnContinueGame.setName("ContinueGame_MainPanel");
		
		btnRules = new MyButton("Rules");
		center.add(btnRules, "cell 0 3,growx");
		btnRules.addActionListener(actionListener);
		btnRules.setName("Rule_MainPanel");
		
		btnAbout = new MyButton("About");
		center.add(btnAbout, "cell 0 4,growx");
		// setting the action for about button
		btnAbout.addActionListener(actionListener);
		btnAbout.setName("About_MainPanel");
		
	}
	
	
	
	/**
	 * Getter method for the content pane.
	 */
	public JPanel getContentPane(){
		return contentPane;
	}
}
