package main;

import engine.core.CoreEngine;
import game.Game;

public class TDKMain extends Game
{
	public static void main(String arg[])
	{
		CoreEngine core = new CoreEngine();
		
		TDKGame currentGame = new TDKGame("res/maps/Mission1.xml");
		
		core.start();	
	}
}
