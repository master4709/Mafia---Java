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
 * This class will be called first in RunMafia to begin the game. 
 * @author Mahsa Lotfi 10072013
 * 
 */

public class MainController {
	/**
	 * Instance variables
	 */
	private static MainController instance = null;
	
	private JFrame frame = new JFrame();
	
	private MainPanel mp;
	private RulePanel rp;
	private AboutPanel ap;
	
	private Listener listener;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelMain;
	private JPanel panelRule;
	private JPanel panelAbout;
	
	/**
	 * Constructor with one argument of JFrame frame.
	 * This constructor will initialize the frame and set the bounds.
	 * Must be private, so only one instance can be made
	 */
	private MainController(JFrame frame){

		listener = new Listener();
		
		mp = new MainPanel(listener);
		rp = new RulePanel(listener);
		ap = new AboutPanel(listener);
		
		//setting up the frame 
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
	}
	
	/**
	 * This method will change the static instance variable 
	 * of this class from null to a new object of 
	 * MainController with determined frame.
	 * It will ensures only one instance of the GameController Class can be made
	 * @param frame
	 */
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new MainController(frame);
		}
	}
	
	/**
	 * Accesor method which will return instance of MainController class.
	 * @return instance
	 */
	public static MainController getInstance(){
		if(instance!=null){
			return instance;
		}else{
			return null;
		}		
	}
	
	/**
	 * Creates each of the needed contentPanes panels
	 * Set current panel to the Main and sets it to visible 
	 */
	public void start(){
		//Create all of the panels. The Main menu and the ones that are included in Main menu
		panelMain = mp.getContentPane();
		panelRule = rp.getContentPane();
		panelAbout = ap.getContentPane();
		
		switchMain();
	}
	
	/**
	 * switches the content panel to the about page. This method will be called for action listener. 
	 */
	public void switchAbout(){
		//Sets current content panel to hidden
		frame.getContentPane().setVisible(false);
		//Sets the content panel to the About page 
		frame.setContentPane(panelAbout);
		//Sets current content panel to visible
		panelAbout.setVisible(true);
	}
	/**
	 * switches the content panel to the rule page. This method will be called for action listener. 
	 */
	public void switchRule(){
		//Sets current content panel to hidden
		frame.getContentPane().setVisible(false);
		//Sets the content panel to the Rule page 
		frame.setContentPane(panelRule);
		//Sets current content panel to visible
		panelRule.setVisible(true);
	}
	/**
	 * switches the content panel to the main menu. This method will be called for action listener.
	 */
	public void switchMain(){
		//Sets current content panel to hidden
		frame.getContentPane().setVisible(false);
		//Sets the content panel to the Main page 
		frame.setContentPane(panelMain);
		//Sets current content panel to visible
		panelMain.setVisible(true);
	}
	/**
	 * This method will switch the contentPanel by calling the SetUpController. 
	 */
	public void switchToSetUp(){
		//creating instance object in SetUpController
		SetUpController.createInstance(frame);
		//creating panels for next stage of the game which is set up.
		SetUpController.getInstance().start();
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
	 * This class is the ActionListenr for all of the buttons in the displayMain Package
	 * When a button is pressed, the name String of the button is stored as a local variable
	 * A switch statement is used to compare the name with other string values to find the correct button
	 */
	public class Listener implements ActionListener{
		
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
}
