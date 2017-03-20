package Testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import logic.Debug;
import logic.Player;
import logic.Role;

/**
 * This class contains functions that facilitate the testing
 * for the game witout having to go through the role assignment
 * process
 * @author Elvin Limpin 30018832
 *
 */

public abstract class TestingTools extends Debug {
	private static List<String> mafiaMembers = new ArrayList<>();
	private static int lynchTargetID;
		
		/**
		 * creates a JFrame to facilitate the GUI
		 * @return the Jframe
		 */
		public static JFrame init(){
			int width;
			int height;
			JFrame frame = new JFrame();
			
			width = 480;
			height = 720;
			frame.setBounds(550, 20, width, height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setVisible(false);
			frame.setTitle("Mafia - Test");
			return frame;
		}
		
		/**
		 * Creates information for the players to facilitate
		 * testing
		 * @return List<Player> containing finalized roles
		 */
		public static List<Player> makeInfo() {
			int totalPlayers = 6;
			List<Player> playerInfo = initialInfo(totalPlayers);
			List<String> assignments = new ArrayList<>();
			List<String> assignmentsInfo = new ArrayList<>();
			List<String> assignmentsGoals = new ArrayList<>();
			
			List<Role> chosenRoles = new ArrayList<>();
			for(Role role: Role.values()) chosenRoles.add(role);
			Random rand = new Random();
			Role select;
			for (int i = 0; i < totalPlayers; i++) {
				int num = rand.nextInt(totalPlayers-i);
				select = chosenRoles.get(num);
				chosenRoles.remove(num);
				assignments.add(select.getRoleID());
				assignmentsInfo.add(select.getRoleInfo());
				assignmentsGoals.add(select.getRoleGoal());
			}
			$("(temp) RANDOMIZED ROLES: " + assignments.toString());
			
			return setNewInfo(assignments, assignmentsInfo,assignmentsGoals, playerInfo, totalPlayers);
		}
		
		/**
		 * matches the roles and the other attributes of the player
		 * @param assignments
		 * @param assignmentsInfo
		 * @param assignmentsGoals
		 * @param playerInfo
		 * @param totalPlayers
		 * @return List<Player> containing the player 
		 */
		private static List<Player> setNewInfo(List<String> assignments, List<String> assignmentsInfo,
				List<String> assignmentsGoals, List<Player> playerInfo, int totalPlayers) {
			
			List<String> roles = assignments;
			List<String> rolesInfo = assignmentsInfo;
			List<String> Goals = assignmentsGoals;
			
			//Loops through all of the players and assigns them a Role, Info and Goal
			for(int i=0; i<totalPlayers; i++){
				playerInfo.get(i).setRole(roles.get(i));			
				playerInfo.get(i).setRoleInfo(rolesInfo.get(i));
				playerInfo.get(i).setGoal(Goals.get(i));
				
				if(playerInfo.get(i).getRole().contains("Lynch")) {
					lynchTarget(playerInfo, totalPlayers);
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
				if(playerInfo.get(i).getRole().contains("Mafia")){
					//This is a list of the names, used at night to display the ALL Mafia members to other Mafia members
					mafiaMembers.add(playerInfo.get(i).getName());				
				}			
			}
			return playerInfo;		
			
		}
		
		/**
		 * Finds a randomized target for the lyncher
		 * @param playerInfo
		 * @param totalPlayers
		 */
		public static void lynchTarget(List<Player> playerInfo, int totalPlayers){
			List<Player> possibleTargets = playerInfo;
			int i = new Random().nextInt(totalPlayers-1);
			while(possibleTargets.get(i).getRole() == "Lyncher") {
				i = new Random().nextInt(totalPlayers-1);			
			}
			lynchTargetID = i;
		}

		/**
		 *  Initalizes the player information
		 * @param totalPlayers
		 * @return List<Player> names for the players
		 */
		private static List<Player> initialInfo(int totalPlayers){
			List<Player> playerInfo = new ArrayList<>();
			List<String> names = new ArrayList<>(Arrays.asList(
					"Frodo","Gandalf","Sam","Merry","Gimli","Legolas","Aragorn","Eomer","Elrond","Theoden"));
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
			return playerInfo;
		}
		
		/**
		 * Getter for the lynch target
		 * @return the location of the lynch target
		 */
		public static int getLynchTargetID(){
			return lynchTargetID;
		}
}
