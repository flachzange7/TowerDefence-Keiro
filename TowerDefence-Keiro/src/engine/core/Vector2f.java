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
	
	public float length()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y);
	}
	
	public float dot(Vector2f r)
	{
		return m_x * r.x() + m_y * r.y();
	}
	
	public Vector2f normalize()
	{
		float length = length();
		
		m_x /= length;
		m_y /= length;
		
		return this;
	}
	
	public Vector2f rotate(float angle)
	{
		m_x = (float) (m_x * Math.cos(Math.toRadians(angle)) - m_y * Math.sin(angle));
		m_y = (float) (m_x * Math.cos(Math.toRadians(angle)) + m_y * Math.sin(angle));
		return null;
	}
	
	public Vector2f add(Vector2f r)
	{
		m_x = m_x + r.m_x;
		m_y = m_y + r.m_y;
		return this;
	}
	
	public Vector2f add(float r)
	{
		m_x = m_x + r;
		m_y = m_y + r;
		return this;
	}
	
	public Vector2f sub(Vector2f r)
	{
		m_x = m_x - r.m_x;
		m_y = m_y - r.m_y;
		return this;
	}
	
	public Vector2f sub(float r)
	{
		m_x = m_x - r;
		m_y = m_y - r;
		return this;
	}
	
	public Vector2f mul(Vector2f r)
	{
		m_x = m_x * r.m_x;
		m_y = m_y * r.m_y;
		return this;
	}
	
	public Vector2f mul(float r)
	{
		m_x = m_x * r;
		m_y = m_y * r;
		return this;
	}
	
	public Vector2f div(Vector2f r)
	{
		m_x = m_x / r.m_x;
		m_y = m_y / r.m_y;
		return this;
	}
	
	public Vector2f div(float r)
	{
		m_x = m_x / r;
		m_y = m_y / r;
		return this;
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
