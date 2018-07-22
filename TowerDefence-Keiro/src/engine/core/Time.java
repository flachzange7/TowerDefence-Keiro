package engine.core;

public class Time 
{
	public static final long SECOND = 1000000000L;
	private static double m_delta;
	
	public static long time()
	{
		return System.nanoTime();
	}
	
	public static double delta()
	{
		return m_delta;
	}
	
	public static void setDelta(double delta)
	{
		m_delta = delta;
	}
	
}
