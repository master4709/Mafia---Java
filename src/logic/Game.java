package logic;

import java.util.ArrayList;
import java.util.List;

import displayGame.GameController;

/**Game Class
	*This class runs the main logic and loop for the game
	*This class uses the Players class to assign values for the amount of players and their names vis Player Input
	*It then receives a list of all the Players with their assigned role etc.
	*The class then enters the game loop and asks for the user input on the first person to be lynched on Day 1 dayCycle()
	*The game then goes to the nightCycle() which asks for the target of each player every night. Players may die during this phase also
	*The gave is over when the Mafia out number the town members or no Mafia members are left
	*@author Pierce de Jong 30006609
*/

public class Game extends Action{
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
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
	}
	
	
	public void mafiaMembers(){
		for(int i =0;i<playerInfo.size();i++){
			if(playerInfo.get(i).getRole().contains("Mafia")){
				mafiaMembers.add(playerInfo.get(i).getName());
			}
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
				//newHitman(target);
			}
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @param playerTarget
	 */
	public void nightAction(){
		setPlayerInfo(playerInfo);
		nightActions();
		setPlayerInfo(getPlayerInfo());
		resetStatus();
		
	}
	
	
	/**
	 * Resets all of the status for every player
	 */
	private void resetStatus(){
		String name ="";
		boolean dead = false;
		for(int i=0;i<playerInfo.size();i++){
			//Saves the target of that night to the variable OldPlayerTarget for the 
			playerInfo.get(i).setOldPlayerTarget(playerInfo.get(i).getPlayerTarget());
			
			if(playerInfo.get(i).isTargeted()&& !playerInfo.get(i).islynched()){
				name = playerInfo.get(i).getName();
				dead = true;
				playerInfo.get(i).setIsDead(true);
			}
			if(playerInfo.get(i).isHealed()&&playerInfo.get(i).isTargeted()){
				name = playerInfo.get(i).getName();
				dead = false;
				playerInfo.get(i).setIsDead(false);
			}
			if(playerInfo.get(i).isDead()&&playerInfo.get(i).getRole().contains("Hitman")){
				//newHitman(i);
			}
			playerInfo.get(i).setIsTargeted(false);
			playerInfo.get(i).setIsHealed(false);
			playerInfo.get(i).setIsProtected(false);
			playerInfo.get(i).setInBar(false);//Removes any player that may have been in the bar out
			playerInfo.get(i).setPlayerTarget(-1);//Resets the target for each player
		}
		
		if(!name.equals("")){
			GameController.getInstance().switchStoryPanel(name, dead);
		}else{
			GameController.getInstance().switchDayCycle();
		}
	}
	
	private void newHitman(int k){
		for(int i=0;i<playerInfo.size();i++){
			if(playerInfo.get(i).getRole().contains("Barman")){
				Player hitman = playerInfo.get(k);
				playerInfo.get(i).setRole(hitman.getRole());
				playerInfo.get(i).setRoleInfo(hitman.getRoleInfo());
			}
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
	/**
	 * Sets the target of the current player
	 * @param position
	 * @param target
	 */
	public void setPlayerTarget(int position, int target){
		playerInfo.get(position).setPlayerTarget(target);
	}
	/**
	 * Sets the list of Players to the param
	 * @param playerInfo
	 */
	public void setPlayerInfo(List<Player> playerInfo){
		this.playerInfo = playerInfo;
	}
	/**
	 * Returns a COPY of the list of Players
	 * This is used for printing information in the different Panels
	 * @return
	 */
	public List<Player> getPlayerInfo(){
		List<Player> x = playerInfo;
		return x;
	}
	/**
	 * Returns a COPY of the list of Mafia Members
	 * This is used for printing in the NightPanel
	 * @return
	 */
	public List<String> getMafiaMember(){
		List<String> x = mafiaMembers;
		return x;
	}
}