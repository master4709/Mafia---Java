package displaySetUp;

import myJStuff.Colors;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 
 * @author Christilyn Arjona, 30033435
 *
 */
public class SetUpController implements ActionListener{
	
	private ActionListener globalListener;
	
	private JFrame frame;
	
	private PlayerCountPanel pcp;
    private PlayerNamePanel pnp;
    private RoleSelectionPanel rsp;
    
	private JPanel panelCount;
	private JPanel panelName;
	private JPanel panelRole;
	
	//Current amount of players selected on the panelCount
	private int playerTotal;

    /***
     * Initializes the setup frame
     *
     * @param frame frame
     */
	public SetUpController(JFrame frame, ActionListener globalListener){
		this.globalListener = globalListener;
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    /***
     * Create panel for selecting number of total players
     * @see 
     */
	public void start(){
		pcp = new PlayerCountPanel(this);
		pnp = new PlayerNamePanel(this);
		rsp = new RoleSelectionPanel(this,globalListener);
		panelCount = pcp.getContentPane();
		panelName = pnp.getContentPane();
		panelRole = rsp.getContentPane();
		
		playerTotal = 5;
		
		switchPanel(panelCount);
		
	}
	
	public void switchPanel(JPanel panel){
		System.out.println(panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		String name = source.getName();

		if (name.contains("RoleButton")) {
			onRoleButtonClick(source);
		} else {
			switch(name){
				case"Continue_PlayerCount":
					//Creates a list of player input fields for the amount of players selected
					pnp.displayCenter(playerTotal);
					switchPanel(panelName);
					break;
				case"Continue_PlayerName":
					System.out.println(pnp.getPlayerNames());
					switchPanel(panelRole);
					rsp.getPlayersLeft().setText(String.valueOf(pnp.getPlayerNames().size()));
					break;
				case "Reset_RoleSelection":
					resetSelections();
					break;
				case "AssignTownies_RoleSelection":
					assignRestAsTownies(source);
					break;
				default:
					//If the button that was pressed was in the PlayerCountPanel
					if(name.contains("PlayerCount ")){
						playerTotal = Integer.parseInt(name.substring(12,name.length()));//Receive the int value of the button that was pressed
						pcp.changeButtonSelected(playerTotal);//Sets the pressed button to the selected color and all the other buttons to default colors
					}
					break;
			}
		}
		}

	private void resetSelections() {
		rsp.clearRolesSelected();
		for (JButton roleButton : rsp.getRoleButtons()) {
			roleButton.setEnabled(true);
			roleButton.setBackground(Colors.grey);
		}
		rsp.getAssignTownies().setEnabled(true);
		rsp.getContinueButton().setVisible(false);
		rsp.getPlayersLeft().setText(String.valueOf(pnp.getPlayerNames().size()));
	}

	private void assignRestAsTownies(JButton source) {
		final int initialSize = rsp.getRolesSelected().size();
		for (int i = 0; i < pnp.getPlayerNames().size() - initialSize; i++) {
			rsp.addRole("Townie");
		}

		for (JButton roleButton : rsp.getRoleButtons()) {
			roleButton.setEnabled(false);
		}
		rsp.getPlayersLeft().setText("0");
		source.setEnabled(false);
		rsp.getContinueButton().setVisible(true);
	}

	private void onRoleButtonClick(JButton source) {
		rsp.addRole(source.getText());
		final int rolesSelectedSize = rsp.getRolesSelected().size();
		final int playerNamesSize = pnp.getPlayerNames().size();

		source.setBackground(Colors.white);
		source.setEnabled(false);

		rsp.getPlayersLeft().setText(String.valueOf(
				(playerNamesSize - rolesSelectedSize < 0) ? 0 : playerNamesSize - rolesSelectedSize
		));

		verifySelections(playerNamesSize, rolesSelectedSize);

	}

	private void verifySelections(int playerNamesSize, int rolesSelectedSize) {
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

	public List<String> getPlayerNames(){
		return pnp.getPlayerNames();
	}
	
	/**
	 * This needs to return the list of roles somehow
	 * @return
	 */
	public List<String> getRoles(){
		return rsp.getRolesSelected();
	}

}
