
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
	private boolean isDead;
	
	//constructor for story object. Create a story object with a string as a parameter
	public Story (String str, boolean dead) {
		name = str;
		isDead = dead;
	}
	//method to extract information from text files
	public void information() {
		//file names
	    String fileName = "data/story/stories.txt";
	    String fileName2 = "data/story/locations.txt";
	    String fileName3 = "data/story/causeOfDeath.txt";
	    try {
	    	Scanner inputStream = new Scanner (new File(fileName));
	    
	    	//reads all info from the text file as a string
	    	String story = inputStream.nextLine();
	    	//splits the string at every semicolon and stores in array
	    	String[] stories2 = story.split(";");
	    	//adds that array to the stories ArrayList
	    	for (int i = 0; i < 10; i++) {
	    		stories.add(stories2[i]);
	    	}
	    	
	    	
	    
	    	inputStream.close();
	    	
	    	
	    	/**
	    	 * Put this in a method called locationScanner
	    	 * Do this for all three scanners
	    	 */
	        Scanner inputStream2 = new Scanner (new File(fileName2));
	        while(inputStream2.hasNextLine()){
	    		String line = inputStream2.nextLine();
	    		locations.add(line);
	        }
	        inputStream2.close();
	        
	        Scanner inputStream3 = new Scanner (new File(fileName3));
	        
	        String cause = "";
	        //reads input from text file until it has read everything
	        //stores it as a string
	        while (inputStream3.hasNextLine() == true) {
	        	cause += inputStream3.nextLine();
	        }
	        //splits the string at every semicolon
	        String[] causeOfDeath2 = cause.split(";");
	        //adds that array to causeOfDeath ArrayList
	        for (int k = 0; k < 10; k++) {
	        	causeOfDeath.add(causeOfDeath2[k]);
	        }
	        
	        inputStream3.close();
	    }//condition if compiler cannot find the file
	    catch(FileNotFoundException e){
	    	System.out.println("File not found");
	    }
	}
	//prints out what happens if the current target gets killed
	public String[] dead() {
		int randomNumber = new Random().nextInt(10);
		String dead[] = new String[3];
		dead[0] = "They were killed by the assasin.";
		//prints out a random outcome from causeOfDeath ArrayList
		dead[1] = causeOfDeath.get(randomNumber);
		dead[2] = "Cause of death: " + dead[1].toString();
		return dead;
	}
	//prints out what happens if the current target gets healed by the doctor
	public String[] healed() {
		int randomNumber = new Random().nextInt(10);
		String[] healed = new String[3];
		healed[0] = "They were saved by the doctor.";
		//prints out random outcome from causeOfDeath ArrayList
		healed[1] = causeOfDeath.get(randomNumber);
		healed[2] = "Potential cause of death: " + healed[1].toString();
		return healed;
	}
	//prints out a random story, name and location
	public String[] initialScenario() {
		int randomNumber = new Random().nextInt(10);
		//randomizes a story and location from ArrayLists
		String[] initalize = new String[5];
	    initalize[0] = stories.get(randomNumber);
	    initalize[1] = locations.get(randomNumber);
	    initalize[2] = "Name: " + name;
	    initalize[3] = "Location: " + initalize[1].toString();
	    initalize[4] = "Here's what happened...\n" + initalize[0].toString();
	    return initalize;
	}	
	
	public String getLocation(){
		int rand = new Random().nextInt(locations.size());
		return locations.get(rand);
	}
	
	public String getEvent(){
		if(isDead){
			return "dead";
		}else{
			return "saved";
		}
	}
}
