package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import playerInfo.Player;

/**
 * This class will create a list to store all the information 
 * about each player and pass that list by a getter method.
 * It will shuffle the roles and then assign them to each names.
 * Also it will assign a target for the Lyncher role and pass it by a getter method.
 * 
 * Author: Mahsa Lotfi 10072013 
 */	
public class FileUtil{
	/**
	 * Instance variables
	 */
	private List<String> names;
	private List<String> roles;
	protected List<String> line;
	
	List<Player> playerInfo = new ArrayList<>();
	private int lynchTargetID = -1;

	protected final String save = "data/saveGame.txt";
	
	public List<Player> newGame(List<String> names, List<String> rolesSelected){
		this.names = names;
		this.roles = rolesSelected;
		setAllPlayers();
		return playerInfo;
	}
	

	public void newFile(List<String> names, List<String> rolesSelected){
		saveFile(newGame(names, rolesSelected), setLynchTarget());
	}
	
	public void loadFile(){
		int position = 0;
		try {
			System.out.println("Loading Player data from file: "+save);
			Scanner fileScanner = new Scanner(new File(save));
			while(fileScanner.hasNextLine()){
				String currentLine = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(currentLine);
				line = new ArrayList<>();
				while(lineScanner.hasNext()){
					line.add(lineScanner.next());
				}
				lineScanner.close();
				position = scanLine(line, position);
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			loadException();
		}
	}
	
	public void saveFile(List<Player> playerInfo, int lynchTarget){
		try {
			PrintWriter pw = new PrintWriter(save);
			System.out.println("SAVING Player info to "+save);
			for(Player p: playerInfo){
				pw.print(p.getStatus()+" ");
				pw.print(String.valueOf(p.wasLynched())+" ");
				pw.print(p.getRole()+" ");
				if(p.getRole().contains("Lyncher")){
					pw.print(lynchTarget+" ");
				}
				pw.print(p.getName());
				pw.println();
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
		
	/**
	 * Method to shuffle the roles and matches them with the names.
	 */
	public void setAllPlayers(){
		Collections.shuffle(roles);
		for(int i =0; i<names.size(); i++){
			playerInfo.add(CreatePlayerUtil.createPlayer(names.get(i),roles.get(i),i));
		}
	}
	
	/**
	 * 
	 */
	protected void loadException(){
		System.out.println("ERROR: Could not find file :"+save);
		System.out.println("Loading internal default save game");
		List<String> names = new ArrayList<>(
			Arrays.asList("Jon Snow","Daenerys","Cersei","Arya","Samwell","Bran","Sandor","Gregor","Tyrion"));
		List<String> roles = new ArrayList<>((
			Arrays.asList("Town","Detective","Mafia: Barman","Mafia: Hitman","Doctor","Survivor","Lyncher","Bodyguard","Townie","Mafia-Godfather")));
		for(int i=0;i<names.size();i++){
			Player p = CreatePlayerUtil.createPlayer(names.get(i),roles.get(i),i);
			playerInfo.add(p);
		}
	}
	
	/**
	 * 
	 * @param line
	 * @param positionID
	 * @return
	 */
	protected int scanLine(List<String> line, int positionID){
		int status = Integer.parseInt(line.get(0));
		boolean lynched = Boolean.valueOf(line.get(1));
		String role = line.get(2);
		
		String name;
		if(role.contains("Mafia")){
			role+=" "+line.get(3);
			name = getName(line,4);
		}else if(role.contains("Lyncher")){
			lynchTargetID = Integer.parseInt(line.get(3));
			name = getName(line,4);
		}else{
			name = getName(line,3);
		}
		
		Player p = CreatePlayerUtil.createPlayer(name,role,positionID,status,lynched);
		playerInfo.add(p);
		return positionID++;
	}
	
	/**
	 * 
	 * @param line
	 * @param place
	 * @return
	 */
	private String getName(List<String> line, int place){
		String name="";
		for(int i=place;i<line.size();i++){
			name+=line.get(i)+" ";
		}
		name=name.substring(0, name.length()-1);
		return name;
	}
	
	/**
	 * This method will find and set a target for the lyncher.
	 */
	public int setLynchTarget(){
		for(int i = 0; i < roles.size(); i++){
			if(!playerInfo.get(i).getRole().equalsIgnoreCase("Lyncher")){
				this.lynchTargetID = findPosition(roles.get(i));
				return findPosition(roles.get(i));
			} 
		}
		return -1;
	}
	
	/**
	 * This method will find the position of a role 
	 * @param role
	 */
	public int findPosition(String role){
		for(Player p : playerInfo){
			if(p.getRole().equalsIgnoreCase(role)) {
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