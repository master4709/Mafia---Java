package playerInfo;
/**
 * The Godfather is a mafia member that
 * stays hidden from the detective
 * 
 * The Godfather becomes the hitman once the
 * they are the only mafia member one alive.
 * @author Elvin Limpin 30018832
 */

public class GodFather extends Mafia {
	
	/**
	 * Constructor passes values to player while
	 * creating a Godfather role
	 * @param name
	 * @param position
	 */
	public GodFather(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public GodFather(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/** 
	 * Copy constructor
	 * @param b
	 */
	public GodFather(GodFather g){
		super(g);
	}
	
	public Player copy(){
		return new GodFather(this);
	}

	@Override
	/**
	 * Unique action of the godfather
	 * @param p
	 */
	public int doAction(Player p) {
		return p.getStatus();
	}
	
	/** returns the role name */
	@Override
	public String getRole() {
		return "Mafia- GodFather";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Hidden from the Detective";
	}
}