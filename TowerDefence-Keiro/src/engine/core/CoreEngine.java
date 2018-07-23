package engine.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.core.Time;
import engine.gui.Window;

import game.Game;

public class CoreEngine 
{
	private Window m_window;
	private boolean m_isRunning;
	private final double MAX_FPS = 5000.0;
	
	private Game m_game;
	
	public CoreEngine()
	{
		m_window = new Window("TowerDefence - Keiro", 800, 600);		
		m_game = new Game();
	}
	
	public void start()
	{
		if(m_isRunning)
			return;
		
		run();
	}
	
	public void stop()
	{
		if(!m_isRunning)
			return;
		
		m_isRunning = false;
	}
	
	public void run()
	{
		m_isRunning = true;
		
		int frames = 0;
		int frameCounter = 0;
		
		final double frameTime = 1.0 / MAX_FPS;
		
		long lastTime = Time.time();
		double unprocessedTime = 0;
		
		while(m_isRunning)
		{
			boolean renderRequested = false;
			
			long startTime = Time.time();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime)
			{
				renderRequested = true;
				
				unprocessedTime -= frameTime;
				
				if(m_window.isCloseRequested())
					stop();
				
				Time.setDelta(frameTime);
				
				Input.update();
				
				m_game.input();
				m_game.update();
				
				if(frameCounter >= Time.SECOND)
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(renderRequested)
			{
				render();
				frames++;
			}
			else
				try 
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
		}
		
		cleanUp();
	}
	
	public void render()
	{
		m_game.render();
		m_window.render();
	}
	
	public void cleanUp()
	{
		m_window.dispose();
	}
}
