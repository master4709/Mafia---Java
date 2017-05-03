package displaySetUp;

import myJStuff.*;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;

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
        List<String> availableRoles = scanForAvailableRoles();
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
    private List<String> scanForAvailableRoles() {
        List<String> availableRoles = new ArrayList<>();
        Scanner fileScanner;
		try {
			fileScanner = new Scanner( new File("src/resources/roles.txt"));
			while(fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] split = line.split("\\|");
            availableRoles.add(split[0]);
        }
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file: data/roles.txt");
			System.out.println("Please contacta a system admin about downloading the file again");
			availableRoles.add("No data/roles.txt");
		}
        System.out.println(availableRoles);
        return availableRoles;
    }

    /***
     * Create labels to be displayed in north of existing BorderLayout.
     */
    private void displayNorth() {
        playersLeft = new MyLabel("", 40);
        JLabel title = new MyLabel("Select possible player roles", 37);
        north.add(title, "cell 0 0,alignx center");

        JLabel centralLabel = new MyLabel("Players remaining: ", 30);
        north.add(centralLabel, "cell 0 1,alignx center");
        north.add(playersLeft, "cell 0 1,alignx center");
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
        south.add(resetBtn, "cell 1 2, alignx center");

        south.add(assignTownies, "cell 1 2, alignx right");
        
        recomendedMafia = new MyLabel("Recomended Mafia: ",20);
        south.add(recomendedMafia,"cell 1 0,alignx center");
        
        JLabel recommended = new MyLabel("Docotor and Mafia: Hitman are HIGHLY recommended in every game",13);
        south.add(recommended,"cell 1 1,alignx center");

        btnHome = new MyButton("Home",buttonFontSize);
        south.add(btnHome,"cell 1 3,alignx left");
        btnHome.addActionListener(globalListener);
        btnHome.setName("Home");

        continueButton = new MyButton("Continue",buttonFontSize);
        south.add(continueButton,"cell 1 3,growx");
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
    public ArrayList<JButton> getRoleButtons() {
        return roleButtons;
    }

    /***
     * @return JButton that handles assignment of remaining players as townie
     */
    public JButton getAssignTownies() {
        return assignTownies;
    }

    /***
     * @return JLabel that displays the remaining amount of players who has
     * not selected a role.
     */
    public JLabel getPlayersLeft() {
        return playersLeft;
    }
    
    public JLabel getRecomdedMafia(){
    	return recomendedMafia;
    }

    /***
     * @return a list of type string containing selected roles
     */
    public ArrayList<String> getRolesSelected() {
        return new ArrayList<>(rolesSelected);
    }

    /***
     * Adds the specified role to the selected roles list
     * @param role to add
     */
    public void addRole(String role) {
        rolesSelected.add(role);
    }

    /***
     * @return continue button in existing panel
     */
    public JButton getContinueButton() {
        return continueButton;
    }
}
