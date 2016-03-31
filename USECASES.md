Team DoovalSalad: Use cases
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

=============

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
