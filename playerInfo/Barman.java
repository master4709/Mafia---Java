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
	 */
	public Barman(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Barman(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/** 
	 * Copy constructor
	 * @param b
	 */
	public Barman(Barman b){
		super(b);
	}
	
	public Player copy(){
		return new Barman(this);
	}


	@Override
	/**
	 * Unique action of barman
	 * @param p
	 */
	public int doAction(Player p) {
		if(getStatus()!=0 && getInBar()!=1){
			return 1;//Player in bar
		}
		return 0;//Not in bar
	}
	
	/** returns the role name */
	@Override
	public String getRole() {
		return "Mafia: Barman";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Stop the action of another player every night";
	}
}