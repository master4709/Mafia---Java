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
	 */
	public Hitman(String name, int position) {
		super(name, position);
	}
	
	/**
	 * Used for continuing saved games
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Hitman(String name, int position, int status, boolean isLynched){
		super(name, position, status, isLynched);
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
	
	@Override
	public Player copy(){
		return new Hitman(this);
	}

	/** Unique action for the hitman */
	@Override
	public int doAction(Player p) {
		if(getStatus()!=0 && getInBar()!=1){//not dead or stopped by barman and the player has not been protected by the Bodyguard
			System.out.print("ACTIVE: ");
			return TARGETED;
		}
		return p.getStatus();
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Mafia: Hitman";
	}
	@Override
	public String getRoleInfo() {
		// TODO Auto-generated method stub
		return "Kill a different player each night";
	}
}
