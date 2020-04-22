package engine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL11;

public class Core {
	
	public static void main(String[] args){
		long window = Application.Init();
		
		//keeps the game open until the user closes it
		while(!glfwWindowShouldClose(window)) {
			
			//poll the events at the beginning
			glfwPollEvents();
			
			//technical background code stuff
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			// background is black
			GL11.glClearColor(0, 0, 0, 1);
			
			// think pygame.display.flip()
			glfwSwapBuffers(window);
		}
		
		//Destory the window and clean up
		glfwTerminate();
	}
	
}

