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