package logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import playerInfo.Player;

public class SaveFile {
	
	private String saveFile = "data/saveGame.txt";
	
	public SaveFile(){
		
	}
	
	public void save(List<Player> playerInfo, int lynchTarget){
		try {
			PrintWriter pw = new PrintWriter(saveFile);
			System.out.println("Saving Player info to "+saveFile);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
