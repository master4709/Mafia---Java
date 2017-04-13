package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import playerInfo.Player;

/**
 * This class will create a new game and also load an existing 
 * game by reading from a text file.
 * This class will also create a list to store all the information 
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
	private List<Player> playerInfo = new ArrayList<>();
	
	private int lynchTargetID = -1;

	private final String saveName = "data/saveGame.txt";
	
	/**
	 * default constructor
	 */
	public FileUtil(){
	}
	
	/**
	 * This method will shuffle the roles and by using
	 * another method, setAllNewPlayers(), assign those roles
	 * to the names. If there is a Lyncher role in the role pile 
	 * it will call the setLynchTarget method to set a target 
	 * for the lyncher.
	 * @param names, List<String>
	 * @param roles, List<String>
	 */
	public void newFile(List<String> names, List<String> roles){
		playerInfo = new ArrayList<>();
		Collections.shuffle(roles);
		setAllNewPlayers(names,roles);
		if(findPosition("Lyncher")!=-1){
			lynchTargetID = setLynchTarget(roles);
		}else{
			lynchTargetID = -1;
		}
		SaveFileUtil.saveGame(playerInfo, lynchTargetID);
	}
	
	
	/**
	 * This method will load an existing game by try to read 
	 * the last stage of that game from a text file and store 
	 * it as a List<String>. The catch exception will call 
	 * loadException method to find FileNotFindException.
	 */
	public void loadFile(){
		lynchTargetID = -1;
		playerInfo = new ArrayList<>();
		int position = 0;
		List<String> line;
		try {
			System.out.println("Loading Player data from file: "+saveName);
			Scanner fileScanner = new Scanner(new File(saveName));
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
	
	/**
	 * FileNotFoundException for loadFile method.
	 * This method will print out if the file of previous game 
	 * was not found and instead will load the internal default
	 * save game. It will assign all the names and roles to specific 
	 * ones. 
	 */
	private void loadException(){
		System.out.println("ERROR: Could not find file :"+saveName);
		System.out.println("Loading internal default save game");
		List<String> names = new ArrayList<>(
			Arrays.asList("Jon Snow","Daenerys","Cersei","Arya","Samwell","Bran","Sandor","Gregor","Tyrion","Bron"));
		List<String> roles = new ArrayList<>((
			Arrays.asList("Town","Detective","Mafia: Barman","Mafia: Hitman","Doctor","Survivor","Lyncher","Bodyguard","Prostitute","Mafia-Godfather")));
		newFile(names,roles);
	}
	
	/**
	 * This method will be called from loadFile method and it 
	 * will set the status, name, role and position of each player 
	 * properly from the list that was created from reading the 
	 * saved text file.
	 * @param line, List<String>
	 * @param positionID, integer
	 * @return
	 */
	private int scanLine(List<String> line, int positionID){
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
		playerInfo.add(CreatePlayerUtil.createPlayer(name,role,positionID,status,lynched));
		return positionID++;
	}
	/**
	 * Adds the remaining String elements in the the List together to make the player name
	 * @param line - current line of the scanner
	 * @param place - place in the line to start adding the rest of the list to the name
	 * @return String - name of player
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
	 * This method will store the names of the players 
	 * and their role into the playerInfo List.
	 * @param names
	 * @param roles
	 */
	private void setAllNewPlayers(List<String> names,List<String> roles){
		for(int i =0; i<names.size(); i++){
			playerInfo.add(CreatePlayerUtil.createPlayer(names.get(i),roles.get(i),i));
		}
	}
	
	/**
	 * This method will set a target to Lyncher role.
	 * @param roles, List<String>
	 * @return target, integer
	 */
	private int setLynchTarget(List<String> roles){
		Random r = new Random();
		int target = r.nextInt(roles.size());
		int lyncher = findPosition("Lyncher");
		while(target==lyncher){
			target = r.nextInt(roles.size());
		}
		return target;
	}
	
	/**
	 * This method will find the position of a role and return it.
	 * @param role, String
	 */
	private int findPosition(String role){
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
		List<Player> clone = new ArrayList<>(playerInfo);
		playerInfo = new ArrayList<>();
		return clone;
	}
	
	/**
	 * Getter method for lynchTargetID.
	 */
	public int getLynchTarget(){
		return lynchTargetID;
	}
}