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
 * @author Christilyn Arjona, 30033435
 *
 */
public class SetUpController implements ActionListener{
	private SetUp setUp;

    private PlayerCountPanel pcp;
    private PlayerNamePanel pnp;
    private RoleSelectionPanel rsp;
	private ActionListener globalListener;
	private JFrame frame;
	private JPanel panelCount;

	private List<String> playerNames;

    /***
     * Initializes the setup frame
     *
     * @param frame frame
     */
	public SetUpController(JFrame frame, ActionListener globalListener){
		pcp = new PlayerCountPanel(this);
		playerNames = new ArrayList<>();
		
		this.globalListener = globalListener;
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);

	}

    /***
     * Create panel for selecting number of total players
     * @see #switchPlayerTotal()
     */
	public void start(){
		panelCount = pcp.getContentPane();
		switchPlayerTotal();
	}

    /***
     * Switch frame content to PlayerCountPanel created in start()
     * @see #start()
     */
	public void switchPlayerTotal(){
		frame.getContentPane().setVisible(false);
		frame.setContentPane(panelCount);
		panelCount.setVisible(true);
	}

    /***
     * Create a panel for inputting the name of players and display that panel to existing frame
     * @param playerTotal initial number of players
     */
	public void switchToPlayerName(int playerTotal) {
		frame.getContentPane().setVisible(false);
        pnp = new PlayerNamePanel(playerTotal);
		frame.setContentPane(pnp.getContentPane());
        pnp.getContentPane().setVisible(true);
	}

    /***
     * Create a panel for selecting roles to include in the game and display that panel to existing frame
     * @param names A list of type string for the player names
     */
	public void switchToRoleSelection(List<String> names) {
		playerNames = names;
		frame.getContentPane().setVisible(false);
		rsp = new RoleSelectionPanel(names);
		frame.setContentPane(rsp.getContentPane());
		rsp.getContentPane().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		String btnText = ((JButton)e.getSource()).getText();
		if (btnText.equals("Continue")) {
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
