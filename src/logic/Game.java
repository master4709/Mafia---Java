package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import playerInfo.Player;

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
	
	//List of all of the Mafia members to be presented to each one every night
	private List<String> mafiaMembers = new ArrayList<>();
	
	//List of each player (class) and his/her info (name, role, target, position, etc)
	private List<Player> playerInfo = new ArrayList<>();
	
	private List<String> nightPlayer = new ArrayList<>(Arrays.asList("Mafia: Barman","Bodyguard","Mafia: Hitman","Vigilante","Mafia- Godfather","Doctor"));
	
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
	 * This method will kill one player each day, depending on who the players vote out.
	 */
	public void dayCycle(int target){
		if(target!=-1){
			System.out.println(getPlayer(target).getName()+" has been lynched");
			setPlayerStatus(target,0);//Sets status of player to DEAD
			getPlayer(target).setLynched(true);
			if(playerInfo.get(target).getRole().contains("Hitman")){
				//newHitman(target);
			}
		}
	}
	
	/**
	 * This method takes the value from the target selection of 
	 * @param playerTarget
	 */
	public Integer nightAction(){
		System.out.println("Do night logic");
		
		for(String role: nightPlayer){
			for(Player p: playerInfo){
				if(p.getRole().contains(role)){
					if(p.getTarget()!=-1){
						if(getPlayer(p.getTarget()).getStatus()==4){
							setPlayerStatus(getPlayer("Bodyguard").getPosition(),p.doAction(getPlayer("Bodyguard")));
							System.out.println(p.toString()+" "+p.getName().toUpperCase() +" is doing action against player "+getPlayer("Bodyguard").getName().toUpperCase()+" "+getPlayer("Bodyguard").getRole());
						}else{
							setPlayerStatus(p.getTarget(),p.doAction(getPlayer(p.getTarget())));
							System.out.println(p.toString()+" "+p.getName().toUpperCase() +" is doing action against player "+getPlayer(p.getTarget()).getName().toUpperCase());
						}
					}else{
						System.out.println(p.toString()+" "+p.getName().toUpperCase() +" is NOT doing action");
					}
					break;
				}
			}
		}
		
		int target = resetStatus();
		return target;
	}
	
	
	/**
	 * Resets all of the status for every player
	 * Starts the loop through players at the last position 
	 */
	private Integer resetStatus(){
		int target = -1;
		for(int i=0;i<playerInfo.size();i++){
			//If the player has been targeted that night to be killed
			if(getPlayer(i).getStatus()==2){
				System.out.println(getPlayer(i).getName()+" is dead");
				target = i;
			}else if(getPlayer(i).getStatus()==3){
				System.out.println(getPlayer(i).getName()+" is saved");
				target = i;
			}else if(getPlayer(i).getStatus()!=0){
				setPlayerStatus(i,1);
			}
			
		}
		return target;
	}
	
	public String checkWinner(){
		int mafiaTotal = 0;
		int townTotal = -1;
		for(Player p: playerInfo){
			if(p.getStatus()!=0){
				if(p.getRole().contains("Mafia")){
					mafiaTotal++;
				}else{
					townTotal++;
				}
			}
		}
		if(mafiaTotal>townTotal){
			return "Mafia";
		}else if(mafiaTotal==0){
			return "Town";
		}else if(lynchTarget!=-1 && getPlayer(lynchTarget).wasLynched()){
			return "Lyncher";
		}else if(townTotal==1 && getPlayer("Survivor").getStatus()!=0){
			return "Survivor";
		}else{
			return "None";
		}
	}

	/**
	 * Sets the target of the current player
	 * @param position
	 * @param target
	 */
	public void setPlayerTarget(int position, int target){
		playerInfo.get(position).setTarget(target);
	}

	public void setPlayerStatus(int position, int status){
		playerInfo.get(position).setStatus(status);
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
	 * Returns a player
	 * @param i
	 * @return player
	 */
	private Player getPlayer(int i){
		Player player = playerInfo.get(i);
		return player;
	}
	
	private Player getPlayer(String s){
		Player player = null;
		for(Player p: playerInfo){
			if(p.getRole().contains(s)){
				player = p;
				break;
			}
		}
		return player;
	}
	
	public Player getPlayerCopy(int i){
		return playerInfo.get(i);
	}
	
	public int getLynchTarget(){
		return lynchTarget;
	}
}