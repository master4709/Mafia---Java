package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerInfo.*;

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

public class Game{
	
	
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
	//List of player names that are alive at the start of the game, used for making the buttons in Day Panel and Night Panel
	private List<String> playerNames = new ArrayList<>();
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//Lmao
	private List<String> nightPlayer = new ArrayList<>(Arrays.asList("Mafia: Barman","Bodyguard","Mafia: Hitman","Vigilante","Mafia- Godfather","Doctor"));
	
	private List<Integer> events = new ArrayList<>();
	
	//Index value for the target of the Lyncher
	private int lynchTarget = -1;
	
	private int round;
	/**
	 * Constructor
	 * Takes params values and stores them into local versions
	 *
	 * @param playerInfo
	 * @param mafiaMembers
	 * @param lynchTarget
	 */
	public Game(List<Player> playerInfo, int lynchTarget, int round, boolean test){
		this.playerInfo = playerInfo;
		this.lynchTarget = lynchTarget;
		this.round = round;
		mafiaMembers();
		alivePlayers(test);
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
	 * Creates a list of player names
	 * If they are dead, adds "Dead | " to the front f the players name
	 * @param test - if true, adds the players role to the players name
	 */
	private void alivePlayers(boolean test){
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				if(test){
					playerNames.add(p.getName()+" | "+p.getRole());
				}else{
					playerNames.add(p.getName());
				}
			}else{
				playerNames.add("Dead | "+p.getName());
			}
			
		}
	}
	
	/**
	 * This method will kill one player each day, depending on who the players vote out.
	 */
	public void dayCycle(int target){
		if(target!=-1){
			System.out.println(getPlayer(target).getName()+" has been lynched");
			setPlayerStatus(target,0);//Sets status of player to DEAD
			setPlayerLynched(target,true);
			if(playerInfo.get(target).getRole().contains("Hitman")){
				//newHitman(target);
			}
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @return int - position in the list for the target of night action (EG killed or healed event)
	 */
	public void nightAction(){
		System.out.println("Do night logic");
		
		for(String role: nightPlayer){
			for(Player p: playerInfo){
				if(p.getRole().contains(role)){
					if(p.getTarget()!=-1){
						//If the player doing action was a killer, checks if the target was protected by the bodyguard
						//If yes, targets the bodyguard instead of the target of the player
						if((role.contains("Hitman") || role.contains("Vigilante")) && getPlayer(p.getTarget()).getStatus()==4){
							System.out.println("The Bodyguard has save player: "+getPlayer(p.getTarget()).getName());
							setPlayerStatus(getPlayer("Bodyguard").getPosition(),p.doAction(getPlayer("Bodyguard")));
							System.out.println(p.toString() +" is doing action against player "+getPlayer("Bodyguard").toString());
						//Changes the in Bar of the player if the current role is Barman
						}else if(role.contains("Barman")){
							setPlayerInBar(p.getTarget(),p.doAction(getPlayer(p.getTarget())));
							System.out.println(p.toString() +" is doing action against player "+getPlayer(p.getTarget()).getName());
						}else{//Targets that player
							setPlayerStatus(p.getTarget(),p.doAction(getPlayer(p.getTarget())));
							System.out.println(p.toString() +" is doing action against player "+getPlayer(p.getTarget()).getName());
						}
					}else{
						System.out.println(p.toString()+" is NOT doing action");
					}
					break;
				}
			}
		}
		round++;
		events = new ArrayList<>();
		for(Player p:playerInfo){
			int player = resetPlayer(p.copy());
			if(player!=-1){
				events.add(player);
			}
		}
	}
	
	
	/**
	 * Resets all of the status for every player
	 * Starts the loop through players at the last position 
	 */
	private Integer resetPlayer(Player p){
		setPlayerInBar(p.getPosition(),0);
		if(p.getStatus()==2){
			System.out.println(p.getName()+" is dead");
			return p.getPosition();
		}else if(p.getStatus()==3){
			System.out.println(p.getName()+" is saved");
			return p.getPosition();
		}else if(p.getStatus()!=0){
			setPlayerStatus(p.getStatus(),1);
		}
		return -1;
	}
	/**
	 * Checks if any player has achieved victory
	 * @return - String - winner or "None"
	 */
	public String checkWinner(){
		int mafiaTotal = 0;
		int townTotal = -1;
		//Counts total amount of alive Mafia and Town Members
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				if(p.getRole().contains("Mafia")){
					System.out.println(p.getRole());
					mafiaTotal++;
				}else{
					System.out.println(p.getRole());
					townTotal++;
				}
			}
		} if(townTotal==1 && getPlayer("Survivor").getStatus()!=0){
			System.out.println("The Survivor was the last remaining Town emeber alive");
			return "Survivor";
		}else if(mafiaTotal>townTotal){
			System.out.println("THe Mafia have a majority: "+ mafiaTotal +" | " + townTotal);
			return "Mafia";
		}else if(mafiaTotal==0){
			System.out.println("The Mafia have been removed from the Town!");
			return "Town";
		}else if(lynchTarget!=-1 && getPlayer(lynchTarget).wasLynched()){
			System.out.println("hHe Lyncher has lynched player: " + getPlayer(lynchTarget).getName());
			return "Lyncher";
		}else{
			return "None";
		}
	}

	/**
	 * Sets the target of the current player
	 * @param position- index value of player list
	 * @param target - index value for the player who is selecting night action
	 */
	public void setPlayerTarget(int position, int target){
		playerInfo.get(position).setTarget(target);
	}
	/**
	 * 
	 * @param position - index value of the player being targeted for the bar
	 * @param yes - sets the value for in bar. 0 not in bar | 1 in bar
	 */
	public void setPlayerInBar(int position, int yes){
		playerInfo.get(position).setInBar(yes);
	}
	/**
	 * Sets the status of the designated player
	 * @param position - index value of player in the list of players
	 * @param status - int value of status of the player being changed
	 * | 0 Dead | 1 Alive | 2 Targeted | 3 Healed | 4 Protected | 
	 */
	public void setPlayerStatus(int position, int status){
		playerInfo.get(position).setStatus(status);
	}
	/**
	 * Sets the status of the current player to be in the bar or not in
	 * @param position - index value of player in the list of players
	 * @param lynched - boolean for if the player was lynched during the day
	 */
	public void setPlayerLynched(int position, boolean lynched){
		playerInfo.get(position).setLynched(lynched);
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
	 * Returns a copy of the current player
	 * @param i - index value of the current player
	 * @return player
	 */
	public Player getPlayer(int i){
		return playerInfo.get(i).copy();
	}
	/**
	 * Returns a copy of the current player
	 * @param s - name of the role of the current player
	 * @return
	 */
	private Player getPlayer(String s){
		Player player = new Town("The player does not exist: "+s,0,0,false);
		for(Player p: playerInfo){
			if(p.getRole().contains(s)){
				player = p.copy();
				break;
			}
		}
		return player;
	}
	/**
	 * Returns the index value of the target of the lyncher
	 * @return integer
	 */
	public int getLynchTarget(){
		return lynchTarget;
	}
	/**
	 * Returns the current round
	 * @return integer - round
	 */
	public int getRound(){
		return round;
	}
	/**
	 * Returns the total amount of players
	 * @return integer - totalPlayers
	 */
	public int getPlayerTotal(){
		return playerInfo.size();
	}
	/**
	 * @return list<String> of current player name
	 */
	public List<String> getPlayerNames(){
		return playerNames;
	}
	
	public List<Integer> getEvents(){
		return events;
	}
}