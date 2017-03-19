package displaySetUp;

import logic.*;
import myJStuff.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author
 *
 */
public class RoleSelectionPanel {

    private JPanel contentPane;
    private ButtonListener buttonListener;
    private ArrayList<JButton> playerButtons;
    private JLabel centralLabel;

    private int totalPlayers;
    private ArrayList<String> rolesSelected; //temp
    private ArrayList<String> playersWhoHasChosenARole; //temp
    private HashMap<String, String> playersAndTheirRole;
    private ArrayList<Role> availableRoles;
    private String playerToAssign;


    /***
     * Constructor. Specifies the initial number of total players
     * @param totalPlayers
     */
    public RoleSelectionPanel(int totalPlayers) {

        this.totalPlayers = totalPlayers;
        System.out.println("Total Players: " + totalPlayers);

        rolesSelected = new ArrayList<>();
        playersWhoHasChosenARole = new ArrayList<>();
        playersAndTheirRole = new HashMap<>();
        availableRoles = new ArrayList<>(Arrays.asList(Role.values()));
        contentPane = new JPanel(new MigLayout( "",
                "[][grow, grow][]",
                ""));
        buttonListener = new ButtonListener();
        playerButtons = new ArrayList<>();
        centralLabel = new MyLabel("Enter a role for each player", Colors.black, 30);

        initialize();

    }

    /**
     * Initialize contents.
     */
    private void initialize() {

        contentPane.setBackground(Colors.black);

        JButton goToMain = new MyButton("Back", Colors.white, Colors.black, 15);
        contentPane.add(goToMain);

        JButton continueButton = new MyButton("Continue", Colors.white, Colors.black, 15);
        continueButton.addActionListener(buttonListener);
        contentPane.add(continueButton, "wrap, right");

        centralLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border selectRolesMargin = new EmptyBorder(10, 10, 20, 10);
        centralLabel.setBorder(new CompoundBorder(contentPane.getBorder(), selectRolesMargin));
        contentPane.add(centralLabel, "span, center, wrap");

        createPlayerButtons();

    }

    /***
     * Create ten buttons by default. Extra buttons are used to display additional roles.
     */
    private void createPlayerButtons() {

        for (int count = 0; count< 10; count++) {
            JButton playerBtn = new MyButton("Player " + (count+1), Colors.white, Colors.grey, 30);
            if (count >= totalPlayers) {
                playerBtn.setVisible(false);
            }
            playerButtons.add(playerBtn);
            playerBtn.addActionListener(buttonListener);
            contentPane.add(playerBtn, "span, pushx, grow, wrap");
        }

    }

    /***
     * Change texts of existing player buttons to roles that are available for selection
     * @param playerToAssignARoleTo
     */
    private void showAvailableRoles(String playerToAssignARoleTo) {

        playersWhoHasChosenARole.add(playerToAssignARoleTo);
        playerToAssign = playerToAssignARoleTo;
        centralLabel.setText("Select a role for " + playerToAssignARoleTo);

        for (int count = 0; count < 10; count++) {
            playerButtons.get(count).setText(availableRoles.get(count).getRoleID());
            playerButtons.get(count).setEnabled(true);
            playerButtons.get(count).setVisible(true);
            if (inSelectedRoles(playerButtons.get(count).getText())) {
                playerButtons.get(count).setEnabled(false);
            }
        }

    }

    /***
     * Check if the specified string is in the list of rolesSelected
     * @param roleID
     * @return
     */
    private boolean inSelectedRoles(String roleID) {

        for (String role : rolesSelected) {
            if (role.equals(roleID)) {
                return true;
            }
        }
        return false;

    }

    private void assignRolesToPlayers() {

        // TODO

    }

    /***
     * Reset button texts to corresponding player numbers. Hide extra buttons.
     */
    private void resetButtonsText() {

        int count = 0;
        for (JButton playerBtn : playerButtons) {
            if (count < totalPlayers) {
                playerBtn.setVisible(true);
                playerBtn.setEnabled(true);
                playerBtn.setText("Player " + (count+1));
                if (inPlayersWhoHasChosenARole(playerBtn.getText())) {
                    playerBtn.setText(playerBtn.getText() + ": " + playersAndTheirRole.get(playerBtn.getText()));
                    playerBtn.setEnabled(false);
                }
            } else {
                playerBtn.setVisible(false);
            }
            count++;
        }

        centralLabel.setText("Enter a role for each player");

    }

    /***
     * Check if the specified String of player's name is in the list of players
     * who has chosen a role
     * @param playerName
     * @return
     */
    private boolean inPlayersWhoHasChosenARole(String playerName) {

        for (String player : playersWhoHasChosenARole) {
            if (player.equals(playerName)) {
                return true;
            }
        }
        return false;

    }

    public JPanel getContentPane() {
        return contentPane;
    }


    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String btnText = ((JButton)e.getSource()).getText();
            if (btnText.contains("Player")) {
                showAvailableRoles(btnText);
            } else if (btnText.equals("Continue")) {
                // TODO proceed to the game
            } else { // a specific role button is entered
                rolesSelected.add(btnText);
                playersAndTheirRole.put(playerToAssign, btnText);
                System.out.println(rolesSelected.toString());
                resetButtonsText();
            }

        }

    }

}
