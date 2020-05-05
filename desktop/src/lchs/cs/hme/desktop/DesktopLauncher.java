package lchs.cs.hme.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lchs.cs.hme.HerobrineEscape;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		// sets various settings for the window
		config.foregroundFPS = 60;
		config.width = HerobrineEscape.WIDTH;
		config.height = HerobrineEscape.HEIGHT;
		config.resizable = false;
		config.fullscreen = false;
		
		// add the window icons
		config.addIcon("images/icons/icon_128.png", FileType.Internal);
		config.addIcon("images/icons/icon_32.png", FileType.Internal);
		config.addIcon("images/icons/icon_16.png", FileType.Internal);
		new LwjglApplication(new HerobrineEscape(), config);
	}
}
