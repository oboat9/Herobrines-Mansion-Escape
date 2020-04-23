package lchs.cs.hme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lchs.cs.hme.screens.MainGameScreen;

public class SpaceGame extends Game {
	
	public SpriteBatch batch;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
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
