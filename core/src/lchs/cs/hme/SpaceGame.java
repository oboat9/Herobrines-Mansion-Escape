package lchs.cs.hme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lchs.cs.hme.screens.MainMenuScreen;

public class SpaceGame extends Game {
	
	public SpriteBatch batch;
	
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this));
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
