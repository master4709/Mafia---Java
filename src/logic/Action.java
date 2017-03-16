package logic;
// Author: Elvin Limpin 30018832

import java.util.HashMap;
import java.util.List;

/*
 * This class is the Action scenario that happens every night
 * for every set of actions that the characters do
 * Initialize a new Action every night cycle.
 * 
 * Action is inherited by game.
 * 
 * "of" is shorthand markup for the list of Player classes
 * 
 ***  For the Hash Map  ***
 *  Key-Value: Role-PositionIndex
 */
public class Action{

	// Instance variables
    private int bodyguardPosition;
    private List<Player> of;
    protected HashMap<String, Integer> playersJobPosition = new HashMap<>();
   
    public Action(){
    	//this.of = playerInfo;
    	//initJobPositionMap();
    	//nightActions();
    }
    public void initJobPositionMap() {
        for (Player player : of) {
            playersJobPosition.put(player.getRole(), player.getPlayPosition());
        }
    }
    
    // Searches for the position of the target
    public int target(int position){
        int target;
        target = of.get(position).getPlayerTarget();
        return target;
    }
    
    //This will be called in the Game class
    public void nightActions(){
    	// If such players exist, their actions will be implemented
    	//This should be changed to loop through all of the players and call each method depending on what role the player is
    	if(of.size()>5) barman(); 
        if(of.size()>6) bodyguard();
        if(of.size()>8) godFather();
        killer("hitman");
        if(of.size()>9) killer("vigilante");
        doctor();
    }
    
    //Modifying the properties of the players//
    
    
    //sets all the players
    protected void setPlayerInfo(List<Player> playerInfo){
    	
    	of = playerInfo;
    	initJobPositionMap();
    }
    
    protected void barman(){
        int position = playersJobPosition.get("Mafia: Barman");
        
        // Sets the target of the barman to be in the bar.
    	// They can not do anything that night in that case
        
        if(target(position) !=-1) of.get(target(position)).setInBar(true);
    }

    protected void bodyguard(){
        int position = playersJobPosition.get("Bodyguard");
        
        //If the player did not target someone or was stopped by the barman
        if(target(position)!=-1 && !of.get(position).inBar()){
        	
            of.get(target(position)).setIsProtected(true);;
            //Sets the target of the bodyguard to protected for the night
            
            bodyguardPosition = position;
            // This allows the Mafia or vigilante to target and kill
            // the bodyguard if the target is protected
        }
    }

    protected void killer(String who){
    	int position = 0;
        switch(who){
            case "vigilante":
                position = playersJobPosition.get("Vigilante"); break;
            case "hitman":
                position = playersJobPosition.get("Mafia: Hitman"); break;
        }
        
        //If the player did not target someone or was stopped by the barman
        if(target(position)!=-1 && !of.get(position).inBar()){
            if(of.get(target(position)).isProtected()){
                of.get(bodyguardPosition).setIsTargeted(true);;
                //If the target was protected, bodyguard is killed instead    
            }
            
            else{
                of.get(target(position)).setIsTargeted(true);
                //Sets the target of the hitman to 1. this kills them :(
                
            }
        }
    }

    protected void doctor(){
        int position = playersJobPosition.get("Doctor");
        //If the player did not target someone to save or was stopped by the barman
        if(target(position) !=-1 && !of.get(position).inBar()){
        
        	of.get(target(position)).setIsHealed(true);
        	//This saves them from death
        }
    }

    protected void godFather(){
        int position = playersJobPosition.get("Mafia- GodFather");
        //If the player did not target someone or was stopped by the barman
        //Do nothing
        if(target(position) !=-1 && !of.get(position).inBar()) ;
        	// TODO
    }

    public List<Player> getPlayerInfo(){
    	return of;
    }
}