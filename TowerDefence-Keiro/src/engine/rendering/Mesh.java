package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

import engine.core.Vertex;

public class Mesh 
{
	private int m_vbo;
	private int m_ibo;
	private int m_size;
	
	private ShaderProgram m_program;
	
	public Mesh(ShaderProgram program)
	{
		m_vbo = glGenBuffers();
		m_ibo = glGenBuffers();
		m_size = 0;
		
		m_program = program;
	}
	
	public void addVertices (Vertex[] vertices, int[] indices)
	{
		m_size = indices.length;
		
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
		
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_ibo);
		
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
	}
	
	public void draw()
	{
		m_program.enableAttributeArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_ibo);
		glDrawElements(GL_TRIANGLES, m_size, GL_UNSIGNED_INT, 0);
		
		m_program.disableAttributeArray(0);
	}
	
	public void loadOBJModel(String filename)
	{
		String[] splitArray = filename.split("\\.");
		String ext = splitArray[splitArray.length-1];
		
		if(!ext.equals("obj"))
		{
			System.err.println("Error: '" + ext + "' file format not supported for mesh data.");
			System.exit(1);
		}
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = "";
			
			while(line != reader.readLine())
			{
				String[] tokens = line.split(" ");
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
