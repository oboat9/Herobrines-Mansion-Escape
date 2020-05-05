package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;
import lchs.cs.hme.tools.TextInput;

public class MainGameScreen implements Screen{

	//sets all the main settings for the game
	
	//temp
	public static final int PLAYER_WIDTH_PIXEL = 32;
	public static final int PLAYER_HEIGHT_PIXEL = 72;
	public static final int PLAYERWIDTH = PLAYER_WIDTH_PIXEL * 3;
	public static final int PLAYERHEIGHT = PLAYER_HEIGHT_PIXEL * 3;
	
	//temp player starting location
	float x = 0;
	float y = 0;
	
	// resets the escape timer
	float time=0;
	
	// mostly redundant time sync
	float stateTime; 
	
	HerobrineEscape game;
	
	// init textures
	Texture playerImg;
	
	// runs when the Main Game Screen is shown
	public MainGameScreen (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		// sets player starting location (temp)
		y = 50;
		x = HerobrineEscape.WIDTH /2 - PLAYERWIDTH /2;
		
		// pauses the menu music (will be used when/if we get different music for puzzles)
		//game.menuMusic.pause();

	}
	
	// runs when the game starts (loads the images)
	@Override
	public void show() {
		playerImg = new Texture ("images/Steve.png");
		
	}

	//render the game every frame (60fps)
	@Override
	public void render(float delta) {
		
		// opens command window
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			TextInput listener = new TextInput();
			Gdx.input.getTextInput(listener, "Enter Command", "", "");
		}
		// back to main menu
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			game.menuMusic.play();
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}

		// text command handling
		switch(TextInput.getText()) {
		
		//player movement
			case "up":
				y += 100;
				TextInput.currentCommand = "none";
				break;
			case "down":
				y -= 100;
				TextInput.currentCommand = "none";
				break;
			case "left":
				x -= 100;
				TextInput.currentCommand = "none";
				break;
			case "right":
				x  += 100;
				TextInput.currentCommand = "none";
				break;
		}
		
		// escape timer
		time += Gdx.graphics.getDeltaTime();
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((time % 3600) / 60);
		int seconds = (int) (time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
		// mostly redundant frame rate sync (again)
		stateTime += delta;
		
		//clear the screen before every frame
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		
		//draw the player image (temp)
		game.batch.draw(playerImg, x, y, PLAYERWIDTH, PLAYERHEIGHT);
		
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
		
	}

}
