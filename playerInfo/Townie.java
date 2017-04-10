package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author Mahsa Lotfi 10072013
 *
 */
public class Townie extends Player {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Townie(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Townie(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Townie"));
	}
	
	public Townie(Townie toCopy){
		super(toCopy);
		setRole(scan("Townie"));
	}
	
	public Player copy(){
		return new Townie(this);
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
	
}
