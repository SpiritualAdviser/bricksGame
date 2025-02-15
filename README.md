Cavas And Crystals 
========
![logo](https://github.com/user-attachments/assets/b7e392d9-3e62-4190-b1a2-cd04561b7a96)

# History

When I was writing web games in javascript, I had the idea that it would be great to create a standalone game that is not connected to the Internet. At this point, I decided to learn the new kotlin programming language and android application development. So I got excited to write my own game.


# Project objectives
Creating this project was a challenge for me to create a game in a new language. The goal of this project was to create a simple, ad-free logic game aimed at the average player. This game contains the best three-in-a-row game mechanics. At the same time, it is not loaded logically and allows you to spend time comfortably.

# How to play
In the game you have to open 200 scrolls, Each scroll is a level in which you need to complete the objectives. The next level opens after completing the previous one. By dropping combinations of colored crystals, you score points. When the required number of points is reached, the level is completed successfully. You have a limited number of moves to do this, and there are obstacles on the playing squares that need to be cleared. Among other things, your achievements are saved in the player's card. You can create as many players as you need, but each player has their own game progress.

# Technologies
The project is implemented using the Single Activity Application pattern. In the game, I use elements from my engine to calculate collisions of objects on the stage. The visual design is implemented on Jetpack Compose. I use Hilt for dependencies. The database is based on Room. I am deploying json files using the libs.gson library. To animate the winning and losing levels libs.coil.gif . The game implements the logic of launching sprites without using any libraries. I use navigation compose to navigate the game. The audio was played using MediaPlayer

# Game elements
## The main menu is the main component of the entrance to the application
## Player menu - here we create and select players in the game
## Free-to-play mode - One level for endless play and practice
## Adventure is the main story mode of the game
## Information - information about the author and links to his works
