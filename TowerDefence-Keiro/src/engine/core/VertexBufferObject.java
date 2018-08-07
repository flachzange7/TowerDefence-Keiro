package engine.core;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class VertexBufferObject 
{
	private int m_vbo;
	private int m_bufferUsage;
	
	public VertexBufferObject(int bufferUsage)
	{
		m_vbo = glGenBuffers();
		
		if(m_vbo == GL_INVALID_VALUE)
		{
			System.err.println("ERROR: Unable to generate VertexBufferObject");
		}
	}
	
	@Override
	public void finalize() 
	{
	    if(isVald())
	    	glDeleteBuffers(m_vbo);
	}
	
	public boolean isVald()
	{
		return (m_vbo == GL_INVALID_VALUE)?false:true;
	}
	
	public int vboBuffer()
	{
		return m_vbo;
	}
	
	public void bind()
	{
		glBindBuffer(m_bufferUsage, m_vbo);
	}
	
	public void unbind()
	{
		glBindBuffer(m_bufferUsage, 0);
	}
	

}
