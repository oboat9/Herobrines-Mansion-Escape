package lchs.cs.hme.screens;

/*
 *Last / Fifth Puzzle
 * Author: Owen Stevnson
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;
import lchs.cs.hme.tools.TextInput;

public class FourthPuzzle implements Screen{

	/*
	 * all the main settings for the screen
	 */
	
	// the obervation text size (in pixels)
	private final int OBSERVEWIDTH = 600;
	private final int OBSERVEHEIGHT = 100;
	
	// starts the game with you looking at the observation text
	boolean isObserving = true;
	
	// turns to true when the player solves the puzzle
	boolean isComplete = false;
	//a timer to wait the puzzle complete chime to finish before going to the next scene
	double winChimeWaiter = 0;
	
	String facing = "west";
	
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


	Texture lvl1Description;
	
	//acutal textures used
	Texture lvl5EntranceTex;
	Texture lvl5KeypadTex;
	Texture lvl5ExitTex;
	Texture lvl5OutsideTex;
	
	
	// Sounds
	Sound clickSound;
	Sound badCommandSound;
	Sound pistonDoorSound;
	Sound puzzleClearSound;
	Sound victorySound;
	
	/*
	 * END INIT ASSETS
	 */
	
	//constructor (runs when the screen starts)
	public FourthPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		
		// sets the initial background
		currentBackground = "lvl5Entrance";
	}
	
	// runs when the game starts (loads the assets into memory from the file)
	
	@Override
	public void show() {
		
		//load images
		lvl1Description = new Texture ("images/scenedescriptions/levelone.png");
		
		
		lvl5EntranceTex = new Texture ("images/backgrounds/levelfive/bg1.png");
		lvl5KeypadTex = new Texture("images/backgrounds/levelfive/bg2.png");
		lvl5ExitTex = new Texture("images/backgrounds/levelfive/bg3.png");
		lvl5OutsideTex = new Texture("images/backgrounds/levelfive/bg4.png");
		
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
		
		
		/*
		lvl5EntranceTex
		lvl5KeypadTex
		lvl5ExitTex
		lvl5OutsideTex
		*/
		

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
			

		    //level's puzzle commands
				
			case "west":
			case "w":
				//goes to puzzle completing area
				if (currentBackground == "lvl5Entrance") {
					facing = "west";
					currentBackground = "lvl5Keypad";
					TextInput.currentCommand = "none";
				}
				
			break; 
			case "north":
			case "n":

				//goes outside after finishing puzzle
				if (currentBackground == "lvl5Exit") {
					facing = "north";
					currentBackground = "Outside";
					TextInput.currentCommand = "none";
				}
				
				break;
			
			//complete's puzzle
			case "1234":
				if (currentBackground == "lvl5Keypad") {
					pistonDoorSound.play(1.0f);
					facing = "north";
					currentBackground = "lvl5Exit";
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
			game.setScreen(new LoadingScreen(game, "SuccessScreen"));
		}
		
		// runs the escape timer (realtime)
		if (!isComplete) {
			MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		}
		
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape " + "Fourth Puzzle" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
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
			case "lvl5Entrance":
				game.batch.draw(lvl5EntranceTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;

			//after player walks to keypad
			case "lvl5Keypad":
				game.batch.draw(lvl5KeypadTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			
			//after riddle is solved
			case "lvl5Exit":
				game.batch.draw(lvl5ExitTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//outside screen
			case "Outside":
				game.batch.draw(lvl5OutsideTex, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
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
			game.batch.draw(lvl1Description, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
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

		lvl1Description.dispose();
		
		lvl5EntranceTex.dispose();
		lvl5KeypadTex.dispose();
		lvl5ExitTex.dispose();
		lvl5OutsideTex.dispose();
		
		//dispose audio
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
