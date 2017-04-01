package PlayerInfo;
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
	 */
	public Bodyguard(String name, int position) {
		super(name, position, new Role(){{
			this.setRoleName("Bodyguard");
		}});
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
			if(p.getStatus() == DEAD){
				sacrifice(); // :(
				return PROTECTED;
			}
		} return p.getStatus();
	}
	
	@Override
	public String toString() {
		return "Mafia Bodguard";
	}
}