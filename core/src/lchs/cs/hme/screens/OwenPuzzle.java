package lchs.cs.hme.screens;

/*
 * Owen's Puzzle
 * Author: Owen Stevnson
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;
import lchs.cs.hme.tools.TextInput;

public class OwenPuzzle implements Screen{

	//sets all the main settings for the game
	
	//temp
	public static final int PLAYER_WIDTH_PIXEL = 32;
	public static final int PLAYER_HEIGHT_PIXEL = 72;
	public static final int PLAYERWIDTH = PLAYER_WIDTH_PIXEL * 3;
	public static final int PLAYERHEIGHT = PLAYER_HEIGHT_PIXEL * 3;
	
	public static final int LVL1LOOKWIDTH = 600;
	public static final int LVL1LOOKHEIGHT = 100;
	
	//temp player starting location
	float x = 0;
	float y = 0;
	
	// resets the escape timer
	
	
	// i would hope this is self explanitory
	boolean isLooking = true;
	
	// mostly redundant time sync
	float stateTime; 
	
	String currentBackground;
	
	HerobrineEscape game;
	
	// init textures
	Texture playerImg;
	
	// level one assets
	Texture lvl1doorClosedTex;
	Texture lvl1doorOpenTex;
	Texture lvl1Description;
	
	Sound clickSound;
	Sound badCommandSound;
	Sound pistonDoorSound;
	
	Sound puzzleClearSound;
	Sound victorySound;
	
	Music puzzleMusic;
	
	// runs when the Main Game Screen is shown
	public OwenPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		// sets player starting location (temp)
		y = 50;
		x = HerobrineEscape.WIDTH /2 - PLAYERWIDTH /2;
		
		currentBackground = "lvl1doorclosed";

	}
	
	// runs when the game starts (loads the images)
	@Override
	public void show() {
		
		//load images
		playerImg = new Texture ("images/Steve.png");
		lvl1doorClosedTex = new Texture ("images/backgrounds/levelone/doorclosed.png");
		lvl1doorOpenTex = new Texture ("images/backgrounds/levelone/dooropen.png");
		lvl1Description = new Texture ("images/scenedescriptions/levelone.png");
		
		//load sound
		pistonDoorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pistondoor.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		badCommandSound = Gdx.audio.newSound(Gdx.files.internal("sounds/badcommand.wav"));
		victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victorymusic.wav"));
		puzzleClearSound = Gdx.audio.newSound(Gdx.files.internal("sounds/puzzleclear.wav"));
		
		puzzleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/puzzlemusic.ogg"));
		puzzleMusic.setVolume(0.40f);
		puzzleMusic.setLooping(true); 
		puzzleMusic.play();
		
	}

	//render the game every frame (60fps)
	@Override
	public void render(float delta) {
		
		if (MainMenuScreen.time > 600) {
			this.dispose();
			// TODO change to the game over screen
			game.setScreen(new GameOverScreen(game));
		}
		
		// opens command window
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			TextInput listener = new TextInput();
			Gdx.input.getTextInput(listener, "Enter Command", "", "");
		}
		// back to main menu
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			clickSound.play(1.0f);
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			clickSound.play(1.0f);
			isLooking = false;
		}
		

		// text command handling
		switch(TextInput.getText()) {
		/*
		 * Generic
		 */
			case "look":
			case "l":
				isLooking = true;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;

			/*
			 *  Level Specific
			 */
			case "use lever":
				if (currentBackground == "lvl1doorclosed") {
					puzzleMusic.pause();
					pistonDoorSound.play(1.0f);
					
					puzzleClearSound.play();
					currentBackground = "lvl1dooropen";
					TextInput.currentCommand = "none";
				}
				break;
				
			case "north":
			case "n":
				if (currentBackground == "lvl1dooropen") {
					TextInput.currentCommand = "none";
					this.dispose();
					game.setScreen(new MainRuleScreen(game));
				}
				/*
				 * Other (command doesnt exist)
				 */
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
		}
		
		
			
		
		
		// escape timer
		MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
		// mostly redundant frame rate sync (again)
		stateTime += delta;
		
		//clear the screen before every frame
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		
		//handles the background rendering
		switch (currentBackground) {	
			case "lvl1doorclosed":
				game.batch.draw(lvl1doorClosedTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			case "lvl1dooropen":
				game.batch.draw(lvl1doorOpenTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
		}
		
		
		
		//draw the player image (temp)
		game.batch.draw(playerImg, x, y, PLAYERWIDTH, PLAYERHEIGHT);
		
		if (isLooking) {
			switch(currentBackground) {
			case "lvl1doorclosed":
			case "lvl1dooropen":
				game.batch.draw(lvl1Description, (HerobrineEscape.WIDTH/2)-(LVL1LOOKWIDTH/2), HerobrineEscape.HEIGHT/2-(LVL1LOOKHEIGHT/2), LVL1LOOKWIDTH, LVL1LOOKHEIGHT);					
			}
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
		puzzleMusic.dispose();
		pistonDoorSound.dispose();
		puzzleClearSound.dispose();
		victorySound.dispose();
		clickSound.dispose();
		badCommandSound.dispose();
	}

}
