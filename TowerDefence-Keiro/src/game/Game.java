package game;

import org.lwjgl.input.Keyboard;

import engine.core.Input;
import engine.core.Vertex;
import engine.rendering.Mesh;
import engine.rendering.ShaderProgram;
import engine.rendering.Shader.ShaderType;
import engine.core.Vector3f;


// TODO: Maxi implement
/*
 * Game = abstract class; have to be inherrit game Game itself 
 * Game build as a tree; GameObject are Elements
 * input, update, render, children, root -> Methods
 * 
 * 
 */
public class Game 
{
	private Mesh m_mesh;
	ShaderProgram m_program;
	
	public Game()
	{
		m_program = new ShaderProgram();
		
		m_mesh = new Mesh(m_program);
		Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
									  new Vertex(new Vector3f(0, 1, 0)),
									  new Vertex(new Vector3f(1, -1, 0))};
		
		m_mesh.addVertices(data);
		
		m_program.addShader("vertexShader.vs", ShaderType.VERTEX);
		m_program.addShader("fragmentShader.frag", ShaderType.FRAGMENT);
		m_program.link();
		
		m_program.registerUniform("size");
		m_program.setUniformValue("size", 0.25f);
		
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
		m_program.bind();
		
		m_mesh.draw();
	}
}
