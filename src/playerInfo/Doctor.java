package playerInfo;
/**
 * The doctor is a town member that may heal
 * any player at night.
 * @author Elvin Limpin 30018832
 */

public class Doctor extends Town{
	
	/**
	 * Constructor passes values to player while
	 * creating a doctor role
	 * @param name
	 * @param position
	 */
	public Doctor(String name, int position, Role role) {
		super(name, position, role);
	}
	
	/** 
	 * Copy constructor
	 * @param d
	 */
	public Doctor(Doctor d){
		super(d);
	}
	
	/**
	 * Unique action of the doctor
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus() != ACTIVE){
			if(p.getStatus() == TARGETED){ 
				return HEALED;
			}
		} return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Town Doctor";
	}	
}