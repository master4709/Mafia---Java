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
	private String name;
	
	//constructor for story object. Create a story object with a string as a parameter
	public Story (String str) {
		name = str;
	}
	//calls scanner methods
	public void information() {
		storyScanner();
		locationScanner();
		causeScanner();
	}
	//extracts info from stories.txt
	private void storyScanner() {
		String fileName = "src/data/story/stories.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				stories.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found in location: " +fileName);
	    }
	}
	//extracts info from locations.txt
	private void locationScanner() {
		String fileName = "src/data/story/locations.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				locations.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found in location: " +fileName);
	    }
	}
	//extracts info from causeOfDeath.txt
	private void causeScanner() {
		String fileName = "src/data/story/causeOfDeath.txt";
		try {
			Scanner inputStream = new Scanner (new File(fileName));
			while(inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				causeOfDeath.add(line);
			}
			inputStream.close();
		}
		catch(FileNotFoundException e){
	    	System.out.println("File not found in location: " +fileName);
	    }
	}
	/**
	 * getters for variables
	 */
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
	
}
