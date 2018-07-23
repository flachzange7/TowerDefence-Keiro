package game;

import org.lwjgl.input.Keyboard;

import engine.core.Input;

public class Game 
{
	public Game()
	{
		
	}
	
	public void input()
	{
		if(Input.keyDown(Keyboard.KEY_UP))
			System.out.println("Key up pressed");
		
		if(Input.keyUp(Keyboard.KEY_UP))
			System.out.println("Key up released");
		
		if(Input.mouseButtonPressed(1))
			System.out.println("MouseButton 1 pressed " + Input.mousePosition());
		
		if(Input.mouseButtonReleased(1))
			System.out.println("MouseButton 1 released");
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
}
