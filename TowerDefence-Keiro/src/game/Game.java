package game;

import engine.gui.Window;

public class Game 
{
	private Window m_window;
	
	public Game()
	{
		m_window = new Window("TowerDefence - Keiro", 800, 600);
	}
	
	public void start()
	{
		run();
	}
	
	public void stop()
	{
		
	}
	
	public void run()
	{
		while(!m_window.isCloseRequested())
		{
			render();
		}
	}
	
	public void render()
	{
		m_window.render();
	}
	
	public void cleanUp()
	{
		
	}
}
