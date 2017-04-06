package playerInfo;
/**
 * The detective is a town member that may check
 * the affiliation of any player at night.
 * @author Elvin Limpin 30018832
 */

public class Detective extends Town{

	/**
	 * Constructor passes values to player while
	 * creating a detective role
	 * @param name
	 * @param position
	 * @param role
	 */
	public Detective(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Detective(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/**
	 * Copy constructor
	 * @param d
	 */
	public Detective(Detective d){
		super(d);
	}
	

	/**
	 * Unique action of vigilante
	 * @param p
	 */
	@Override
	public int doAction(Player p){
		if(getStatus() == ACTIVE){
			if(visibleMafia(p)){
				return TEAMMAFIA;
			} return TEAMTOWN;
		} return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Detective";
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
		return "Reveals the team for one player per night";
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