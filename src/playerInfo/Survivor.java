package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author Elvin Limpin
 *
 */
public class Survivor extends Town {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Survivor(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Survivor(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/**
	 * Copy constructor
	 * @param t
	 */
	public Survivor(Survivor s){
		super(s);
	}
	
	public Player copy(){
		return new Survivor(this);
	}

	/** The lyncher does not do anything at night */
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
		return "Survivor";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Do nothing at night";
	}

	/** returns the goal of the role */
	@Override
	public String getRoleGoal() {
		return "Be the last one alive to win the game solo";
	}
	
}
