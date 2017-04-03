package playerInfo;
/**
 * The barman is a mafia member that may
 * stop the actions of a player at night.
 * 
 * The barman becomes the hitman once the
 * hitman is dead.
 * @author Elvin Limpin 30018832
 */

public class Barman extends Mafia {

	/**
	 * Constructor passes values to player while
	 * creating a barman role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Barman(String name, int position, Role role) {
		super(name, position, role);
	}
	
	/** 
	 * Copy constructor
	 * @param b
	 */
	public Barman(Barman b){
		super(b);
	}


	@Override
	/**
	 * Unique action of barman
	 * @param p
	 */
	public int doAction(Player p) {
		if(getStatus() != ACTIVE) return p.getStatus();
		
		return STOPPED;
	}
	
	@Override
	public String toString(){
		return "Mafia Barman";
	}
}