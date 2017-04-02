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
					
		switchMain();
	}
	
	/**
	 * switches the content panel to the about page. 
	 * This method will be called in actionPerformed method. 
	 */
	public void switchAbout(){
		//Refresh the JPanel
		panelAbout = ap.getContentPane();
		//Set the frame to the new JPanel content panel
		switchPanel(panelAbout);
	}
	/**
	 * switches the content panel to the rule page. 
	 * This method will be called in actionPerformed method.
	 */
	public void switchRule(){
		//Refresh the JPanel
		panelRule = rp.getContentPane();
		//Set the frame to the new JPanel content panel
		switchPanel(panelRule);
	}
	/**
	 * switches the content panel to the main menu.
	 * This method will be called in actionPerformed method.
	 */
	public void switchMain(){
		//Refresh the JPanel
		panelMain = mp.getContentPane();
		//Set the frame to the new JPanel content panel
		switchPanel(panelMain);
	}
	/**
	 * This method will switch the contentPanel by calling the SetUpController. 
	 * This method will be called in actionPerformed method.
	 */
	public void switchToSetUp(){
		suc = new SetUpController(frame,this);
		suc.start();
		
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
			switchMain(); 
			break;
		case "Back_AboutPanel":
			switchMain(); 
			break;
		case "NewGame_MainPanel":
			switchToSetUp();
			break;
		case "ContinueGame_MainPanel":
			break;
		case "Rule_MainPanel":
			switchRule();
			break;
		case "About_MainPanel":
			switchAbout();
			break;
		case "Test_MainPanel":
			switchTest();
		default:
			break;
		}
       
	}
    
}
