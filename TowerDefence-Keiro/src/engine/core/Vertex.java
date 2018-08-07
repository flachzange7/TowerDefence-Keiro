package engine.core;

public class Vertex 
{
	public static final int SIZE = 3;
	
	private Vector3f m_position;
	private Vector2f m_textureCoordninate;
	private Vector3f m_normals;
	
	public Vertex(Vector3f position)
	{
		this(position, new Vector2f(0, 0));
	}
	
	public Vertex(Vector3f position, Vector2f textureCoordinate)
	{
		this(position, textureCoordinate, new Vector3f(0, 0, 0));
	}
	
	public Vertex(Vector3f position, Vector2f textureCoordinates, Vector3f normals)
	{
		m_position = position;
		m_textureCoordninate = textureCoordinates;
		m_normals = normals;
	}

	public Vector3f position() 
	{
		return m_position;
	}
	
	public Vector2f textureCoordinate()
	{
		return m_textureCoordninate;
	}
	
	public Vector3f normals()
	{
		return m_normals;
	}

	public void setPosition(Vector3f pos) 
	{
		m_position = pos;
	}
	
	public void setTextureCoordinate(Vector2f textureCoordinate) 
	{
		m_textureCoordninate = textureCoordinate;
	}
	
	public void setNormals(Vector3f normals) 
	{
		m_normals = normals;
	}
	


}
