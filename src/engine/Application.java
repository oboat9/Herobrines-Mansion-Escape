package engine;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;

import org.lwjgl.opengl.GL;

public class Application {
	
	private static final String name = "HME - Escape";
	private static int width = 1280;
	private static int height = 720;
	private static long window;
	
	public static long Init() {
	//if glfw cannot init
		
		if (!glfwInit()) {
			System.err.println("GLFW failed init");
			System.exit(1);
		}//if
		
		window = glfwCreateWindow(width, height, name, 0 , 0);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glClearColor(0,0,0,1);
		
		return window;
	}//Init
}//class
