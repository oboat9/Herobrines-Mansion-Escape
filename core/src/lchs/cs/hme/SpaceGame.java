package lchs.cs.hme;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends ApplicationAdapter {
	
	public static final float SPEED = 120;
	
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
		//System.out.println(Gdx.graphics.getDeltaTime());
		
		//character movement
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED*Gdx.graphics.getDeltaTime();
		} if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED*Gdx.graphics.getDeltaTime();
		} if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED*Gdx.graphics.getDeltaTime();
		} if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED*Gdx.graphics.getDeltaTime();
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
