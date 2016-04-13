#Team DoovalSalad: Extra use cases
(14 so far)
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

### Let user switch standard physics to planetary physics

* Primary Actor: Authoring Environment
* Scope: Authoring, game Engine, player
* Brief: If a player chooses to switch from the standard 'Mario' physics engine to one with sprites to gravitate towards, they should be able to switch to and fro and create a new game.
* Stakeholders: authoring, player, events
* Preconditions: authoring environment is open 
* PostCondition: authoring serializes a different physics module tag for the player 
* Minimal Guarantees: different physics module tag passed to game Engine for event calling (on authoring saved)
* Success Guarantees: player runs a game with an entirely different physics module
* Basic flow:
	1. User requests to change physics module in authoring
	2. authoring switches module tag for serialization and player adds any additional information per sprite (such as center of gravity or gravity strength)
	3. data serializes the new game
	4. game engine and player receive the new data in sprite and event managers 
	5. event manager now references the planetaryPhysics engine for events during running of player

### Sound implementation in game 

* Primary Actor: Player Display
* Scope: Authoring, game runner, player display, events
* Brief: Sound will be saveable and playable for specific inputs and in general play of the game.
* Stakeholders: player, fileManager
* Preconditions: user chooses to save an audio clip for an event in authoring
* PostCondition: player can play the audio clip when appropriate
* Minimal Guarantees: audio clip is playable
* Success Guarantees: dynamic sound is played when event is triggered
* Basic flow:
	1. As audio is not serializable, user directly saves audio files to a sound hub location when authoring specific events
	2. Player UI begins in game
	3. Sound triggering cause returns true
	4. Effect triggers sound to play
	5. playerDisplay (standardDisplay) loads audio file and plays sound in UI

### Producing overlayed sounds or pausing sounds for another

* Primary Actor: Player Display
* Scope: Game runner, player display, events
* Brief: For various sounds (weapon firing), the sound should simply overlay the main soundtrack, but for certain events (such as boss fight start), events should trigger all sounds to stop for another
* Stakeholders: player, fileManager
* Preconditions: user authored sounds to trigger overlay or stop other sound sequences
* PostCondition: player creates/pauses/deletes audio managers during user play
* Minimal Guarantees: player accesses audio files
* Success Guarantees: player handles audio like a real game
* Basic flow:
	1. game begins in player runner and displayed
	2. stacking/deleting audio cause detected
	3. new audio manager created or previous manager paused in manager list per event call
	4. player display creates the correct audio response beginning in the following frame

### Flip physics directions per key mid game

* Primary Actor: Player 
* Scope: game engine, events
* Brief: If a certain level or item features reversed physics, then the user should not have to recreate all of that in authoring, rather, some simple flip in the engine should be able to handle the change to cause left to be right, up to be down, etc. 
* Stakeholders: player, authoring
* Preconditions: user reaches point in run display UI to flip physics
* PostCondition: physics are flipped in game 
* Minimal Guarantees: physics flipping cause and event are created
* Success Guarantees: physics module can be inverted per mid player run 
* Basic flow:
	1. player runs a game with physics flipping set in authoring to occur for some event effect
	2. cause triggered in game to flip physics
	3. impacting velocity vectors coordinates flipped in sprite 
	4. for all physics method calls, results are the same but velocity changes experienced are all opposite to the expected
	5. player plays with the flipped sprite velocities


### Change volume of sound in game

* Primary Actor: GameDisplay
* Scope: Game runner, player display
* Brief: If the user is running the game, then he/she can change the volume or even mute the game sound (seperate music for fx as well)
* Stakeholders: Player
* Preconditions: Game is playing
* PostCondition: Game volume changed
* Minimal Guarantees: Player runs game at lower volume
* Success Guarantees: Volume transitions by the next frame
* Basic flow:
	1. Player runs, user clicks volume button
	2. User slides sliders to new volume levels for music and/or fx
	3. audio managers in playerDisplay modify output volume in gameLoop to new values
	4. play resumes in frame with the new volume

### User hits play for a file (updated)

* Primary Actor: Data
* Scope: data, authoring, playerRunner, gameEngine
* Brief: When the player feels ready to play their game from authoring, they can play and begin playing the game.
* Stakeholders: Player, data, gameEngine
* Preconditions: Authoring is available
* PostCondition: player Display begins running
* Minimal Guarantees: game play file deserialized to managers
* Success Guarantees: game play begins
* Basic flow:
	1. User clicks play from authoring
	2. Authoring calls to deserialize data and run the main method for the player display and runner
	3. data managers all load in nodes to display 
	4. player runs and runs the gameLoop in runner to handle events and general play of the game.
