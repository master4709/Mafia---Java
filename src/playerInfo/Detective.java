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
		setRole(scan("Detective"));
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
		setRole(scan("Detective"));
	}
	
	/**
	 * Copy constructor
	 * @param d, Detective d
	 */
	public Detective(Detective d){
		super(d);
	}
	
	/**Copy method*/
	public Player copy(){
		return new Detective(this);
	}
	

	/**
	 * Unique action of Detective which is checking 
	 * one player's affiliation at night.
	 * @param p, Player p
	 */
	@Override
	public int doAction(Player p){
		return p.getStatus();
	}
}