package playerInfo;
/**
 * The Mafia is an abstract class that
 * signifies a mafia affiliation of a player
 * 
 * Unlike the Town class, Mafia is abstract
 * @author Elvin Limpin 30018832
 *
 */
public class Mafia extends Player {

	/**
	 * Constructor passing values from different mafia
	 * classes to the Player class
	 * @param name
	 * @param position
	 * @param role
	 */
	public Mafia(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Mafia(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	public Mafia(Mafia toCopy){
		super(toCopy);
	}
	
	/**
	 * Copy method
	 * @param m
	 */
	public Player copy(){
		return new Mafia(this);
	}

	@Override
	public int doAction(Player p) {
		// TODO Auto-generated method stub
		return p.getStatus();
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Mafia: Something";
	}

	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "Do nothing at night";
	}

	@Override
	public String getRoleGoal() {
		// TODO Auto-generated method stub
		return "To make the majority of the town Mafia members";
	}
}
