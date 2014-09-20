package org.mgilgar.horseracing;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for Game class.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameTest 
{
	private static final String PLAYERS1_INPUT = "HORSE0, HORSE1, HORSE2, HORSE3, HORSE4, HORSE5, HORSE6";
	private static final String PLAYERS2_INPUT = "HORSE0, HORSE1, HORSE2, HORSE3, HORSE4, HORSE5, HORSE6, HORSE7, HORSE8";
	private static final String PLAYERS3_INPUT = "HORSE0, HORSE1, HORSE2, HORSE3";
	private static final String TOSS1_INPUT = "2 5";
	private static final String TOSS2_INPUT = "2";
	private static final String TOSS3_INPUT = "2 5 5";
	private static final String TOSS4_INPUT = "3 10";
	private static final String TOSS5_INPUT = "3 20";
	private static final String TOSS6_INPUT = "4 5";
	private static final String TOSS7_INPUT = "5 20";
	private static final String TOSS8_INPUT = "6 60";
	private static final Toss TOSS1 = new Toss(1, 5);
	private static final Toss TOSS2 = new Toss(1000, 5);
	private static final Toss TOSS3 = new Toss(1, 7);
	private static final int ONE_FURLONG = 220;

	private Player player0 = new Player(1, "HORSE0");
	private Player player1 = new Player(2, "HORSE1");
	private Player player2 = new Player(3, "HORSE2");
	private List<Player> players1 = Arrays.asList(player0, player1, player2);

	private Game app;

	@Mock
	BufferedReader in;
	
	@Before
	public void setup() {
		app = new Game();
		app.setIn(in);
	}
		
	@Test
	public void applyTossShouldAddDistanceOnAPlayer() {
		app.setPlayers(players1);
		app.applyToss(TOSS1);
		Assert.assertEquals(players1.get(TOSS1.getPlayerIndex()).getDistance(), TOSS1.getDistance());
	}
	
	@Test
	public void applyTossShouldWorkIfPlayerIsNotFound() {
		app.setPlayers(players1);
		app.applyToss(TOSS2);
		for (Player player: players1) {
			Assert.assertEquals(player.getDistance(), 0);			
		}
	}
	
	@Test
	public void applyTossShouldNotMakeAnyChangeIfTheDistanceIsNotAllowed() {
		app.setPlayers(players1);
		app.applyToss(TOSS3);
		for (Player player: players1) {
			Assert.assertEquals(player.getDistance(), 0);			
		}
	}
	
	@Test
	public void buildPlayersShouldPopulatePlayers() {
		app.buildPlayers(PLAYERS1_INPUT);
		Assert.assertEquals(app.getPlayers().size(), 7);
	}

	@Test
	public void buildPlayersShouldPopulateOnlyUpTo7Players() {
		app.buildPlayers(PLAYERS2_INPUT);
		Assert.assertEquals(app.getPlayers().size(), 7);
	}

	@Test
	public void buildPlayersShouldPopulateLessThan7PlayersIfThereLessThan7() {
		app.buildPlayers(PLAYERS3_INPUT);
		Assert.assertEquals(app.getPlayers().size(), 4);
	}
	
	@Test
	public void buildTossShouldReturnAWellPopulatedToss() {
		Toss toss = app.buildToss(TOSS1_INPUT);
		Assert.assertEquals(toss.getPlayerIndex(), 1);
		Assert.assertEquals(toss.getDistance(), 5);
	}
	
	@Test
	public void buildTossShouldReturnNullIfThereAreLessThan2Elements() {
		Toss toss = app.buildToss(TOSS2_INPUT);
		Assert.assertNull(toss);
	}
	
	@Test
	public void buildTossShouldReturnNullIfThereAreMoreThan2Elements() {
		Toss toss = app.buildToss(TOSS3_INPUT);
		Assert.assertNull(toss);
	}
	
	@Test
	public void getPlayersStringShouldReturnAStringRepresentingThePlayers() throws IOException {
		app.setPlayers(players1);
		String string = app.getPlayersString();
		Assert.assertEquals(string, "\nPosition, Lane, Horse Name\n1, 1, HORSE0\n2, 2, HORSE1\n3, 3, HORSE2\n");
	}
	
	@Test
    public void playShouldTerminateIfEmptyIsGiven() throws IOException {
		when(in.readLine()).thenReturn("\n");
		app.play();
    	Assert.assertTrue(true);
    }
	
	@Test
	public void playShouldReadPlayerNamesFirstThing() throws IOException {
		when(in.readLine())
			.thenReturn(PLAYERS1_INPUT)
			.thenReturn("\n");
		app.play();
		List<Player> players = app.getPlayers();
		Assert.assertNotNull(players);
		Assert.assertEquals(players.size(), 7);
	}
	
	@Test
	public void playShouldApplyTossAfterReadingPlayers() throws IOException {
		when(in.readLine())
		.thenReturn(PLAYERS1_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn(TOSS4_INPUT)
		.thenReturn("\n");
		app.play();
		List<Player> players = app.getPlayers();
		Assert.assertNotNull(players);
		Assert.assertEquals(players.get(0).getDistance(), 10);
		Assert.assertEquals(players.get(1).getDistance(), 5);
	}
	
	@Test
	public void playShouldPrintTheResultWhenEndOfLineAfterBallThrows() throws IOException {
		when(in.readLine())
		.thenReturn(PLAYERS1_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn(TOSS4_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn("\n");
		app.play();
		List<Player> players = app.getPlayers();
		Assert.assertNotNull(players);
		Assert.assertEquals(players.get(0).getDistance(), 10);
		Assert.assertEquals(players.get(1).getDistance(), 10);		
	}
	
	@Test
	public void playShouldOrderPlayersBasedOnDistancesAtTheEndOfTheGame() throws IOException {
		when(in.readLine())
		.thenReturn(PLAYERS1_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn(TOSS4_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn(TOSS5_INPUT)
		.thenReturn(TOSS6_INPUT)
		.thenReturn(TOSS7_INPUT)
		.thenReturn(TOSS8_INPUT)
		.thenReturn("\n");
		app.play();
		List<Player> players = app.getPlayers();
		for (int counter=0; counter < players.size()-1; counter++) {
			Assert.assertTrue(players.get(counter).getDistance() >= players.get(counter+1).getDistance());
		}
	}
	
	
	@Test
	public void playShouldFinishOnceOnePlayerHasReachedOneFurlongAndWeFinishTheGame() throws IOException {
		when(in.readLine())
		.thenReturn(PLAYERS1_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn(TOSS8_INPUT)
		.thenReturn(TOSS8_INPUT)
		.thenReturn(TOSS8_INPUT)
		.thenReturn(TOSS8_INPUT)
		.thenReturn(TOSS1_INPUT)
		.thenReturn("\n");
		app.play();
		List<Player> players = app.getPlayers();
		Assert.assertEquals(players.get(0).getDistance(), ONE_FURLONG);
		Assert.assertEquals(players.get(1).getDistance(), 5);
		for (int counter=1; counter < players.size(); counter++) {
			Assert.assertNotEquals(players.get(counter).getDistance(), ONE_FURLONG);
		}
	}

}
