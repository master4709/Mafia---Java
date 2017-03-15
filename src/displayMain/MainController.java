package displayMain;

import displaySetUp.SetUpController;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainController {
	
	private static MainController instance = null;
	
	private JFrame frame = new JFrame();
	
	//Store the size of the screen
	private int width;
	private int height;
	
	private MainPanel mp;
	private RulePanel rp;
	private AboutPanel ap;
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelMain;
	private JPanel panelRule;
	private JPanel panelAbout;
	/**
	 * initialize the frame and set the bounds
	 */
	private MainController(){
		//Set the bounds and exit command
		width = 563;
		height = 1000;
		frame.setBounds(600, 25, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start();
		frame.setVisible(true);
	}
	
	public static void createInstance(){
		if(instance==null){
			instance = new MainController();
		}
	}
	
	public static MainController getInstance(){
		return instance;
	}
	/**
	 * Creates each of the need contentPanes panels
	 * Set current panel to the Main and sets it to visible 
	 */
	public void start(){
		//Create all of the panels
		mp = new MainPanel();
		rp = new RulePanel();
		ap = new AboutPanel();
		panelMain = mp.getContentPane();
		panelRule = rp.getContentPane();
		panelAbout = ap.getContentPane();
		
		//Sets the frame to the main screen and to visible
		frame.setContentPane(panelMain);
		panelMain.setVisible(true);
	}
	
	/**
	 * switches the content panel to the about page
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
	 * switches the content panel to the rule page
	 */
	public void switchRule(){
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelRule);
		panelRule.setVisible(true);
	}
	/**
	 * switches the content panel to the main page 
	 */
	public void switchMain(){
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelMain);
		panelMain.setVisible(true);
	}
	/**
	 * This method goes to the GameController
	 */
	public void switchToSetUp(){
		SetUpController.createInstance(frame);
	}
}
