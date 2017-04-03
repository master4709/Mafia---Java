package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author nestr
 *
 */
public class Lyncher extends Player {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Lyncher(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Copy constructor
	 * @param t
	 */
	public Lyncher(Lyncher l){
		super(l);
	}

	/**
	 * The lyncher does not do anything at night
	 */
	@Override
	public int doAction(Player p) {
		// These players do nothing
		return p.getStatus();
	}
	
	public String toString() {
		return "Lyncher";
	}

	/**
	 * returns the role name
	 */
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	/**
	 * returns the role information
	 * for directions
	 */
	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "Do nothing at night";
	}

	/**
	 * returns the goal of the role
	 */
	@Override
	public String getRoleGoal() {
		// TODO lynch target
		return "Lynch "+ getLynchTarget() + " to win the game solo";
	}
	
}
