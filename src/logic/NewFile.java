package logic;
	
	
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import playerInfo.*;

/**
 * This class will create a list to store all the information 
 * about each player and pass that list by a getter method.
 * It will shuffle the roles and then assign them to each names.
 * Also it will assign a target for the Lyncher role and pass it by a getter method.
 * 
 * Author: Mahsa Lotfi 10072013 
 */	
public class NewFile extends SaveFile{
		/**
		 * Instance variables
		 */
		private List<String> names;
		private List<String> roles;
		List<Player> playerInfo = new ArrayList<>();
		private int lynchTargetID;
			
		
		/**
		 * Constructor with 2 arguments. It will initialize names and roles of the 
		 * players and create a list which has all the info of players in it.
		 * @param List<String> names, name of players.
		 * @param ArrayList<String> roleSelected, roles that are selected by players.
		 */	
		public NewFile( List<String> names, List<String> rolesSelected){
			this.names = names;
			this.roles = rolesSelected;
			setAllPlayers();	
			this.lynchTargetID = setLynchTarget();
			save(playerInfo, lynchTargetID);
		}
	
			
		/**
		 * Method to shuffle the roles and matches them with the names.
		 */
		public void setAllPlayers(){
			Collections.shuffle(roles);
			for(int i =0; i<names.size(); i++){
				playerInfo.add(createPlayer(names.get(i),roles.get(i),i));
			}
		}
		
		/**
		 * This method will find and set a target for the lyncher.
		 * @param calls
		 * @return lynchTargetID
		 */
		public int setLynchTarget(){
			for(int i = 0; i < roles.size(); i++){
				if(roles.get(i) != "Lyncher"){
					return findPosition(roles.get(i));
				} 
			}
			return -1;
		}
		
		public Player createPlayer(String name, String role, int position){
			switch(role){
			case "Mafia: Barman": 		return new Barman(name,position);
			case "Bodyguard": 			return new Bodyguard(name,position);
			case "Detective": 			return new Detective(name,position);
			case "Doctor": 				return new Doctor(name,position);
			case "Mafia- GodFather": 	return new GodFather(name,position);
			case "Mafia: Hitman": 		return new Hitman(name,position);
			case "Lyncher": 			return new Lyncher(name,position);
			case "Survivor": 			return new Survivor(name,position);
			case "Vigilante": 			return new Vigilante(name,position);
			case "Mafia: Goon":			return new Goon(name,position);
			default: 					return new Town(name,position);
			}
		}
		
		
		/**
		 * This method will find the position of a role or the first Townie
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