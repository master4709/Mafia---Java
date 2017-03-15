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
*/

public class Game{
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
	//Index value for the target of the Lyncher
	private int lynchTarget;
	
	public Game(List<Player> playerInfo, List<String> mafiaMembers, int lynchTarget){
		this.lynchTarget = lynchTarget;
		this.playerInfo = playerInfo;
		this.mafiaMembers = mafiaMembers;
	}
	
	public Game(){	
	}
	
	/**
	 * This method will kill one player each day, depending on who the players vote out.
	 */
	public void dayCycle(int target){
		if(target!=-1){
			System.out.println(playerInfo.get(target).getName()+" has been lynched");
			playerInfo.get(target).setIsDead(true);//Sets the target of the lynching to dead, So they can not be used or targeted again
			playerInfo.get(target).setIsLynched(true);
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @param playerTarget
	 */
	public void nightAction(){
		
		//Action a = new Action(playerInfo);
		//setPlayerInfo(a.getPlayerInfo());
		resetStatus();
	}
	
	
	/**
	 * This method resets the status of all players to 0 (alive)
	 * If the player has a status of 1 (targeted by mafia/vigilante) a story is printed displaying how they died and their status is set to 4
	 * If the status of the player is 4 ( dead) will skip them as both a player an a target for each player
	 */
	private void resetStatus(){
		for(int i=0;i<playerInfo.size();i++){
			//Saves the target of that night to the variable OldPlayerTarget for the 
			playerInfo.get(i).setOldPlayerTarget(playerInfo.get(i).getPlayerTarget());
			
			if(playerInfo.get(i).isDead()){
				printEvent(i,"dead");
			}
			if(playerInfo.get(i).isHealed()&&playerInfo.get(i).isTargeted()){
				printEvent(i,"alive");
			}
			
			playerInfo.get(i).setIsTargeted(false);
			playerInfo.get(i).setIsHealed(false);
			playerInfo.get(i).setIsProtected(false);
			playerInfo.get(i).setInBar(false);//Removes any player that may have been in the bar out
			playerInfo.get(i).setPlayerTarget(-1);//Resets the target for each player
		}
	}
	/**
	 * This method prints the death message of any player that may have died during the night
	 * Then prints that they were either killed by the attacker or saved by the doctor
	 * @param player, status
	 */
	private void printEvent(int player, String status){
		String name = playerInfo.get(player).getName();
		Story s = new Story(name);
		s.information();
		s.initialScenario();
		if(status.equals("dead")){
			s.dead();
		}else if (status.equals("alive")){
			s.healed();
		}
	}
	
	
	public void setPlayerInfo(List<Player> playerInfo){
		this.playerInfo = playerInfo;
	}

	public List<Player> getPlayerInfo(){
		return playerInfo;
	}
	
	public List<String> getMafiaMember(){
		return mafiaMembers;
	}
	
	
}