package lchs.cs.hme;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x = 0;
	float y = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//character movement
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += 5;
		} if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= 5;
		} if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += 5;
		} if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= 5;
		}
		
		// start drawing images to the screen
		batch.begin();
		
		//draw the img image
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
