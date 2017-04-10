package playerInfo;
/**
 * This class extends Town class.
 * The bodyguard is a town member that may protect 
 * a player at night by dying in their place.
 * @author Mahsa Lotfi 10072013
 */

public class Bodyguard extends Townie{

	/**
	 * Constructor passes values to player while
	 * creating a doctor role
	 * @param name, String name
	 * @param position, integer position
	 */
	public Bodyguard(String name, int position) {
		super(name, position);
		setRole(scan("Bodyguard"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String 
	 * @param position, integer
	 * @param status, integer
	 * @param isLynched, boolean
	 */
	public Bodyguard(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Bodyguard"));
	}
	
	/**
	 * Copy constructor
	 * @param b, Bodyguard 
	 */
	public Bodyguard(Bodyguard b){
		super(b);
	}
	
	/**Copy method*/
	public Player copy(){
		return new Bodyguard(this);
	}

	/**
	 * Unique action of the bodyguard which is protecting
	 * one player at night.
	 * @param p, Player
	 */
	@Override
	public int doAction(Player p){
		if(getStatus()!=0 && getInBar()!=1){
			System.out.print("ACTIVE: ");
			return 4;
		}
		System.out.print("FAILED: ");
		return p.getStatus();
	}
}