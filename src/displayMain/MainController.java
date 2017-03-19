package displayMain;

import displaySetUp.SetUpController;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Testing.GameTest;

/**
 * This class controls 3 panels which are related to the main menu. The panels are: AboutPanel, 
 * RulePanel and MainPanel. This class will be called first in RunMafia to begin the game. 
 * @author Mahsa Lotfi
 */
public class MainController {
	/**
	 * Instance variables
	 */
	//
	private static MainController instance = null;
	
	//frame for the game
	private JFrame frame = new JFrame();
	
	//
	private MainPanel mp;
	private RulePanel rp;
	private AboutPanel ap;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelMain;
	private JPanel panelRule;
	private JPanel panelAbout;
	
	/**
	 * Constructor with one argument of JFrame frame.
	 * This constructor will initialize the frame and set the bounds.
	 */
	private MainController(JFrame frame){
		
		mp = new MainPanel();
		rp = new RulePanel();
		ap = new AboutPanel();
		
		//setting up the frame 
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

	}
	
	/**
	 * This method will change the static instance variable of this class from null to a new object of 
	 * MainController with determined frame.
	 */
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new MainController(frame);
		}
	}
	
	/**
	 * Accessor method which will return MainController object.
	 */
	public static MainController getInstance(){
		if(instance!=null){
			return instance;
		}else{
			return null;
		}		
	}
	
	/**
	 * Creates each of the need contentPanes panels
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
		//Sets the content panel to the About page 
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
		//Sets the content panel to the About page 
		frame.setContentPane(panelMain);
		//Sets current content panel to visible
		panelMain.setVisible(true);
	}
	/**
	 * This method goes to the SetUpController. 
	 */
	public void switchToSetUp(){
		//creating instance object in SetUpController
		SetUpController.createInstance(frame);
		//creating panels for next stage of the game which is set up.
		SetUpController.getInstance().start();
	}
	
	public void switchTest(){
		frame.getContentPane().setVisible(false);
		frame.dispose();
		GameTest.run();
	}
}
