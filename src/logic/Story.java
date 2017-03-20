package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


/*To use the class you must create a story object with the name of the current target as a parameter.  
 The class then calls initialScenario() right away and based of the status of the target 
 defined in the game class, either method dead() or healed() gets called. It prints out a 
 story based on what happened to the target during that turn.*/

public class Story {
	//instance variables declared to store the information for text files
	private ArrayList<String> stories = new ArrayList<String>();
	private ArrayList<String> locations = new ArrayList<String>();
	private ArrayList<String> causeOfDeath = new ArrayList<String>();
	//instance variable for the name of the current target
	private boolean isDead;
	private String name;
	
	//constructor for story object. Create a story object with a string as a parameter
	public Story (String str, boolean dead) {
		name = str;
		isDead = dead;
	}
	//calls scanner methods
	public void information() {
		storyScanner();
		locationScanner();
		causeScanner();
	}
	//extracts info from stories.txt
	public void storyScanner() {
		String fileName = "data/story/stories.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				stories.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found");
	    }
	}
	//extracts info from locations.txt
	public void locationScanner() {
		String fileName = "data/story/locations.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				locations.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found");
	    }
	}
	//extracts info from causeOfDeath.txt
	public void causeScanner() {
		String fileName = "data/story/causeOfDeath.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				causeOfDeath.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found");
	    }
	}
	public String getName() {
		return name;
	}
	public String getStory(){
		int rand = new Random().nextInt(stories.size());
		return stories.get(rand);
	}
	public String getLocation(){
		int rand = new Random().nextInt(locations.size());
		return locations.get(rand);
	}
	public String getCause() {
		int rand = new Random().nextInt(causeOfDeath.size());
		return causeOfDeath.get(rand);
	}
	public String getEvent(){
		if(isDead) {
			return "They were killed by the mafia.";
		}
		else {
			return "They were healed by the doctor.";
		}
	}
}
