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
	private List<Player> playerInfo = new ArrayList<>();
	
	private int lynchTargetID = -1;

	private final String saveName = "src/data/saveGame.txt";
	
	
	public FileUtil(){
	}
	
	public void newFile(List<String> names, List<String> roles){
		Collections.shuffle(roles);
		setAllNewPlayers(names,roles);
		if(findPosition("Lyncher")!=-1){
			lynchTargetID = setLynchTarget(roles);
		}else{
			lynchTargetID = -1;
		}
		
	}
	
	public void loadFile(){
		lynchTargetID = -1;
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
	 * 
	 */
	private void loadException(){
		System.out.println("ERROR: Could not find file :"+saveName);
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
	 * SHOULD RENAME
	 * Method to shuffle the roles and matches them with the names.
	 */
	private void setAllNewPlayers(List<String> names,List<String> roles){
		for(int i =0; i<names.size(); i++){
			playerInfo.add(CreatePlayerUtil.createPlayer(names.get(i),roles.get(i),i));
		}
	}
	
	/**
	 * This method will find and set a target for the lyncher.
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
	 * This method will find the position of a role 
	 * @param role
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