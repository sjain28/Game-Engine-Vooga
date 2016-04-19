Team DoovalSalad: Use cases

Anita’s Use Cases 

###Player leaves bounds of map

* Primary Actor: Main character (sprite)
* Scope: PhysicsManager, EventManager, Sprite
* Level:  
* Brief: The character falls off of the ground and there is no other platform to land on- the character is no longer on the screen
* Stakeholders: Sprite, SpriteManager, VariableManager
* PostCondition: The character’s state should be set to dead once the character passes a boundary below the bottom of the screen and the user defined event that occurs upon death is executed
* Minimal Guarantees: The character will be set to dead
* Success Guarantees: The game will be reset (assuming the on death event is successful) so that the game is once again 
* Preconditions: There must be an event handler for death established for the game and the user must either set a reasonable below screen boundary or use the default boundary.

###User meets win game conditions

* Primary Actor: User
* Scope: Event, Cause, Effect, EventManager, VariableManager
* Level: 
* Brief: The user defined event for game winning conditions will be met, which triggers the effect of changing the global variable for gameWon to true. The EventManager, which is checking through the update method, will detect that the global variable has been set to true and trigger the effect for the win game event.
* Stakeholders: EventManager, SpriteManager, VariableManager
* PostCondition: The effect defined in the win game event will determine the conditions of the game
* Minimal Guarantees: There will be a change defined by the user, at least going to the next level or a win screen
* Success Guarantees: The best case scenario would be if the user defines a transition to a game or a win screen that is then displayed
* Preconditions:The user must define game win conditions and game win effects. The game win conditions must be met.

###User defines a new parameter for sprite

* Primary Actor: User
* Scope: SpriteHandler, Sprite
* Level: 
* Brief: The user decides a new parameter in the front end which then adds the parameter as either a VoogaDouble or a VoogaBoolean to the hashmap of parameters for the sprite. Depending on if it’s a double or a boolean, methods are now available to increment/decrement or toggle the parameter.
* Stakeholders: Sprite
* PostCondition: Sprite now has a new active parameter
* Minimal Guarantees: The sprite has an additional feature than can now be edited
* Success Guarantees: The parameter can be used in events now
* Preconditions: The sprite did not have this parameter already

###Keystroke cause triggered

* Primary Actor: User
* Scope: EventManager, Sprite, GameHandler
* Level: 
* Brief: The user hits a keystroke that is associated with a cause in an event. This causes a listener in the GameHandler to add the key as a parameter in the sprite’s parameter hashmap which then triggers the cause in the event. 
* Stakeholders: Sprite, EventManager, GameHandler
* PostCondition: The effect associated with that cause in the event gets triggered
* Minimal Guarantees: The character is changed in state
* Success Guarantees: Changed state for the character according to the keystroke and added parameter of the keystroke toggled on
* Preconditions:

Josh's use cases

###User hits Play
* Primary Actor: The user of the game authoring environment.
* Scope: User action during gameplay, interaction with GamePlayer
* Level: 
* Brief: The user has finished creating his game and wants to transition to playing the game. The user hits the play button to instantiate the player.
* Stakeholders: GamePlayer
* PostCondition: Game play begins.
* Minimal Guarantees: Game play will begin.
* Success Guarantees: User has the ability to resume gameplay, and start playing the game from its previous state
* Preconditions: The user has created a valid level from the game authoring environment.

### Player defeats a level by defeats a level by reaching the checkpoint or obtaining a score.
* Primary Actor: The player playing the game.
* Scope: Player.
* Level:
* Brief: The player obtains all the win conditions and the scene on the screen changes, telling the player he has won the game (via text).  Then, a new scene will pop up on the screen.
* Stakeholders: GamePlayer, VariableManager
* PostCondition: The state of the Sprites has been modified as per the game author’s instructions (Ex. both Sprites have their health reduced)
* Minimal Guarantees: There will be a detection of one of the multiple win conditions.
* Success Guarantees: The scene will switch to either accommodating text or switching a scene, indicating that the user has won the level.
* Preconditions: The player reaches the correct spot on the screen (checkpoint) or a global variable reaches the target value, 


### The user gives a sprite an invalid value (such as a string instead of a value).
* Primary Actor: The user of the game authoring environment
* Scope: Game Authoring Environment, Sprite class.
* Level:
* Brief: The user specifies a value of the sprite that is illegal 
* Stakeholders: SpriteManager, Sprite, Game Authoring Environment
* PostCondition: The sprite will not be placed in the right position (will not appear in the game authoring environment) and an error message will pop up on the screen telling the user of his mistake.
* Minimal Guarantees: There will be a message that appears on the screen telling the user that he placed the sprite in the wrong location.
* Success Guarantees: The sprite itself will be removed or not placed on the screen at all.
* Preconditions: A field populated for the sprite is an incorrect format or invalid value.


### The user deletes a sprite from the game authoring environment
* Primary Actor: The user of the game authoring environment
* Scope: Game authoring environment
* Level:
* Brief: The user decides to take a sprite that has already been made from the game authoring environment
* Stakeholders: Game Authoring Environment
* PostCondition: The sprite will no longer exist in its original spot on the screen.
* Minimal Guarantees: The game authoring environment will detect that the user is trying to take the sprite off the screen.
* Success Guarantees: The sprite itself will be removed from the screen.
* Preconditions: The sprite must exist in a location that is shown by the game authoring environment

Saumya’s Use Cases
###User hits “Pause” 
* Primary Actor: The person using the software
* Scope: User action during gameplay, interaction with GamePlayer
* Level: 
* Brief: The user has selected the option to play an existing game. During the course of the game the user wants to temporarily halt gameplay.
* Stakeholders: GamePlayer
* PostCondition: Game play has halted
* Minimal Guarantees: Game play halts
* Success Guarantees: User has the ability to resume gameplay, and start playing the game from its previous state
* Preconditions: The user has started playing a game

###Two Sprites collide
* Primary Actor: EventManager class in the GameEngine package
* Scope: EventManager (game engine)
* Level:
* Brief: During gameplay, two Sprites on the screen collide, triggering a response predefined by the author of the game. An example response is decreasing the “health” property of both of the Sprites. 
* Stakeholders: Sprite, EventManager, Event, CollisionCause, Effect
* PostCondition: The state of the Sprites has been modified as per the game author’s instructions (Ex. both Sprites have their health reduced)
* Minimal Guarantees: The collision is detected by the EventManager.
* Success Guarantees: The collision is detected by the EventManager, and the Event that manages this collision executes its Effect, modifying the states of the Sprites. 
* Preconditions: The author of the game must have created an Event object whose cause is the trigger between these two Sprites, and the effect of that Event must modify the states of both Sprites. 

###User applies gravity-Saumya
* Primary Actor: Physics engine
* Scope: Game Engine (game handler class specifically)
* Level: 
* Brief: The author of the game applies gravity to Sprites. When the game is played the game engine reads this gravity property from the XML file and initializes its physics engine with the gravity parameter. The Sprites experience gravity during gameplay.
* Stakeholders: SpriteManager, Physics, Gamehandler, FileReader
* PostCondition: The Velocity vectors of all of the Sprites have been modified to accelerate downwards
* Minimal Guarantees: Author has the option to apply gravity, gravity gets written to the XML file, read in game engine. 
* Success Guarantees: Y-velocities of Sprites modified according to level of gravity.
* Preconditions: The author of the game has chosen to apply gravity to Sprites

###Authoring environment defines Sprites with different properties -Saumya
* Primary Actor: Authoring environment, Sprite
* Scope: Defined in authoring environment, passed via XML, read in by FileReader
* Level: 
* Brief: The author will define GameObjects and add properties to them in the game authoring environment. The author can define two Sprites that have different properties, storing them in HashMaps. The game authoring environment serializes these objects and stores them in an XML file. The filereader will read the serialized objects and instantiate Sprites using the SpriteManager. The properties will be read from the HashMap. 
* Stakeholders: Game authoring environment, filereader, SpriteManager
* PostCondition: The SpriteManager contains Sprites whose properties accurately match the properties defined in the game authoring environment. 
* Minimal Guarantees: The properties that the author defined in the authoring environment are present in the game engine during gameplay.
* Success Guarantees: The properties that the user defined during authoring work the way they are supposed to - they are correctly bound to Events and they can be accessed and updated. 



# Game Engine Use Cases

###Player collides with a platform
* Primary Actor: The Sprite that is colliding with the platform
* Scope: Action of Sprite during GamePlay
* Level: Any level type
* Brief: The Gameplay in action, and a sprite collides with some sort of platform for which a collision is defined.
* Stakeholders: Player, Sprite
* PostCondition: Sprite reacts in a way defined by collision, and its new position updates accordingly
* Minimal Guarantees: Player will perform some sort of colliding action, as defined by collision effect type.
* Success Guarantees: The Sprite collides in the correct fashion, and this is displayed accordingly in the game player
* Preconditions: The GamePlayer is running an active game level, and a sprite exists that “collides” with or touches a platform at some point in the game.

###Score exceeds a certain number, and an action is triggered
* Primary Actor: The VoogaNumber used in the trigger (in this case score), and whatever variables or Sprites are involved in the action being triggered
* Scope: Cause (a score exceeding a certain number), and Effect (action being triggered), in game play
* Level: Any level type
* Brief: Game play is in action, and score exceeds some defined number
* Stakeholders: Player, Cause, Effect, Event
* PostCondition: Action is triggered
* Minimal Guarantees: Action will be triggered once a score exceeds a certain number
* Success Guarantees: Action is triggered whenever the score exceeds a certain number, and this action is carried out successfully by the game player, and updated and displayed accordingly
* Preconditions: The GamePlayer is running an active game level, and the variable “Score” exists, and this Event has been defined by the user.

###Player triggers audio clip for specified action (ex. death). 
* Primary Actor: The Sprite that is performing an action
* Scope: Action of Sprite during GamePlay
* Level: Any level type
* Brief: The Gameplay in action, and a sprite performs some sort of action, like jump, die, or shoot. As a result of this action, an audio clip must play
* Stakeholders: Player, Sprite
* PostCondition: Audio clip plays
* Minimal Guarantees: Sound will play for the specified action
* Success Guarantees: The sound that plays is the correct sound for the correct action
* Preconditions: The GamePlayer is running an active game level, and an action is performed that produces an audio clip. This audio clip is defined as an action itself, however, so this interaction must be bundled by the user in the authoring environment.

###User defined Cause bundle is stored in Causes and named as “Cause 1”
* Primary Actor: The Cause that is being stored
* Scope: After file read when xstream is being deserialized
* Level: Any
* Brief: This Cause is mapped to cause action ids of the Causes that it is composed of in the Cause name map as “Cause 1”
* Stakeholders: Cause, CauseManager
* PostCondition: Cause bundle is stored as “Cause 1”
* Minimal Guarantees: The Cause is then stored as “Cause 1”
* Success Guarantees: When the Cause is checked for, it will essentially e anding all of the causes that it is composed of, and will result in the correct boolean evaluation.
* Preconditions: The other Causes work and are stored in a map.

### Main Character Jump
* Primary Actor: Player (Registered User)
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


### Main Character Moves Left
* Primary Actor: Player (Main Character) (Registered User)
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
  update method is called by the Player
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
  to have the new appropriate xpos , so that after update is called by the Player, and the new
  positions are display, the xpos will be adjusted according to the speed and physical constants.
  
#Main player character moves right
Primary Actor: Player (Registered User)
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
The game player loads the game and the user can play the game. While in gameplay, if the key is pressed, the main character will move.

Extensions:
Let the user vary the velocity at which the main character moves.


###Main player character moves right
* Primary Actor: Player (Registered User)
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
  3. The game player loads the game and the user can play the game. While in gameplay, if the key is pressed, the main character will move.

* Extensions:
  * Let the user vary the velocity at which the main character moves.

  
###Main player character dies (death action)
* Primary Actor: Player (Registered User)
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
  4. The game player loads the game and the user can play the game. While in gameplay, if the death conditions are met, the character will take the death action.


  
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

### Player bouncing off of a wall
* Primary Actor: Player and Data
* Scope: 
  * Player observable
  * Data
  * Physics Engine
  * Collision Engine
* Brief: 
  *  Player collides into an object while moving at some velocity greater than zero. Depending on the ‘bounciness’ of the object, the player will change their velocity accordingly.
* Stakeholders
  * Sprite data
* Preconditions:
  * Player makes contact with a non-damaging, non-transparent sprite
* Postconditions
  * Player reflects momentum by some factor of collision velocity
* Minimal Guarantees:
  * Collision detected between player and surface
* Success Guarantees:
  * Player changes velocity accordingly (reflection of angle, momentum dissipation, etc.)
* Triggers:
  * Player contacts object which is ‘bounceable’
* Basic Flow
  * Player collision detected with an object
  * Causes map checked for ‘bounce’, and if valid, objects set to bounce
  * Velocity and heading entered and modified results and translated positions for next frame returned by physics engine
  * Data stores new physics values
  * Player displays changes to environment
* Extensions
  * Implement mass for objects and create momentum for both collider and collidee
  * Destructible environment, if we have a year and a fifty person team

### Player take damage from enemy
* Primary Actor: Player
* Scope: 
  * Data: Player and enemy sprites
  * Collision Engine
  * Data: Cause and Effect Interface
  * Player observable
* Brief: 
  *  Our majestic player, running gallantly through the meadows of level 5, lacks the time to look around the corner, makes a brave but fool-hearted leap, and collides face first into the scariest of all mid game foes. Upon hitting each other, the player is terribly hurt, the enemy laughs and the consequences continue to roll in the form of fear of future leaps.
* Stakeholders
  * Sprite data
* Preconditions:
  * Collision engine detects player contact with enemy
* Postconditions
  * Player loses health or a life with possible animation
  * Enemy potentially loses health or dies as well
  * Player recoils or gains temporary damage immunity
* Minimal Guarantees:
  * Player and enemy contact detected
* Success Guarantees:
  * Player loses the health/life associated with contact with the specific enemy
* Triggers:
  * Player contacts enemy while player not immune to damage
* Basic Flow
  * Collision detected between player and enemy sprite
  * Cause key for damage checked for any reasons to not take damage ex. Shield
  * If all conditions for damage true, damage value taken from enemy
  * Health of player taken and subtracted from in the data
  * Player and enemy each display any other actions associated with collision
* Extensions
  * Allow for recoil or temporary immortality after contact
  * Allow user-created script animation for death or damage

### Key input generates ‘bullet’
* Primary Actor: Player
* Scope: 
  * Data: Cause and Effect Interface
  * Data: Sprite
* Brief: 
  *  Upon pressing the properly mapped key in game, the game will respond to produce the projectile and any related effects (such as audio or image change) in addition to producing the new game sprite.
* Stakeholders
  * Sprite data
  * Player
* Preconditions:
  * Player is in a state in which the ‘fire’ key input boolean is deemed true for the fire effect.
* Postconditions
  * The cause for ‘fire’ input in the event map is made true, player produces a projectile, potentially audio, and any additional images or physical recoils. 
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
  * Animations of each sprite processed by player and engine
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
