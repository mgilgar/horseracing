package org.mgilgar.horseracing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private static final int ONE_FURLONG = 220;
	
	Player player1;
	Player player2;
	
	@Before
	public void setUp() {
		player1 = new Player(1, "HORSE1");
		player1.addDistance(5);
		player2 = new Player(2, "HORSE2");
	}
	
	@Test
	public void addDistanceShouldStopWhenReachingOneFurlong() {
		player1.addDistance(240);
		Assert.assertEquals(player1.getDistance(), ONE_FURLONG);
	}
	
	@Test
	public void compareToShouldReturn1IsPlayer1DistanceIsMoreThanPlayers2() {
		Assert.assertTrue(player1.compareTo(player2) == 1);
	}

	@Test
	public void compareToShouldReturn0IsPlayer1DistanceIsEqualsToPlayers2() {
		player2.addDistance(5);
		Assert.assertTrue(player1.compareTo(player2) == 0);
	}
	
	@Test
	public void compareToShouldReturnMinus1IsPlayer1DistanceIsLessThanPlayers2() {
		player2.addDistance(10);
		Assert.assertTrue(player1.compareTo(player2) == -1);
	}
}
