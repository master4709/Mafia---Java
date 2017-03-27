package logic;

import java.util.ArrayList;
import java.util.List;

/**Game Class
	*This class runs the main logic and loop for the game
	*This class uses the Players class to assign values for the amount of players and their names vis Player Input
	*It then receives a list of all the Players with their assigned role etc.
	*The class then enters the game loop and asks for the user input on the first person to be lynched on Day 1 dayCycle()
	*The game then goes to the nightCycle() which asks for the target of each player every night. Players may die during this phase also
	*The gave is over when the Mafia out number the town members or no Mafia members are left
	*@author Pierce de Jong 30006609
	*
*/

public class Game extends Action{
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
	private List<String> names = new ArrayList<>();
	
	//Index value for the target of the Lyncher
	private int lynchTarget;
	/**
	 * Constructor
	 * Takes params values and stores them into local versions
	 *
	 * @param playerInfo
	 * @param mafiaMembers
	 * @param lynchTarget
	 */
	public Game(List<Player> playerInfo, int lynchTarget){
		this.lynchTarget = lynchTarget;
		this.playerInfo = playerInfo;
		mafiaMembers();
		playerNames();
	}
	
	/**
	 * Creates a list of all of the Mafia Members
	 */
	private void mafiaMembers(){
		for(int i =0;i<playerInfo.size();i++){
			System.out.println(getPlayer(i).getRole());
			if(getPlayer(i).getRole().contains("Mafia")){
				mafiaMembers.add(getPlayer(i).getName());
			}
		}
	}
	/**
	 * Creates a list of all of the player names
	 */
	private void playerNames(){
		for(Player player: playerInfo){
			names.add(player.getName());
		}
	}
	
	/**
	 * This method will kill one player each day, depending on who the players vote out.
	 */
	public void dayCycle(int target){
		if(target!=-1){
			System.out.println(playerInfo.get(target).getName()+" has been lynched");
			playerInfo.get(target).setIsDead(true);//Sets the target of the lynching to dead, So they can not be used or targeted again
			playerInfo.get(target).setIsLynched(true);
			if(playerInfo.get(target).getRole().contains("Hitman")){
				newHitman(target);
			}
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @param playerTarget
	 */
	public Integer nightAction(){
		System.out.println("Do night logic");
		setPlayerInfoAction(playerInfo);
		nightActionLoop();
		setPlayerInfo(getPlayerInfo());
		int target = resetStatus();
		return target;
	}
	
	
	/**
	 * Resets all of the status for every player
	 * Starts the loop through players at the last position 
	 */
	private Integer resetStatus(){
		int target = -1;
		for(int i=0;i<playerInfo.size();i++){
			//If the player has been targeted and was NOT healed
			if(getPlayer(i).isTargeted() && !getPlayer(i).isHealed()){
				//Call the story panel with a death story
				System.out.println(getPlayer(i).getName()+" is dead");
				getPlayer(i).setIsDead(true);
				resetPlayer(i);
				target = i;
				//If the hitman is dead, changes the bar man to the new hitman
				if(getPlayer(i).getRole().contains("Hitman")){
					newHitman(i);
				}
			}else if(getPlayer(i).isTargeted()&& getPlayer(i).isHealed()){
				//Call the story panel with a survived story
				System.out.println(getPlayer(i).getName()+" is saved");
				resetPlayer(i);
				getPlayer(i).setIsHealed(true);
				target = i;
			}else{
				resetPlayer(i);
			}
			
		}
		return target;
	}
	/**
	 * Resets all of the status for the player
	 * @param i
	 */
	private void resetPlayer(int i){
		getPlayer(i).setIsTargeted(false);
		getPlayer(i).setIsHealed(false);
		getPlayer(i).setIsProtected(false);
		getPlayer(i).setInBar(false);//Removes any player that may have been in the bar out
		getPlayer(i).setPlayerTarget(-1);//Resets the target for each player
	}
	/**
	 * If the hitman has been killed, the barman will become the new hitman
	 * @param k - index value of the location of hitman
	 */
	private void newHitman(int k){
		//Loop through the list of players
		for(int i=0;i<playerInfo.size();i++){
			//Finds the barman
			if(getPlayer(i).getRole().contains("Barman")){
				//Sets the Barman to the role and role inforamtion of the hitman
				Player hitman = playerInfo.get(k);
				getPlayer(i).setRole(hitman.getRole());
				getPlayer(i).setRoleInfo(hitman.getRoleInfo());
			}
		}
		//Sets the dead Mafia Hitman's role to dead
		playerInfo.get(k).setRole("Dead Mafia");
	}

	/**
	 * Sets the target of the current player
	 * @param position
	 * @param target
	 */
	public void setPlayerTarget(int position, int target){
		playerInfo.get(position).setPlayerTarget(target);
	}
	/**
	 * Sets the boolean healed for the player
	 * @param i, b
	 */
	public void setHealed(int i, boolean b){
		getPlayer(i).setIsHealed(b);
	}
	/**
	 * Sets the list of Players to the param
	 * @param playerInfo
	 */
	public void setPlayerInfo(List<Player> playerInfo){
		this.playerInfo = playerInfo;
	}
	/**
	 * Returns a list of Mafia Members
	 * This is used for printing in the NightPanel
	 * @return x (List of Mafia Members)
	 */
	public List<String> getMafiaMember(){
		List<String> clone = new ArrayList<String>(mafiaMembers);
		return clone;
	}
	/**
	 * Returns a list of players
	 * @return x (List of Players)
	 */
	public List<Player> getPlayerInfo(){
		List<Player> clone = new ArrayList<Player>(playerInfo);
		return clone;
	}
	/**
	 * Returns a player
	 * @param i
	 * @return player
	 */
	private Player getPlayer(int i){
		Player player = playerInfo.get(i);
		return player;
	}
	
	public Player getPlayerCopy(int i){
		Player player = new Player(playerInfo.get(i));
		return player;
	}
	
	public List<String> getPlayerNames(){
		return names;
	}
}