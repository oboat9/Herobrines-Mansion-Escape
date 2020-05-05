package lchs.cs.hme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lchs.cs.hme.HerobrineEscape;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = HerobrineEscape.WIDTH;
		config.height = HerobrineEscape.HEIGHT;
		new LwjglApplication(new HerobrineEscape(), config);
	}
}
