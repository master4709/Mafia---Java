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
	public Godfather(String name, int position, Role role) {
		super(name, position, role);
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
		return "The Godfather";
	}
}
