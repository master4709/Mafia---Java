# MAFIA README.MD #

A Computer Science 233 Final Project
Copyright 2017 All rights reserved

### SUMMARY ###

* Authors:
   * Christilyn Arjona	Role&Player SetUp / SetUp GUI
   * Ronelle Bakima		Story / Story IO / Story GUI
   * Pierce De Jong		Game Cycle / Save IO / Game GUI
   * Mahsa Lotfi		Player Classes / Main GUI
   * Elvin Limpin		Action Logic / OO Architect / UI&UX
* University of Calgary CPSC 233 L01 Winter 2017 Team XVIII
* Version 4.20
* Not available in a public repository as of Version 4.20

### How do I get set up? ###

##### Compiling and Running ######

1) Use a computer with Java/JVM installed
* Here are instructions for installing Java:
* http://pages.cpsc.ucalgary.ca/~verwaal/courses/219/dev.php

2) Once Java is Installed:
* Windows: Run MafiaWindows.bat
* Mac or Linux: Run MafiaLinux.bat

##### Set up notes ######

* There is only one CONFIG in this version
* For upcomming versions, a CONFIG file will be introduced
    to keep track of languages/alternate themes
* We use the test game feature to skip the player setup

### What is Mafia? ###

##### Overview ######

Mafia is a role-playing game for parties.
Traditionally, a "god" player must be present
who keeps track of the decisions players make

Through this application, we eliminate the
need of the god

###### Gameplay #####

Goals:
* Team Mafia: become the majority by killing
the town members
* Team Town: lynch/kill all mafia
* Lone Wolf: Win alone by survivng/lynching a target

Setup:
* 5-12 players can play the game
* Players are randomly assigned a role
* Possible Roles are chosen by players

Game Cycle:
*Day: one player must be lynched everyday
as decided upon through real life discussion
*Night: players perform their unique actions

Game Over:
*Mafia, Town, or a Lone Wolf wins once their goal
is achieved

### Contribution guidelines ###

##### Members ######

* Work on your own class, unless you ask for permission
* Alert the group chat whenever you make commits
* Properly document your code!

###### Markers / Instructors ######

* Use the class diagram as reference
* Use test game to skip the setup functionality
* Overview of packages:
    *run - initializes the panel and calls the logic
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
	Elvin Limpin 30018832
	elvin.limpin@ucalgary.ca