package game;

import org.lwjgl.input.Keyboard;

import engine.core.Input;
import engine.core.Vertex;
import engine.rendering.Mesh;
import engine.core.Vector3f;

public class Game 
{
	private Mesh m_mesh;
	
	public Game()
	{
		m_mesh = new Mesh();
		Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
									  new Vertex(new Vector3f(0, 1, 0)),
									  new Vertex(new Vector3f(1, -1, 0))};
		
		m_mesh.addVertices(data);
	}
	
	public void input()
	{
		if(Input.keyDown(Keyboard.KEY_UP))
			System.out.println("Key up pressed");
		
		if(Input.keyReleased(Keyboard.KEY_UP))
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
		m_mesh.draw();
	}
}
