package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.SpaceGame;

public class MainMenuScreen implements Screen {

	private static final int EXIT_BUTTON_WIDTH = 300;
	private static final int EXIT_BUTTON_HEIGHT = 150;
	private static final int EXIT_BUTTON_Y = 100;
	
	private static final int PLAY_BUTTON_WIDTH = 330;
	private static final int PLAY_BUTTON_HEIGHT = 135;
	private static final int PLAY_BUTTON_Y = 250;
	
	
	SpaceGame game;
	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	
	public MainMenuScreen (SpaceGame game) {
		this.game = game;
		playButtonActive = new Texture("play_button_active.png");
		playButtonInactive = new Texture("play_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		
		Gdx.gl.glClearColor(0, 0, .1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		// start drawing images to the screen
		game.batch.begin();
		
		
		int playX = (SpaceGame.WIDTH/2) - (PLAY_BUTTON_WIDTH /2);
		int exitX = (SpaceGame.WIDTH/2) - (EXIT_BUTTON_WIDTH / 2);
		
		int playY = PLAY_BUTTON_Y;
		int exitY = EXIT_BUTTON_Y;
		
		if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && SpaceGame.HEIGHT - Gdx.input.getY() < exitY + EXIT_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > exitY) {
			game.batch.draw(exitButtonActive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonInactive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
		if (Gdx.input.getX() < playX + PLAY_BUTTON_WIDTH && Gdx.input.getX() > playX && SpaceGame.HEIGHT - Gdx.input.getY() < playY + PLAY_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > playY) {
			game.batch.draw(playButtonActive, (SpaceGame.WIDTH/2) - (PLAY_BUTTON_WIDTH / 2), PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new MainGameScreen(game));
			}
		} else {
			game.batch.draw(playButtonInactive, playX, playY, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}
		
		// stop drawing things to the screen 
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	
	
}
