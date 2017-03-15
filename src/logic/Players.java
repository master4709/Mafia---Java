package logic;
/**
 * Author: Mahsa Lotfi Gaskarimahalleh, UCID#10072013
 */

/**
*This class will stores the number, name and role of each player
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Players {
	
	private  Scanner scan;
	
	//This int stores the total number of Players, does not once entered in numberOfPlayers()
	private int totalPlayers;
	
	//This string will store the theme of the game
	private String playTheme;
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private static List<Player> playerInfo = new ArrayList<>();
		
	//List of mafia members
	private List<String> mafiaMembers = new ArrayList<>();
	
	//This int stores the information for the lyncher target	
	private int lynchTargetID;
	
	public Players(){
		init();
	}

	
	/**
	 * This method calls other methods in the Players class
	 */
	public void init(){
		numberOfPlayers();
		//themeOfGame();
		nameOfPlayers();
		roleOfPlayers();
	}
		
	/**
	 * This method asks the user to input the total number of Players
	 */
	public void numberOfPlayers(){
		scan = new Scanner(System.in);
		
		System.out.print("How many players? Min 5, Max 10: ");
		totalPlayers = 7;//scan.nextInt();
			
		if((10 < totalPlayers) ||(totalPlayers < 5)) {
			System.out.println("Input must be in between 5 and 10.");
			System.out.println();
			numberOfPlayers();
		}
		 
	}
	
	/**
	 * This class will asks user to choose the theme of the game
	 */
	public void themeOfGame(){
		scan = new Scanner(System.in);
		System.out.print("Which theme do you want to play?(Enter 'M' for Mafia or 'W' for Werewolf): ");
		playTheme = scan.nextLine();
	}
	
	
	
	
	
	/**
	 * Asks the user to input the name of each player followed by enter
	 * Sets the play position of each player to the order then names were inputed
	 * Sets the status of each player to be alive. As each player starts out alive in the game
	 * Sets the inBar (The barman has stopped them from doing their action tonight) status to false for each player
	 */
	public void nameOfPlayers(){
		List<String> names = new ArrayList<>(Arrays.asList("Frodo","Gandalf","Sam","Merry","Gimli","Legolas","Aragorn","Eomer","Elrond","Theoden"));
		System.out.println("Please enter each players name followed by enter: ");
		for(int i =0; i<totalPlayers; i++){
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
		RoleAssignment r = new RoleAssignment(totalPlayers);
		r.playerAssignment();
		List<String> roles = r.getRoles();
		List<String> rolesInfo = r.getRolesInfo();
		List<String> Goals = r.getGoals();
		//Loops through all of the players and assigns them a Role, Info and Goal
		for(int i=0; i<totalPlayers; i++){
			playerInfo.get(i).setRole(roles.get(i));			
			playerInfo.get(i).setRoleInfo(rolesInfo.get(i));
			playerInfo.get(i).setGoal(Goals.get(i));
			
			if(playerInfo.get(i).getRole().contains("Lynch")) {
				lynchTarget();
				playerInfo.get(i).setGoal("To win the game, you should lynch this person: "+ playerInfo.get(lynchTargetID).getName());
			}
			
			//If the String role of the player contains the word "Mafia:"
			if(playerInfo.get(i).getRole().contains("Mafia:")){
				//This boolean is for the detective checking if the target is part of the Mafia
				//GodFather is not included, as he is hidden from the detective
				playerInfo.get(i).setIsMafia(true);
			
			}else{
				playerInfo.get(i).setIsMafia(false);
			}
			if(playerInfo.get(i).getRole().contains("Mafia")){
				//This is a list of the names, used at night to display the ALL Mafia members to other Mafia members
				mafiaMembers.add(playerInfo.get(i).getName());				
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
	
	//setter for playerInfo
	public void setPlayerInfo(List<Player> info){
		playerInfo = info;
	}
	
	//getters
	
	public String getPlayTheme(){
		return playTheme;
	}
	
	public List<Player> getPlayerInfo(){
		return playerInfo;
	}
	
	public List<String> getMafiaMembers(){
		return mafiaMembers;
	}
	
	public int getLynchTarget(){
	return lynchTargetID;
}
}