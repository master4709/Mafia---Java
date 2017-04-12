package displaySetUp;

//import myJStuff.Colors;
import myJStuff.MyButton;
import myJStuff.MyLabel;
import myJStuff.MyPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * This class creates the panel for selecting roles.
 * @author Christilyn Arjona, 30033435
 *
 */
public class RoleSelectionPanel extends MyPanel{

    private JLabel playersLeft;
    private JButton continueButton;
    private JButton assignTownies;
    private JButton btnHome;
    
    private ActionListener globalListener;
    private ArrayList<String> rolesSelected;
    private ArrayList<JButton> roleButtons;

    private JLabel recomendedMafia;

    /***
     * Class constructor. Specifies the package listener associated with the setup process
     * and the global listener.
     *
     * @param packageListener Package listener
     * @param globalListener global listener
     */
    public RoleSelectionPanel(ActionListener packageListener, ActionListener globalListener) {
    	this.packageListener = packageListener;
    	this.globalListener = globalListener;
    	contentPane.setName("RoleSelection Panel");

        initialize();

    }

    /***
     * Add each role button to center of existing BorderLayout.
     */
    private void displayCenter() {
        createRoleButtons();
        for (int count = 0; count < roleButtons.size(); count++) {
        	
            center.add(roleButtons.get(count), "cell 0 "+count+",growx");
        }
    }

    /***
     * Create button for each role.
     */
    private void createRoleButtons() {
        ArrayList<String> availableRoles = scanForAvailableRoles();
        for (int count = 0; count < availableRoles.size(); count++) {
            if (!availableRoles.get(count).equals("Townie")) {
                JButton roleBtn = new MyButton(availableRoles.get(count));
                roleBtn.setName("RoleButton " + (count+1));
                roleBtn.addActionListener(packageListener);
                if (availableRoles.get(count).contains("Mafia")) {
                    roleButtons.add(roleBtn);
                } else {
                    roleButtons.add(0, roleBtn);
                }
            }
        }
    }

    /***
     * Scans the playerInfo directory for available roles to display
     */
    private ArrayList<String> scanForAvailableRoles() {
        ArrayList<String> availableRoles = new ArrayList<>();
        File dir = new File("src/playerInfo");
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains(".java") && !fileName.equals("Player.java")
                    && !fileName.equals("Mafia.java") && !fileName.equals("Town.java")) {

                switch (fileName) {
                    case "Goon.java":
                    case "Hitman.java":
                    case "Barman.java":
                        availableRoles.add("Mafia: " + fileName.substring(0, fileName.length() - 5));
                        break;
                    case "GodFather.java":
                        availableRoles.add("Mafia- " + fileName.substring(0, fileName.length() - 5));
                        break;
                    default:
                        availableRoles.add(fileName.substring(0, fileName.length() - 5));
                        break;
                }

            }
        }
        return availableRoles;
    }

    /***
     * Create labels to be displayed in north of existing BorderLayout.
     */
    private void displayNorth() {
        playersLeft = new MyLabel("", 20);
        JLabel title = new MyLabel("Select possible player roles", 37);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        north.add(title, "cell 0 0,alignx center");

        JLabel centralLabel = new MyLabel("Players remaining: ", 20);
        centralLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Border selectRolesMargin = new EmptyBorder(10, 10, 20, 10);
        centralLabel.setBorder(new CompoundBorder(contentPane.getBorder(), selectRolesMargin));
        north.add(centralLabel, "cell 0 1,split 2, span, center");
        north.add(playersLeft, "wrap");
    }

    /***
     * Initialize contents.
     */
    private void initialize() {

        rolesSelected = new ArrayList<>();
        roleButtons = new ArrayList<>();
        continueButton = new MyButton("Continue ");

        displayNorth();
        displayCenter();
        displaySouth();
        
    }

    /***
     * Create labels to be displayed in south of existing BorderLayout.
     */
    private void displaySouth(){
        assignTownies = new MyButton("Assign the rest as Townie", 15);
        assignTownies.setName("AssignTownies_RoleSelection");
        assignTownies.addActionListener(packageListener);

        JButton resetBtn = new MyButton("Reset", 15);
        resetBtn.setName("Reset_RoleSelection");
        resetBtn.addActionListener(packageListener);
        south.add(resetBtn, "cell 1 0, alignx center");

        south.add(assignTownies, "cell 1 0, alignx right");
        
        recomendedMafia = new MyLabel("Recomended Mafia: ");
        south.add(recomendedMafia,"cell 1 1,alignx center");

        btnHome = new MyButton("Home",buttonFontSize);
        south.add(btnHome,"cell 1 2,alignx left");
        btnHome.addActionListener(globalListener);
        btnHome.setName("Home");

        continueButton = new MyButton("Continue",buttonFontSize);
        south.add(continueButton,"cell 1 2,growx");
        continueButton.setEnabled(false);
        continueButton.setName("Continue_RoleSelectionPanel");
        continueButton.addActionListener(globalListener);
    }

    /***
     * Clears the list of selected roles.
     */
    public void clearRolesSelected() {
        rolesSelected.clear();
    }

    /***
     * @return list of buttons for each role
     */
    ArrayList<JButton> getRoleButtons() {
        return roleButtons;
    }

    /***
     * @return JButton that handles assignment of remaining players as townie
     */
    JButton getAssignTownies() {
        return assignTownies;
    }

    /***
     * @return JLabel that displays the remaining amount of players who has
     * not selected a role.
     */
    JLabel getPlayersLeft() {
        return playersLeft;
    }
    
    JLabel getRecomdedMafia(){
    	return recomendedMafia;
    }

    /***
     * @return a list of type string containing selected roles
     */
    ArrayList<String> getRolesSelected() {
        return new ArrayList<>(rolesSelected);
    }

    /***
     * Adds the specified role to the selected roles list
     * @param role to add
     */
    void addRole(String role) {
        rolesSelected.add(role);
    }

    /***
     * @return continue button in existing panel
     */
    JButton getContinueButton() {
        return continueButton;
    }
}
