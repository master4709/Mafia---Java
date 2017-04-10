package playerInfo;
/**
 * This class extends Player class.
 * Some players of Town do nothing at night but some do
 * so the doAction method is overridden in those specific classes.
 * @author Mahsa Lotfi 10072013
 *
 */
public class Townie extends Player {

	/**
	 * Used for inheriting different town subclasses
	 * @param name, String
	 * @param position, Integer
	 */
	public Townie(String name, int position) {
		super(name, position);
		setRole(scan("Townie"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String
	 * @param position, integer
	 * @param status, integer
	 * @param isLynched, boolean
	 */
	public Townie(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Townie"));
	}
	
	/** 
	 * Copy constructor
	 * @param toCopy, Townie toCopy
	 */
	public Townie(Townie toCopy){
		super(toCopy);
	}
	
	/**Copy method*/
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
