package displayMain;

import displaySetUp.SetUpController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Testing.GameTest;


/**
 * This class controls 3 panels which are related to the main menu. 
 * The panels are: AboutPanel, RulePanel and MainPanel. 
 * This class will be called first in Controller to begin the game. 
 * @author Mahsa Lotfi 10072013
 * 
 */

public class MainController implements ActionListener {
	/**
	 * Instance variables
	 */
	
	private ActionListener globalListener;
	//JFrame reference 
	private JFrame frame;
	
	private MainPanel mp;
	private RulePanel rp;
	private AboutPanel ap;
	private SetUpController suc;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelMain;
	private JPanel panelRule;
	private JPanel panelAbout;	
	
	/**
	 * Constructor with two arguments.
	 * This constructor will initialize the frame and actionListener
	 *  and set the bounds.
	 * @param frame 
	 * @param globalListener
	 */
	public MainController(JFrame frame, ActionListener globalListener){
		//listener = new Listener();
		this.globalListener = globalListener;		
		//setting up the frame 
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	
	
	
	/**
	 * Initialize all the panels.
	 */
	public void start(){
		mp = new MainPanel(this);
		rp = new RulePanel(this);
		ap = new AboutPanel(this);
					
		switchPanel(panelMain);
	}
	
	/**
	 * This method is for testing. It will call the GameTest class. 
	 */
	public void switchTest(){
		frame.getContentPane().setVisible(false);
		frame.dispose();
		GameTest.run();
	}
	
	/**
	 * Switches the frame to the passed JPanel
	 * Sets current content pane to invisible
	 * Sets the frame to the new JPanel
	 * Sets new contnet pane to visible
	 * @param panel
	 */
	private void switchPanel(JPanel panel){
		//System.out.println(panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}
	
	
	/**
	 * This method will assign an action to each button in the displayMain Package
	 * When a button is pressed, the name String of the button is stored as a local variable
	 * A switch statement is used to compare the name with other string values to find the correct button
	 */
	public void actionPerformed(ActionEvent e){
		//Gets the name (NOT TEXT) of the button that was pressed
		JButton source = (JButton)e.getSource();
		String name = source.getName();
		//Finds the button that was pressed and does the needed commands
		switch(name){
		case "Back_RulePanel":
			switchPanel(panelMain); 
			break;
		case "Back_AboutPanel":
			switchPanel(panelMain); 
			break;
		case "Rule_MainPanel":
			switchPanel(panelRule); 
			break;
		case "About_MainPanel":
			switchPanel(panelAbout); 
			break;
		case "Test_MainPanel":
			switchTest();
		default:
			break;
		}
       
	}
    
}
