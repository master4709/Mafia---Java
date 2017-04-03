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
	public Barman(String name, int position) {
		super(name, position);
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
		if(getStatus() == ACTIVE){
			return STOPPED;
		} return p.getStatus();
	}
	
	@Override
	public String toString(){
		return "Mafia: Barman";
	}
	
	/**
	 * returns the role name
	 */
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return this.toString();
	}

	/**
	 * returns the role information
	 * for directions
	 */
	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "May stop the action of another player each night";
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