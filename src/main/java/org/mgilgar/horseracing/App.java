package org.mgilgar.horseracing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Main Application
 * 
 * @author mgilgar
 *
 */
public class App {

	public static void main(String[] args) {
		Game game = new Game();
		game.setIn(new BufferedReader(new InputStreamReader(System.in)));
		game.play();
	}

}
