package lchs.cs.hme.tools;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input.TextInputListener;

public class TextInput extends ApplicationAdapter implements TextInputListener{

	String text;
	
	public static String currentCommand = "none";
	
	@Override
	public void input(String text) {
		
		if(text == "up") {
			System.out.println("itsgoodduck");
		}
		
		//System.out.println(text);
		currentCommand = text;
		
		if(currentCommand == "up") {
			System.out.println("FORK");
		}
		
		//System.out.println(currentCommand);
		}
	
	@Override
	public void canceled() {
		System.out.println("oof");
		}
		
	public static String getText(){
		//System.out.println(currentCommand);
		return currentCommand;
		}

}
