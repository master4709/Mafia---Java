package logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Author: Mahsa Lotfi Gaskarimahalleh, UCID#10072013
 * 
 *This class will stores the number, name and role of each player
*/
public class SetUp extends RoleAssignment {
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	private List<String> names = new ArrayList<>();
	//This int stores the information for the lyncher target	
	private int lynchTargetID;
	
	public SetUp( List<String> names){
		super(names.size());
		this.names = names;
		nameOfPlayers();
		roleOfPlayers();
	}

		
	/**
	 * Asks the user to input the name of each player followed by enter
	 * Sets the play position of each player to the order then names were inputed
	 * Sets the status of each player to be alive. As each player starts out alive in the game
	 * Sets the inBar (The barman has stopped them from doing their action tonight) status to false for each player
	 * @param name 
	 */
	public void nameOfPlayers(){
		for(int i =0; i<names.size(); i++){
			Player p = new Player();
			playerInfo.add(p);
			playerInfo.get(i).setName(names.get(i));
			playerInfo.get(i).setPlayPosition(i);
			playerInfo.get(i).setPlayerTarget(-1);
			playerInfo.get(i).setOldPlayerTarget(-1);
			playerInfo.get(i).setIsDead(false);
			playerInfo.get(i).setIsHealed(false);
			playerInfo.get(i).setIsTargeted(false);
			playerInfo.get(i).setIsProtected(false);
			playerInfo.get(i).setInBar(false);
			playerInfo.get(i).setIsLynched(false);
		}		
	}
	
	
	/**
	 * Creates a new Instance of the RoleAssignment class
	 * Assigns a role, role info and a goal to each player
	 * If the role contains Mafia, sets the isMafia variable to true 
	 */
	public void roleOfPlayers(){
		playerAssignment();
		List<String> roles = getRoles();
		List<String> rolesInfo = getRolesInfo();
		List<String> Goals = getGoals();
		//Loops through all of the players and assigns them a Role, Info and Goal
		for(int i=0; i<names.size(); i++){
			playerInfo.get(i).setRole(roles.get(i));			
			playerInfo.get(i).setRoleInfo(rolesInfo.get(i));
			playerInfo.get(i).setGoal(Goals.get(i));
			
			if(playerInfo.get(i).getRole().contains("Lynch")) {
				lynchTarget();
				playerInfo.get(i).setGoal("Lynch "+ playerInfo.get(lynchTargetID).getName()+ " to win the game");
			}
			
			//If the String role of the player contains the word "Mafia:"
			if(playerInfo.get(i).getRole().contains("Mafia:")){
				//This boolean is for the detective checking if the target is part of the Mafia
				//GodFather is not included, as he is hidden from the detective
				playerInfo.get(i).setIsMafia(true);
			
			}else{
				playerInfo.get(i).setIsMafia(false);
			}		
		}		
	}
			
	// Finds a random target for the lyncher
	public void lynchTarget(){
		List<Player> possibleTargets = playerInfo;
		int i = new Random().nextInt(totalPlayers-1);
		while(possibleTargets.get(i).getRole() == "Lyncher") {
			i = new Random().nextInt(totalPlayers-1);			
		}
		lynchTargetID = i;
	}
	
	//getters	
	public List<Player> getPlayerInfo(){
		return playerInfo;
	}
	
	public int getLynchTarget(){
	return lynchTargetID;
}
}