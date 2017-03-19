package logic;


/**
 * This class stores the data of each player
 * Author: Mahsa Lotfi Gaskarimahalleh, UCID#10072013
 */
public class Player {
	
	private int playPosition;
	private int playerTarget;
	private int oldPlayerTarget;
	private String name;
	private String role;
	private String roleInfo;
	private String goal;
	private boolean isMafia;
	private boolean isDead;
	private boolean isHealed;
	private boolean isTargeted;
	private boolean isProtected;
	private boolean inBar;//If true the player cannot do anything that night.
	private boolean isLynched;
	
	
	
	//Setters//
	public void setName(String name) {
		this.name = name;
	}
	public void setPlayPosition(int playPosition) {
		this.playPosition = playPosition;
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
	public void setIsHealed(boolean isHealed){
		this.isHealed = isHealed;
	}
	public void setIsDead(boolean isDead){
		this.isDead = isDead;
	}
	public void setIsTargeted(boolean isTargeted){
		this.isTargeted = isTargeted;
	}
	public void setIsProtected(boolean isProtected){
		this.isProtected = isProtected;
	}
	public void setInBar(boolean isBar) {
		this.inBar = isBar;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public void setIsLynched(boolean isLynched) {
		this.isLynched = isLynched;
	}
	
	
	
	//Getters//
	public String getName() {
		return name;
	}
	public int getPlayPosition() {
		return playPosition;
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
	public boolean isHealed(){
		return isHealed;
	}
	public boolean isDead(){
		return isDead;
	}
	public boolean isTargeted(){
		return isTargeted;
	}
	public boolean isProtected(){
		return isProtected;
	}
	public boolean inBar() {
		return inBar;
	}
	public String getGoal() {
		return goal;
	}
	public boolean islynched() {
		return isLynched;
	}
	
}