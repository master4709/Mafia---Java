package displaySetUp;

import logic.Role;
import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class creates the panel for selecting roles.
 * @author Christilyn Arjona, 30033435
 *
 */
public class RoleSelectionPanel {

    private JPanel contentPane;
    private JLabel playersLeft;
    private JButton continueButton;
    private JButton assignTownies;

    private List<String> playerNames;
    private ArrayList<String> rolesSelected;
    private ArrayList<Role> availableRoles;
    private ArrayList<JButton> roleButtons;


    /***
     * Constructor. Specifies a list of player names
     * @param playerNames A list of type string containing names of each player
     */
    public RoleSelectionPanel(List<String> playerNames) {

        this.playerNames = new ArrayList<>(playerNames);
        System.out.println("Total Players: " + playerNames.size());

        rolesSelected = new ArrayList<>();
        availableRoles = new ArrayList<>(Arrays.asList(Role.values()));
        contentPane = new JPanel(new MigLayout( "",
                "[][grow, grow][]",
                ""));
        playersLeft = new MyLabel(String.valueOf(this.playerNames.size()), Colors.black, 20);
        continueButton = new MyButton("Continue ", Colors.white, Colors.black, 15);
        assignTownies = new MyButton("Assign the rest as Townie", Colors.white, Colors.grey, 15);
        roleButtons = new ArrayList<>();

        initialize();

    }

    /**
     * Initialize contents.
     */
    private void initialize() {

        contentPane.setBackground(Colors.black);

        JLabel title = new MyLabel("Select possible player roles", Colors.black, 30);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(title, "span, center, wrap");

        JLabel centralLabel = new MyLabel("Players remaining: ", Colors.black, 20);
        centralLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border selectRolesMargin = new EmptyBorder(10, 10, 20, 10);
        centralLabel.setBorder(new CompoundBorder(contentPane.getBorder(), selectRolesMargin));
        contentPane.add(centralLabel, "split 2, span, center");

        contentPane.add(playersLeft, "wrap");

        createRoleButtons();

        JButton resetBtn = new MyButton("Reset", Colors.white, Colors.grey, 15);
        addButtonListener(resetBtn);
        contentPane.add(resetBtn, "split 2, span, pushx, grow");

        addButtonListener(assignTownies);
        contentPane.add(assignTownies, "span, pushx, grow, wrap");

        JPanel n = new JPanel();
        n.setBackground(Colors.black);
        contentPane.add(n, "span 1, grow, pushy, wrap push");

        addButtonListener(continueButton);
        continueButton.setSize(new Dimension(200, 200));
        contentPane.add(continueButton, "span, pushx, pushy, grow");
        continueButton.setVisible(false);
    }

    private void createRoleButtons() {

        for (int count = 0; count < 10; count++) {
            if (!availableRoles.get(count).equals(Role.TOWNIE)) {
                JButton roleBtn = new MyButton(availableRoles.get(count).getRoleID(), Colors.white, Colors.grey, 30);
                addButtonListener(roleBtn);
                roleButtons.add(roleBtn);
                contentPane.add(roleBtn, "span, pushx, grow, wrap");
            }
        }

    }
    
    private void addButtonListener(JButton button) {
        SetUpController.getInstance().addListener(button);
    }
    
    public JPanel getContentPane() {
        return contentPane;
    }

    public void clearRolesSelected() {
        rolesSelected.clear();
    }
    
    public ArrayList<JButton> getRoleButtons() {
        return roleButtons;
    }
    
    public JButton getAssignTownies() {
        return assignTownies;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JLabel getPlayersLeft() {
        return playersLeft;
    }

    public ArrayList<String> getRolesSelected() {
        return rolesSelected;
    }
}
