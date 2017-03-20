	package logic;
	
	
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Random;
	/**
	 * This class will create a list to store all the information about each player.  
	 * Author: Mahsa Lotfi 10072013 
	 */
	public class SetUp extends RoleAssignment {
		
		//List of each player (class) and his/her info (name, role, target, position, etc)
		private List<Player> playerInfo = new ArrayList<>();
		private List<String> names = new ArrayList<>();
		//This int stores the information for the lyncher target	
		private int lynchTargetID;
		private ArrayList<String> roles;
		
		/**
		 * Constructor with 2 arguments. It will initialize names and roles of the 
		 * players and create a list which has all the info of players in it.
		 * @param List<String> names, name of players.
		 * @param ArrayList<String> roleSelected, roles that are selected by players.
		 */	
		public SetUp( List<String> names, ArrayList<String> rolesSelected){
			super(names.size());
			System.out.println("Set up constructor");
			this.names = names;
			this.roles = rolesSelected; 
			nameOfPlayers();
			roleOfPlayers();
		}
	
			
		/**
		 * This method will add the information of each player to the playerInfo list.
		 * Sets the play position of each player to the order then names were inputed.
		 * Sets player target and keep the record of old player target.
		 * Sets the status of each player to be alive.
		 * Sets the status of targeted, healed or protected to be false.
		 * Sets the inBar (The barman has stopped them from doing their action tonight) status 
		 * to false for each player.
		 * 
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
		 * This method will sets the roles to each player and store it into the playerInfo list.
		 * If the role contains Mafia, sets the isMafia variable to true 
		 * If the player is Lyncher, then it will set a target for it.
		 */
		public void roleOfPlayers(){
			$("Setting role of players");
			//playerAssignment(List<String> chosenRolesString);
			System.out.println(roles);
			
			//Loops through all of the players and assigns them a Role.
			for(int i=0; i<names.size(); i++){
				System.out.println(playerInfo);
				playerInfo.get(i).setRole(roles.get(i));			
				
				
				//If the player is Lyncher then set a target for it.
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
		
		/**
		 * Finds a random target for the lyncher
		 */		
		
		public void lynchTarget(){
			List<Player> possibleTargets = playerInfo;
			int i = new Random().nextInt(totalPlayers-1);
			while(possibleTargets.get(i).getRole() == "Lyncher") {
				i = new Random().nextInt(totalPlayers-1);			
			}
			lynchTargetID = i;
		}
		
		/**
		 * Getter method for playerInfo list.
		 */	
		public List<Player> getPlayerInfo(){
			return playerInfo;
		}
		
		/**
		 * Getter method for lynchTargetID.
		 */
		public int getLynchTarget(){
		return lynchTargetID;
	}
	}