package logic;
import java.util.*;

/**
 * This class will a randomized list of roles based on roles user selected.
 *
 * Each player will have a unique role.
 */
public class RoleAssignment {

	private int totalPlayers;

	private List<String> assignments = new ArrayList<>();
	private List<String> assignmentsInfo = new ArrayList<>();
	private List<String> assignmentsGoals = new ArrayList<>();

	/**
	 * Class Constructor Specifies the initial number of total players,
	 * initializes the roles based on total number of players.
	 *
	 * @param numOfPlayers
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
	 * @see #generateAssignments(List)
	 */
	void playerAssignment(){

		System.out.println("Available Roles:\n");
		for (Role role : Role.values()) {
			System.out.print(role + " | ");
		} System.out.println();

		System.out.print("Enter the roles that you wish to include in the game separated by a comma: ");
		
		List<String> chosenRoles = getChoices();
		generateAssignments(chosenRoles);

	}

	/**
	 * User must select roles among the list displayed in playerAssignment().
	 * The number of roles entered must equal to number of total players.
	 * Case not sensitive.
	 *
	 * ***no input validation implemented. type ur crap carefully***
	 *
	 * @return a list of roles specified by the user
	 */
	private List<String> getChoices() {

		Scanner in = new Scanner("TOWNIE,MAFIA_HITMAN,DOCTOR,SURVIVOR,DETECTIVE,MAFIA_BARMAN,BODYGUARD,LYNCHER,MAFIABOSS_GODFATHER,VIGILANTE");
		String rolesSelected = in.nextLine();
		in.close();
		List<String> choicesList = new ArrayList<>(Arrays.asList(rolesSelected.split("\\s*,\\s*")));
		System.out.println("\nYOUR CHOICES ARE: " + choicesList.toString());

		return choicesList;

	}

	/**
	 * Generates a randomized order of the roles parameter and store in assignments.
	 *
	 * @param roles a list of roles specified by the user
	 */
	private void generateAssignments(List<String> roles) {

		int num;
		Random rand = new Random();
		String select;

		for (int i = 0; i < totalPlayers; i++) {
			num = rand.nextInt(totalPlayers-i);
			select = roles.get(num);
			roles.remove(num);

			for (Role role : Role.values()) {
				if (select.equalsIgnoreCase(role.name())) {
					assignments.add(role.getRoleName());
				}
			}

		}

		// TEMPORARY
		System.out.println("(temp) RANDOMIZED ROLES: " + assignments.toString());

	}

	/**
	 * @return randomized order of specified roles
	 */
	List<String> getRoles() {
		return assignments;
	}

	/**
	 * @return list of info about each role in assignments in corresponding order
	 */
	List<String> getRolesInfo() {

		for (int count = 0; count < assignments.size(); count++) {
			for (Role role : Role.values()) {
				if (assignments.get(count).equals(role.getRoleName())) {
					assignmentsInfo.add(role.getRoleInfo());
				}
			}
		}
		return assignmentsInfo;
	}

	/**
	 * @return list of the role's goal in assignments in corresponding order
	 */
	List<String> getGoals() {

		for (int count = 0; count < assignments.size(); count++) {
			for (Role role : Role.values()) {
				if (assignments.get(count).equals(role.getRoleName())) {
					assignmentsGoals.add(role.getRoleGoal());
				}
			}
		}
		return assignmentsGoals;
	}

}