package lchs.cs.hme.screens;

/*
 * First (real) Puzzle
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

public class ThirdPuzzle implements Screen{

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
	
	Texture lvl1Description;
	
	Texture bg1;
	Texture bg2;
	Texture bgwhite;
	Texture bgred;
	Texture bgcyan;
	Texture bgpink;
	Texture bgpurple;
	Texture bgorange;
	Texture bggreen;
	Texture bg4;
	Texture bg5;
	Texture bg6;
	Texture bg7;
	
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
	public ThirdPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		
		// sets the initial background
		currentBackground = "Entrance";
	}
	
	// runs when the game starts (loads the assets into memory from the file)
	
	@Override
	public void show() {
		
		//load images
		bg1 = new Texture ("images/backgrounds/levelfour/bg1.png");
		bg2 = new Texture ("images/backgrounds/levelfour/bg2.png");
		bgwhite = new Texture ("images/backgrounds/levelfour/bgwhite.png");
		bgred = new Texture ("images/backgrounds/levelfour/bgred.png");
		bgcyan = new Texture ("images/backgrounds/levelfour/bgcyan.png");
		bgpink = new Texture ("images/backgrounds/levelfour/bgpink.png");
		bgpurple = new Texture ("images/backgrounds/levelfour/bgpurple.png");
		bgorange = new Texture ("images/backgrounds/levelfour/bgorange.png");
		bggreen = new Texture ("images/backgrounds/levelfour/bggreen.png");
		bg4 = new Texture ("images/backgrounds/levelfour/bg4.png");
		bg5 = new Texture ("images/backgrounds/levelfour/bg5.png");
		bg6 = new Texture ("images/backgrounds/levelfour/bg6.png");
		bg7 = new Texture ("images/backgrounds/levelfour/bg7.png");
		
		lvl1Description = new Texture ("images/scenedescriptions/levelone.png");

		compassNorth = new Texture ("images/compass/compass-north.gif");
		compassSouth = new Texture ("images/compass/compass-south.gif");
		compassWest = new Texture ("images/compass/compass-west.gif");
		compassEast = new Texture ("images/compass/compass-east.gif");
		
		//load sound
		//pistonDoorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pistondoor.wav"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		badCommandSound = Gdx.audio.newSound(Gdx.files.internal("sounds/badcommand.wav"));
		victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victorymusic.wav"));
		puzzleClearSound = Gdx.audio.newSound(Gdx.files.internal("sounds/puzzleclear.wav"));
		
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
		
		/// back to main menu upon pressing escape
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
			
            /*
             * For this puzzle you move north once to the main solving area
             * and type in "puzzle answer" to open exit door
             * 
             * 
             * Then you repeatedly type the north command in until you get into
             * the elevator (after dropping into hole, player should move into elevator
             * and into next level automatically with space of time between
             */
				
			case "north":
			case "n":
				//goes to puzzle area
				if (currentBackground == "Entrance") {
					facing = "north";
					currentBackground = "PuzzleOverview";
					TextInput.currentCommand = "none";
				}
				//exit opens
				else if (currentBackground == "PuzzleSolved") {
					currentBackground = "PuzzleExit";
					TextInput.currentCommand = "none";
					
				}
				//dropping into hole
				else if (currentBackground == "PuzzleExit") {
					currentBackground = "ExitTunnel";
					TextInput.currentCommand = "none";
					
				}
				//moving towards elevator
				else if (currentBackground == "ExitTunnel") {
					currentBackground = "ExitElevator";
					TextInput.currentCommand = "none";
					
				}
				break;
				//after solving puzzle
			case "puzzle answer":
				if (currentBackground == "PuzzleOverview") {
					facing = "north";
					currentBackground = "PuzzleSolved";
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
			game.setScreen(new LoadingScreen(game,"FourthPuzzle"));
		}
		
		// runs the escape timer (realtime)
		if (!isComplete) {
			MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		}
		
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape " + "Third Puzzle" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
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
			case "Entrance":
				game.batch.draw(bg1, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//after player walks to overview of puzzle (second pic)
			case "PuzzleOverview":
				game.batch.draw(bg2, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			//puzzle wall colours
				
			case "CyanWall":
				game.batch.draw(bgcyan, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "GreenWall":
				game.batch.draw(bggreen, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "OrangeWall":
				game.batch.draw(bgorange, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "PinkWall":
				game.batch.draw(bgpink, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "PurpleWall":
				game.batch.draw(bgpurple, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "RedWall":
				game.batch.draw(bgred, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
			case "WhiteWall":
				game.batch.draw(bgwhite, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;	
				
				
				
			
				
			//after player solved puzzle
			case "PuzzleSolved":
				game.batch.draw(bg4, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//player walks to exit
			case "PuzzleExit":
				game.batch.draw(bg5, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//after goes into exit hole
			case "ExitTunnel":
				game.batch.draw(bg6, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//after goes into exit hole
			case "ExitElevator":
				game.batch.draw(bg7, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
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
