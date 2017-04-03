package playerInfo;
/**
 * The bodyguard is a town member that may check
 * protect a player at night by dying in their place
 * @author Elvin Limpin 30018832
 */

public class Bodyguard extends Town{

	/**
	 * Constructor passes values to player while
	 * creating a doctor role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Bodyguard(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Copy constructor
	 * @param b
	 */
	public Bodyguard(Bodyguard b){
		super(b);
	}
	

	/**
	 * Unique action of the bodyguard
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus() == ACTIVE){
			return PROTECTED;
		} return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Mafia Bodguard";
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
		return "May save another person by dying in their place";
	}

	/**
	 * returns the goal of the role
	 */
	@Override
	public String getRoleGoal() {
		// TODO Auto-generated method stub
		return "Lynch all Mafia to win the game";
	}
}