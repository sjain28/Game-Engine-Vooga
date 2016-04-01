Voogasalad: Design Document: Team DoovalSalad
===================
# Team members
* Anita Desai
* Arjun Desai
* Harry Guo
* Saumya Jain
* Michael Kuryshev
* Hunter Lee
* Nick Lockett
* Krista Opsahl-Ong
* Aditya Srinivasan
* Joshua Xu

# Introduction
- For our project, we will be creating a program that enables users to create and play platformer games (ex: Super Mario Bros., Sonic, Flappy Bird, Donkey Kong). Platformer games often have the main hero (character) moving across the terrain and the map in order to progress. There are a number of key qualities of Scrolling Platformer that make it a unique game genre. First, platforms used in Scrolling Platformer can have different environments and traits. For instance, in some games, certain platforms can be passed through from above but not below. Others can have varying levels of friction, such as ice, which causes the player to have reduced control of his character’s movement. Also, some platform games have differing components in the map such as physical obstacles rather than just enemies that the players have to avoid such as boxes, walls,  lakes or lava (the last two of which will probably end the game). Additionally, some platform games have objects, such as vines in Donkey Kong or ropes in Sonic, that the main hero can and must make use of to reach another platform. 
- Moreover, the direction of movement is unique to each Scrolling Platformer. The game may scroll either sideways--left and right (e.g. Mario and Sonic), upwards (e.g. DoodleJump), or downwards (FallDown) and gravity levels differ depending on the game. Levels can also progress in different ways. In some scenarios the scene changes to a new one when the hero moves to the other side of the screen. In other scenarios, the screen is constantly scrolling. This could also be customized to fit other sub-goal conditions (conditions that check for whether the user gets to move onto the next level).
- Furthermore, collisions add another layer of complexity in Scrolling Platformers. One type of collision could be where a hero jumps on an enemy to attack it. Another could be when a hero shoots an enemy in order to attack.  Additionally, blocks such as a spike blocks or lava can damage the hero or make it bounce off.
Our design will need to be able to handle these multiple layers of complexity, namely, objects, blocks, events, physics, etc.. An extremely important aspect of platformers is handling the collisions between the hero and platforms, which will be done through the physics engine. Our program will need to be handle multiple scrolling directions, and handling what events will cause a level end or the entire game to end.

# Overview
- The primary goal of our project’s design is to write a Scrolling Platformer game framework that can support the seamless interaction of the game authoring environment and game playing environment. A key characteristic we will strive for is the flexibility that users will have when they are creating the game so that they will be able to manage the creation of data from the authoring environment and reading-in of data in the player environment. For this key functionality, we have decided that game data would be a channel through which the game authoring environment and player will interact.

![](design_resources/author_player_data.png?raw=true)

- This abstraction allows users to create hierarchies of levels and sprites, enabling them to come up with a unique game of their own. The following is the bigger picture of our rough design of the program.

![](design_resources/overall_view.png?raw=true)

### Modules
Modules
* Authoring 	
	* Resources: all resources (author-uploaded and default) available for author to use in game
		* Explorer.java: Explorer Window- displays the resources to the user to select from
		* ResourceDragger.java: allow user to drag resource from Explorer window onto Authoring Window
		* ResourceProperties.properties: maps resource name (as written in XML) to path of the resource on the local device
		* Resources.xml: uses XML tagging to mirror folder-tree structure where the leaves are
		* Implementation
		  	* Double click lets you preview: opens image, plays music, etc

 	* Authoring Game: how the author creates the game on the Graphics Window
		* ResourceDragger.java: allow user to drag resource from Explorer window onto Authoring Window
		* Properties.java: Properties Window- displays variables of selected object
		* AuthoringWindow.java: Scrollable pane where the user can drag and drop items onto and program interactions
		* Objects: creating and editing objects and their variables

 	* Cause-Effect Relationship: regulates the events (causes and effects having to do with variables and collisions)
		* Event.java: contains a bundle of causes and a bundle of effects

 	* Saving/Loading Game: process of saving and loading the game data the author created
		* XMLWriter.java: used to write data onto XML file for Game Engine to run
		* XMLReader.java: used to read data from XML to load authoring workspace or launch game
		* Game Data XMLs: tracks all the additions and deletions the author has made to the game
			* Used by Game Engine to launch game
			* Used to save progress of user

 	* Objects: all objects (static, character, AI)
		* Characteristics (Interfaces)
			* Imageable.java: can be represented by an image
			* Moveable.java: can move
			* Sizeable.java: can be sized (related to Imageable)
			* Collidable.java: interacts with other objects (onCollision)
	* StaticObject.java: data and management related to objects that dont move
	* CharacterObject.java: data and management related to user-controlled objects
	* AIObject.java: data and management related to computer-generated/controlled objects
			
 	* Miscellaneous:
		* Vector.java: represents vectors in x,y,z-coordinate axis

![](design_resources/IMG_2098.JPG?raw=true)


* Player
  * Front-end
    * Through the use of the Authoring Module, the player will have full access to the keyboard, mouse, and other events that were created to run the game.
  * Back-end
    * (add a description)
* Data
    * (Explain the use of XStream here and how it will be dynamically updated for smooth interaction between authoring environment and game engine)
* Physics
    * (Copy my writing in google doc with the diagrams)
* Goal
    * (add a description)
* Resources
    * (add a description)
	

	
	
	
	
	
# User Interface
Authoring: 

![](design_resources/IMG_2097.JPG?raw=true)

UI Design

* Explorer: resources for user to use (images, sounds, fonts, texture, colors  etc) organized with folders 
* Properties: where selected objects variables appear and can be edited
* Graphics Window: GUI for game authoring environment
 	* Characteristics
		* ScrollPane
		* StackPane inside of ScrollPane
	* Uses
		* Drag and drop images
		* Select objects to edit
	* Toolbar
		* Run
		* Save
		* Add Object
	* Menubar
		* File
			* New Game
			* Save
			* Load
			* Run
		* Edit
			* New
				* Game Object
				* Event
				* Global Variable
			* Copy
			* Paste
		* Help
		
# Design Details






# Example games
* Super Mario Bros
  * Super Mario Bros. has multiple platform types, enemy types, the screen that moves with the main hero (Mario), and a goal of reaching a certain point (castle) or defeating a boss (bowser). Our level creator will work for all of these features. First, we will let users in the authoring environment to set create the goal of reaching a flagpole (castle) for the normal levels or defeat a boss (bowser) in a boss level. This would be done using cause-effect structure that we define for all events. Enemy behavior in Mario is simple in that enemies go forward and backward and change directions when they hit an object (wall). This type of behavior can be set in the authoring environment. The screen scrolling with the hero feature will also be one of our scrolling options that we pre-set for the user. Hero actions include horizontal walking (left and right), jumping, and possible state change (eat a mushroom, eat a flower). The physics engine will be in charge of handling all of the collision detection and collision resolution.
  
* Flappy Bird
  * Flappy Bird is a simple game in which the user controls a bird and trys to avoid pipes that stick down from the sky and on the ground, with each set of pipes they pass the user scores more points. It is a continuous scroll with one command, usually keyboard.

* Sonic (?)
  *
  
  
# Design Considerations

* Key characteristics considered
  * Abstraction
    *
  * Extendability
    *
  * Concurrency
    * (by concurrency, I mean authoring environment and player could be run concurrently?)
  * And more
* Alternative designs proposed
  * (not necessary, but we could talk about Unity and how we could have used a scripting language to make it as flexible as possible for the user)
