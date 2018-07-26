package engine.core;

public class Vertex 
{
	public static final int SIZE = 3;
	
	private Vector3f m_pos;
	
	public Vertex(Vector3f pos)
	{
		setPosition(pos);
	}

	public Vector3f position() 
	{
		return m_pos;
	}

	public void setPosition(Vector3f pos) 
	{
		m_pos = pos;
	}
	


}
