package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;

import lchs.cs.hme.SpaceGame;

public class MainGameScreen implements Screen{

	public static final float SPEED = 300;
	public static final float SHIP_ANIMATION_SPEED = 0.5f;
	public static final int SHIP_WIDTH_PIXEL = 32;
	public static final int SHIP_HEIGHT_PIXEL = 72;
	
	public static final int SHIPWIDTH = SHIP_WIDTH_PIXEL * 3;
	public static final int SHIPHEIGHT = SHIP_HEIGHT_PIXEL * 3;

	//Animation<TextureRegion>[] rolls;
	
	float x = 0;
	float y = 0;
	
	
	
	int roll;
	
	float stateTime; 
	
	SpaceGame game;
	
	Texture playerImg;
	
	public MainGameScreen (SpaceGame game) {
		this.game = game;
		y = 15;
		x = SpaceGame.WIDTH /2 - SHIPWIDTH /2;

	}
	
	@Override
	public void show() {
		playerImg = new Texture ("Steve.png");
		
	}

	@Override
	public void render(float delta) {
		//character movement
		if(Gdx.input.isKeyPressed(Keys.UP) | Gdx.input.isKeyPressed(Keys.W)) {
			y += SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.DOWN) | Gdx.input.isKeyPressed(Keys.S)) {
			y -= SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.RIGHT) | Gdx.input.isKeyPressed(Keys.D)) {
			x += SPEED*Gdx.graphics.getDeltaTime();
		} 
		if(Gdx.input.isKeyPressed(Keys.LEFT) | Gdx.input.isKeyPressed(Keys.A)) {
			x -= SPEED*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		//draw the img image
		game.batch.draw(playerImg, x, y, SHIPWIDTH, SHIPHEIGHT);
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
		//playerImg.dispose();
		
	}

}
