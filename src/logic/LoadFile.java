package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import playerInfo.Barman;
import playerInfo.Bodyguard;
import playerInfo.Detective;
import playerInfo.Doctor;
import playerInfo.Godfather;
import playerInfo.Hitman;
import playerInfo.Lyncher;
import playerInfo.Player;
import playerInfo.Survivor;
import playerInfo.Town;
import playerInfo.Vigilante;

public class LoadFile {
	
	private final String save = "data/saveGame.txt";
	
	private List<String> line;
	
	private List<Player> playerInfo = new ArrayList<>();
	
	private int lynchTarget = -1;
	
	
	
	public LoadFile(){
		
	}
	
	public void scan(){
		int position = 0;
		try {
			Scanner fileScanner = new Scanner(new File(save));
			while(fileScanner.hasNextLine()){
				String currentLine = fileScanner.nextLine();
				Scanner lineScanner = new Scanner(currentLine);
				line = new ArrayList<>();
				while(lineScanner.hasNext()){
					line.add(lineScanner.next());
				}
				lineScanner.close();
				int status = Integer.parseInt(line.get(0));
				boolean lynched = Boolean.valueOf(line.get(1));
				String role = line.get(2);
				
				String name;
				if(role.contains("Mafia")){
					role+=" "+line.get(3);
					name = getName(line,4);
				}else if(role.contains("Lyncher")){
					lynchTarget = Integer.parseInt(line.get(3));
					name = getName(line,4);
				}else{
					name = getName(line,3);
				}
				
				switch(role){
				case "Mafia: Barman": 		playerInfo.add(new Barman(name, position, status, lynched)); break;
				case "Bodyguard": 			playerInfo.add(new Bodyguard(name, position, status, lynched)); break;
				case "Detective": 			playerInfo.add(new Detective(name, position, status, lynched)); break;
				case "Doctor": 				playerInfo.add(new Doctor(name, position, status, lynched)); break;
				case "Mafia- Godfather": 	playerInfo.add(new Godfather(name, position, status, lynched)); break;
				case "Mafia: Hitman": 		playerInfo.add(new Hitman(name, position, status, lynched)); break;
				case "Lyncher": 			playerInfo.add(new Lyncher(name, position, status, lynched)); break;
				case "Survivor": 			playerInfo.add(new Survivor(name, position, status, lynched)); break;
				case "Vigilante": 			playerInfo.add(new Vigilante(name, position, status, lynched)); break;
				default: 					playerInfo.add(new Town(name, position, status, lynched)); break;
				}
				position++;
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Could not find file :"+save);
			e.printStackTrace();
		}
		
	}
	
	private String getName(List<String> line, int place){
		String name="";
		for(int i=place;i<line.size();i++){
			name+=line.get(i)+" ";
		}
		return name;
	}
	
	public List<Player> getPlayers(){
		return playerInfo;
	}
	
	public int getLynchTarget(){
		return lynchTarget;
	}
}
