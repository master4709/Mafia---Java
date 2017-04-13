# MAFIA README.MD #

A Computer Science 233 Final Project
Copyright 2017 All rights reserved

### SUMMARY ###

* Authors:
   * Christilyn Arjona	Role&Player SetUp / SetUp GUI
   * Ronelle Bakima		Story / Story IO / Story GUI
   * Pierce de Jong		Game Cycle / Save IO / Game GUI
   * Mahsa Lotfi		Player Classes / Main GUI
   * Elvin Limpin		Action Logic / OO Architect / UI&UX
* University of Calgary CPSC 233 L01 Winter 2017 Team XVIII (18)
* Version 4.20
* Not available in a public repository as of Version 4.20

### How do I get set up? ###

##### Compiling and Running ######

1) Use a computer with Java/JDK installed
* Here are instructions for installing Java:
* http://pages.cpsc.ucalgary.ca/~verwaal/courses/219/dev.php

2) Once Java is Installed:
* Windows: Run MafiaWindows.bat
* Mac or Linux: Run MafiaLinux.bat
* On Eclipse via File -Import Projects from File System...
  -Select the correct directory for Mafia and press finish
  -Open Mafia/src/run/RunMafia.java in the package explorer
  -Press the Green Button 

##### Set up notes ######

* There is only one CONFIG in this version
* For upcoming versions, a CONFIG file will be introduced
    to keep track of languages/alternate themes
* We use the test game feature to skip the player setup

### What is Mafia? ###

Mafia is a party game designed to have two teams:

1.The Informed minority -The Mafia (Each member knows who the other Mafia members are)
2.The Uninformed majority -The Town (No member knows the side of another player)

The Mafia is trying to take control of the town by gaining a majority while
the town is trying to lynch all members of the Mafia. After each lynching,
night passes and specific players will perform night actions against other 
players.


##### Application Overview ######

Mafia is a role-playing game for parties.
Traditionally, a "god" player must be present
who keeps track of the decisions players make

Through this application, we eliminate the
need of the god player

The Phone removes the need for one player to lead the group and tell everyone else
to go to sleep, going through each players actions, and also making a story

The application removes the need for everyone to close their eyes for a pro-longed time
while most of the players do his/her night action. The phone is instead passed around the 
group while everyone selects his/her action for the night, allowing everyone to continue
to talk to each other and have fun. At the end of the night a story will appear to be read
out to the entire group
 
The phone also allows every player to play instead of one player
forced to be God 

Members of the Mafia know who each other are where as Town members
have no idea who anyone else is

###### Gameplay #####

Goals:
* Team Mafia: Gain control of the town through lynching, killing 
	and other actions during then night
* Team Town: Lynch all of Mafia members of Kill them (Vigilante)
* Lown Wolf:
	1. Lyncher: Win by lynching a specific player during the day
	2. Survivor: Win by being the last member of the Town alive

Setup:
* 5-12 players can play the game
* Players are randomly assigned a role
* Possible Roles are chosen by players

Game Cycle:
*Day: Each day consists of player discussing between each other
and then voting to Lynch and kill one player
*Night: Each player takes control of the phone in the order they
inputed their names. The player then selects a target for the 
night action. (E.G. killing, healing, stopping another player)
Game Over:
*Mafia, Town, or a Lone Wolf wins once their goal
is achieved

### Contribution guidelines ###

* Work on your own class, unless you ask for permission
* Alert the group chat whenever you make commits
* Properly document your code!

##### Members ######



###### Markers / Instructors ######

* Use the class diagram as reference
* Use test game to skip the setup functionality
* Overview of packages:
    *run - initializes the frame and controls the displayPackages
    *logic - contains mechanics for the game cycle and game
    over (game) as well as the mechanics for generating
    stories (Story)
    logic also handles new game/save file/ load file management
    *myJStuff - custom themes
    *playerInfo - contains object Oriented classes that
    differentiate the different player classes
    *displayMain - GUI and controller for the main panel
    *displaySetUp - GUI and controller for the setup portion
    *displayGame - GUI and controller for the game cycle,
    player checking, and game over

### Who do I talk to? ###

* For more information, contact:
	CPSC 233 L01 Winter 2017: Team XVIII
	-- OR --
	readme author
	Elvin Limpin 30018832
	elvin.limpin@ucalgary.ca