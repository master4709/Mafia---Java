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
			this.lynchTargetID = setLynchTarget(0);
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
		public void setAllPlayers(){
			Collections.shuffle(roles);
			for(int i =0; i<names.size(); i++){
				playerInfo.add(createPlayer(i));
			}
		}
		
		public int setLynchTarget(int i){
			String target = roles.get(i);
			if(target != "Lyncher") return findPosition(target);
			return setLynchTarget(i+1);
		}
		
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
		
		public int findPosition(String role){
			for(Player p : playerInfo){
				if(p.toString() == role) return p.getPosition();
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