package org.mgilgar.horseracing;

/**
 * Player/ Horse class.
 * 
 * @author mgilgar
 *
 */
public class Player implements Comparable<Player> {
	
	private static final int ONE_FURLONG = 220;
	
	private int lane;
	private String name;
	private int distance;
	
	public Player(int lane, String name) {
		super();
		this.lane = lane;
		this.name = name;
		this.distance = 0;
	}
	
	public int getLane() {
		return lane;
	}

	public void setLane(int lane) {
		this.lane = lane;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDistance() {
		return distance;
	}
	public void addDistance(int distance) {
		this.distance = this.distance + distance;
		if (this.distance > ONE_FURLONG) this.distance = ONE_FURLONG;
	}

	public int compareTo(Player otherPlayer) {
       return (this.distance < otherPlayer.distance ) ? -1: (this.distance > otherPlayer.distance) ? 1:0 ;
	}
	
}
