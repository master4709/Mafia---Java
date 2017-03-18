package displayMain;

import displaySetUp.SetUpController;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author
 *
 */
public class MainController {
	
	private static MainController instance = null;
	
	private JFrame frame = new JFrame();
	
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
	private MainController(JFrame frame){
		
		mp = new MainPanel();
		rp = new RulePanel();
		ap = new AboutPanel();
		
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

	}
	
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new MainController(frame);
		}
	}
	
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
		//Create all of the panels
		panelMain = mp.getContentPane();
		panelRule = rp.getContentPane();
		panelAbout = ap.getContentPane();
		
		switchMain();
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
		SetUpController.getInstance().start();
	}
}
