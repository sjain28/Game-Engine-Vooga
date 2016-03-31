Team DoovalSalad: Use cases
===================
### Main Character Jump
* Primary Actor: Player (Registered User)
* Scope: Game map
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

###