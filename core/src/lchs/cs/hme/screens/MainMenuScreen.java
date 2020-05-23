package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;


public class MainMenuScreen implements Screen {

	
	// sets the settings for things
	private static final int EXIT_BUTTON_WIDTH = 296;
	private static final int EXIT_BUTTON_HEIGHT = 62;
	private static final int EXIT_BUTTON_Y = 120;
	
	private static final int PLAY_BUTTON_WIDTH = 600;
	private static final int PLAY_BUTTON_HEIGHT = 60;
	private static final int PLAY_BUTTON_Y = EXIT_BUTTON_Y + 100;
	
	
	private static final int TITLE_WIDTH = HerobrineEscape.WIDTH-(HerobrineEscape.WIDTH/16);
	private static final int TITLE_HEIGHT = (int) (TITLE_WIDTH*0.09773);
	
	private static final int TITLE_Y = HerobrineEscape.HEIGHT - 200;
	
	private static final int BACKGROUND_WIDTH = HerobrineEscape.WIDTH;
	private static final int BACKGROUND_HEIGHT = (int) (BACKGROUND_WIDTH*0.625);
	
	
	
	HerobrineEscape game;
	
	// init textures
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	
	Texture titleTex;
	
	Texture wallpaper;
	
	Sound clickSound;
	
	static Music menuMusic;
	
	public static float time=0;
		
	public MainMenuScreen (HerobrineEscape game) {
		// passes the game through
		this.game = game;
		
		//loads textures from files
		playButtonActive = new Texture("images/buttons/play_button_active.png");
		playButtonInactive = new Texture("images/buttons/play_button_inactive.png");
		exitButtonActive = new Texture("images/buttons/exit_button_active.png");
		exitButtonInactive = new Texture("images/buttons/exit_button_inactive.png");
		
		titleTex = new Texture("images/titles/title.png");
		
		wallpaper = new Texture("images/backgrounds/menu_wallpaper_mansion_lowerbit.png");
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/titlescreenmusic.wav"));
		menuMusic.setVolume(0.25f);
		menuMusic.setLooping(true); 
		
	}
	
	@Override
	public void show() {
		if (!HerobrineEscape.isMenuMusicPlaying) {
			menuMusic.play();
			HerobrineEscape.isMenuMusicPlaying = true;
		}
	}

	// renders the game every frame
	@Override
	public void render(float delta) {
		
		// clears the screen between frames
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		// start drawing images to the screen
		game.batch.begin();
		
		// sets the coords of various things on the screen
		int playX = (HerobrineEscape.WIDTH/2) - (PLAY_BUTTON_WIDTH /2);
		int exitX = (HerobrineEscape.WIDTH/2) - (EXIT_BUTTON_WIDTH / 2);
		
		int titleX = (HerobrineEscape.WIDTH/2) - (TITLE_WIDTH /2);
		
		int playY = PLAY_BUTTON_Y;
		int exitY = EXIT_BUTTON_Y;
		
		//draws static images (wallpaper, title)
		game.batch.draw(wallpaper, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		game.batch.draw(titleTex, titleX, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
		
		if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && HerobrineEscape.HEIGHT - Gdx.input.getY() < exitY + EXIT_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > exitY) {
			// mouse hovers over button
			game.batch.draw(exitButtonActive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				clickSound.play(1.0f);
				Gdx.app.exit();
			}
		} else {
			// mouse does not over button
			game.batch.draw(exitButtonInactive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
		if (Gdx.input.getX() < playX + PLAY_BUTTON_WIDTH && Gdx.input.getX() > playX && HerobrineEscape.HEIGHT - Gdx.input.getY() < playY + PLAY_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > playY) {
			// mouse hovers over button
			game.batch.draw(playButtonActive, (HerobrineEscape.WIDTH/2) - (PLAY_BUTTON_WIDTH / 2), PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				clickSound.play(1.0f);
				this.dispose();
				game.setScreen(new MainRuleScreen(game));
			}
		} else {
			// mouse does not over button
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
