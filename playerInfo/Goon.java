package playerInfo;
/**
 * The goon is a mafia member that does not
 * do any actions at night
 * @author Elvin Limpin 30018832
 */

public class Goon extends Mafia {

	/**
	 * Constructor passes values to player while
	 * creating a barman role
	 * @param name
	 * @param position
	 */
	public Goon(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Goon(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
	}
	
	/** 
	 * Copy constructor
	 * @param b
	 */
	public Goon(Goon g){
		super(g);
	}
	
	public Player copy(){
		return new Goon(this);
	}


	@Override
	/**
	 * Unique action of barman
	 * @param p
	 */
	public int doAction(Player p) {
		if(getStatus()!=0 && getInBar()!=1){
			System.out.print("ACTIVE ");
			return 1;//Player in bar
		}
		return 0;//Not in bar
	}
	
	/** returns the role name */
	@Override
	public String getRole() {
		return "Mafia: Goon";
	}

	/** returns the role information for directions */
	@Override
	public String getRoleInfo() {
		return "Do nothing at night";
	}
}