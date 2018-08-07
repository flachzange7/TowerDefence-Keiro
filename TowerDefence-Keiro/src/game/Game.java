package game;

import org.lwjgl.input.Keyboard;

import engine.core.Input;
import engine.core.Time;
import engine.core.Transform;
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
	private ShaderProgram m_program;
	private Transform m_transform;
	
	public Game()
	{
		m_program = new ShaderProgram();
		
		m_mesh = new Mesh(m_program);
		Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
									  new Vertex(new Vector3f(0, 1, 0)),
									  new Vertex(new Vector3f(1, -1, 0)),
									  new Vertex(new Vector3f(0, -1, 1))};
		
		int[] indices = new int[] {0, 1, 3,
								   3, 1, 2,
								   2, 1, 0,
								   0, 2, 3}; 
		
		
		m_mesh.addVertices(data, indices);
		
		m_transform = new Transform();
		
		m_program.addShader("vertexShader.vs", ShaderType.VERTEX);
		m_program.addShader("fragmentShader.frag", ShaderType.FRAGMENT);
		m_program.link();
		
		m_program.registerUniform("transform");
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
	
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.delta();
		
		float sinTemp = (float)Math.sin(temp);
		
		m_transform.setTranslation(sinTemp, 0, 0);
		m_transform.setRotation(0, 0, sinTemp * 180);
		m_transform.setScaling(sinTemp, sinTemp, sinTemp);
	}
	
	public void render()
	{
		m_program.bind();
		
		m_program.setUniformValue("transform", m_transform.transformation());
		
		m_mesh.draw();
	}
}
