package logic;
import java.util.*;

/**
 * This class will create randomized list of roles and its assiciated info & goal
 * based on roles user selected.
 *
 * Players will each have a unique role.
 */
public class RoleAssignment extends Debug {

	protected int totalPlayers;

	private List<Role> chosenRoles = new ArrayList<>();
	private List<String> assignments = new ArrayList<>();
	private List<String> assignmentsInfo = new ArrayList<>();
	private List<String> assignmentsGoals = new ArrayList<>();

	/**
	 * Class Constructor Specifies the initial number of total players,
	 * initializes the roles based on total number of players.
	 *
	 * @param numOfPlayers num total players
	 */
	RoleAssignment(int numOfPlayers) {
		totalPlayers = numOfPlayers;
		System.out.println("num of total players: " + totalPlayers);
	}

	/**
	 * Asks user which roles are to be included in the game and generates
	 * a randomized order of these roles which is stored in the private field assignments.
	 *
	 * @see #getChoices()
	 * @see #generateAssignments()
	 */
	protected void playerAssignment(){

		System.out.println("\nAvailable Roles:");
		for (Role role : Role.values()) {
			System.out.print(role.getRoleID() + " | ");
		} System.out.println();

		System.out.print("Enter the roles that you wish to include in the game separated by a comma: ");

		getChoices();
		generateAssignments();

	}

	/**
	 * User must select roles among the list displayed in playerAssignment().
	 * The number of roles entered must equal to number of total players.
	 * Case not sensitive.
	 *
	 */
	private void getChoices() {

		Scanner in = new Scanner(System.in);
		String rolesSelected;
		List<String> choicesList = new ArrayList<>();
		//Townie,Detective,Mafia: Hitman,Doctor,Survivor,Mafia: Barman,Vigilante,Mafia- GodFather,Lyncher,Bodyguard
		boolean needInput = true;
		boolean errorFound;
		String errorMsg = "";

		while (needInput) {
			errorFound = false;
			rolesSelected = in.nextLine();
			choicesList = Arrays.asList(rolesSelected.split("\\s*,\\s*"));

			if (choicesList.size() != totalPlayers) {
				errorFound = true;
				errorMsg = "Num roles u entered != num total players.";
			} else {
				for (String choice : choicesList) {
					if (!isInEnum(choice)) {
						errorFound = true;
						errorMsg = "One or more roles u entered are not in the list.";
					}
				}
			}
			if (!errorFound) {
				needInput = false;
				modifyChosenRoles(choicesList);
			} else {
				System.out.print("\n" + errorMsg + " Try again: ");
			}

		}
		in.close();
		System.out.println("\nYOUR CHOICES ARE: " + choicesList.toString());

	}
	
	/**
	 * Converts String objects in choicesList parameter to its corresponding enum object
	 * and store result in the private field chosenRoles;
	 *
	 * @param choicesList a list of role id chosen by player
	 */
	private void modifyChosenRoles(List<String> choicesList) {

		for (Role role : Role.values()) {

			for (String choice : choicesList) {
				if (role.getRoleID().equalsIgnoreCase(choice)){
					chosenRoles.add(role);
					break;
				}
			}

		}

	}
	
	/**
	 * Checks if the specified string is in one of enum value's roleID.
	 *
	 * @param roleID name of role
	 * @return boolean true if the given role ID is in enum
	 */
	private boolean isInEnum(String roleID) {

		for (Role role : Role.values()) {
			if (role.getRoleID().equalsIgnoreCase(roleID)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * Generates a randomized order of chosenRoles and store corresponding values
	 * in the fields assignments, assignmentsInfo, and assignmentGoals.
	 *
	 */
	private void generateAssignments() {

		int num;
		Random rand = new Random();
		Role select;

		for (int i = 0; i < totalPlayers; i++) {

			num = rand.nextInt(totalPlayers-i);
			select = chosenRoles.get(num);
			chosenRoles.remove(num);
			assignments.add(select.getRoleID());
			assignmentsInfo.add(select.getRoleInfo());
			assignmentsGoals.add(select.getRoleGoal());

		}
		System.out.println("(temp) RANDOMIZED ROLES: " + assignments.toString());

	}

	/**
	 * @return randomized order of specified roles
	 */
	protected List<String> getRoles() {
		return assignments;
	}

	/**
	 * @return list of info about each role in assignments in corresponding order
	 */
	protected List<String> getRolesInfo() {
		return assignmentsInfo;
	}

	/**
	 * @return list of the role's goal in assignments in corresponding order
	 */
	protected List<String> getGoals() {
		return assignmentsGoals;
	}

}