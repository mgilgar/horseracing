package org.mgilgar.horseracing;

/**
 * Toss class. Represents everytime a user throws the ball.
 * 
 * 
 * @author mgilgar
 *
 */
public class Toss {
	
	private int playerIndex; // horse identifier
	private int distance; // the score or distance to go
	
	public Toss(int playerIndex, int distance) {
		this.playerIndex = playerIndex;
		this.distance = distance;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
}
