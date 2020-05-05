package lchs.cs.hme;

import java.awt.Dimension;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lchs.cs.hme.screens.MainMenuScreen;

public class HerobrineEscape extends Game {
	
	public SpriteBatch batch;
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	//public static final int WIDTH = (int) dimension.getWidth();
	public static final int WIDTH = 1600;
	//public static final int HEIGHT = (int) dimension.getHeight();
	public static final int HEIGHT = 900;
	
	public Music menuMusic;
	
	@Override
	public void create () {
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("titlescreenmusic.wav"));
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
		menuMusic.setVolume(0.3f);
		menuMusic.setLooping(true); 
		menuMusic.play();
		Gdx.graphics.setTitle("Herobrine's Mansion Escape");
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
