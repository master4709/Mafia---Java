package displaySetUp;

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
		
		switch(name){
		case"Continue_PlayerCount":
			//Creates a list of player input fields for the amount of players selected
			pnp.displayCenter(playerTotal);
			switchPanel(panelName);
			break;
		case"Continue_PlayerName":
			System.out.println(pnp.getPlayerNames());
			switchPanel(panelRole);
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
	
	public List<String> getPlayerNames(){
		return pnp.getPlayerNames();
	}
	
	/**
	 * This needs to return the list of roles somehow
	 * @return
	 */
	public List<String> getRoles(){
		return null;
	}
	
	/*
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
	*/
}
