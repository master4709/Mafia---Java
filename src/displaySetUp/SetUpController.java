package displaySetUp;

import displayGame.GameController;
import logic.*;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class SetUpController {
	
	private static SetUpController instance = null;
	
	private JFrame frame;
	

	
	
	//All of the possible panels to be displayed on the frame
	private JPanel panelPlayerTotal;
	/**
	 * initialize the frame and set the bounds
	 */
	private SetUpController(JFrame frame){
		//Set the bounds and exit command
		this.frame = frame;
		
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
		//frame.setContentPane(panelPlayerTotal);
		//panelPlayerTotal.setVisible(true);
		switchToGame();
	}
	
	/**
	 * switches the content panel to the main page 
	 */
	public void switchPlayerTotal(){
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelPlayerTotal);
		panelPlayerTotal.setVisible(true);
	}
	/**
	 * This method goes to the GameController
	 */
	public void switchToGame(){
		Players p = new Players();
		Game game = new Game(p.getPlayerInfo(),p.getMafiaMembers(),p.getLynchTarget());
		GameController.createInstance(frame, game);
	}
}
