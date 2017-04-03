package playerInfo;
/**
 * The hitman is a mafia member that may kill any player
 * at night
 * @author Elvin Limpin 30018832
 *
 */
public class Hitman extends Mafia {

	/**
	 * Constructor passes values to player while
	 * creating a hitman role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Hitman(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Copy constructor. Will be used when
	 * a different mafia member becomes the
	 * hitman
	 * @param h
	 */
	public Hitman(Hitman h){
		super(h);
	}

	/**
	 * Unique action for the hitman
	 */
	@Override
	public int doAction(Player p) {
		if(getStatus() == ACTIVE){//not dead or stopped by barman
			return TARGETED;
		}
		return p.getStatus();
	}
	
	@Override
	public String toString(){
		return "Mafia: Hitman";
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
		return "May kill new person each night";
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
