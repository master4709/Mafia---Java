package displaySetUp;

import logic.Role;
import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class creates the panel for selecting roles.
 * @author Christilyn Arjona, 30033435
 *
 */
public class RoleSelectionPanel extends MyPanel{

    private JLabel playersLeft;
    private JButton continueButton;
    private JButton assignTownies;
    
    private ActionListener globalListener;
    private ArrayList<String> rolesSelected;
    private ArrayList<Role> availableRoles;
    private ArrayList<JButton> roleButtons;


    /***
     * Constructor. Specifies a list of player names
     * @param
     */
    public RoleSelectionPanel(ActionListener packageListener, ActionListener globalListener) {
    	this.packageListener = packageListener;
    	this.globalListener = globalListener;
    	contentPane.setName("RoleSelection Panel");

        initialize();

    }

    private void displayCenter() {
        for (int count = 0; count < 10; count++) {
            if (!availableRoles.get(count).equals(Role.TOWNIE)) {
                JButton roleBtn = new MyButton(availableRoles.get(count).getRoleID(), Colors.white, Colors.grey, 30);
                roleBtn.setName("RoleButton " + (count+1));
                roleBtn.addActionListener(packageListener);
                roleButtons.add(roleBtn);
                center.add(roleBtn, "cell 0 "+count+",growx");
//                contentPane.add(roleBtn, "span, pushx, grow, wrap");
            }
        }

    }

    private void displayNorth() {
        playersLeft = new MyLabel("", Colors.black, 20);
        JLabel title = new MyLabel("Select possible player roles", Colors.black, 30);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        north.add(title, "cell 0 0,alignx center");

        JLabel centralLabel = new MyLabel("Players remaining: ", Colors.black, 20);
        centralLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border selectRolesMargin = new EmptyBorder(10, 10, 20, 10);
        centralLabel.setBorder(new CompoundBorder(contentPane.getBorder(), selectRolesMargin));
        north.add(centralLabel, "cell 0 1,split 2, span, center");
        north.add(playersLeft, "wrap");
    }

    /**
     * Initialize contents.
     */
    private void initialize() {

        rolesSelected = new ArrayList<>();
        availableRoles = new ArrayList<>(Arrays.asList(Role.values()));
        roleButtons = new ArrayList<>();
        continueButton = new MyButton("Continue ", Colors.white, Colors.black, 15);

        displayNorth();
        displayCenter();
        displaySouth();
        
    }
    
    private void displaySouth(){
        continueButton = new MyButton("Continue",buttonFont);
    	south.add(continueButton,"cell 0 1,alignx center");
    	continueButton.setVisible(false);
        continueButton.setName("Continue_RoleSelectionPanel");
        continueButton.addActionListener(globalListener);

        assignTownies = new MyButton("Assign the rest as Townie", Colors.white, Colors.grey, 15);
        assignTownies.setName("AssignTownies_RoleSelection");
        assignTownies.addActionListener(packageListener);

        JButton resetBtn = new MyButton("Reset", Colors.white, Colors.grey, 15);
        resetBtn.setName("Reset_RoleSelection");
        resetBtn.addActionListener(packageListener);
        south.add(resetBtn, "cell 0 0, alignx center");

        south.add(assignTownies, "cell 0 0, alignx right");
    }

    public void clearRolesSelected() {
        rolesSelected.clear();
    }
    
    ArrayList<JButton> getRoleButtons() {
        return roleButtons;
    }
    
    JButton getAssignTownies() {
        return assignTownies;
    }

    JLabel getPlayersLeft() {
        return playersLeft;
    }

    ArrayList<String> getRolesSelected() {
        return new ArrayList<>(rolesSelected);
    }

    void addRole(String role) {
        rolesSelected.add(role);
    }

    JButton getContinueButton() {
        return continueButton;
    }
}
