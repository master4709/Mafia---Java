package playerInfo;
/**
 * This class extends Player class.
 * The vigilante is a town member that may kill
 * Any player at night.
 * @author Mahsa Lotfi 10072013
 */


public class Vigilante extends Townie{

	/**
	 * Constructor passes values to player while
	 * creating a vigilante role
	 * @param name, String
	 * @param position, integer
	 */
	public Vigilante(String name, int position) {
		super(name, position);
		setRole(scan("Vigilante"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String
	 * @param position, integer
	 * @param status, integer
	 * @param isLynched, boolean
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
	
	/**Copy method*/
	public Player copy(){
		return new Vigilante(this);
	}
	
	/**
	 * Unique action of vigilante which is killing
	 * one player at night. 
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus()!=0 && getInBar()!=1){
			System.out.print("ACTIVE: ");return 2;
		} System.out.print("FAIL: ");return p.getStatus();
	}	
}