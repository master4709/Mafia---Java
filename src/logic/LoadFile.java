package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import playerInfo.*;

public class LoadFile extends CreatePlayer{
	
	private final String save = "data/saveGame.txt";
	
	private List<String> line;
	
	private List<Player> playerInfo = new ArrayList<>();
	
	private int lynchTarget = -1;
	
	public LoadFile(){
		
	}
	
	public void scan(){
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
				
				Player p = createPlayer(name,role,position,status,lynched);
				playerInfo.add(p);
				position++;
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Could not find file :"+save);
			System.out.println("Loading internal default save game");
			List<String> names = new ArrayList<>(Arrays.asList("Jon Snow","Daenerys","Cersei","Arya","Samwell","Bran","Sandor","Gregor","Tyrion"));
			List<String> roles = new ArrayList<>((Arrays.asList("Town","Detective","Mafia: Barman","Mafia: Hitman","Doctor","Survivor","Lyncher","Bodyguard","Townie","Mafia-Godfather")));
			for(int i=0;i<names.size();i++){
				Player p = createPlayer(names.get(i),roles.get(i),i);
				playerInfo.add(p);
			}
		}
	}
	
	private String getName(List<String> line, int place){
		String name="";
		for(int i=place;i<line.size();i++){
			name+=line.get(i)+" ";
		}
		name=name.substring(0, name.length()-1);
		return name;
	}
	
	public List<Player> getPlayers(){
		return playerInfo;
	}
	
	public int getLynchTarget(){
		return lynchTarget;
	}
}
