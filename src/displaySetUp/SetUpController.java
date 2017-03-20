package displaySetUp;

import displayGame.GameController;
import logic.SetUp;

import javax.swing.*;
import java.util.List;

/**
 * 
 * @author 
 *
 */
public class SetUpController {
	
	private static SetUpController instance = null;
	
	private SetUp setUp;
	private PlayerCountPanel pcp;
	private InputPlayerPanel ipp;
	
	private JFrame frame;
	private JPanel panelCount;

	/**
	 * initialize the frame and set the bounds
	 */
	private SetUpController(JFrame frame){
		//Set the bounds and exit command
		
		pcp = new PlayerCountPanel();
		
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
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
		panelCount = pcp.getContentPane();
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
	 * @param name
	 */
	public void switchToGame(List<String> name){
		setUp = new SetUp(name);
		GameController.createInstance(frame);
		GameController.getInstance().start(setUp.getPlayerInfo(), setUp.getLynchTarget() , false);
	}
	

	public void switchToInputPlayer(int playerTotal) {
		frame.getContentPane().setVisible(false);
		ipp = new InputPlayerPanel(playerTotal);
		frame.setContentPane(ipp.getContentPane());
		ipp.getContentPane().setVisible(true);
	}

	public void switchToRoleSelection(List<String> names) {
		frame.getContentPane().setVisible(false);
		RoleSelectionPanel rsp = new RoleSelectionPanel(names);
		frame.setContentPane(rsp.getContentPane());
		rsp.getContentPane().setVisible(true);
	}
}
