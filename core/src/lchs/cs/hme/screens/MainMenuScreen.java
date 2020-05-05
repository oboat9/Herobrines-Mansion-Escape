package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;



public class MainMenuScreen implements Screen {

	private static final int EXIT_BUTTON_WIDTH = 296;
	private static final int EXIT_BUTTON_HEIGHT = 62;
	private static final int EXIT_BUTTON_Y = 150;
	
	private static final int PLAY_BUTTON_WIDTH = 600;
	private static final int PLAY_BUTTON_HEIGHT = 60;
	private static final int PLAY_BUTTON_Y = 250;
	
	
	private static final int TITLE_WIDTH = 1424;
	private static final int TITLE_HEIGHT = 112;
	
	//private static final int TITLE_X = 200;
	private static final int TITLE_Y = 575;
	
	private static final int BACKGROUND_WIDTH = HerobrineEscape.WIDTH;
	private static final int BACKGROUND_HEIGHT = (int) (BACKGROUND_WIDTH*0.625);
	
	
	
	
	
	HerobrineEscape game;
	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	
	Texture titleTex;
	
	Texture wallpaper;
	
	Sound clickSound;
	
	public MainMenuScreen (HerobrineEscape game) {
		this.game = game;
		playButtonActive = new Texture("play_button_active.png");
		playButtonInactive = new Texture("play_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		
		titleTex = new Texture("title.png");
		
		wallpaper = new Texture("menu_wallpaper_mansion_lowerbit.png");
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("buttonclick.wav"));
		
		
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
		
		
		int playX = (HerobrineEscape.WIDTH/2) - (PLAY_BUTTON_WIDTH /2);
		int exitX = (HerobrineEscape.WIDTH/2) - (EXIT_BUTTON_WIDTH / 2);
		
		int titleX = (HerobrineEscape.WIDTH/2) - (TITLE_WIDTH /2);
		
		int playY = PLAY_BUTTON_Y;
		int exitY = EXIT_BUTTON_Y;
		
		game.batch.draw(wallpaper, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		game.batch.draw(titleTex, titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		
		if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && HerobrineEscape.HEIGHT - Gdx.input.getY() < exitY + EXIT_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > exitY) {
			game.batch.draw(exitButtonActive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				//clickSound.play(1.0f);
				clickSound.play(1.0f);
				Gdx.app.exit();
			}
		} else {
			game.batch.draw(exitButtonInactive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
		if (Gdx.input.getX() < playX + PLAY_BUTTON_WIDTH && Gdx.input.getX() > playX && HerobrineEscape.HEIGHT - Gdx.input.getY() < playY + PLAY_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > playY) {
			game.batch.draw(playButtonActive, (HerobrineEscape.WIDTH/2) - (PLAY_BUTTON_WIDTH / 2), PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				clickSound.play(1.0f);
				this.dispose();
				game.setScreen(new MainRuleScreen(game));
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
