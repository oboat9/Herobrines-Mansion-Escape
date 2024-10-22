package lchs.cs.hme.screens;

/*
 * Second Puzzle
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

public class FirstPuzzle implements Screen{

	/*
	 * all the main settings for the screen
	 */
	
	// the obervation text size (in pixels)
	private final int OBSERVEWIDTH = 1280;
	private final int OBSERVEHEIGHT = 720;
	
	// starts the game with you looking at the observation text
	boolean isObserving = true;
	String currentObservations = "P2S1";
	
	// turns to true when the player solves the puzzle
	boolean isComplete = false;
	
	//a timer to wait the puzzle complete chime to finish before going to the next scene
	double winChimeWaiter = 0;
	
	String facing = "west";
	
	boolean hasStone = false;
	boolean bridgeBuilt = false;
	
	public static boolean foundMap = false;
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
	
	Texture P2S1;
	Texture P2S2;
	Texture P2S4;
	Texture P2S5;
	Texture P2S6;
	
	// Sounds
	Sound clickSound;
	Sound badCommandSound;
	Sound walkSound;
	Sound puzzleClearSound;
	Sound victorySound;
	
	/*
	 * END INIT ASSETS
	 */
	
	//constructor (runs when the screen starts)
	public FirstPuzzle (HerobrineEscape game) {
		// passes through the main game stuff
		this.game = game;
		
		// sets the initial background
		currentBackground = "bg1";
	}
	
	// runs when the game starts (loads the assets into memory from the file)
	
	@Override
	public void show() {
		
		//load images
		bg1 = new Texture ("images/backgrounds/leveltwo/bg1.png");
		bg2 = new Texture ("images/backgrounds/leveltwo/bg2.png");
		bg3 = new Texture ("images/backgrounds/leveltwo/bg3.png");
		bg4 = new Texture ("images/backgrounds/leveltwo/bg4.png");
		bg5 = new Texture ("images/backgrounds/leveltwo/bg5.png");
		bg6 = new Texture ("images/backgrounds/leveltwo/bg6.png");
		bg7 = new Texture ("images/backgrounds/leveltwo/bg7.png");
		
		P2S1 = new Texture ("images/scenedescriptions/secondlevel/P2S1.png");
		P2S2 = new Texture ("images/scenedescriptions/secondlevel/P2S2.png");
		P2S4 = new Texture ("images/scenedescriptions/secondlevel/P2S4.png");
		P2S5 = new Texture ("images/scenedescriptions/secondlevel/P2S5.png");
		P2S6 = new Texture ("images/scenedescriptions/secondlevel/P2S6.png");
		
		compassNorth = new Texture ("images/compass/compass-north.gif");
		compassSouth = new Texture ("images/compass/compass-south.gif");
		compassWest = new Texture ("images/compass/compass-west.gif");
		compassEast = new Texture ("images/compass/compass-east.gif");
		
		//load sound
		walkSound = Gdx.audio.newSound(Gdx.files.internal("sounds/walkstone.wav"));
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
			
				
			case "use stone":
			case "use blocks":
			case "pickup stone":
			case "pickup blocks":
			case "break stone":
			case "break blocks":
				
				if(currentBackground.equals("bg4")) {
					hasStone = true;
					currentBackground = "bg7";
					TextInput.currentCommand = "none";
					
				} else if ((currentBackground == "bg3") && (hasStone == true)) {
					bridgeBuilt = true;
					currentBackground = "bg6";
					TextInput.currentCommand = "none";
				}
				else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;
			//opens the door
			case "use bridge":
			case "build bridge":
			case "repair bridge":
			case "fix bridge":
			

				if ((currentBackground == "bg3") && (hasStone == true)) {
					bridgeBuilt = true;
					currentBackground = "bg6";
					TextInput.currentCommand = "none";
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;

			case "west":
			case "w":
				//goes through the door only if it is open
				if (currentBackground.equals("bg1")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					currentBackground = "bg2";
					
				} else if (currentBackground.equals("bg2")){
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg3";
					
				} else if (currentBackground.equals("bg3")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg4";
					
				} else if((currentBackground.equals("bg5"))) {
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg4";
					
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
					System.err.println("bad");
				}
				break;
			
			case "east":
			case "e":
				if(currentBackground.equals("bg4") || currentBackground.equals("bg7")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg3";
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;
				
			case "south":
			case "s":
				
				if(currentBackground.equals("bg3") && (bridgeBuilt == false)) {
					walkSound.play();
					badCommandSound.play();
					TextInput.currentCommand = "none";
				} else if((currentBackground.equals("bg4")) || currentBackground.equals("bg7")) {
					walkSound.play();
					TextInput.currentCommand = "none";
					foundMap = true;
					facing = "east";
					currentBackground = "bg5";
				} else if(currentBackground.equals("bg6") && (bridgeBuilt == true)) {
					walkSound.play();
					TextInput.currentCommand = "none";
					TutorialPuzzle.puzzleMusic.pause();
					puzzleClearSound.play();
					isComplete = true;
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				break;
				
			case "north":
			case "n":
				if((currentBackground.equals("bg5"))) {
					walkSound.play();
					TextInput.currentCommand = "none";
					facing = "south";
					currentBackground = "bg4";
					break;
				} else {
					badCommandSound.play();
					TextInput.currentCommand = "none";
				}
				

			//runs if the player enters an invalid command
			default:
				badCommandSound.play();
				TextInput.currentCommand = "none";
				break;
		} // CLOSES TEXT INPUT
		
		
		// runs if the user goes through the open door
		if(isComplete) {
			winChimeWaiter += Gdx.graphics.getDeltaTime();			
		}
		
		// waits for the puzzle end chime to end before going to the next level
		if(winChimeWaiter > 8) {
			this.dispose();
			game.setScreen(new LoadingScreen(game,"SecondPuzzle"));
		}
		
		// runs the escape timer (realtime)
		if (!isComplete) {
			MainMenuScreen.time += Gdx.graphics.getDeltaTime();
		}
		
		// converts timer from seconds to minutes:seconds
		int minutes = (int) ((MainMenuScreen.time % 3600) / 60);
		int seconds = (int) (MainMenuScreen.time % 60);
		// updates the timer in the title bar
		Gdx.graphics.setTitle("Herobrine's Mansion Escape - " + "Bridge Puzzle" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));
		
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
			case "bg1":
				game.batch.draw(bg1, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			//after the player types "use lever"
			case "bg2":
				game.batch.draw(bg2, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				//after the player types "use lever"
			case "bg3":
				currentObservations = "P2S2";
				game.batch.draw(bg3, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
				//after the player types "use lever"
			case "bg4":
				if(hasStone == true) {
					game.batch.draw(bg7, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
					currentObservations = "P2S4";
				} else{
					game.batch.draw(bg4, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
					currentObservations = "P2S4";
				}
				break;

			case "bg5":
				game.batch.draw(bg5, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				currentObservations = "P2S5";
				break;

			case "bg6":
				currentObservations = "P2S6";
				game.batch.draw(bg6, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			case "bg7":
				game.batch.draw(bg7, 0, 0, HerobrineEscape.WIDTH, HerobrineEscape.HEIGHT);
				break;
			default:
				game.setScreen(new LoadingScreen(game,"crash"));
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
				case "P2S1":
					game.batch.draw(P2S1, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
					break;
				case "P2S2":
					game.batch.draw(P2S2, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
					break;
				case "P2S4":
					game.batch.draw(P2S4, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
					break;
				case "P2S5":
					game.batch.draw(P2S5, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
					break;
				case "P2S6":
					game.batch.draw(P2S6, (HerobrineEscape.WIDTH/2)-(OBSERVEWIDTH/2), HerobrineEscape.HEIGHT/2-(OBSERVEHEIGHT/2), OBSERVEWIDTH, OBSERVEHEIGHT);					
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
		bg1.dispose();
		bg2.dispose();
		P2S1.dispose();
		
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
