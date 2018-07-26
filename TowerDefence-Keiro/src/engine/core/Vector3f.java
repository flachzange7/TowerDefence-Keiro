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
	
	public Vector3f normalize()
	{
		//TODO: implement me
		return null;
	}
	
	public Vector3f cross(Vector3f r)
	{
		//TODO: implement me
		return null;
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
