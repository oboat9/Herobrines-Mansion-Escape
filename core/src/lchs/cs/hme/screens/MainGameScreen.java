package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.SpaceGame;

public class MainGameScreen implements Screen{

	public static final float SPEED = 120;
	Texture playerImg;
	float x = 0;
	float y = 0;
	
	SpaceGame game;
	
	public MainGameScreen (SpaceGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		playerImg = new Texture("Steve.png");
		
	}

	@Override
	public void render(float delta) {
		//character movement
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			y += SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		//draw the img image
		game.batch.draw(playerImg, x, y, 128, 288);
		// stop drawing things to the screen
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		playerImg.dispose();
		
	}

}
