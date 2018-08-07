package game;

import engine.core.CoreEngine;
import engine.rendering.RenderEngine;
import engine.rendering.Shader;

public abstract class GameComponent 
{
	// TODO: Maxi
	/*
	 * class for rendering, physics or sound (abstract)
	 * input, update, render with delta
	 * 
	 * 
	 */
	
	private GameObject m_parent;
	
	public void input(float delta) {}
	public void update() {}
	public void render(Shader shader, RenderEngine renderEngine) {}
	
	public void setParent(GameObject parent)
	{
		this.m_parent = parent;
	}
	
	/*public Transform GetTransform()
	{
		return m_parent.GetTransform();
	}*/
	
	public void addToEngine(CoreEngine engine) {}
}
