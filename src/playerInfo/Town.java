package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author Elvin Limpin 30018832
 *
 */
public class Town extends Player {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Town(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Town(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	public Town(Town toCopy){
		super(toCopy);
	}
	
	public Player copy(){
		return new Town(this);
	}

	/**
	 * By default, Town Players do not have any night actions.
	 * This might be overriden by subclasses of Town
	 */
	@Override
	public int doAction(Player p) {
		// These players do nothing
		return p.getStatus();
	}
	
	public String toString() {
		return getRole()+ " " + getName();
	}

	/** returns the role name */
	@Override
	public String getRole() {
		return "Townie";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Do nothing at night";
	}

	/** returns the goal of the role */
	@Override
	public String getRoleGoal() {
		return "Lynch all Mafia to win the game";
	}
	
}
