package engine.core;

public class Vector2f 
{
	private float m_x;
	private float m_y;
	
	public Vector2f(float x, float y)
	{
		m_x = x;
		m_y = y;
	}
	
	public float length() { return (float)Math.sqrt(m_x * m_x + m_y * m_y);	}
	
	public float max() { return Math.max(m_x, m_y); }
	
	public float dot(Vector2f r) { return m_x * r.x() + m_y * r.y(); }
	
	public Vector2f normalize()
	{
		float length = length();
		return new Vector2f(m_x / length, m_y / length);
	}
	
	public float cross(Vector2f r) { return m_x * r.y() - m_y * r.x(); }
	
	public Vector2f lerp(Vector2f dest, float lerpFactor) { return dest.sub(this).mul(lerpFactor).add(this); }
	
	public Vector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(m_x * cos - m_y * sin),(float)(m_x * sin + m_y * cos));
	}
	
	public Vector2f add(Vector2f r) { return new Vector2f(m_x + r.x(), m_y + r.y()); }
	public Vector2f add(float r){ return new Vector2f(m_x + r, m_y + r); }
	
	public Vector2f sub(Vector2f r) { return new Vector2f(m_x - r.x(), m_y - r.y()); }
	public Vector2f sub(float r) { return new Vector2f(m_x - r, m_y - r); }
	
	public Vector2f mul(Vector2f r) { return new Vector2f(m_x * r.x(), m_y * r.y()); }
	public Vector2f mul(float r) { return new Vector2f(m_x * r, m_y * r); }
	
	public Vector2f div(Vector2f r) { return new Vector2f(m_x / r.x(), m_y / r.y()); }
	public Vector2f div(float r) { return new Vector2f(m_x / r, m_y / r); }
	
	public Vector2f abs() { return new Vector2f(Math.abs(m_x), Math.abs(m_y)); }
	
	public String toString() { return "(" + m_x + " " + m_y + ")"; }

	public Vector2f set(float x, float y) { this.m_x = x; this.m_y = y; return this; }
	public Vector2f set(Vector2f r) { set(r.x(), r.y()); return this; }

	public float x() { return m_x; }
	public float y() { return m_y; }

	public void setX(float x) { this.m_x = x; }
	public void setY(float y) { this.m_y = y; }

	public boolean equals(Vector2f r) { return m_x == r.x() && m_y == r.y(); }
}
