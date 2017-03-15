package logic;
/**
 * Author: Mahsa Lotfi Gaskarimahalleh, UCID#10072013
 */

/**
 * This class stores the data of each player
 */



public class Player {
	
	private int playPosition;
	private int status;//0:Alive | 1:Targeted by Mafia/Vigilante | 2:Heal | 3:Protected | 4: Dead for more that one turn
	private int playerTarget;
	private int oldPlayerTarget;
	private String name;
	private String role;
	private String roleInfo;
	private String goal;
	private boolean isMafia;
	private boolean isBar;//If true the player cannot do anything that night.
	private boolean wasLynched;
	
	
	
	//Setters//
	public void setName(String name) {
		this.name = name;
	}
	public void setPlayPosition(int playPosition) {
		this.playPosition = playPosition;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setPlayerTarget(int playerTarget){
		this.playerTarget = playerTarget;
	}
	public void setOldPlayerTarget(int oldPlayerTarget) {
		this.oldPlayerTarget = oldPlayerTarget;
	}
	public void setRole(String role){
		this.role = role;
	}
	public void setRoleInfo(String roleInfo){
		this.roleInfo = roleInfo;
	}
	public void setIsMafia(boolean isMafia) {
		this.isMafia = isMafia;
	}
	public void setInBar(boolean isBar) {
		this.isBar = isBar;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public void setWasLynched(boolean wasLynched) {
		this.wasLynched = wasLynched;
	}
	
	
	
	//Getters//
	public String getName() {
		return name;
	}
	public int getPlayPosition() {
		return playPosition;
	}
	public int getStatus() {
		return status;
	}
	public int getPlayerTarget(){
		return playerTarget;
	}
	public int getOldPlayerTarget(){
		return oldPlayerTarget;
	}
	public String getRole(){
		return role;
	}
	public String getRoleInfo(){
		return roleInfo;
	}
	public boolean isMafia() {
		return isMafia;
	}
	public boolean inBar() {
		return isBar;
	}
	public String getGoal() {
		return goal;
	}
	public boolean wasLynched() {
		return wasLynched;
	}
	
}