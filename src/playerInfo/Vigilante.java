package playerInfo;
/**
 * The vigilante is a town member that may kill
 * Any player at night.
 * @author Mahsa Lotfi 10072013
 */


public class Vigilante extends Town{

	/**
	 * Constructor passes values to player while
	 * creating a vigilante role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Vigilante(String name, int position) {
		super(name, position);
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
	
	
	/** returns the role name */
	@Override
	public String getRole() {
		return "Vigilante";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "May kill new person each night";
	}

	/** returns the goal of the role */
	@Override
	public String getRoleGoal() {
		return "Lynch all Mafia to win the game";
	}	
}