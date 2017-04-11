package logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import playerInfo.Player;

public class SaveFileUtil {
	
	private final String saveFile = "src/data/saveGame.txt";
	
	public void saveGame(List<Player> playerInfo, int lynchTarget){
		try(PrintWriter pw = new PrintWriter(saveFile)) {
			System.out.println("SAVING Player info to "+saveFile);
			for(Player p: playerInfo){
				//System.out.print(p.getName() + " " + p.getStatus() + " | ");
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
			System.out.println("Could not save to file: "+saveFile);
			System.out.println("");
			e.printStackTrace();
		}
		
	}
}
