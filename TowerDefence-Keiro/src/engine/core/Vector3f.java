package engine.core;

public class Vector3f 
{
	private float m_x;
	private float m_y;
	private float m_z;
	
	public Vector3f(float x, float y, float z)
	{
		setX(x);
		m_y = y;
		m_z = z;
	}
	
	public float length()
	{
		float length;
		length = (float) Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z);
		return length;
	}
	
	public Vector3f dot(Vector3f r)
	{
		m_x = m_x * r.m_x;
		m_y = m_y * r.m_y;
		m_z = m_z * r.m_z;
		return this;
	}
	
	public Vector3f normalize()
	{
		float length = length();
		m_x = m_x /= length;
		m_y = m_y /= length;
		m_z = m_z /= length;
		return this;
	}
	
	public Vector3f cross(Vector3f r)
	{
		m_x = m_y * r.m_z - m_z * r.m_y;
		m_y = m_z * r.m_x - m_x * r.m_z;
		m_z = m_x * r.m_y - m_y * r.m_x;
		return this;
	}
	
	public Vector3f rotate(float angle)
	{
		
		return this;
	}
	
	public Vector3f add(Vector3f r)
	{
		m_x = m_x + r.m_x;
		m_y = m_y + r.m_y;
		m_z = m_z + r.m_z;
		return this;
	}
	
	public Vector3f add(float r)
	{
		m_x = m_x + r;
		m_y = m_y + r;
		m_z = m_z + r;
		return this;
	}
	
	public Vector3f sub(Vector3f r)
	{
		m_x = m_x - r.m_x;
		m_y = m_y - r.m_y;
		m_z = m_z - r.m_z;
		return this;
	}
	
	public Vector3f sub(float r)
	{
		m_x = m_x - r;
		m_y = m_y - r;
		m_z = m_z - r;
		return this;
	}
	
	public Vector3f mul(Vector3f r)
	{
		m_x = m_x * r.m_x;
		m_y = m_y * r.m_y;
		m_z = m_z * r.m_z;
		return this;
	}
	
	public Vector3f mul(float r)
	{
		m_x = m_x * r;
		m_y = m_y * r;
		m_z = m_z * r;
		return this;
	}
	
	public Vector3f div(Vector3f r)
	{
		m_x = m_x / r.m_x;
		m_y = m_y / r.m_y;
		m_z = m_z / r.m_z;
		return this;
	}
	
	public Vector3f div(float r)
	{
		m_x = m_x / r;
		m_y = m_y / r;
		m_z = m_z / r;
		return this;
	}

	public float x() 
	{
		return m_x;
	}
	
	public float y() 
	{
		return m_y;
	}
	
	public float z() 
	{
		return m_z;
	}

	public void setX(float x) 
	{
		m_x = x;
	}
	
	public void setY(float y) 
	{
		m_y = y;
	}
	
	public void setZ(float z) 
	{
		m_z = z;
	}
}
