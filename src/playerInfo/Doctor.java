package playerInfo;
/**
 * This class extends Town class.
 * The doctor is a town member that may heal
 * any player at night.
 * @author Mahsa Lotfi 10072013
 */

public class Doctor extends Townie{
	
	/**
	 * Constructor passes values to player while
	 * creating a doctor role
	 * @param name, String name
	 * @param position, integer position
	 */
	public Doctor(String name, int position) {
		super(name, position);
		setRole(scan("Doctor"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name, String name
	 * @param position, integer position
	 * @param status, integer status
	 * @param isLynched, boolean isLynched
	 */
	public Doctor(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Doctor"));
	}
	
	/** 
	 * Copy constructor
	 * @param d, Doctor d
	 */
	public Doctor(Doctor d){
		super(d);
	}
	
	/**Copy method*/
	public Player copy(){
		return new Doctor(this);
	}
	
	/**
	 * Unique action of the doctor which is healing 
	 * one player at night.
	 * @param p, Player p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus()!=0 && getInBar()!=1){
			if(p.getStatus() == 2){ 
				return 3;
			}
		} return p.getStatus();
	}
}