package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;

public class LoadingScreen implements Screen {

	private static final int BACKGROUND_WIDTH = HerobrineEscape.WIDTH;
	private static final int BACKGROUND_HEIGHT = HerobrineEscape.HEIGHT;
	
	private double loadtime = 0;
	
	HerobrineEscape game;
	
	private String previousPuzzle;
	
	Texture wallpaper;
	
	//second argument is the current puzzle, the LoadingScreen class then determines the next puzzle to load based on that
	public LoadingScreen (HerobrineEscape game, String prevPuzzle) {
		this.game = game;
		wallpaper = new Texture("images/titles/loadingscreen.png");	
		previousPuzzle = prevPuzzle;
	}
	
	@Override
	public void show() {
		MainMenuScreen.menuMusic.stop();
		MainMenuScreen.menuMusic.dispose();
		HerobrineEscape.isMenuMusicPlaying = false;
	}

	@Override
	public void render(float delta) {
		
		// clears the screen before rendering the frame
		Gdx.gl.glClearColor(0, 0, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		
		// draws the static images (background, title, rules)
		game.batch.draw(wallpaper, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
		
		// stop drawing things to the screen 
		game.batch.end();
		
		loadtime += Gdx.graphics.getDeltaTime();
		if (loadtime > 0.05) {
			this.dispose();
			switch(previousPuzzle) {
				case "MainRuleScreen":
					game.setScreen(new OwenPuzzle(game));
					break;
				case "OwenPuzzle":
					game.setScreen(new TestPuzzle(game));
					break;
				default:
					game.setScreen(new GameOverScreen(game));
					break;
			}
		}
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
		wallpaper.dispose();
	}
}
