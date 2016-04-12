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
* Scope: Game runner, player display.
* Brief: If the user starts the game and decides to speed up the pace of the game, the user may click on the speed-up button to increase the framerate of the timeline.
* Stakeholders: Player
* Preconditions: The game is in play and playable.
* PostCondition: GameDisplay is displaying the game at a faster speed (at a higher framerate).
* Minimal Guarantees: Player continues to run and game is still playable.
* Success Guarantees: Game runs at a different framerate.
* Basic flow:
	1. While the game is in play, the player clicks SpeedUp button
	2. GameDisplay class calls GameRunner interface's SpeedUp method
	3. Framerate is updated in GameRunner
	4. AnimationTimer runs at the new framerate
    
###Speed down the game

* Primary Actor: GameDisplay
* Scope: Game runner, player display.
* Brief: If the user starts the game and decides to speed up the pace of the game, the user may click on the speed-down button to decrease the framerate of the timeline.
* Stakeholders: Player
* Preconditions: The game is in play and playable.
* PostCondition: GameDisplay is displaying the game at a slower speed (at a lower framerate).
* Minimal Guarantees: Player continues to run and game is still playable.
* Success Guarantees: Game runs at a different framerate.
* Basic flow:
	1. While the game is in play, the player clicks SpeedDown button
	2. GameDisplay class calls GameRunner interface's SpeedDown method
	3. Framerate is updated in GameRunner
	4. AnimationTimer runs at the new framerate

### A zigzag path is followed by a sprite entering main screen

* Primary Actor: Sprite in the animation
* Scope: Game runner, player display, eventsx
* Brief: When sprite authored to perform a zigzag enters the player display, it will follow a periodic zigzag path in its flight in game. The sprite can still collide with the player but ignores gravity and any friction.
* Stakeholders: Player, Events, GameEngine, Physics (may extract physics apart from general motion)
* Preconditions: Zigzagging sprite enters the bounds of the displayed portion of the level 
* PostCondition: Physics methods called per position in cause and effect to move the sprite
* Minimal Guarantees: Sprite is created in the level
* Success Guarantees: Player display reaches the sprite and it begins to zigzag
* Basic Flow:
	1. Authored sprite is read in to spriteManager
	2. Player.runner begins timer for level and display is created
	3. Sprite of interest enters the bounds of display (cause)
	4. Effect initiated for sprite to zigzag
	5. zigzag implemented frame by frame by checking conditions of location and translating accoringly
	6. Sprite leaves bound of display and animation ends

### Main player sprite moves right on key input

* Primary Actor: Sprite in the animation
* Scope: Game runner, player display, events
* Brief: The user presses an input key which maps to the main sprite moving right.
* Stakeholders: Player, Events, GameEngine, Physics
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
* Scope: Game runner, player display, events, sprite
* Brief: If a player chooses to teleport per some input, then they should be able to instantly jump to a new location. If the teleport action results in a player in the bounds of nontraversable wall, then the teleport action will be cancelled (or reversed)
* Stakeholders: Player, Events, GameEngine, Physics
* Preconditions: Player teleports into wall, collision detected
* PostCondition: Player teleport event cancelled (or reversed in the next frame)
* Minimal Guarantees: Player teleport into wall detected
* Success Guarantees: Player does not enter wall
* Basic flow:
	1. Key input registered as a key cause
	2. So long as teleporting sprite within bounds of UI for player display, cause for teleport confirmed
	3. Teleport event called 
	4. Either the animation timer has time in frame to interpolate the collision with wall and can cancel the teleport, or the teleport happens
	5. If the teleport occured (no time for interpolation), sprite collision with wall detected with teleport cause
	6. Sprite position reverted pre-teleport in next gameLoop frame.

### Allow player to rewatch gameplay

* Primary Actor: Game runner
* Scope: Game runner, player display.
* Brief: If the user plays through a level and decides it is a performance worth saving for later viewing, then they can choose to save the gameplay after level completion.
* Stakeholders: Player
* Preconditions: Level completion cause confirmed
* PostCondition: Video or play either saved or passed on
* Minimal Guarantees: Player can choose to save performance
* Success Guarantees: Play saves performance
* Basic flow:
	1. As game played by user, each frame state is saved to a temporary file.
	2. In player UI, level is played and completion cause confirmed
	3. player is prompted to save their performance
	4. temporary video file is saved for the users viewing pleasure.
