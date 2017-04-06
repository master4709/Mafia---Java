package playerInfo;
/**
 * The Godfather is a mafia member that
 * stays hidden from the detective
 * 
 * The Godfather becomes the hitman once the
 * they are the only mafia member one alive.
 * @author Elvin Limpin 30018832
 */

public class Godfather extends Mafia {
	
	/**
	 * Constructor passes values to player while
	 * creating a Godfather role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Godfather(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Godfather(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/** 
	 * Copy constructor
	 * @param b
	 */
	public Godfather(Godfather g){
		super(g);
	}


	@Override
	/**
	 * Unique action of the godfather
	 * @param p
	 */
	public int doAction(Player p) {
		return p.getStatus();
	}
	
	@Override
	public String toString(){
		return getRole()+ " " + getName();
	}
	
	/**
	 * returns the role name
	 */
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Mafia- GodFather";
	}

	/**
	 * returns the role information
	 * for directions
	 */
	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "Hidden from the Detective";
	}

	/**
	 * returns the goal of the role
	 */
	@Override
	public String getRoleGoal() {
		// TODO Auto-generated method stub
		return "To make the majority of the town mafia members";
	}
}
