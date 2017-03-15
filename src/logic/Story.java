package logic;
//Ronelle Bakima, 30005568

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
	
	//method to extract information from text files
	public void information() {
		//file names
	    String fileName = "C:/Users/pierc/OneDrive - University of Calgary/CompSci 233/Mafia2/src/stories.txt";
	    String fileName2 = "C:/Users/pierc/OneDrive - University of Calgary/CompSci 233/Mafia2/src/locations.txt";
	    String fileName3 = "C:/Users/pierc/OneDrive - University of Calgary/CompSci 233/Mafia2/src/causeOfDeath.txt";
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
	    	
	        Scanner inputStream2 = new Scanner (new File(fileName2));
	        
	        String location = "";
	        //reads in all info until it's read everything in the text file
	        //stores it as a string
	        while (inputStream2.hasNextLine() == true) {
	        	location += inputStream2.nextLine();
	        }
	        //splits the string at semicolons
	    	String[] locations2 = location.split(";");
	    	//stores in locations ArrayList
	    	for (int j = 0; j < 10; j++) {
	    		locations.add(locations2[j]);
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
	public void dead() {
		int randomNumber = new Random().nextInt(10);
		System.out.println("They were killed by the assasin.");
		//prints out a random outcome from causeOfDeath ArrayList
		String cause1 = causeOfDeath.get(randomNumber);
		System.out.println("Cause of death: " + cause1);
	}
	//prints out what happens if the current target gets healed by the doctor
	public void healed() {
		int randomNumber = new Random().nextInt(10);
		System.out.println("They were saved by the doctor.");
		//prints out random outcome from causeOfDeath ArrayList
		String cause1 = causeOfDeath.get(randomNumber);
		System.out.println("Potential cause of death: " + cause1);
	}
	//prints out a random story, name and location
	public void initialScenario() {
		int randomNumber = new Random().nextInt(10);
		//randomizes a story and location from ArrayLists
	    String story1 = stories.get(randomNumber);
	    String location1 = locations.get(randomNumber);
	    System.out.println("Name: " + name);
	    System.out.println("Location: " + location1);
	    System.out.println("Here's what happened...\n" + story1);
	}

}
