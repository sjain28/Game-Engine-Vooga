Team DoovalSalad: Use cases
> Game Engine Use Cases

===================
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

> Game Authoring Use Cases

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



