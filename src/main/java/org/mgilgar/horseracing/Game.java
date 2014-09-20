package org.mgilgar.horseracing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * Game class.
 * A game contains a list of players and all the operations to change the statuses of the players based on the given input.
 *
 * Rules of the Game:
 * 
 * We are going to play a special version of the horse racing derby game (better known as Kentucky Derby) you can find in several 
 * fun fairs.
 * 
 * The track length is 1 furlong.
 * There will be a maximum of 7 horses playing. Consequently, the track has 7 lanes in it (numbered from 1 to 7).
 * Each horse has a name and it will be in one lane.
 * To move a horse each virtual player tosses a ball to a set of holes, each hole has a number. This is the number of yards the horse will advance.
 * There are 11 holes for each lane. The numbers in the holes are the following: 5, 5, 5, 5, 10, 10, 10, 20, 20, 40, 60
 * The winner will be the first horse to reach the goal (i.e. the first horse to go acrross 1 furlong)
 * The race ends when the first horse reaches the goal. The positions of the other horses will stand frozen even if more balls are thrown.
 * Input
 *
 * The first line of the input will contain the names of the horses ordered according to the lane they will be running in: HORSE1, HORSE2, ....
 * The rest of the lines of the input will represent a ball throw. The format will be: LANE_NUMBER YARDS.
 * Output
 *
 * The output will be the list of horses ordered by position in the race:
 *
 *     Position, Lane, Horse name
 *     1, 2, HORSE2
 *     2, 5, HORSE5
 *  
 * Example test case
 *
 * Test Input:
 *
 *   Star, Dakota, Cheyenne, Misty, Spirit
 *   1 60
 *   3 5
 *   1 60
 *   4 5
 *   4 10
 *   2 5
 *   5 10
 *   1 60
 *   3 20
 *   7 10
 *   1 40
 *   2 60
 * 
 * Expected Output:
 *
 *  
 *   Position, Lane, Horse name
 *   1, 1, Star
 *   2, 3, Cheyenne
 *   3, 4, Misty
 *   4, 5, Spirit
 *   5, 2, Dakota
 */
public class Game 
{	

	private static Set<Integer> ALLOWED_DISTANCES = new HashSet<Integer>(Arrays.asList(5, 10, 20, 40, 60));	
	private static final int ONE_FURLONG = 220;

	BufferedReader in;
	
	List<Player> players = new ArrayList<Player>();


	/**
	 * plays the horse racing game until the end of the game.
	 */
	public void play()
    {
        String s;
        try {
        	List<Toss> tossList = new ArrayList<Toss>();
			while (( s = in.readLine()) != null && s.length() != 0 && !s.equals("\n")) {
				if (players.size() == 0) {
					this.buildPlayers(s);
				} else {
					Toss toss = buildToss(s);
					if (toss!=null) {
						tossList.add(toss); // Save ball throws and then we will apply them
					}
				}
			}
			// We apply ball throws
			for (Toss toss: tossList) {
				this.applyToss(toss);
				// We stop when we reach ONE_FURLONG
				if (toss.getPlayerIndex() < players.size() && toss.getPlayerIndex() >= 0 
						&& players.get(toss.getPlayerIndex()).getDistance() == ONE_FURLONG) {
					break;
				}
			}
			Collections.sort(players);
			Collections.reverse(players);
			System.out.println(this.getPlayersString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * Applies a toss to the game changing the status of the players accordingly.
	 * if the toss refers to a non existent player, it will leave as it was. 
	 * if the toss uses a non allowed distance, it will leave as it was.
	 * 
	 * @param toss the {@link Toss} to apply to the game.
	 */
	protected void applyToss(final Toss toss) {
		if (ALLOWED_DISTANCES.contains(toss.getDistance()) && toss.getPlayerIndex() < players.size() && toss.getPlayerIndex() >= 0)  {
			players.get(toss.getPlayerIndex()).addDistance(toss.getDistance());			
		}
	}
	
	/**
	 * Given an input, it builds the corresponding list of players for the game.
	 * @param s players input in a command-separated list of horse names.
	 * 
	 */
	protected void buildPlayers(final String s) {
		StringTokenizer st = new StringTokenizer(s, ",");
		int counter = 0;
		while (st.hasMoreTokens() && counter < 7) {
			players.add(new Player(counter+1, st.nextToken().trim()));
			counter++;
		}
	}
	
	/**
	 * Given an input, it builds and returns the corresponding toss.
	 * 
	 * @param s toss input containing a list of two space-separated values. First one refers to 
	 * the player lane and second one to the distance.
	 * @return a Toss object representing the given input.
	 */
	protected Toss buildToss(String s) {
		Toss toss = null;
		String[] tossInput = s.split(" ");
		if (tossInput.length == 2) {
			toss = new Toss(Integer.valueOf(tossInput[0])-1, Integer.valueOf(tossInput[1]));
		}
		return toss;
	}
	
	/**
	 * Returns the list of players
	 * @return the list of players.
	 */
	protected List<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Given a List of players it returns a String representing the status of the game.
	 * @return a string representing the status of the game.
	 */
	protected String getPlayersString() {
		StringBuilder string = new StringBuilder();
		int counter = 1;
		string.append("\nPosition, Lane, Horse Name\n");
		for (Player player: players) {
			string.append(counter).append(", ").append(player.getLane()).append(", ").append(player.getName()).append("\n");
			counter++;
		}
		return string.toString();
	}
	
	/**
	 * Sets the in member.
	 * @param in
	 */
	protected void setIn(BufferedReader in) {
		this.in = in;
	}
	
	/**
	 * Sets the players member.
	 * @param players
	 */
	protected void setPlayers(List<Player> players) {
		this.players = players;
	}
}
