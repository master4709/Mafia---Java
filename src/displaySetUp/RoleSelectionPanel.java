package displaySetUp;

import logic.Role;
import myJStuff.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class creates the panel for selecting roles.
 * @author Christilyn Arjona, 30033435
 *
 */
public class RoleSelectionPanel extends MyPanel{

    private JLabel playersLeft;
    private JButton btnContinue;
    private JButton assignTownies;
    
    private ActionListener globalListener;
    private ArrayList<String> rolesSelected;
    private ArrayList<Role> availableRoles;
    private ArrayList<JButton> roleButtons;


    /***
     * Constructor. Specifies a list of player names
     * @param playerNames A list of type string containing names of each player
     */
    public RoleSelectionPanel(ActionListener packageListener, ActionListener globalListener) {
    	this.packageListener = packageListener;
    	this.globalListener = globalListener;
    	contentPane.setName("RoleSelection Panel");
    	

        //System.out.println("Total Players: " + playerNames.size());

        //rolesSelected = new ArrayList<>();
        //availableRoles = new ArrayList<>(Arrays.asList(Role.values()));
        //playersLeft = new MyLabel(String.valueOf(this.playerNames.size()), Colors.black, 20);
        //continueButton = new MyButton("Continue ", Colors.white, Colors.black, 15);
        //assignTownies = new MyButton("Assign the rest as Townie", Colors.white, Colors.grey, 15);
        //roleButtons = new ArrayList<>();

        //initialize();
    	
    	displaySouth();

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
        contentPane.add(resetBtn, "split 2, span, pushx, grow");
        
        contentPane.add(assignTownies, "span, pushx, grow, wrap");

        JPanel n = new JPanel();
        n.setBackground(Colors.black);
        contentPane.add(n, "span 1, grow, pushy, wrap push");
        
    }
    
    private void displaySouth(){
    	btnContinue = new MyButton("Continue",buttonFont);
    	south.add(btnContinue,"cell 0 0,alignx center");
    	btnContinue.setName("Continue_RoleSelection");
    	btnContinue.addActionListener(globalListener);
    }

    private void createRoleButtons() {

        for (int count = 0; count < 10; count++) {
            if (!availableRoles.get(count).equals(Role.TOWNIE)) {
                JButton roleBtn = new MyButton(availableRoles.get(count).getRoleID(), Colors.white, Colors.grey, 30);
                roleButtons.add(roleBtn);
                contentPane.add(roleBtn, "span, pushx, grow, wrap");
            }
        }

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

    public JLabel getPlayersLeft() {
        return playersLeft;
    }

    public ArrayList<String> getRolesSelected() {
        return rolesSelected;
    }
}
