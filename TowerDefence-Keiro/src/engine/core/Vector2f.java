package engine.core;

public class Vector2f 
{
	private float m_x;
	private float m_y;
	
	public Vector2f(float x, float y)
	{
		setX(x);
		setY(y);
	}

	public String toString()
	{
		return "(" + m_x + " / " + m_y + ")";
	}
	
	public float y() 
	{
		return m_y;
	}

	public void setY(float y) 
	{
		m_y = y;
	}

	public float x() 
	{
		return m_x;
	}

	public void setX(float x)
	{
		m_x = x;
	}
	
	
}
