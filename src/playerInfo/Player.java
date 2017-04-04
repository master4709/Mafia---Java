package playerInfo;

/**
 * Inherited by different roles as a framework to store
 * attributes and a unique action
 * 
 * Each player is assigned a unique position:
 * -Position: index entry in List<Player> in Game
 * -Status: Possible states player may have
 * 
 * For the algorithms to work, the following night
 * players must be active players must be called
 * in order:
 * 1) Barman - Stops action
 * 2) Bodyguard - sacrifices self if target is not
 *			 	  healed
 * 3) Vigilante & Hitman - kills
 * 5) Doctor - heals
 * 
 * @author Elvin Limpin 30018832
 *
 */

public abstract class Player {
	
	private String name; //from UserInput

	private int position;
	private int target;  	//position of target. -1 if N/A
	private int status;
	
	private boolean isLynched; 
	
	/**
	 * Constants for status
	 */
	protected final int DEAD = 0;
	protected final int ALIVE = 1;
	protected final int TARGETED = 2;
	protected final int HEALED = 3;
	protected final int PROTECTED = 4;
	protected final int STOPPED = 5;
	
	/**
	 * Used by the detective to determine affiliation
	 */
	protected final int TEAMTOWN = 6;
	
	/**
	 * Used by the detective to determine affiliation
	 */
	protected final int TEAMMAFIA  = 7;
	
	/**
	 * on default, protected, or healed
	 */
	protected final int ACTIVE = 1 | 2 | 3 | 4;
	
	/**
	 * Initializes default values for Player attributes
	 * @param name
	 * @param position
	 * @param role
	 */
	public Player(String name, int position){
		this.name = name;
		this.position = position;
		this.target = -1;
		this.status = 0;
		this.isLynched = false;
	}
	
	/**
	 * Creates a clone to prevent privacy leaks
	 * Used for doAction() and mafia players who 
	 * switch roles
	 * @param p
	 */
	public Player(Player p){
		this.name = p.name;
		this.position = p.position;
		this.target = p.target;
		this.status = p.status;
		this.isLynched = p.isLynched;
	}
	
	/**
	 * determines whether the player is the 
	 * barman or the hitman
	 * @param p
	 * @return if player is part of mafia
	 */
	protected boolean visibleMafia(Player p){
		if(p.getRole().contains("Mafia:")){
			return true;
		} return false;
	}
	
	/**
	 * Requires a copy of a player (the target) as param
	 * Abstract method returns the changed or unchanged
	 * position of the target player
	 * @param p
	 * @return new Status of player
	 */
	
	public abstract int doAction(Player p);
	
	public abstract String getRole();
	public abstract String getRoleInfo();
	public abstract String getRoleGoal();
	
	public void setTarget(int target){
		this.target = target;
	}
	
	public void setLynched(boolean lynched){
		this.isLynched = lynched;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
		
	public String getName(){
		return this.name;
	}
	
	public int getPosition(){
		return this.position;
	}
	
	public int getTarget(){
		return this.target;
	}
	
	public int getStatus(){
		return this.status;
	}
	
	public boolean wasLynched(){
		return this.isLynched;
	}
}
