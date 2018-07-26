package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import engine.core.Vertex;

public class Mesh 
{
	private int m_vbo;
	private int m_size;
	
	private ShaderProgram m_program;
	
	public Mesh(ShaderProgram program)
	{
		m_vbo = glGenBuffers();
		m_size = 0;
		
		m_program = program;
	}
	
	public void addVertices (Vertex[] vertices)
	{
		m_size = vertices.length * Vertex.SIZE;
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo);
		
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);
		for(int i = 0; i != vertices.length; i++)
		{
			buffer.put(vertices[i].position().x());
			buffer.put(vertices[i].position().y());
			buffer.put(vertices[i].position().z());
		}
		
		buffer.flip();
		
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
	
	public void draw()
	{
		m_program.enableAttributeArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, m_size);
		
		m_program.disableAttributeArray(0);
	}
}
