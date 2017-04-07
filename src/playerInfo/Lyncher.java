package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author Elvin Limpin 30018832
 *
 */
public class Lyncher extends Town {

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
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Lyncher(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/**
	 * Copy constructor
	 * @param t
	 */
	public Lyncher(Lyncher l){
		super(l);
	}
	
	public Player copy(){
		return new Lyncher(this);
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
		// TODO Auto-generated method stub
		return "Lyncher";
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

	/** returns the goal of the role */
	@Override
	public String getRoleGoal() {
		// TODO lynch target		
		return "Get them lynched to win the game solo:";
	}
	
}
