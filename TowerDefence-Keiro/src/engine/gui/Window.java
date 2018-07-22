package engine.gui;

import org.lwjgl.LWJGLException;
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
		}
		catch(LWJGLException e)
		{
			System.err.println("Cannot create Window");
			e.printStackTrace();
		}
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
