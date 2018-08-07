package game;

import java.util.ArrayList;

import engine.core.CoreEngine;
import engine.rendering.RenderEngine;
import engine.rendering.Shader;

public class GameObject
{
	// TODO: Maxi
	/* GameObject contains list of children, list of components
	 * addchildren, addcomponents
	 * input, update, render current and all objects (2 way)
	 * 
	 * 
	 * 
	 */
	private GameObject m_parent;
	private ArrayList<GameObject> m_children;
	private ArrayList<GameComponent> m_components;
	//private Transform m_transform;
	private CoreEngine m_engine;
	
	public GameObject()
	{
		m_children = new ArrayList<GameObject>();
		m_components = new ArrayList<GameComponent>();
		//m_transform = new Transform();
		m_engine = null;
	}
	
	public GameObject addChild(GameObject child)
	{
		m_children.add(child);
		child.setEngine(m_engine);
		//child.getTransform().setParent(m_transform);
		
		return this;
	}
	
	public GameObject addComponent(GameComponent component)
	{
		m_components.add(component);
		component.setParent(this);
		
		return this;
	}
	
	public void inputAll(float delta)
	{
		input(delta);
		
		for(GameObject child : m_children)
			child.inputAll(delta);
	}
	
	public void updateAll()
	{
		update();
		
		for(GameObject child : m_children)
			child.updateAll();
	}
	
	public void renderAll(Shader shader, RenderEngine renderEngine)
	{
		render(shader, renderEngine);
		
		for(GameObject child : m_children)
			child.renderAll(shader, renderEngine);
	}

	public void input(float delta) 
	{
		//m_transform.update();
		
		for(GameComponent component : m_components)
			component.input(delta);
	}
	
	public void update() 
	{
		for(GameComponent component : m_components)
			component.update();
	}
	
	public void render(Shader shader, RenderEngine renderEngine) 
	{
		for(GameComponent component : m_components)
			component.render(shader, renderEngine);
	}
	
	public ArrayList<GameObject> getAllAttached()
	{
		ArrayList<GameObject> result = new ArrayList<GameObject>();
		
		for(GameObject child : m_children)
			result.addAll(child.getAllAttached());
		
		result.add(this);
		return result;
	}

	public void setParent(GameObject parent)
	{
		this.m_parent = parent;
	}

	public void setEngine(CoreEngine engine)
	{
		if(this.m_engine != engine)
		{
			this.m_engine = engine;

			for(GameComponent component : m_components)
				component.addToEngine(engine);

			for(GameObject child : m_children)
				child.setEngine(engine);
		}
	}
}
