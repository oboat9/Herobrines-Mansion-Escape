package engine;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class Core {
	
	public static void main(String[] args){
		// init glfw
		long window = Application.Init();
		
		//keeps the game open until the user closes it
		while(!glfwWindowShouldClose(window)) {
			
			//poll the events at the beginning
			glfwPollEvents();
			
			if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
				glfwSetWindowShouldClose(window, true);
				//GLFW.glfwTerminate();
				break;
			}

			//technical background code stuff (clears the screen so put before drawing anything)
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			//make a test square
			glBegin(GL_QUADS);
			//top left
				glColor4f(1,0,0,1);
				glVertex2f(-0.5f, 0.5f);
			//top right
				glColor4f(1,0,0,1);
				glVertex2f(0.5f, 0.5f);
			//bottom right
				glColor4f(1,0,0,1);
				glVertex2f(0.5f, -0.5f);
			//bottom left
				glColor4f(1,0,0,1);
				glVertex2f(-0.5f, -0.5f);
			glEnd();
			
			// think pygame.display.flip()
			glfwSwapBuffers(window);
		}
		
		//Destory the window and clean up
		glfwTerminate();
	}
	
}

