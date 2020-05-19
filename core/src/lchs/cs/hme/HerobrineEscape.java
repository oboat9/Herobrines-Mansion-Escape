package lchs.cs.hme;

/*
 * Herobrine's Mansion Escape
 * Java escape game using LibGDX engine for java
 * 
 * Authors: 
 * 
 * Owen Stevenson, 
 * 
 * Steven Grabia,
 * 
 * Braden Horn,
 * 
 * Jacob Templado,
 * 
 * Carter Krause,
 * 
 * Jackson Daigneault
 * 
 */

import java.awt.Dimension;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lchs.cs.hme.screens.MainMenuScreen;

public class HerobrineEscape extends Game {
	
	public SpriteBatch batch;
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	//public static final int WIDTH = (int) dimension.getWidth();
	public static final int WIDTH = 1280;
	//public static final int HEIGHT = (int) dimension.getHeight();
	public static final int HEIGHT = 720;
	
	public static boolean isMenuMusicPlaying = false;
	
	
	
	@Override
	public void create () {
		// libGDX stuff (whos knows what is does)
		batch = new SpriteBatch();
		
		//sets the screen to the menu screen
		this.setScreen(new MainMenuScreen(this));
		
		// starts the music
		
		
		
		// sets the window title
		Gdx.graphics.setTitle("Herobrine's Mansion Escape");
	}

	@Override
	public void render () {
		// renders the entire window
		super.render();

	}
	
	@Override
	public void dispose () {
		// cleans up after the game closes
		batch.dispose();
	}
}
