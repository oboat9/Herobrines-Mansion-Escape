package lchs.cs.hme.screens;

/*
 * First Puzzle
 * Mostly A Tutorial
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

public class TutorialPuzzle implements Screen{

	/*
	 * all the main settings for the screen
	 */
	
	// the obervation text size (in pixels)
	private final int OBSERVEWIDTH = 1280;
	private final int OBSERVEHEIGHT = 720;
	
	// starts the game with you looking at the observation text
	boolean isObserving = true;
	String currentObservations = "P1S1";
	
	// turns to true when the player solves the puzzle
	boolean isComplete = false;
	//a timer to wait the puzzle complete chime to finish before going to the next scene
	double winChimeWaiter = 0;
	
	String facing = "north";
	
	/*
	 *  INIT ASSETS
	 */
	
	// pass through the main game file from HerobrineEscape.java
	HerobrineEscape game;
	
	// keeps track of the current background to render (also used to keep track of how "complete" the puzzle is)
	String currentBackground;
	
	// Textures
	Texture compassNorth;
	Texture compassSouth;
	Texture compassWest;
	Texture compassEast;
	
	Texture lvl1doorClosedTex;
	Texture lvl1doorOpenTex;
	Texture p1s1;
	Texture p1s2;
	
	// Sounds
	Sound clickSound;
	Sound badCommandSound;
	Sound pistonDoorSound;
	Sound puzzleClearSound;
	Sound victorySound;
	
	// Music
	static Music puzzleMusic;
	
	/*
	 * END INIT ASSETS
	 */
	
	//constructor (runs when the screen starts)
	public TutorialPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		
		// sets the initial background
		currentBackground = "lvl1doorclosed";
	}
	
	// runs when the game starts (loads the assets into memory from the file)
	
	@Override
	public void show() {
		
		//load images
		lvl1doorClosedTex = new Texture ("images/backgrounds/levelone/doorclosed.png");
		lvl1doorOpenTex = new Texture ("images/backgrounds/levelone/dooropen.png");
		p1s1 = new Texture ("images/scenedescriptions/firstlevel/P1S1.png");
		p1s2 = new Texture ("images/scenedescriptions/firstlevel/P1S2.png");
		
		compassNorth = new Texture ("images/compass/compass-north.gif");
		compassSouth = new Texture ("images/compass/compass-south.gif");
		compassWest = new Texture ("images/compass/compass-west.gif");
		compassEast = new Texture ("images/compass/compass-east.gif");
		
		//load sound
		pistonDoorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pistondoor.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		badCommandSound = Gdx.audio.newSound(Gdx.files.internal("sounds/badcommand.wav"));
		victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victorymusic.wav"));
		puzzleClearSound = Gdx.audio.newSound(Gdx.files.internal("sounds/puzzleclear.wav"));
		
		//load music
		puzzleMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/puzzlemusic.ogg"));
		puzzleMusic.setVolume(0.40f);
		puzzleMusic.setLooping(true); 
		puzzleMusic.play();
	}

	/*
	 * Render the game 60 times a second
	 */
	@Override
	public void render(float delta) {
		
		/* 
		 * checks if the timer hits 10 minutes to end the game
		 * 600 seconds = 10 minutes
		 */ 
		if (MainMenuScreen.time > 600) {
			this.dispose();
			game.setScreen(new GameOverScreen(game));
		}
		
		// opens command window upon pressing space
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			TextInput listener = new TextInput();
			Gdx.input.getTextInput(listener, "Enter Command", "", "");
		}
		
		// back to main menu upon pressing escape
		/*
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			TutorialPuzzle.puzzleMusic.stop();
			clickSound.play(1.0f);
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}
		*/
		
		// closes the obervation text upon pressing enter
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			clickSound.play(1.0f);
			isObserving = false;
		}
		
		// text input handling
		switch(TextInput.getText()) {
			//opens the obervation text
			case "look":
			case "l":
				isObserving = true;
				TextInput.currentCommand = "none";
				break;
				
			//if there is no command entered
			case "none":
				break;
			
			//opens the door
			case "use lever":
				//opens the door if it is still closed
				if (currentBackground.equals("lvl1doorclosed")) {
					pistonDoorSound.play(1.0f);
					currentBackground = "lvl1dooropen";
					isObserving = true;
					TextInput.currentCommand = "none";
				}
				break;
			//goes through the door
			case "north":
			case "n":
				//goes through the door only if it is open
				if (currentBackground.equals("lvl1dooropen")) {
					TextInput.currentCommand = "none";
					puzzleMusic.pause();
					puzzleClearSound.play();
					isComplete = true;
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;
			//runs if the player enters an invalid command
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
		}// CLOSES TEXT INPUT
		
		
		// runs if the user goes through the open door
		if(isComplete) {
			winChimeWaiter += Gdx.graphics.getDeltaTime();			
		}
		
		// waits for the puzzle end chime to end before going to the next level
		if(winChimeWaiter > 8) {
			this.dispose();
			game.setScreen(new LoadingScreen(game, "FirstPuzzle"));
		}
		
		// runs the escape timer (realtime)
		if (!isComplete) {
			MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		}
		
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape - " + "Lever Puzzle" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
		//clears the screen before drawing every frame
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		
		/*
		 * handles the rendering the background
		 * checks what the currentBackground string says and renders the appropriate background every frame
		 */
		switch (currentBackground) {
			//at the beginning of the level
			case "lvl1doorclosed":
				currentObservations = "P1S1";
				game.batch.draw(lvl1doorClosedTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//after the player types "use lever"
			case "lvl1dooropen":
				currentObservations = "P1S2";
				game.batch.draw(lvl1doorOpenTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
		}
		
		if(facing.equals("north")) {
			game.batch.draw(compassNorth, (HerobrineEscape.WIDTH-160), HerobrineEscape.HEIGHT-160, 160, 160);
		}
		if(facing.equals("south")) {
			game.batch.draw(compassSouth, (HerobrineEscape.WIDTH-160), HerobrineEscape.HEIGHT-160, 160, 160);
		}
		if(facing.equals("west")) {
			game.batch.draw(compassWest, (HerobrineEscape.WIDTH-160), HerobrineEscape.HEIGHT-160, 160, 160);
		}
		if(facing.equals("east")) {
			game.batch.draw(compassEast, (HerobrineEscape.WIDTH-160), HerobrineEscape.HEIGHT-160, 160, 160);
		}
		
		//if the observation text is supposed to be open it renders it
		if (isObserving) {
			switch(currentObservations) {
			case "P1S1":
				game.batch.draw(p1s1, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);									
				break;
			case "P1S2":
				game.batch.draw(p1s2, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);									
				break;
			}
		}
		
		// stop drawing things to the screen
		game.batch.end();
		
	}
	
	/*
	 * this gets rid of all the assets used in the level to clean it up after closing the game
	 */
	@Override
	public void dispose() {
		//dispose textures
		lvl1doorClosedTex.dispose();
		lvl1doorOpenTex.dispose();
		p1s1.dispose();
		
		//dispose audio
		//puzzleMusic.dispose();
		pistonDoorSound.dispose();
		puzzleClearSound.dispose();
		victorySound.dispose();
		clickSound.dispose();
		badCommandSound.dispose();
	}
	
	// all code below here is part of LibGDX and should not be changed unless you know what you are doing
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

}
