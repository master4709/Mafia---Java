package displaySetUp;

import displayGame.GameController;
import logic.SetUp;
import myJStuff.Colors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private PlayerNamePanel ipp;
	private RoleSelectionPanel rsp;
	private ButtonListener buttonListener;
	private JFrame frame;
	private JPanel panelCount;

	private List<String> playerNames;

	/**
	 * initialize the frame and set the bounds
	 */
	private SetUpController(JFrame frame){
		//Set the bounds and exit command

		buttonListener = new ButtonListener();
		pcp = new PlayerCountPanel(buttonListener);
		playerNames = new ArrayList<>();
		
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
	

	public void switchToPlayerName(int playerTotal) {
		frame.getContentPane().setVisible(false);
		ipp = new PlayerNamePanel(playerTotal);
		frame.setContentPane(ipp.getContentPane());
		ipp.getContentPane().setVisible(true);
	}

	public void switchToRoleSelection(List<String> names) {
		playerNames = names;
		frame.getContentPane().setVisible(false);
		rsp = new RoleSelectionPanel(names);
		frame.setContentPane(rsp.getContentPane());
		rsp.getContentPane().setVisible(true);
	}

	public void addListener(JButton button) {
		button.addActionListener(buttonListener);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String btnText = ((JButton)e.getSource()).getText();
			if (btnText.equals("Continue ")) {
				switchToGame(playerNames);
			} else if (btnText.equals("Continue")) {
				switchToPlayerName(pcp.getPlayerTotal());
			}
			else if (btnText.equals("Reset")){

				rsp.clearRolesSelected();
				for (JButton roleButton : rsp.getRoleButtons()) {
					roleButton.setEnabled(true);
					roleButton.setBackground(Colors.grey);
				}
				rsp.getAssignTownies().setEnabled(true);
				rsp.getContinueButton().setVisible(false);
				rsp.getPlayersLeft().setText(String.valueOf(playerNames.size()));

			} else if (btnText.equals("Assign the rest as Townie")){

				final int initialSize = rsp.getRolesSelected().size();
				for (int i = 0; i < playerNames.size() - initialSize; i++) {
					rsp.getRolesSelected().add("Townie");
				}
				System.out.println(rsp.getRolesSelected().toString());

				for (JButton roleButton : rsp.getRoleButtons()) {
					roleButton.setEnabled(false);
				}
				rsp.getPlayersLeft().setText("0");
				((JButton) e.getSource()).setEnabled(false);
				rsp.getContinueButton().setVisible(true);


			} else { // a specific role button is entered
				rsp.getRolesSelected().add(btnText);
				final int rolesSelectedSize = rsp.getRolesSelected().size();
				final int playerNamesSize = playerNames.size();

				((JButton) e.getSource()).setBackground(Colors.white);
				((JButton) e.getSource()).setEnabled(false);
				rsp.getPlayersLeft().setText(String.valueOf(
						(playerNamesSize - rolesSelectedSize < 0) ? 0 : playerNamesSize - rolesSelectedSize
				));

				System.out.println(rsp.getRolesSelected().toString());

				if (playerNamesSize == rolesSelectedSize) {
					rsp.getContinueButton().setVisible(true);
					rsp.getAssignTownies().setEnabled(false);
					for (JButton roleButton : rsp.getRoleButtons()) {
						roleButton.setEnabled(false);
					}
				} else {
					rsp.getContinueButton().setVisible(false);
				}
			}

		}

	}
}
