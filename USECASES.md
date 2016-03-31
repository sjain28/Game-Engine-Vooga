Team DoovalSalad: Use cases

# Game Engine Use Cases
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

===========
### Main Character Moves Left
* Primary Actor: Player (Main Character) (Registered User)
* Scope: Game map (level)
* Level: ! (User goal or sea level)
* Brief: (equivalent to a user story or an epic)
  *The user presses a left arrow button that causes the main character to move to the left.
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
