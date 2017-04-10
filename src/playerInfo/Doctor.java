package playerInfo;
/**
 * The doctor is a town member that may heal
 * any player at night.
 * @author Mahsa Lotfi 10072013
 */

public class Doctor extends Townie{
	
	/**
	 * Constructor passes values to player while
	 * creating a doctor role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Doctor(String name, int position) {
		super(name, position);
		setRole(scan("Doctor"));
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Doctor(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
		setRole(scan("Doctor"));
	}
	
	/** 
	 * Copy constructor
	 * @param d
	 */
	public Doctor(Doctor d){
		super(d);
	}
	
	public Player copy(){
		return new Doctor(this);
	}
	
	/**
	 * Unique action of the doctor
	 * @param p
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