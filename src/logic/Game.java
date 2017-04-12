package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerInfo.*;

/**Game Class
 * Handles most of the logic for day and night cycle
 * Stores the list of players
 * Lynch and kill a a player after the day in  lynchPlayer(int)
 * Do all of the actions for all of the players in nightAction()
 * Resets each player at the end of night
 * This class stores all of the player information for the game
 * @author Pierce de Jong 30006609
*/

public class Game{
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
	//List of player names that are alive at the start of the game, used for making the buttons in Day Panel and Night Panel
	private List<String> playerNames = new ArrayList<>();
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//Lmao
	private List<String> nightPlayer = new ArrayList<>(Arrays.asList("Mafia: Barman","Bodyguard","Mafia: Hitman","Vigilante","Doctor"));
	
	//List of player indexes that were healed or killed that night, maximum of 2 people in the list
	private List<Integer> events = new ArrayList<>();
	
	//Index value for the target of the Lyncher
	private int lynchTarget = -1;
	/**
	 * Constructor
	 * Takes params values and stores them into local versions
	 *
	 * @param playerInfo - List<Player> - current players in the game
	 * @param lynchTarget - Integer - ID value of target of Lyncher (if no lynncher then -1)
	 * @param test - Boolean for if the game is in test mode
	 */
	public Game(List<Player> playerInfo, int lynchTarget, boolean test){
		this.playerInfo = playerInfo;
		this.lynchTarget = lynchTarget;
		playerNames(test);
		System.out.println(playerInfo.toString());
	}
	/**
	 * Creates a list of player names
	 * Also creates a list of Mafia members
	 * If they are dead, adds "Dead | " to the front f the players name
	 * @param test - if true, adds the players role to the players name
	 */
	private void playerNames(boolean test){
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				if(test){
					playerNames.add(p.toString());
				}else{
					playerNames.add(p.getName());
				}
			}else{
				playerNames.add("Dead | "+p.getName());
			}
			if(p.getRole().contains("Mafia")){
				mafiaMembers.add(p.getName());
			}
		}
	}
	
	/**
	 * This method will kill one player each day, depending on who the players vote out.
	 */
	public void lynchPlayer(int target){
		if(target!=-1){
			System.out.println(getPlayer(target).getName().toUpperCase()+" has been lynched");
			setPlayerStatus(target,0);//Sets status of player to DEAD
			setPlayerLynched(target,true);///Sets player to has been lynched during the day time
			if(playerInfo.get(target).getRole().contains("Hitman")){
				newHitman(target);//If the hitman dies, try find a new Mafia member to become Hitman
			}
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @return int - position in the list for the target of night action (EG killed or healed event)
	 */
	public void nightAction(){
		System.out.println("NIGHT ACTIONS");
		
		for(String role: nightPlayer){
			for(Player p: playerInfo){
				if(p.getRole().contains(role)){
					if(p.getTarget()!=-1){
						//Targets the bodyguard if the target of killer is protected (status = 4)
						if((role.contains("Hitman") || role.contains("Vigilante")) && getPlayer(p.getTarget()).getStatus()==4){
							int body = p.doAction(getPlayer("Bodyguard"));
							setPlayerStatus(getPlayer("Bodyguard").getPosition(), body);
						}else if(role.contains("Barman")){//Changes the in Bar of the player if the current role is Barman
							int bar = p.doAction(getPlayer(p.getTarget()));
							setPlayerInBar(p.getTarget(),bar);
						}else{//Does action against target of current night role
							int status  = p.doAction(getPlayer(p.getTarget()));
							setPlayerStatus(p.getTarget(),status);
						}
						System.out.println(p.toString()+ " is doing night action against player " + getPlayer(p.getTarget()).getName());
						break;
					}else if(p.getStatus()==0){
						System.out.println("DEAD: "+p.toString()+" is low key dead");
					}else{
						System.out.println("NOTHING: "+p.toString() +" did not choose a night target");
					}
				}
			}
		}
	}
	/**
	 * Resets the status of every player
	 * 
	 */
	public void reset(){
		events = new ArrayList<>();
		System.out.println("RESET All Player targets, inBar, status");
		for(Player p:playerInfo){
			//Returns the Index value of any player that was killed or saved that night
			int player = resetPlayer(p);
			if(player!=-1){
				events.add(player);
			}
		}
		for(int i: events){
			Player p = getPlayer(i);
			System.out.print("EVENT: ");
			if(p.getStatus()==2){
				//System.out.println(getPlayer(i).toString()+ " had DEATH event happen to them");
			}else{
				//System.out.println(getPlayer(i).toString()+ " had SAVE event happen to them");
			}
		}
	}
	
	/**
	 * Resets the status of the current Player
	 * In bar = 0
	 * Target = -1
	 * @return -1 if the player was not saved or targeted by the Mafia
	 * @return player position if the player was involved in night action
	 */
	private Integer resetPlayer(Player p){
		setPlayerInBar(p.getPosition(),0);
		setPlayerTarget(p.getPosition(),-1);
		if(p.getStatus()==2){//Player was killed by Htman/ Vigialnte
			//System.out.println(p.getName()+" was targeted and KILLED");
			if(p.getRole().contains("Hitman")) newHitman(p.getPosition());
			return p.getPosition();
		}else if(p.getStatus()==3){//Player was saved by Doctor
			//System.out.println(p.getName()+" was targeted but SAVED");
			return p.getPosition();
		}else if(p.getStatus()==4){//Player was saved by Bodyguard
			setPlayerStatus(p.getStatus(),1);
			//System.out.println(p.getName()+"was saved by the Bodyguard (Should not be here for final game)");
			return -1;
		}else{
			if(p.getStatus()==0){
				//System.out.println(p.getName()+ " is dead. Lynching: " +Boolean.toString(p.wasLynched()));
			}else{
				//System.out.println(p.getName()+ " is alive");
			}
			return -1;
		}
	}
	/**
	 * Checks if any player has achieved victory
	 * @return - String - winner or "None"
	 */
	public String checkWinner(){
		int mafiaTotal = 0;
		int townTotal = 0;
		//Counts total amount of alive Mafia and Town Members
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				if(p.getRole().contains("Mafia")){
					mafiaTotal++;
				}else{
					townTotal++;
				}
			}
		} if(townTotal==1 && getPlayer("Survivor").getStatus()!=0){
			System.out.println("The Survivor was the last remaining Town member alive");
			return "Survivor";
		}else if(lynchTarget!=-1 && getPlayer(lynchTarget).wasLynched()){
			System.out.println("The Lyncher has lynched player: " + getPlayer(lynchTarget).getName());
			return "Lyncher";
		}else if(mafiaTotal>townTotal){
			System.out.println("The Mafia have a majority: "+ mafiaTotal +" | " + townTotal);
			return "Mafia";
		}else if(mafiaTotal==0){
			System.out.println("The Mafia have been removed from the Town!");
			return "Town";
		}else{
			return "None";
		}
	}
	/**
	 * Removes the current Hitman from the game if dead
	 * Finds the next Mafia member to become the new Hitman
	 *  1. Goon, 2. Barman, 2. GodFather.
	 * @param hitman - index value of dead Hitman
	 */
	private void newHitman(int hitman){
		Player p;
		if(getPlayer("Goon")!=null && getPlayer("Goon").getStatus()!=0){
			p = getPlayer("Goon");
			System.out.println(p.toString()+ " is taking Hitmans spot");
			setHitman(p);
		}else if(getPlayer("Barman")!=null && getPlayer("Barman").getStatus()!=0){
			p = getPlayer("Barman");
			System.out.println(p.toString()+ " is taking Hitmans spot");
			setHitman(p);
		}else if(getPlayer("GodFather")!=null && getPlayer("GodFather").getStatus()!=0){
			p = getPlayer("GodFather");
			System.out.println(p.toString()+ " is taking Hitmans spot");
			setHitman(p);
		}
	}
	/**
	 * Sets the passed Player to become the new Hitman
	 * @param p - Player changing to the Hitman position, does not change Player location in list
	 */
	private void setHitman(Player p){
		playerInfo.set(p.getPosition(), new Hitman(p.getName(),p.getPosition()));
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
	 * @return clone - List<String>
	 */
	public List<String> getMafiaMember(){
		List<String> clone = new ArrayList<String>(mafiaMembers);
		return clone;
	}
	/**
	 * Returns a list of players
	 * Used for Saving the game at the start of every day cycle
	 * @return clone - List<Player>
	 */
	public List<Player> getPlayerInfo(){
		List<Player> clone = new ArrayList<Player>(playerInfo);
		return clone;
	}
	/**
	 * Returns a copy of the current player
	 * @param i - index value of the current player
	 * @return Player
	 */
	public Player getPlayer(int i){
		if(i>=0&&i<playerInfo.size()){
			return playerInfo.get(i).copy();
		}else{
			return new Townie("No player "+i,-1); 
		}
	}
	/**
	 * Returns a copy of the current player
	 * @param s - name of the role of the current player
	 * @return
	 */
	private Player getPlayer(String s){
		for(Player p: playerInfo){
			if(p.getName().contains(s) || p.getRole().contains(s)){
				return p.copy();
			}
		}
		return null;
	}
	
	public String getLynchTargetString(){
		if(lynchTarget!=-1){
			return playerInfo.get(lynchTarget).getName();
		}else{
			return "No Target";
		}
		
	}
	/**
	 * Returns the index value of the target of the lyncher
	 * @return integer
	 */
	public int getLynchTarget(){
		return lynchTarget;
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
	/**
	 * Returns a List of events that happened during the night
	 * @return
	 */
	public List<Integer> getEvents(){
		return events;
	}
}