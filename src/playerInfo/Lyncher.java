package playerInfo;
/**
 * Unlike the Mafia class, some Town Players are classified
 * only as town players.
 * @author Mahsa Lotfi 10072013
 *
 */
public class Lyncher extends Townie {

	/**
	 * Used for inheriting different town subclasses
	 * @param name
	 * @param position
	 * @param role
	 */
	public Lyncher(String name, int position) {
		super(name, position);
		setRole(scan("Lyncher"));
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
		setRole(scan("Lyncher"));
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
		return p.getStatus();
	}
}
