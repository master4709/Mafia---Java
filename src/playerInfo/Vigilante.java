package playerInfo;
/**
 * The vigilante is a town member that may kill
 * Any player at night.
 * @author Elvin Limpin 30018832
 */


public class Vigilante extends Town{

	/**
	 * Constructor passes values to player while
	 * creating a vigilante role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Vigilante(String name, int position, Role role) {
		super(name, position, role);
	}
	
	/**
	 * Copy constructor
	 * @param v
	 */
	public Vigilante(Vigilante v){
		super(v);
	}
	
	/**
	 * Unique action of vigilante
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus() == ACTIVE){
			return TARGETED;
		}
		return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Town Vigilante";
	}
	
}
