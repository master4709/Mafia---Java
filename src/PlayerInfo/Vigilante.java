package PlayerInfo;
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
	 */
	public Vigilante(String name, int position) {
		super(name, position, new Role(){{
			this.setRoleName("vigilante");
			//
			}});
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
			return DEAD;
		}
		return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Town Vigilante";
	}
	
}
