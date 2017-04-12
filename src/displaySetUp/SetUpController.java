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

	private int playerTotal;

	/***
	 * Initializes the setup frame and specifies the global listener.
	 *
	 * @param frame setup frame
	 * @param globalListener global listener
	 */
	public SetUpController(JFrame frame, ActionListener globalListener){
		this.globalListener = globalListener;
		this.frame = frame;
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

    /***
     * Create appropriate panels associated with the setup process.
	 * Set default total player to 5.
     *
     */
	public void start(){
		pcp = new PlayerCountPanel(this,globalListener);
		pnp = new PlayerNamePanel(this,globalListener);
		rsp = new RoleSelectionPanel(this,globalListener);
		panelCount = pcp.getContentPane();
		panelName = pnp.getContentPane();
		panelRole = rsp.getContentPane();
		
		playerTotal = 5;
		
		switchPanel(panelCount);
		
	}

	/***
	 * Sets the current pane to invisible and set the specified panel
	 * to visible.
	 *
	 * @param panel to switch to
	 */
	public void switchPanel(JPanel panel){
		System.out.println(panel.getName());
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panel);
		frame.getContentPane().setVisible(true);
	}

	/***
	 * A method for the package listener.
	 * Handles appropriate events that are involved in the setup process.
	 *
	 * @param e event
	 */
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
					switchPanel(panelRole);
					rsp.getPlayersLeft().setText(String.valueOf(pnp.getPlayerNames().size()));
					int total = pnp.getPlayerNames().size();
					if(total<6){
						rsp.getRecomdedMafia().setText("1 Mafia Player Recomeded");
					}else if(total<9){
						rsp.getRecomdedMafia().setText("2 Mafia Player Recomeded");
					}else if(total<12){
						rsp.getRecomdedMafia().setText("3 Mafia Player Recomeded");
					}else if(total==12){

						rsp.getRecomdedMafia().setText("4 Mafia Player Recomeded");
					}
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

	/***
	 * Clears the current selection of roles and configure
	 * corresponding ui components to be enabled and set appropriate visibility
	 * based on the current state of selections.
	 */
	private void resetSelections() {
		rsp.clearRolesSelected();
		for (JButton roleButton : rsp.getRoleButtons()) {
			roleButton.setEnabled(true);
			roleButton.setForeground(Colors.defaultButtonTextColor);
			roleButton.setBackground(Colors.defaultButtonBackgroundColor);
		}
		rsp.getAssignTownies().setEnabled(true);
		rsp.getContinueButton().setEnabled(false);
		rsp.getPlayersLeft().setText(String.valueOf(pnp.getPlayerNames().size()));
	}

	/***
	 * Assigns the remaining players as Townies and configure
	 * corresponding ui components to be enabled and set appropriate visibility
	 * based on the current state of selections.
	 *
	 * @param source button event source
	 */
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
		rsp.getContinueButton().setEnabled(true);
	}

	/***
	 * Adds the selected role to the role selection list.
	 * Configure corresponding ui components to be enabled and set appropriate visibility
	 * based on the current state of selections.
	 * Updates the players remaining label to its appropriate value.
	 *
	 * @param source button event source corresponding to a specific role
	 */
	private void onRoleButtonClick(JButton source) {
		rsp.addRole(source.getText());
		final int rolesSelectedSize = rsp.getRolesSelected().size();
		final int playerNamesSize = pnp.getPlayerNames().size();

		source.setEnabled(false);
		source.setBackground(Colors.defaultButtonTextColor);
		source.setForeground(Colors.defaultButtonBackgroundColor);

		rsp.getPlayersLeft().setText(String.valueOf(
				(playerNamesSize - rolesSelectedSize < 0) ? 0 : playerNamesSize - rolesSelectedSize
		));

		verifySelections(playerNamesSize, rolesSelectedSize);

	}

	/***
	 * Verifies current number of selected roles and configure
	 * corresponding ui components to be enabled and set appropriate visibility
	 * based on the current number of selections.
	 *
	 * @param playerNamesSize total number of players
	 * @param rolesSelectedSize total number of selected roles
	 */
	private void verifySelections(int playerNamesSize, int rolesSelectedSize) {
		if (playerNamesSize == rolesSelectedSize) {
			rsp.getContinueButton().setEnabled(true);
			rsp.getAssignTownies().setEnabled(false);
			for (JButton roleButton : rsp.getRoleButtons()) {
				roleButton.setEnabled(false);
			}
		} else {
			rsp.getContinueButton().setEnabled(false);
		}
	}

	/***
	 *
	 * @return list of type string containing names of each player.
	 */
	public List<String> getPlayerNames(){
		return pnp.getPlayerNames();
	}
	
	/**
	 *
	 * @return list of type string containing selected roles.
	 */
	public List<String> getRoles(){
		return rsp.getRolesSelected();
	}

}
