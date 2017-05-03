package playerInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	private String name=""; //from UserInput
	
	private String role="";
	private String action="";
	private String goal="";

	private int position;
	private int target;  	//position of target. -1 if N/A
	private int status;
	private int inBar; //0 not in bar, 1 in bar
	
	private boolean lynched; 
	
	/** Constants for status */
	
	protected final int DEAD = 0;
	protected final int ALIVE = 1;
	protected final int TARGETED = 2;
	protected final int HEALED = 3;
	protected final int PROTECTED = 4;
	
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
		this.inBar = 0;
		this.status = 1;
		this.lynched = false;
	}
	
	/**
	 * Used for loading a saved game
	 * @param name
	 * @param position
	 * @param status
	 * @param isLynched
	 */
	public Player(String name, int position, int status, boolean lynched){
		this.name = name;
		this.position = position;
		this.target = -1;
		this.inBar = 0;
		this.status = status;
		this.lynched = lynched;		
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
		this.lynched = p.lynched;
		this.role = p.role;
		this.action = p.action;
		this.goal = p.goal;
	}
	
	public abstract Player copy();
	/**
	 * Scans the roles text document to find the name, action and goal of each player
	 * @param roleName
	 * @return List<String> role
	 */
	protected List<String> scan(String roleName){
		String location = "src/resources/roles.txt";
		List<String> role = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(location));){
			 String line;
			    while ((line = br.readLine()) != null) {
			       if(line.contains(roleName)){
			    	   String[] split = line.split("\\|");
			    	   role.add(split[0]);
			    	   role.add(split[1]);
			    	   role.add(split[2]);
			    	   break;
			       }
			    }if (role.size()==0){
			    	System.out.println("Cound not find role: "+roleName+" in file "+location);
			    	role.add(roleName);
			    	role.add("No role found in data/roles.txt");
			    	role.add("No goal found for role");
			    }
			    return role;
		}catch(IOException e){
			System.out.println("Could not find file: "+location);
			System.out.println("Please contacta a system admin about downloading the file again");
			role.add(roleName + " No name found");
	    	role.add("No action found for role");
	    	role.add("No goal found for role");
	    	return role;
		}
	}
	
	protected void setRole(List<String> roleInfo){
		if(roleInfo == null || roleInfo.size()==0){
			role = "No Role";
			action = "No Action";
			goal = "No Goal";
		}else{
			role = roleInfo.get(0);
			action = roleInfo.get(1);
			goal = roleInfo.get(2);
		}
	}
	
	/**
	 * Requires a copy of a player (the target) as param
	 * Abstract method returns the changed or unchanged
	 * position of the target player
	 * @param p
	 * @return new Status of player
	 */
	public abstract int doAction(Player p);
	
	/**
	 * determines whether the player is the 
	 * barman or the hitman
	 * @param p
	 * @return if player is part of mafia
	 */
	public boolean visibleMafia(){
		if(getRole().contains("Mafia:")){
			return true;
		} return false;
	}
	
	public void setTarget(int target){
		this.target = target;
	}
	
	public void setLynched(boolean lynched){
		this.lynched = lynched;
	}
	
	public void setStatus(int status){
		this.status = status;
	}
	
	/** In bar means the player is stopped form their
	 * action by the barman
	 * @param inBar
	 */
	public void setInBar(int inBar){
		this.inBar = inBar;
	}
		
	public String getName(){
		return this.name;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public String getAction(){
		return this.action;
	}
	
	public String getGoal(){
		return this.goal;
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
	
	/** returns whether the player is in the bar or not */
	public int getInBar(){
		return inBar;
	}
	
	/** returns whether the player was lynched */
	public boolean isLynched(){
		return this.lynched;
	}
	
	public String toString(){
		return getRole()+ ": " + getName();
	}
}
