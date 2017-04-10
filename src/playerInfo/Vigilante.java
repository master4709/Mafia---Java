package playerInfo;
/**
 * The vigilante is a town member that may kill
 * Any player at night.
 * @author Mahsa Lotfi 10072013
 */


public class Vigilante extends Townie{

	/**
	 * Constructor passes values to player while
	 * creating a vigilante role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Vigilante(String name, int position) {
		super(name, position);
		setRole(scan("Vigilante"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Vigilante(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Vigilante"));
	}
	
	/**
	 * Copy constructor
	 * @param v
	 */
	public Vigilante(Vigilante v){
		super(v);
	}
	
	public Player copy(){
		return new Vigilante(this);
	}
	
	/**
	 * Unique action of vigilante
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus()!=0 && getInBar()!=1){
			return 2;
		}
		return p.getStatus();
	}	
}