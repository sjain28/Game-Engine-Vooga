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
