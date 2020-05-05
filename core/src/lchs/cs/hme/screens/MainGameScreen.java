package lchs.cs.hme.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import lchs.cs.hme.HerobrineEscape;
import lchs.cs.hme.tools.TextInput;

public class MainGameScreen implements Screen{

	public static final float SPEED = 300;
	public static final float SHIP_ANIMATION_SPEED = 0.5f;
	public static final int PLAYER_WIDTH_PIXEL = 32;
	public static final int PLAYER_HEIGHT_PIXEL = 72;
	
	public static final int PLAYERWIDTH = PLAYER_WIDTH_PIXEL * 3;
	public static final int PLAYERHEIGHT = PLAYER_HEIGHT_PIXEL * 3;

	//Animation<TextureRegion>[] rolls;
	
	float x = 0;
	float y = 0;
	
	float time=0;
	
	
	
	int roll;
	
	float stateTime; 
	
	HerobrineEscape game;
	
	Texture playerImg;
	
	public MainGameScreen (HerobrineEscape game) {
		this.game = game;
		y = 50;
		x = HerobrineEscape.WIDTH /2 - PLAYERWIDTH /2;
		game.menuMusic.pause();

	}
	
	@Override
	public void show() {
		playerImg = new Texture ("images/Steve.png");
		
	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			TextInput listener = new TextInput();
			Gdx.input.getTextInput(listener, "Enter Command", "", "");
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			game.menuMusic.play();
			this.dispose();
			game.setScreen(new MainMenuScreen(game));
		}

		//text command handling
		switch(TextInput.getText()) {
			case "up":
				y += 100;
				System.out.println("going up!");
				TextInput.currentCommand = "none";
				break;
			case "down":
				y -= 100;
				System.out.println("going down!");
				TextInput.currentCommand = "none";
				break;
			case "left":
				x -= 100;
				System.out.println("going left!");
				TextInput.currentCommand = "none";
				break;
			case "right":
				x  += 100;
				System.out.println("going right!");
				TextInput.currentCommand = "none";
				break;
		}
		
		
		time += Gdx.graphics.getDeltaTime();
		//System.out.println(time);
		
		int minutes = (int) ((time % 3600) / 60);
		int seconds = (int) (time % 60);
		
		//System.out.println((9-minutes)+":"+(60-seconds));
		Gdx.graphics.setTitle("Herobrine's Mansion Escape" + " - Time Remaining: " + (9-minutes) + ":" + (59-seconds));

		//timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
		
		stateTime += delta;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// start drawing images to the screen
		game.batch.begin();
		//draw the img image
		game.batch.draw(playerImg, x, y, PLAYERWIDTH, PLAYERHEIGHT);
		// stop drawing things to the screen
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		//playerImg.dispose();
		
	}

}
