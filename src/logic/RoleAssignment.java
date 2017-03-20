package logic;
import java.util.*;

/**
 *
 * @author Christilyn Arjona, 30033435
 */
public class RoleAssignment {

	int totalPlayers;

	private List<Role> chosenRoles = new ArrayList<>();

	/**
	 * Class Constructor Specifies the initial number of total players,
	 *
	 * @param numOfPlayers num total players
	 */
	RoleAssignment(int numOfPlayers) {
		totalPlayers = numOfPlayers;
	}

	/***
	 * Converts a specified list of type String to a list of their corresponding enum objects.
	 * Store result in chosenRoles list
	 * @param chosenRolesString A list of type String for the selected roles
	 */
	void playerAssignment(List<String> chosenRolesString){

		for (String role : chosenRolesString) {
			this.chosenRoles.add(findRoleByID(role));
		}

	}

	/***
	 * Converts a specified String of roleID to its corresponding enum object
	 * @param roleID roleID
	 * @return Role object
	 */
	private Role findRoleByID(String roleID) {

		for (Role role : Role.values()) {
			if (role.getRoleID().equals(roleID)) {
				return role;
			}
		}
		return null;

	}

	/***
	 * @return A List of type Role
	 */
	List<Role> getChosenRoles() {
		return chosenRoles;
	}

}