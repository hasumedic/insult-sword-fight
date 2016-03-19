# Description
Command line implementation of the mythical sword-fighting game in Monkey Island 1.

Mechanics are very simple. The verbal fight is split between insults and comebacks. The player will start the game knowing a couple of insults, and the aim of the game is to learn insults and comebacks from other pirates. Every time an insult or a comeback is mentioned during a fight, the player "learns" it and is able to use it in the very same fight and in next fights.

Once the player knows at least 75% of the insults and respective comebacks, can challenge the Sword Master™. The Sword Master™ uses different insults than the ones learnt from regular battles, but the same comebacks apply.
 
The game ends when the player beats the Sword Master™ or quits the game.

# Requirements
* Scala 2.11.7+
* SBT 0.13.8+

# Run

Simply run in a console with access to SBT: 

`sbt run`

# TODO
* Remove the possibility of being asked the same insults by pirates/Sword Master on the same fight
* Better error handling on non-expected inputs
* Better display of insult options, removing the display of their IDs (this should make the game tiny bit harder)
* Code clean-up to eliminate duplication
* Make it more functional, isolating side-effects as much as possible
