package lchs.cs.hme.screens;

/*
 * Third Puzzle
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

public class SecondPuzzle implements Screen{

	/*
	 * all the main settings for the screen
	 */
	
	// the obervation text size (in pixels)
	private final int OBSERVEWIDTH = 1280;
	private final int OBSERVEHEIGHT = 720;
	
	// starts the game with you looking at the observation text
	boolean isObserving = true;
	
	// turns to true when the player solves the puzzle
	boolean isComplete = false;
	//a timer to wait the puzzle complete chime to finish before going to the next scene
	double winChimeWaiter = 0;
	
	
	boolean falling = false;
	double fallWaiter = 0;
	boolean hasFell = false;
	
	String facing = "east";
	
	int currentMazePos = 0;
	
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
	
	Texture bg1;
	Texture bg2;
	Texture bg3;
	Texture bg4;
	Texture bg5;
	Texture bg6;
	Texture bg7;
	Texture bgblack;
	Texture bgblackmap;
	
	// TODO
	Texture lvl1Description;
	
	// Sounds
	Sound clickSound;
	Sound badCommandSound;
	Sound fallThenSplashSound;
	Sound walkSound;
	Sound puzzleClearSound;
	Sound victorySound;
	
	/*
	 * END INIT ASSETS
	 */
	
	//constructor (runs when the screen starts)
	public SecondPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		
		// sets the initial background
		currentBackground = "bg1";
	}
	
	// runs when the game starts (loads the assets into memory from the file)
	
	@Override
	public void show() {
		
		//load images
		bg1 = new Texture ("images/backgrounds/levelthree/bg1.png");
		bg2 = new Texture ("images/backgrounds/levelthree/bg2.png");
		bg3 = new Texture ("images/backgrounds/levelthree/bg3.png");
		bg4 = new Texture ("images/backgrounds/levelthree/bg4.png");
		bg5 = new Texture ("images/backgrounds/levelthree/bg5.png");
		bg6 = new Texture ("images/backgrounds/levelthree/bg6.png");
		bg7 = new Texture ("images/backgrounds/levelthree/bg7.png");
		bgblack = new Texture ("images/backgrounds/levelthree/bgblack.png");
		bgblackmap = new Texture ("images/backgrounds/levelthree/bgblackmap.png");
		
		lvl1Description = new Texture ("images/scenedescriptions/levelone.png");

		
		
		compassNorth = new Texture ("images/compass/compass-north.gif");
		compassSouth = new Texture ("images/compass/compass-south.gif");
		compassWest = new Texture ("images/compass/compass-west.gif");
		compassEast = new Texture ("images/compass/compass-east.gif");
		
		//load sound
		walkSound = Gdx.audio.newSound(Gdx.files.internal("sounds/walkstone.wav"));
		fallThenSplashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fallthensplash.ogg"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/buttonclick.wav"));
		badCommandSound = Gdx.audio.newSound(Gdx.files.internal("sounds/badcommand.wav"));
		victorySound = Gdx.audio.newSound(Gdx.files.internal("sounds/victorymusic.wav"));
		puzzleClearSound = Gdx.audio.newSound(Gdx.files.internal("sounds/puzzleclear.wav"));
		
		//start music
		TutorialPuzzle.puzzleMusic.play();
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
		if(currentMazePos == 0) {
		switch(TextInput.getText()) {
			//opens the observation text
			case "look":
			case "l":
				isObserving = true;
				TextInput.currentCommand = "none";
				break;
				
			//if there is no command entered
			case "none":
				break;
				
			case "south":
			case "s":
				if(currentBackground.equals("bg3")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					currentBackground = "bg4";
					
				} else if (currentBackground.equals("bg4")){
					walkSound.play();
					TextInput.currentCommand = "none";
					currentMazePos = 1;
					currentBackground = "bgblack";
					
				} else if (currentBackground.equals("bg5")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					currentBackground = "bg6";
				} else if (currentBackground.equals("bg6")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					currentBackground = "bg7";
				} else if(currentBackground.equals("bg7")){
					walkSound.play();
					fallThenSplashSound.play();
					falling = true;
					TextInput.currentCommand = "none";
					currentBackground = "bg8";
					
				} 
				else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;
				
			case "east":
			case "e":
				if(currentBackground.equals("bg1")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					currentBackground = "bg2";
					
				} else if(currentBackground.equals("bg2")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg3";
					
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
				
		} //closes main movement switch
		} // if currentMazePos = 0;
		
		/*
		 * MAZE MOVEMENT
		 */
		if(currentMazePos == 1) {
			switch(TextInput.getText()){
			
				case "west":
				case "w":
					walkSound.play();
					currentMazePos = 2;
					TextInput.currentCommand = "none";
					break;
				case "none":
					break;
				default:
					badCommandSound.play();
					TextInput.currentCommand = "none";
					break;
			}
		} else if(currentMazePos == 2) {
			switch(TextInput.getText()){
			
			case "south":
			case "s":
				walkSound.play();
				currentMazePos = 3;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
		} else if(currentMazePos == 3) {
			switch(TextInput.getText()){
		
			case "east":
			case "e":
				walkSound.play();
				currentMazePos = 4;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}	
			
		} else if(currentMazePos == 4) {
			switch(TextInput.getText()){

			case "south":
			case "s":
				walkSound.play();
				currentMazePos = 5;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 5) {
			switch(TextInput.getText()){
			
			case "east":
			case "e":
				walkSound.play();
				currentMazePos = 6;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 6) {
			switch(TextInput.getText()){
			
			case "north":
			case "n":
				walkSound.play();
				currentMazePos = 7;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 7) {
			switch(TextInput.getText()){
			
			case "east":
			case "e":
				walkSound.play();
				currentMazePos = 8;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 8) {
			switch(TextInput.getText()){
			
			case "south":
			case "s":
				walkSound.play();
				currentMazePos = 9;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 9) {
			switch(TextInput.getText()){
			
			case "north":
			case "west":
			case "w":
				walkSound.play();
				currentMazePos = 10;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 10) {
			switch(TextInput.getText()){
			
			case "north":
			case "n":
				walkSound.play();
				currentMazePos = 11;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 11) {
			switch(TextInput.getText()){
			
			case "west":
			case "w":
				walkSound.play();
				currentMazePos = 12;
				TextInput.currentCommand = "none";
				break;
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
			
		} else if(currentMazePos == 12) {
			switch(TextInput.getText()){
			
			case "south":
			case "s":
				walkSound.play();
				currentMazePos = 0;
				currentBackground = "bg5";
				TextInput.currentCommand = "none";
				break;
		
			case "none":
				break;
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
			}
		}
		
		

		if(falling) {
			fallWaiter += Gdx.graphics.getDeltaTime();
		} if (fallWaiter > 2.75 && hasFell == false) {
			hasFell = true;
			TutorialPuzzle.puzzleMusic.pause();
			puzzleClearSound.play();
			isComplete = true;
		}
		
		if(isComplete) {
			winChimeWaiter += Gdx.graphics.getDeltaTime();			
		}
		
		// waits for the puzzle end chime to end before going to the next level
		if(winChimeWaiter > 8) {
			this.dispose();
			game.setScreen(new LoadingScreen(game,"ThirdPuzzle"));
		}
		
		// runs the escape timer (realtime)
		if (!isComplete) {
			MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		}
		
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape - " + "Maze Puzzle" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
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

			case "bg1":
				game.batch.draw(bg1, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;

			case "bg2":
				game.batch.draw(bg2, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			case "bg3":
				game.batch.draw(bg3, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			case "bg4":
				game.batch.draw(bg4, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			case "bg5":
				game.batch.draw(bg5, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			case "bg6":
				game.batch.draw(bg6, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				
			case "bg7":
				game.batch.draw(bg7, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			
			case "bg8":
				break;
				
			case "bgblack":
				System.out.println(currentMazePos);
				if(FirstPuzzle.foundMap) {
					game.batch.draw(bgblackmap, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
					break;					
				} else {
					game.batch.draw(bgblack, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
					break;					
				}
				
			default:
				game.setScreen(new LoadingScreen(game,"crash"));
				break;
				
		}
		if(!currentBackground.equals("bgblack")) {
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
		bg1.dispose();
		bg2.dispose();
		bg3.dispose();
		bg4.dispose();
		bg5.dispose();
		bg6.dispose();
		bg7.dispose();
		bgblack.dispose();
		
		compassNorth.dispose();
		compassSouth.dispose();
		compassEast.dispose();
		compassWest.dispose();
		//dispose textures
		lvl1Description.dispose();
		
		//dispose audio
		walkSound.dispose();
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
