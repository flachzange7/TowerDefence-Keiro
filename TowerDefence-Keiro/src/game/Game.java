package game;

import org.lwjgl.input.Keyboard;

import engine.core.Camera;
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
	
	private Camera m_camera;
	
	public Game()
	{
		m_program = new ShaderProgram();
		m_camera = new Camera();
		
		m_mesh = new Mesh(m_program);

		m_mesh.loadOBJModel("res/models/box.obj");
		
		m_transform = new Transform();
		Transform.setCamera(m_camera);
		Transform.setProjection(70f, 800, 600, 0.1f, 1000f);
		
		m_program.addShader("vertexShader.vs", ShaderType.VERTEX);
		m_program.addShader("fragmentShader.frag", ShaderType.FRAGMENT);
		m_program.link();
		
		m_program.registerUniform("transform");
	}
	
	public void input()
	{
		m_camera.input();
	}
	
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.delta();
		
		float sinTemp = (float)Math.sin(temp);
		
		m_transform.setTranslation(sinTemp, 0, 5);
		m_transform.setRotation(0, sinTemp * 180, 0);
		//m_transform.setScaling(sinTemp, sinTemp, sinTemp);
	}
	
	public void render()
	{
		m_program.bind();
		
		m_program.setUniformValue("transform", m_transform.projectedTransformation());
		
		m_mesh.draw();
	}
}
