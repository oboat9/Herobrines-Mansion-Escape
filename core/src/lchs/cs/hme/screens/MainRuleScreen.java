package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;

public class MainRuleScreen implements Screen {

	private static final int EXIT_BUTTON_WIDTH = 191;
	private static final int EXIT_BUTTON_HEIGHT = 54;
	private static final int EXIT_BUTTON_Y = 30;
	
	private static final int OK_BUTTON_WIDTH = 254;
	private static final int OK_BUTTON_HEIGHT = 54;
	private static final int OK_BUTTON_Y = 120;

	
	private static final int RULES_WIDTH = 1280;
	private static final int RULES_HEIGHT = 720;
	
	private static final int RULES_Y = 0;
	
	private static final int BACKGROUND_WIDTH = HerobrineEscape.WIDTH;
	private static final int BACKGROUND_HEIGHT = (int) (BACKGROUND_WIDTH*0.625);
	
	
	
	
	
	HerobrineEscape game;
	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture okButtonActive;
	Texture okButtonInactive;

	Texture rulesTex;
	
	Texture wallpaper;
	
	Sound clickSound;
	
	public MainRuleScreen (HerobrineEscape game) {
		this.game = game;
		okButtonActive = new Texture("images/buttons/continue_button_active.png");
		okButtonInactive = new Texture("images/buttons/continue_button_inactive.png");
		exitButtonActive = new Texture("images/buttons/back_button_active.png");
		exitButtonInactive = new Texture("images/buttons/back_button_inactive.png");

		rulesTex = new Texture("images/titles/rules.png");
		
		wallpaper = new Texture("images/backgrounds/rules_wallpaper.png");
		
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		
		
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
		
		// set the coordinates of all the images
		int okX = (HerobrineEscape.WIDTH/2) - (OK_BUTTON_WIDTH /2);
		int exitX = (HerobrineEscape.WIDTH/2) - (EXIT_BUTTON_WIDTH / 2);
		
		int rulesX = (HerobrineEscape.WIDTH/2) - (RULES_WIDTH /2);
		
		int okY = OK_BUTTON_Y;
		int exitY = EXIT_BUTTON_Y;
		
		// draws the static images (background, title, rules)
		game.batch.draw(wallpaper, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		game.batch.draw(rulesTex, rulesX, RULES_Y, RULES_WIDTH, RULES_HEIGHT);
		
		//handles the exit button
		if (Gdx.input.getX() < exitX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > exitX && HerobrineEscape.HEIGHT - Gdx.input.getY() < exitY + EXIT_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > exitY) {
			// mouse hovers over button
			game.batch.draw(exitButtonActive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				clickSound.play(1.0f);
				this.dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		} else {
			// mouse does not over button
			game.batch.draw(exitButtonInactive, exitX, exitY, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}
		
		//handles the continue button
		if (Gdx.input.getX() < okX + OK_BUTTON_WIDTH && Gdx.input.getX() > okX && HerobrineEscape.HEIGHT - Gdx.input.getY() < okY + OK_BUTTON_HEIGHT && HerobrineEscape.HEIGHT - Gdx.input.getY() > okY) {
			// mouse hovers over button
			game.batch.draw(okButtonActive, (HerobrineEscape.WIDTH/2) - (OK_BUTTON_WIDTH / 2), OK_BUTTON_Y, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
			if (Gdx.input.isTouched()) {
				clickSound.play(1.0f);
				this.dispose();
				
				game.setScreen(new LoadingScreen(game));
			}
		} else {
			// when the mouse is not hovering over the button
			game.batch.draw(okButtonInactive, okX, okY, OK_BUTTON_WIDTH, OK_BUTTON_HEIGHT);
		}
		
		// stop drawing things to the screen 
		game.batch.end();
	}
	// all code below here is part of LibGDX and should not be changed
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
		exitButtonActive.dispose();
		exitButtonInactive.dispose();
		okButtonActive.dispose();
		okButtonInactive.dispose();
		rulesTex.dispose();
		wallpaper.dispose();
		clickSound.dispose();
	}

	
	
}
