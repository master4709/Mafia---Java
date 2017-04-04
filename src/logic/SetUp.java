package logic;
	
	
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import playerInfo.Barman;
import playerInfo.Bodyguard;
import playerInfo.Detective;
import playerInfo.Doctor;
import playerInfo.Godfather;
import playerInfo.Hitman;
import playerInfo.Lyncher;
import playerInfo.Player;
import playerInfo.Survivor;
import playerInfo.Town;
import playerInfo.Vigilante;

/**
 * This class will create a list to store all the information about each player.  
 * Author: Mahsa Lotfi 10072013 
 */	
public class SetUp {
		
		private List<String> names;
		//This int stores the information for the lyncher target	
		private int lynchTargetID;
		private List<String> roles;
		List<Player> playerInfo = new ArrayList<>();
		
		/**
		 * Constructor with 2 arguments. It will initialize names and roles of the 
		 * players and create a list which has all the info of players in it.
		 * @param List<String> names, name of players.
		 * @param ArrayList<String> roleSelected, roles that are selected by players.
		 */	
		public SetUp( List<String> names, List<String> rolesSelected){
			this.names = names;
			this.roles = rolesSelected;
			setAllPlayers();	
			this.lynchTargetID = setLynchTarget();
		}
	
			
		/**
		 * Method shuffles the roles and matches them with the names
		 */
		public void setAllPlayers(){
			Collections.shuffle(roles);
			for(int i =0; i<names.size(); i++){
				playerInfo.add(createPlayer(i));
			}
		}
		
		/**
		 * Recursively finds and sets a target for the lyncher
		 * @param calls
		 * @return lynchTargetID
		 */
		public int setLynchTarget(int... calls){
			
			//null calls is interpreted as 0 calls
			int i = calls == null ? 0 : calls[0] + 1;
			String target = roles.get(i);
			
			if(target != "Lyncher"){
				return findPosition(target);
			} return setLynchTarget(i);
		}
		
		/**
		 * Creates a player determined by the role
		 * @param i (position)
		 * @return a Player class
		 */
		public Player createPlayer(int i){
			switch(roles.get(i)){
				case "Mafia: Barman": 		return new Barman(names.get(i), i);
				case "Bodyguard": 			return new Bodyguard(names.get(i), i);
				case "Detective": 			return new Detective(names.get(i), i);
				case "Doctor": 				return new Doctor(names.get(i), i);
				case "Mafia- Godfather": 	return new Godfather(names.get(i), i);
				case "Mafia: Hitman": 		return new Hitman(names.get(i), i);
				case "Lyncher": 			return new Lyncher(names.get(i), i);
				case "Survivor": 			return new Survivor(names.get(i), i);
				case "Vigilante": 			return new Vigilante(names.get(i), i);
				default: 					return new Town(names.get(i), i);
				}
		}
		
		/**
		 * Finds the position of a role or the
		 * first Townie
		 * @param role
		 * @return position of the player
		 */
		public int findPosition(String role){
			for(Player p : playerInfo){
				if(p.toString() == role) {
					return p.getPosition();
				}
			} return -1;
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