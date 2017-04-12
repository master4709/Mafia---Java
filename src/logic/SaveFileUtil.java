package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

import playerInfo.Player;

public class SaveFileUtil {
	
	private static final String saveFile = "src/data/saveGame.txt";
	
	public static void saveGame(List<Player> playerInfo, int lynchTarget){
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
	
	public static void deleteFile(){
		try {
			System.out.println("Game Over");
			System.out.println("Deleteing Save Game");
			Files.deleteIfExists(new File(saveFile).toPath());
		} catch (IOException e) {
			System.out.println("Could not delete saveGame");
		}
	}
}
