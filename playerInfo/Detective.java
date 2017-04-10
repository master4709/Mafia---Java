package playerInfo;
/**
 * This class extends Town class.
 * The detective is a town member that may check
 * the affiliation of any player at night.
 * @author Mahsa Lotfi 10072013
 */

public class Detective extends Townie{

	/**
	 * Constructor passes values to player while
	 * creating a detective role.
	 * @param name, String
	 * @param position, integer
	 */
	public Detective(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String
	 * @param position, integer
	 * @param status, integer
	 * @param isLynched, boolean
	 */
	public Detective(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/**
	 * Copy constructor
	 * @param d
	 */
	public Detective(Detective d){
		super(d);
	}
	
	public Player copy(){
		return new Detective(this);
	}
	

	/**
	 * Unique action of Doctor
	 * Not done here boiiiiiss
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		return p.getStatus();
	}
	
	
	/** returns the role name */
	@Override
	public String getRole() {
		return "Detective";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Reveals the team for one player per night";
	}

	/** returns the goal of the role */
	@Override
	public String getRoleGoal() {
		return "Lynch all Mafia to win the game";
	}
	
	
}