#Team DoovalSalad: Extra use cases

###Game screen scrolls centered on the main character

* Primary Actor: Main character (sprite)
* Scope: GameRunner, LevelDataManager, DisplayScroller
* Level: All levels
* Brief: The main character moves in the game. When it moves to the right, the screen is centered about the main character and scrolls when the character moves.
* Stakeholders: GameDisplay, DisplayScroller
* PostCondition: The main character is at a different set of coordinates.
* Success Guarantees: The screen will have scrolled and the sprites (nodes) displayed in GameDisplay will be a different set of sprites.
* Preconditions: The game must be in play. The main character is alive and playable. The main character moves.

###Game screen scrolls constantly as time passes

* Primary Actor: Main character (sprite)
* Scope: GameRunner, LevelDataManager, DisplayScroller
* Level: All levels
* Brief: GameScreen is constantly scrolling as time ticks by. If the main character is out of the visible scope of the screen, he/she dies.
* Stakeholders: GameDisplay, DisplayScroller
* PostCondition: None.
* Success Guarantees: The screen will have scrolled and the sprites (nodes) displayed in GameDisplay will be a different set of sprites.
* Preconditions: The game must be in play. The main character is alive and playable. The main character moves.

###Speed up the game

* Primary Actor: GameDisplay
* Scope: Game runner, archetype display.
* Brief: If the user starts the game and decides to speed up the pace of the game, the user may click on the speed-up button to increase the framerate of the timeline.
* Stakeholders: archetype
* Preconditions: The game is in play and playable.
* PostCondition: GameDisplay is displaying the game at a faster speed (at a higher framerate).
* Minimal Guarantees: archetype continues to run and game is still playable.
* Success Guarantees: Game runs at a different framerate.
* Basic flow:
	1. While the game is in play, the archetype clicks SpeedUp button
	2. GameDisplay class calls GameRunner interface's SpeedUp method
	3. Framerate is updated in GameRunner
	4. AnimationTimer runs at the new framerate
    
###Speed down the game

* Primary Actor: GameDisplay
* Scope: Game runner, archetype display.
* Brief: If the user starts the game and decides to speed up the pace of the game, the user may click on the speed-down button to decrease the framerate of the timeline.
* Stakeholders: archetype
* Preconditions: The game is in play and playable.
* PostCondition: GameDisplay is displaying the game at a slower speed (at a lower framerate).
* Minimal Guarantees: archetype continues to run and game is still playable.
* Success Guarantees: Game runs at a different framerate.
* Basic flow:
	1. While the game is in play, the archetype clicks SpeedDown button
	2. GameDisplay class calls GameRunner interface's SpeedDown method
	3. Framerate is updated in GameRunner
	4. AnimationTimer runs at the new framerate

### A zigzag path is followed by a sprite entering main screen

* Primary Actor: Sprite in the animation
* Scope: Game runner, archetype display, eventsx
* Brief: When sprite authored to perform a zigzag enters the archetype display, it will follow a periodic zigzag path in its flight in game. The sprite can still collide with the archetype but ignores gravity and any friction.
* Stakeholders: archetype, Events, GameEngine, Physics (may extract physics apart from general motion)
* Preconditions: Zigzagging sprite enters the bounds of the displayed portion of the level 
* PostCondition: Physics methods called per position in cause and effect to move the sprite
* Minimal Guarantees: Sprite is created in the level
* Success Guarantees: archetype display reaches the sprite and it begins to zigzag
* Basic Flow:
	1. Authored sprite is read in to spriteManager
	2. archetype.runner begins timer for level and display is created
	3. Sprite of interest enters the bounds of display (cause)
	4. Effect initiated for sprite to zigzag
	5. zigzag implemented frame by frame by checking conditions of location and translating accoringly
	6. Sprite leaves bound of display and animation ends

### Main archetype sprite moves right on key input

* Primary Actor: Sprite in the animation
* Scope: Game runner, archetype display, events
* Brief: The user presses an input key which maps to the main sprite moving right.
* Stakeholders: archetype, Events, GameEngine, Physics
* Preconditions: Right moving sprite is present in the UI, mapped key is entered to move
* PostCondition: effect called to translate sprite until key released or cause deemed false
* Minimal Guarantees: Key input registered, sprite present in the level
* Success Guarantees: Sprite moves to the right in the UI
* Basic flow:
	1. Key input registered as a key cause
	2. So long as cause for sprite present to be checked for causes as well, move right called
	3. sprite's move right by some velocity initiated and physics engine translate is called
	4. sprite's modified
	5. as new game loop begins, new sprite position displayed
	6. check for keyCause once more for right

### Teleport input cancels if goes into bounds of wall

* Primary Actor: Game runner
* Scope: Game runner, archetype display, events, sprite
* Brief: If a archetype chooses to teleport per some input, then they should be able to instantly jump to a new location. If the teleport action results in a archetype in the bounds of nontraversable wall, then the teleport action will be cancelled (or reversed)
* Stakeholders: archetype, Events, GameEngine, Physics
* Preconditions: archetype teleports into wall, collision detected
* PostCondition: archetype teleport event cancelled (or reversed in the next frame)
* Minimal Guarantees: archetype teleport into wall detected
* Success Guarantees: archetype does not enter wall
* Basic flow:
	1. Key input registered as a key cause
	2. So long as teleporting sprite within bounds of UI for archetype display, cause for teleport confirmed
	3. Teleport event called 
	4. Either the animation timer has time in frame to interpolate the collision with wall and can cancel the teleport, or the teleport happens
	5. If the teleport occured (no time for interpolation), sprite collision with wall detected with teleport cause
	6. Sprite position reverted pre-teleport in next gameLoop frame.

### Allow archetype to rewatch gameplay

* Primary Actor: Game runner
* Scope: Game runner, archetype display.
* Brief: If the user plays through a level and decides it is a performance worth saving for later viewing, then they can choose to save the gameplay after level completion.
* Stakeholders: archetype
* Preconditions: Level completion cause confirmed
* PostCondition: Video or play either saved or passed on
* Minimal Guarantees: archetype can choose to save performance
* Success Guarantees: Play saves performance
* Basic flow:
	1. As game played by user, each frame state is saved to a temporary file.
	2. In archetype UI, level is played and completion cause confirmed
	3. archetype is prompted to save their performance
	4. temporary video file is saved for the users viewing pleasure.

### Let user switch standard physics to planetary physics

* Primary Actor: Authoring Environment
* Scope: Authoring, game Engine, archetype
* Brief: If a archetype chooses to switch from the standard 'Mario' physics engine to one with sprites to gravitate towards, they should be able to switch to and fro and create a new game.
* Stakeholders: authoring, archetype, events
* Preconditions: authoring environment is open 
* PostCondition: authoring serializes a different physics module tag for the archetype 
* Minimal Guarantees: different physics module tag passed to game Engine for event calling (on authoring saved)
* Success Guarantees: archetype runs a game with an entirely different physics module
* Basic flow:
	1. User requests to change physics module in authoring
	2. authoring switches module tag for serialization and archetype adds any additional information per sprite (such as center of gravity or gravity strength)
	3. data serializes the new game
	4. game engine and archetype receive the new data in sprite and event managers 
	5. event manager now references the planetaryPhysics engine for events during running of archetype

### Sound implementation in game 

* Primary Actor: archetype Display
* Scope: Authoring, game runner, archetype display, events
* Brief: Sound will be saveable and playable for specific inputs and in general play of the game.
* Stakeholders: archetype, fileManager
* Preconditions: user chooses to save an audio clip for an event in authoring
* PostCondition: archetype can play the audio clip when appropriate
* Minimal Guarantees: audio clip is playable
* Success Guarantees: dynamic sound is played when event is triggered
* Basic flow:
	1. As audio is not serializable, user directly saves audio files to a sound hub location when authoring specific events
	2. archetype UI begins in game
	3. Sound triggering cause returns true
	4. Effect triggers sound to play
	5. archetypeDisplay (standardDisplay) loads audio file and plays sound in UI

### Producing overlayed sounds or pausing sounds for another

* Primary Actor: archetype Display
* Scope: Game runner, archetype display, events
* Brief: For various sounds (weapon firing), the sound should simply overlay the main soundtrack, but for certain events (such as boss fight start), events should trigger all sounds to stop for another
* Stakeholders: archetype, fileManager
* Preconditions: user authored sounds to trigger overlay or stop other sound sequences
* PostCondition: archetype creates/pauses/deletes audio managers during user play
* Minimal Guarantees: archetype accesses audio files
* Success Guarantees: archetype handles audio like a real game
* Basic flow:
	1. game begins in archetype runner and displayed
	2. stacking/deleting audio cause detected
	3. new audio manager created or previous manager paused in manager list per event call
	4. archetype display creates the correct audio response beginning in the following frame

### Flip physics directions per key mid game

* Primary Actor: archetype 
* Scope: game engine, events
* Brief: If a certain level or item features reversed physics, then the user should not have to recreate all of that in authoring, rather, some simple flip in the engine should be able to handle the change to cause left to be right, up to be down, etc. 
* Stakeholders: archetype, authoring
* Preconditions: user reaches point in run display UI to flip physics
* PostCondition: physics are flipped in game 
* Minimal Guarantees: physics flipping cause and event are created
* Success Guarantees: physics module can be inverted per mid archetype run 
* Basic flow:
	1. archetype runs a game with physics flipping set in authoring to occur for some event effect
	2. cause triggered in game to flip physics
	3. impacting velocity vectors coordinates flipped in sprite 
	4. for all physics method calls, results are the same but velocity changes experienced are all opposite to the expected
	5. archetype plays with the flipped sprite velocities


### Change volume of sound in game

* Primary Actor: GameDisplay
* Scope: Game runner, archetype display
* Brief: If the user is running the game, then he/she can change the volume or even mute the game sound (seperate music for fx as well)
* Stakeholders: archetype
* Preconditions: Game is playing
* PostCondition: Game volume changed
* Minimal Guarantees: archetype runs game at lower volume
* Success Guarantees: Volume transitions by the next frame
* Basic flow:
	1. archetype runs, user clicks volume button
	2. User slides sliders to new volume levels for music and/or fx
	3. audio managers in archetypeDisplay modify output volume in gameLoop to new values
	4. play resumes in frame with the new volume

### User hits play for a file (updated)

* Primary Actor: Data
* Scope: data, authoring, archetypeRunner, gameEngine
* Brief: When the archetype feels ready to play their game from authoring, they can play and begin playing the game.
* Stakeholders: archetype, data, gameEngine
* Preconditions: Authoring is available
* PostCondition: archetype Display begins running
* Minimal Guarantees: game play file deserialized to managers
* Success Guarantees: game play begins
* Basic flow:
	1. User clicks play from authoring
	2. Authoring calls to deserialize data and run the main method for the archetype display and runner
	3. data managers all load in nodes to display 
	4. archetype runs and runs the gameLoop in runner to handle events and general play of the game.


### Authoring Environment Loads File
* Primary Actor: Authoring environment, Sprite
* Scope: Defined in authoring environment, passed via XML, read in by FileReader
* Level: 
* Brief: The author will define GameObjects and add properties to them in the game authoring environment. The author can define two Sprites that have different properties, storing them in HashMaps. The game authoring environment serializes these objects and stores them in an XML file. The filereader will read the serialized objects and instantiate Sprites using the SpriteManager. The properties will be read from the HashMap. 
* Stakeholders: Game authoring environment, filereader, SpriteManager
* PostCondition: The SpriteManager contains Sprites whose properties accurately match the properties defined in the game authoring environment. 
* Minimal Guarantees: The properties that the author defined in the authoring environment are present in the game engine during gameplay.
* Success Guarantees: The properties that the user defined during authoring work the way they are supposed to - they are correctly bound to Events and they can be accessed and updated. 



# Game Engine Use Cases

### Archtype executes a command
* Primary Actor: The Sprite that is colliding with the platform
* Scope: Action of Sprite during GamePlay
* Level: Any level type
* Brief: The Gameplay in action, and a sprite collides with some sort of platform for which a collision is defined.
* Stakeholders: archetype, Sprite
* PostCondition: Sprite reacts in a way defined by collision, and its new position updates accordingly
* Minimal Guarantees: archetype will perform some sort of colliding action, as defined by collision effect type.
* Success Guarantees: The Sprite collides in the correct fashion, and this is displayed accordingly in the game archetype
* Preconditions: The Gamearchetype is running an active game level, and a sprite exists that “collides” with or touches a platform at some point in the game.

###Score reaches a value and Archetypes are changed
* Primary Actor: The VoogaNumber used in the trigger (in this case score), and whatever variables or Sprites are involved in the action being triggered
* Scope: Cause (a score exceeding a certain number), and Effect (action being triggered), in game play
* Level: Any level type
* Brief: Game play is in action, and score exceeds some defined number
* Stakeholders: archetype, Cause, Effect, Event
* PostCondition: Action is triggered
* Minimal Guarantees: Action will be triggered once a score exceeds a certain number
* Success Guarantees: Action is triggered whenever the score exceeds a certain number, and this action is carried out successfully by the game archetype, and updated and displayed accordingly
* Preconditions: The Gamearchetype is running an active game level, and the variable “Score” exists, and this Event has been defined by the user.

### All archetypes move together
* Primary Actor: The Sprite that is performing an action
* Scope: Action of Sprite during GamePlay
* Level: Any level type
* Brief: The Gameplay in action, and a sprite performs some sort of action, like jump, die, or shoot. As a result of this action, an audio clip must play
* Stakeholders: archetype, Sprite
* PostCondition: Audio clip plays
* Minimal Guarantees: Sound will play for the specified action
* Success Guarantees: The sound that plays is the correct sound for the correct action
* Preconditions: The Gamearchetype is running an active game level, and an action is performed that produces an audio clip. This audio clip is defined as an action itself, however, so this interaction must be bundled by the user in the authoring environment.

### A new Cause is created and linked to an Effect
* Primary Actor: The Cause that is being stored
* Scope: After file read when xstream is being deserialized
* Level: Any
* Brief: This Cause is mapped to cause action ids of the Causes that it is composed of in the Cause name map as “Cause 1”
* Stakeholders: Cause, CauseManager
* PostCondition: Cause bundle is stored as “Cause 1”
* Minimal Guarantees: The Cause is then stored as “Cause 1”
* Success Guarantees: When the Cause is checked for, it will essentially e anding all of the causes that it is composed of, and will result in the correct boolean evaluation.
* Preconditions: The other Causes work and are stored in a map.

### Archetype jump
* Primary Actor: archetype (Registered User)
* Scope: Game map (level)
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The user presses a button (spacebar, for example) to make the character jump. The character is allowed to move in either direction while off the ground.
* Stakeholders
* Postconditions
* Minimal Guarantees:
* Success Guarantees:
The character successfully jumps on the screen (up and down) and lands.
If the character hits another entity, the physics engine runs and detects/solves collisions.
Etc.
* Preconditions:
The character is movable in the game. The goal condition hasn’t been met.
* Triggers:
The user makes a jump request (presses jump key) while the game is in the game loop.


### Archetype global variable changes
* Primary Actor: archetype (Main Character) (Registered User)
* Scope: Game map (level)
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The user presses a left arrow button that causes the main character to move to the left.
*Stakeholders: Game Engine, Design Board
*Preconditions:
  * Images are loaded onto Explorer Window
  * Images are clickable, draggable, etc
* Postconditions:
  * Minimal Guarantees: Sprite's image visible on the Design Board
  * Success Guarantees:
		  * The Sprite is visible and initialized within the authoring environment 
		  * User can modify properties of the sprite
* Triggers: Author clicks on image resource in Explorer Window and drags onto Design Board
* Basic flow: (To be written from here)
  1. Explorer Window and Design Board initialized
  2. Image resources loaded into Explorer Window upon initialization of the Explorer Window
  3. Pane added to Design Board to host other nodes
  4. Author drags image from Explorer onto Design Board
  5. Author releases mouse
  6. New Sprite is initialized and added to SpriteManager
  7. Sprite added to children of Design Board's pane
  8. Sprite is part of game play
* Extensions:
  1. Dragging other resource onto Design Board
  
*Coding Workflow:
  update method is called by the archetype
  this update method calls EventManager to update
  EventManager then calls upon all of it's Causes 
  For all the Causes that evaluate to true, an Effect is placed in a Priority Queue
  In this case, one of these Effects is Left. When the Left Effect is instantiated,
  it is given some parameters such as duration and speed for which it should travel left
  The duration is used to set a counter to determine how many steps the left Effect should 
  continue to execute in the Effect queue when called by update. When a max counter is reached,
  the Effect is removed from the queue and no longer takes place.
  The Left Effect makes use of the Physics Engine, and inputs the xpos to apply an impulse in the -x
  direction according to physical constants. The Left Effect then updates the Sprite's vector
  to have the new appropriate xpos , so that after update is called by the archetype, and the new
  positions are display, the xpos will be adjusted according to the speed and physical constants.
  
#Archetypes all die
Primary Actor: archetype (Registered User)
Scope: Game map
Level: ! (User goal or sea level)
Brief: (equivalent to a user story or an epic)
The user presses a right arrow button that causes the main character to move to the right.
Stakeholders
...
Postconditions
Minimal Guarantees:
Success Guarantees:
The character successfully moves right.
If the character hits another entity, the physics engine runs and detects/solves collisions.
Preconditions:
The character is movable in the game. The goal condition hasn’t been met.
Triggers:
The user makes a move right request (presses right arrow key) while the game is in the game loop.
Basic flow: (To be written from here)
The authoring environment provides the user with the ability to bind the right arrow key to move-right action.
The system saves the game composition using XStream.
The game archetype loads the game and the user can play the game. While in gameplay, if the key is pressed, the main character will move.

Extensions:
Let the user vary the velocity at which the main character moves.


###Archetypes move right
* Primary Actor: archetype (Registered User)
* Scope: Game map
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The user presses a right arrow button that causes the main character to move to the right.
* Stakeholders
  * Map and the main character
* Success Guarantees:
  * The character successfully moves right.
  * If the character hits another entity, the physics engine runs and detects/solves collisions.
* Preconditions:
  * The character is movable in the game. The goal condition hasn’t been met.
* Triggers:
  * The user makes a move right request (presses right arrow key) while the game is in the game loop.
* Basic flow: (To be written from here)
  1. The authoring environment provides the user with the ability to bind the right arrow key to move-right action.
  2. The system saves the game composition using XStream.
  3. The game archetype loads the game and the user can play the game. While in gameplay, if the key is pressed, the main character will move.

* Extensions:
  * Let the user vary the velocity at which the main character moves.

  
###Main archetype character dies (death action)
* Primary Actor: archetype (Registered User)
* Scope: Game map
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The main character takes a death action, causing the game to end and rendering the main character no longer be able to move or be controlled by the user.
* Stakeholders
  * ...All components of the game
*  Success Guarantees:
  * The character successfully jumps on the screen (up and down) and lands.
  * If the character hits another entity, the physics engine runs and detects/solves collisions.
* Preconditions:
  * The game is currently in play.
* Triggers:
  * Goal for the death action is met. For example, time is run out. Health is 0.
* Basic flow: (To be written from here)
  1. The authoring environment provides the user with the ability to set a death action to the main character.
  2. The user sets and modifies goal conditions for the death action. More specifically, the action is the effect, and the user will have to specify a bundle(s) of causes.
  3. The system saves the game composition using XStream.
  4. The game archetype loads the game and the user can play the game. While in gameplay, if the death conditions are met, the character will take the death action.


  
===========

# Game Authoring Use Cases
### Drag Drop Images
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment- Explorer Window and Design Board
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  *The author clicks on an image in the Explorer Window and drags the image to the Design Board. On Release, the image appears on the Design Board as a Sprite.
*Stakeholders: Game Engine, Design Board
*Preconditions:
  * Images are loaded onto Explorer Window
  * Images are clickable, draggable, etc
* Postconditions:
  * Minimal Guarantees: Sprite's image visible on the Design Board
  * Success Guarantees:
		  * The Sprite is visible and initialized within the authoring environment 
		  * User can modify properties of the sprite
* Triggers: Author clicks on image resource in Explorer Window and drags onto Design Board
* Basic flow: (To be written from here)
  1. Explorer Window and Design Board initialized
  2. Image resources loaded into Explorer Window upon initialization of the Explorer Window
  3. Pane added to Design Board to host other nodes
  4. Author drags image from Explorer onto Design Board
  5. Author releases mouse
  6. New Sprite is initialized and added to SpriteManager
  7. Sprite added to children of Design Board's pane
  8. Sprite is part of game play
* Extensions:
  1. Dragging other resource onto Design Board

=============
### Importing Image into Project
* Primary Actor: Author (Registered User)
* Scope: 
  * Authoring Environment: Explorer Window, Menubar
  * Data: ResourceXML
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The author selects to import resource from his/her local device. The author then selects what folder he/she wishes to put the resource in on the project. The resource path is saved in the ResourceXML and is then imported into the project in the Explorer Window.
* Stakeholders: Game Authoring Environment, Author, Explorer Window
* Preconditions:
  * Image exists on local machine
  * Image Path does not change through course of project
  * ResourceXML editable
  * Explorer Window and Menubar initialized
* Postconditions:
* Minimal Guarantees:
  * Path (String) will be added to list of paths in XML
* Success Guarantees:
  * Path is valid
  * Explorer Window will be able to successfully load data into the window
  * Image will be visible on the Explorer Window
* Triggers:
  * Author selects Import option in Menubar (File → Import)
* Basic flow: (To be written from here)
Explorer Window, Menubar initialized and visible to author
Author selects File → Import option on Menubar
Author's local machine's directory opens, allowing user to choose image he/she wishes to add to project
New Stage allows author to store where to store image in project directory
Author accepts (presses OK)
ResourceXML written to to add path of new image under subtag (folder) where author wanted to store image
Explorer Window reloads, loads information back from XML and populates window
* Extensions:
Adding other resources (sound, color, textures, etc)
  
==========

### Use Case: Editing Sprite Variables/Properties
* Primary Actor: Author (Registered User)
* Scope: 
  * Authoring Environment: Properties Window, Design Board
  * Data: Sprite
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The author clicks on a sprite to select it. The author opens the Properties window. The Properties window has all of the editable properties of the sprite, and the user can edit these properties from the window
* Stakeholders: Game Authoring Environment, Author, Sprite
* Preconditions:
  * Sprite exists
  * Properties Window, Design Board initialized
* Postconditions:
* Minimal Guarantees:
  * Properties window opens
* Success Guarantees:
  * Properties Window loads properties of the selected Sprite
  * The data can be edited directly from the window, and updates to sprite
* Triggers:
  * Author selects sprite in Design Board
* Basic flow: (To be written from here)
Author selects Sprite and opens Properties Window
Properties Window loads selected Sprite's properties
Author edits properties in Window
Author selects OK to continue with processing changes
Properties of sprite are directly edited
* Extensions:
Editing features of other selected objects in the authoring environment (textures, colors, etc)

===========
### Use Case: Saving Data from Authoring Environment
* Primary Actor: Author (Registered User)
* Scope: Data, Authoring, Engine
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  * The author presses save on the File option in MenuBar. The Design Board class writes the data for all sprites onto the XML. The data is saved on the XML
* Stakeholders: Game Authoring Environment, Engine
* Preconditions:
  * XML File exists
  * Data can be written using XStream
* Postconditions:
* Minimal Guarantees:
  * XML format written to Data XML
* Success Guarantees:
  * Data is perfectly delivered to XML using Serialization
  * Data can be read in by the Engine
* Triggers:
  * Author selects Save option in Menubar (File → Save)
* Basic flow: (To be written from here)
Author selects Save
Design Board takes its instance of GameObjectManager and writes it to the XML file
The data will be serialized into XML
* Extensions:
Have composition of backend objects in front end to allow for greater encapsulation of backend data and ensures only backend data is written to XML

### Previewing Colors on Package Explorer
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment - Package Explorer
* Level: ! (User goal or sea level)
* Brief: The User wants to add a colored Sprite to the Design Board. There will be thumbnails of the color of the sprite with the name attached. There will also be an option for the user to define their own colors through inputting RGB values.
* Stakeholders: Game Authoring Environment
* Preconditions: A predefined list of sprites must be made with a certain color property. The user will also have the option to create a new sprite of a certain color using RGB values. The files must be clickable. 
* Postconditions: 
* Minimal Guarantees: The Sprite must be clickable
* Success Guarantees: User can see the color of the sprite based on thumbnail
* Triggers: Author clicks on the colored sprite in the Explorer Window
* Basic Flow
Explorer Window is initialized
A predefined list of sprites with certain color properties are initialized
A ColoredSpriteCreator class is instantiated that allows for the creation of a new sprite with a certain color and also a GUI feature (button, etc.) is created that allows the user to create a new sprite with a new color and added to the list of sprites mentioned in step 2.
That list of sprites is imported into the authoring environment
If desired, sprite can be moved to appropriate folder
If desired, the sprite alias can be renamed
     5.    Sprite clicked on, and can be dragged around to the Design Board

* Extensions
When double clicked, properties of the sprite can be viewed



### Setting Cause-Effect of Events
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment - Package Explorer, Data: Sprite, Event Manager
* Level: ! (User goal or sea level)
* Brief: The User wants to set an event that will occur based on interactions of GameObjects within the game.
* Stakeholders: Game Authoring Environment, Event Manager
* Preconditions: the Event Manager class must be created and be flexible to handle interactions between object(s) or user (actors, global variables, keyclicks, etc.) and change characteristics of the objects (effect)
* Postconditions: 
* Minimal Guarantees: When playing the game, the cause produces the effect and the event is successful
* Success Guarantees: User sees the event on the Package Explorer and are able to edit the event
* Triggers: Author uses the Event Manager tab on the Package Explorer to deal with events between objects
* Basic Flow
Explorer Window is initialized
Event Manager is initialized
The user selects to create an event attached to an object
Picks a cause (a key is pressed, a global variable is changed)
Picks an effect (characteristic is changed)
The event is listed in the Package Explorer and is available to edit

* Extensions
Events can be linked to previous events, e.g. multiple effects for a singular cause without creating a new instance of event




### Accessing Help Page
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment
* Level: ! (User goal or sea level)
* Brief: The user needs a resource that explains how our authoring environment works
* Stakeholders: User
* Preconditions: Help page must be made
* Postconditions: 
* Minimal Guarantees: A web browser launches with the help information
* Success Guarantees: The help page help
* Triggers: Author accesses the help page
* Basic Flow
Help page initialized
Help button is pressed

* Extensions
The Help page has an authoring environment itself for walkthrough purposes



### Running Simulation
* Primary Actor: Author (Registered User)
* Scope: Simulation Window
* Level: ! (User goal or sea level)
* Brief: After creating the game, the author wants to launch and play their game
* Stakeholders: Design Board, Data, Game Engine
* Preconditions: All aspects of the Game must be programmed and ready to launch
* Postconditions: 
* Minimal Guarantees: A new window launches and the user can play their game to some extent
* Success Guarantees: Every feature works correctly
* Triggers: Author runs the button “Run Simulation”
* Basic Flow
Everything in the game is created and finalized
“Run Simulation” is pressed
A new window appears and the game can be played.

* Extensions
The Game can be run while still in the Authoring Environment

### Preview Fonts with Explorer Window
* Primary Actor: Author (the Game Designer)
* Scope: Explorer Window
* Level: ! (User goal or sea level)
* Brief: 
  *  The user clicks on the folder shown in the Explorer window entitled fonts. This drops down a list of stored fonts which are displayed as a text image, which the user can double click on to preview.
* Stakeholders:  Game Authoring Window
*Preconditions
   * Text files Loaded
   * Images set and initialized
   * Cickable 
* Postconditions
	Minimal Guarantees 
  	* Font file is present
Success Guarantees:
* Can Identify and evaluate text
* Triggers:
* Clicking on the text file
* Basic flow: 
Explorer is initialized, Font files are present, ie initial conditions are met
User clicks on font file -> dropdown appears
User double clicks on font file -> popup appears with font displayed
* Extensions:
NA

### Creating new Game Object
* Primary Actor: Author (the Game Designer)
* Scope: MenuBar, Design Board, Backend Sprite, Game Object
* Level: ! (User goal or sea level)
* Brief: 
  *  User clicks on edit dropdown and selects new game object. By clicking on the Design Board, the author selects the initial location, and a popup is opened where the author can set base characteristics, for example: position, automation, image, and events that occur.
* Stakeholders:  Game Authoring Window
*Preconditions
   * None necessarily, however setting an image requires images to be loaded, setting events require events to be initialized.
* Postconditions
 Minimal Guarantees 
  	* New Game Object is initialized
Success Guarantees:
* Game Object with a full set of parameters is initialized and linked to backend
* Triggers:
* Selecting New  -> Game Object from menubar
* Basic flow: 
Screen Initialized
Selects New -> Game Object
Initializes a new Game Object
Game Object initializes a Game Engine Sprite
Next click sets initial position in Game Object
New Stage for popup
Pop up populated with buttons, comboboxes ect to set the other preferences for this object
* Extensions:
More Features, More properties, More functionality

### Editing global variables
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment, Engine
* Level: ! (User goal or sea level)
* Brief: The user wants to edit a global variable such as Text, Timer, that may indicate score or time remaining
* Stakeholders: Game Authoring Environment
* Preconditions: A global variable must already be declared for them to edit
* Postconditions: 
* Minimal Guarantees: The variable is clickable and editable
* Success Guarantees: User can edit the variable and it updates the value througout the program
* Triggers: Author clicks on the variable in the Explorer Window
* Basic Flow
Explorer Window is initialized
A variable has been defined before
The user double clicks on the variable in the explorer and brings up a window that allows it to change its value
The value of the Variable object is altered
The field in the authoring environment reflects the change

* Extensions
When changed, the authoring environment dynamically reflects the change while the window is still open (not just when the user clicks OK)

###Preview Image file
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment, Explorer Window
* Level: ! (User goal or sea level)
* Brief: The author clicks on an image in the game explorer and can preview the image as a thumbnail, or double-click and view the image in full resolution.
* Stakeholders: Game Authoring Environment
* Preconditions: Images are loaded onto Explorer Window. Images are clickable, draggable, etc. Image is formatted properly and is a valid file.
* Postconditions: 
* Minimal Guarantees: The image is presented as a thumbnail.
* Success Guarantees: The image can be double-clicked and viewed in full resolution. User can easily identify which image is represented by its thumbnail
* Triggers: Author clicks on image resource in Explorer Window or double clicks on image
* Basic Flow
Explorer Window is initialized
Image is imported into the authoring environment
If desired, the image can be moved to an appropriate folder
If desired, the image alias can be renamed
Image is clicked on, and a thumbnail in the explorer window shows the image
Image is double-clicked on, and the image is opened in a separate window

* Extensions
Animated images will animate as a thumbnail

###Preview Audio file
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment, Explorer Window
* Level: ! (User goal or sea level)
* Brief: The author clicks on an audio file in the explorer window and can see a thumbnail image of a speaker (to represent that it is an audio file), and is able to preview what it sounds like if double-clicked
* Stakeholders: Game Authoring Environment
* Preconditions: Audio files are loaded onto Explorer Window. Audio files are clickable. Audio files are formatted properly and is a valid file
* Postconditions: 
* Minimal Guarantees: The audio file is presented as a thumbnail
* Success Guarantees: User can easily identify which audio is represented by the file name. User can listen to the audio if double-click on the file
* Triggers: Author clicks on an audio resource in Explorer Window or double clicks on an audio file
* Basic Flow
Explorer Window is initialized
Audio file is imported into the authoring environment
If desired, the audio file can be moved to an appropriate folder
If desired, the audio file alias can be renamed
Audio file is clicked on, and a thumbnail in the explorer window shows a speaker logo
Audio file is double-clicked on, and the audio file is opened in a separate window for preview

* Extensions
On single click, the waveform of the audio is displayed instead of a default thumbnail


### Loading game authoring workspace
* Primary Actor: Author (Registered User)
* Scope: Authoring Environment, Engine, Data
* Level: ! (User goal or sea level)
* Brief: The user wants to load a previously saved workspace in order to continue editing after closing and reopening the program
* Stakeholders: Game Authoring Environment, Engine, Data
* Preconditions: The workspace has already been saved before
* Postconditions: 
* Minimal Guarantees: A workspace is opened
* Success Guarantees: The workspace saved earlier is completely restored and opened
* Triggers: Author clicks on File -> Open project...
* Basic Flow
User clicks on File -> Open project…
The authoring environment calls on the engine
The engine calls on the data to restore sprites created before
The engine passes this to the authoring environment to display the workspace as was saved before
* Extensions
When the window is closed and reopened, the session is automatically restored (like how Eclipse does so)


### Setting on Collision 
* Primary Actor: Author (the Game Designer)
* Scope: Sprite, Actormanager, Varaibles
* Level: ! (User goal or sea level)
* Brief: 
  *  User clicks on edit dropdown and selects new event. This makes a popup appear which allows the user to select collision cause, the two colliding objects, and the effect objects of the event. The event is then saved in the Actor Manager Class.
* Stakeholders:  Game Authoring Window
*Preconditions
   * All sprites, text objects and other things which will collide are initialized
* Postconditions
 Minimal Guarantees 
  	* New Event Object is initialized
Success Guarantees:
* Event object makes necessary changes when fired
* Triggers:
* Selecting New  -> Event Object from menubar
* Basic flow: 
Screen Initialized
Selects New -> Event Object
Initializes a new Event Object
Select the Cause and the Effects of the event from pop-up
Event saved
* Extensions:
More causes


### New Global Variable 
* Primary Actor: Author (the Game Designer)
* Scope: Varables
* Level: ! (User goal or sea level)
* Brief: 
  *  User clicks on edit dropdown and selects new Global Variable. This makes a popup appear which allows the user to select variable name and initial value
* Stakeholders:  Game Authoring Window
*Preconditions
   * NA
* Postconditions
 Minimal Guarantees 
  	* New Global Variable Initialized
Success Guarantees:
* Interacts well with all the other variables and groups
* Triggers:
* Selecting New  -> Event Object from menubar
* Basic flow: 
Screen Initialized
Selects New -> Global Variable
Popup appears with textbox for name and initial value
Global variable is initialized with these components
* Extensions:
NA

### archetype bouncing off of a wall
* Primary Actor: archetype and Data
* Scope: 
  * archetype observable
  * Data
  * Physics Engine
  * Collision Engine
* Brief: 
  *  archetype collides into an object while moving at some velocity greater than zero. Depending on the ‘bounciness’ of the object, the archetype will change their velocity accordingly.
* Stakeholders
  * Sprite data
* Preconditions:
  * archetype makes contact with a non-damaging, non-transparent sprite
* Postconditions
  * archetype reflects momentum by some factor of collision velocity
* Minimal Guarantees:
  * Collision detected between archetype and surface
* Success Guarantees:
  * archetype changes velocity accordingly (reflection of angle, momentum dissipation, etc.)
* Triggers:
  * archetype contacts object which is ‘bounceable’
* Basic Flow
  * archetype collision detected with an object
  * Causes map checked for ‘bounce’, and if valid, objects set to bounce
  * Velocity and heading entered and modified results and translated positions for next frame returned by physics engine
  * Data stores new physics values
  * archetype displays changes to environment
* Extensions
  * Implement mass for objects and create momentum for both collider and collidee
  * Destructible environment, if we have a year and a fifty person team

### archetype take damage from enemy
* Primary Actor: archetype
* Scope: 
  * Data: archetype and enemy sprites
  * Collision Engine
  * Data: Cause and Effect Interface
  * archetype observable
* Brief: 
  *  Our majestic archetype, running gallantly through the meadows of level 5, lacks the time to look around the corner, makes a brave but fool-hearted leap, and collides face first into the scariest of all mid game foes. Upon hitting each other, the archetype is terribly hurt, the enemy laughs and the consequences continue to roll in the form of fear of future leaps.
* Stakeholders
  * Sprite data
* Preconditions:
  * Collision engine detects archetype contact with enemy
* Postconditions
  * archetype loses health or a life with possible animation
  * Enemy potentially loses health or dies as well
  * archetype recoils or gains temporary damage immunity
* Minimal Guarantees:
  * archetype and enemy contact detected
* Success Guarantees:
  * archetype loses the health/life associated with contact with the specific enemy
* Triggers:
  * archetype contacts enemy while archetype not immune to damage
* Basic Flow
  * Collision detected between archetype and enemy sprite
  * Cause key for damage checked for any reasons to not take damage ex. Shield
  * If all conditions for damage true, damage value taken from enemy
  * Health of archetype taken and subtracted from in the data
  * archetype and enemy each display any other actions associated with collision
* Extensions
  * Allow for recoil or temporary immortality after contact
  * Allow user-created script animation for death or damage

### Key input generates ‘bullet’
* Primary Actor: archetype
* Scope: 
  * Data: Cause and Effect Interface
  * Data: Sprite
* Brief: 
  *  Upon pressing the properly mapped key in game, the game will respond to produce the projectile and any related effects (such as audio or image change) in addition to producing the new game sprite.
* Stakeholders
  * Sprite data
  * archetype
* Preconditions:
  * archetype is in a state in which the ‘fire’ key input boolean is deemed true for the fire effect.
* Postconditions
  * The cause for ‘fire’ input in the event map is made true, archetype produces a projectile, potentially audio, and any additional images or physical recoils. 
* Minimal Guarantees:
  * Cause boolean for the key input is read as true.
* Success Guarantees:
  * Cause boolean for the key input is read as true
  * Other conditions associated for firing (ex. Must be on ground) read as true
  * Effect triggers all associated new sprites and resultant global/sprite changes
* Triggers:
  * User enters the mapped ‘fire’ key in game
* Basic Flow
  * Event map checks cause’s arraylist for ‘fire’
  * If all causes ruled true in the frame, each effect for ‘fire’ is triggered
  * Data produces any sprites and audio associated with fire
  * Animations of each sprite processed by archetype and engine
* Extensions
  * Allow for grouping of causes to single effect which includes audio, projectile, recoil instead of mapping causes one by one to each effect. 

### Changing Sprites Gravity Value
* Primary Actor: Author
* Scope: 
	*Data: Sprite
	*Authoring environment: Properties window, design board
* Brief: 
  *  Upon selecting a sprite in the UI, the user will select to change physic’s properties in the properties window. Upon entering and confirming a new gravity acceleration constant, the sprite will inherently hold this value, and for every frame update, the sprite position will be influenced in the direction and magnitude indicated by this gravity.
* Stakeholders
	*Game authoring environment, author, sprite data
* Preconditions:
  * In UI for design of levels and sprite exists
* Postconditions
  * Sprite must still exist
* Minimal Guarantees:
  * Reader attempts to pass new value to sprite data
* Success Guarantees:
  * Data entered by author, value confirmed as valid, data rewritten
* Triggers:
  * Author selects sprite within the design board
* Basic Flow
  * User selects sprite
  * Property window opens for sprite
  * Physics tab selected
  * Author toggles gravity and/or changes its numerical value
  * Author confirms input
  * Inputs are checked for errors 
  * Data is directly overwritten
* Extensions
  * Applying inherent velocity, bouncing from wall contact
	
