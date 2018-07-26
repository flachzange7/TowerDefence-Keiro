package engine.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window 
{
	public Window(String title, int width, int height) 
	{
		Display.setTitle(title);
		
		try
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			
			Keyboard.create();
			Mouse.create();
			
		}
		catch(LWJGLException e)
		{
			System.err.println("Cannot create Window");
			e.printStackTrace();
		}
	}
	
	public void dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public void render()
	{
		Display.update();
	}
	
	public boolean isCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public int width()
	{
		return Display.getWidth();
	}
	
	public int height()
	{
		return Display.getHeight();
	}
	
	public String title()
	{
		return Display.getTitle();
	}
}
