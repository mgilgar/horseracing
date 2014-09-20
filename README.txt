Done by Miguel Gil Garcia

Problem/ Exercise:
==================
Horse Racing Exercise

We are going to play a special version of the horse racing derby game (better known as Kentucky Derby) you can find in several fun fairs (see picture below).



Considerations

The track length is 1 furlong.
There will be a maximum of 7 horses playing. Consequently, the track has 7 lanes in it (numbered from 1 to 7).
Each horse has a name and it will be in one lane.
To move a horse each virtual player tosses a ball to a set of holes, each hole has a number. This is the number of yards the horse will advance.
There are 11 holes for each lane. The numbers in the holes are the following: 5, 5, 5, 5, 10, 10, 10, 20, 20, 40, 60
The winner will be the first horse to reach the goal (i.e. the first horse to go acrross 1 furlong)
The race ends when the first horse reaches the goal. The positions of the other horses will stand frozen even if more balls are thrown.
Input

The first line of the input will contain the names of the horses ordered according to the lane they will be running in: HORSE1, HORSE2, ....
The rest of the lines of the input will represent a ball throw. The format will be: LANE_NUMBER YARDS.
Output

The output will be the list of horses ordered by position in the race:

      Position, Lane, Horse name
      1, 2, HORSE2
      2, 5, HORSE5
    
Example test case

Test Input:

    Star, Dakota, Cheyenne, Misty, Spirit
    1 60
    3 5
    1 60
    4 5
    4 10
    2 5
    5 10
    1 60
    3 20
    7 10
    1 40
    2 60
  
Expected Output:

    Position, Lane, Horse name
    1, 1, Star
    2, 3, Cheyenne
    3, 4, Misty
    4, 5, Spirit
    5, 2, Dakota






Implementation notes:
I decide to not stop the game once a user reaches one furlong and only stop when there are two consecutive '\n'.
Then the input will be processed in order and the correct and first user who reaches one Furlong will be obtained.




To build
=========
mvn clean verify

To run from project home:
=========================
java -cp target/classes/ org.mgilgar.horseracing.App

and follow the given instructions