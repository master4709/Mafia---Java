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
	private List<Role> randomizedRoles = new ArrayList<>();

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
	 * @see #randomizeRoles()
	 */
	protected void playerAssignment(List<String> chosenRolesString){

		for (String role : chosenRolesString) {
			this.chosenRoles.add(findRoleByID(role));
		}
		randomizeRoles();

	}

	private Role findRoleByID(String roleID) {

		for (Role role : Role.values()) {
			if (role.getRoleID().equals(roleID)) {
				return role;
			}
		}
		return null;

	}

	/**
	 * Generates a randomized order of chosenRoles and store corresponding values
	 * in the fields assignments, assignmentsInfo, and assignmentGoals.
	 *
	 */
	private void randomizeRoles() {

		int num;
		Random rand = new Random();
		Role select;

		for (int i = 0; i < totalPlayers; i++) {

			num = rand.nextInt(totalPlayers-i);
			select = chosenRoles.get(num);
			chosenRoles.remove(num);
			randomizedRoles.add(select);

		}

	}

	protected List<Role> getRandomizedRoles() {
		return randomizedRoles;
	}

}