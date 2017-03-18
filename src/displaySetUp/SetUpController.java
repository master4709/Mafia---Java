package displaySetUp;

import displayGame.GameController;
import logic.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author 
 *
 */
public class SetUpController {
	
	private static SetUpController instance = null;
	
	private Players p;
	private PlayerCountPanel pcp;
	
	private JFrame frame;
	private JPanel panelCount;

	/**
	 * initialize the frame and set the bounds
	 */
	private SetUpController(JFrame frame){
		//Set the bounds and exit command
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pcp = new PlayerCountPanel();
		panelCount = pcp.getContentPane();
		start();
		frame.setVisible(true);
	}
	
	public static void createInstance(JFrame frame){
		if(instance==null){
			instance = new SetUpController(frame);
		}
	}
	
	public static SetUpController getInstance(){
		return instance;
	}
	/**
	 * Creates each of the need contentPanes panels
	 * Set current panel to the Main and sets it to visible 
	 */
	public void start(){
		//Create all of the panel
		//Sets the frame to the main screen and to visible
		switchPlayerTotal();
	}
	
	/**
	 * switches the content panel to the main page 
	 */
	public void switchPlayerTotal(){
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelCount);
		panelCount.setVisible(true);
	}
	/**
	 * This method goes to the GameController
	 */
	public void switchToGame(int playerTotal){
		p = new Players(playerTotal);
		GameController.createInstance(frame);
		GameController.getInstance().start(p.getPlayerInfo(), p.getLynchTarget());
	}
}
