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
	
	public float length()
	{
		float length = 0;
		length = (float) Math.abs(Math.sqrt(m_x * m_x + m_y * m_y));
		return length;
	}
	
	public void dotProduct(float num)
	{
		float dotProductX = 0;
		float dotProductY = 0;
		dotProductX = m_x * num;
		dotProductY = m_y * num;
	}
	
	public void normalize(float length)
	{
		float normalizedX = 0;
		float normalizedY = 0;
		normalizedX = m_x / length;
		normalizedY = m_y / length;
	}
}
