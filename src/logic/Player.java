package logic;


/**
 * This class stores the data of each player. All these data is going to be used
 * in a list that contain all the data for players. 
 * Author: Mahsa Lotfi, UCID#10072013
 */
public class Player {
	
	/**
	 * Instance variables.
	 */
	private int playPosition;
	private int playerTarget;//target of player to be lynched
	private String name;//name of the player
	private String role;//role of the player
	private String roleInfo;//info for each role which is instruction related to the role
	private boolean isMafia;//true if the person is mafia member
	private boolean isDead;//true if the player is dead
	private boolean isHealed;//true if the player is healed
	private boolean isTargeted;//true if the player is targeted by mafia
	private boolean isProtected;//true if the bodygaurd protect the player to be lynched
	private boolean inBar;//If true the player cannot do anything that night.
	private boolean isLynched;//true if the player is lynched.
	
	
	/**
	 * Setter methods.
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setPlayPosition(int playPosition) {
		this.playPosition = playPosition;
	}
	public void setPlayerTarget(int playerTarget){
		this.playerTarget = playerTarget;
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
	
	public void setIsLynched(boolean isLynched) {
		this.isLynched = isLynched;
	}
	
	
	/**
	 * getter methods.
	 */
	public String getName() {
		return name;
	}
	public int getPlayPosition() {
		return playPosition;
	}
	public int getPlayerTarget(){
		return playerTarget;
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
	public boolean islynched() {
		return isLynched;
	}
	
}