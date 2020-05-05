package lchs.cs.hme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lchs.cs.hme.screens.MainMenuScreen;
import lchs.cs.hme.screens.MainGameScreen;

public class HerobrineEscape extends Game {
	
	public SpriteBatch batch;
	
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
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
