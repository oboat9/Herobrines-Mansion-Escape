package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.SpaceGame;

public class MainRuleScreen implements Screen {

	private static final int EXIT_BUTTON_WIDTH = 296;
	private static final int EXIT_BUTTON_HEIGHT = 62;
	private static final int EXIT_BUTTON_Y = 50;
	
	private static final int OK_BUTTON_WIDTH = 254;
	private static final int OK_BUTTON_HEIGHT = 54;
	private static final int OK_BUTTON_Y = 150;
	
	
	private static final int TITLE_WIDTH = 588;
	private static final int TITLE_HEIGHT = 103;
	
	//private static final int TITLE_X = 200;
	private static final int TITLE_Y = 675;
	
	private static final int BACKGROUND_WIDTH = SpaceGame.WIDTH;
	private static final int BACKGROUND_HEIGHT = (int) (BACKGROUND_WIDTH*0.625);
	
	
	
	
	
	SpaceGame game;
	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture okButtonActive;
	Texture okButtonInactive;
	
	Texture titleTex;
	
	Texture wallpaper;
	
	public MainRuleScreen (SpaceGame game) {
		this.game = game;
		okButtonActive = new Texture("continue_button_active.png");
		okButtonInactive = new Texture("continue_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		
		titleTex = new Texture("How-To-Play.png");
		
		wallpaper = new Texture("rules_wallpaper.png");
		
		
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		// start drawing images to the screen
		game.batch.begin();
		
		
		int okX = (SpaceGame.WIDTH/2) - (OK_BUTTON_WIDTH /2);
		int exitX = (SpaceGame.WIDTH/2) - (EXIT_BUTTON_WIDTH / 2);
		
		int titleX = (SpaceGame.WIDTH/2) - (TITLE_WIDTH /2);
		
		int okY = OK_BUTTON_Y;
		int exitY = EXIT_BUTTON_Y;
		
		game.batch.draw(wallpaper, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		game.batch.draw(titleTex, titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		
		if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && SpaceGame.HEIGHT - Gdx.input.getY() < exitY + EXIT_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > exitY) {
			game.batch.draw(exitButtonActive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonInactive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
		if (Gdx.input.getX() < okX + OK_BUTTON_WIDTH && Gdx.input.getX() > okX && SpaceGame.HEIGHT - Gdx.input.getY() < okY + OK_BUTTON_HEIGHT && SpaceGame.HEIGHT - Gdx.input.getY() > okY) {
			game.batch.draw(okButtonActive, (SpaceGame.WIDTH/2) - (OK_BUTTON_WIDTH / 2), OK_BUTTON_Y, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				this.dispose();
				game.setScreen(new MainGameScreen(game));
			}
		} else {
			game.batch.draw(okButtonInactive, okX, okY, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
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
