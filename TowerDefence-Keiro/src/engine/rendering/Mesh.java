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

import javax.xml.ws.FaultAction;

import org.lwjgl.BufferUtils;

import engine.core.Vector3f;
import engine.core.Vertex;

public class Mesh 
{
	private int m_vbo;
	private int m_ibo;
	private int m_size;
	
	private ArrayList<Vertex> m_vertices;
	private ArrayList<Integer> m_faceIndices;
	
	private ShaderProgram m_program;
	
	public Mesh(ShaderProgram program)
	{
		m_vbo = glGenBuffers();
		m_ibo = glGenBuffers();
		m_size = 0;
		
		m_vertices = new ArrayList<Vertex>();
		m_faceIndices = new ArrayList<Integer>();
		
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
	
	public void testLoading()
	{
		m_vertices.add(new Vertex(new Vector3f(-1, -1, 0)));
		m_vertices.add(new Vertex(new Vector3f(0, 1, 0)));
		m_vertices.add(new Vertex(new Vector3f(1, -1, 0)));
		m_vertices.add(new Vertex(new Vector3f(0, -1, 1)));
		
		m_faceIndices.add(new Integer(0));
		m_faceIndices.add(new Integer(1));
		m_faceIndices.add(new Integer(3));
		m_faceIndices.add(new Integer(3));
		m_faceIndices.add(new Integer(1));
		m_faceIndices.add(new Integer(2));
		m_faceIndices.add(new Integer(2));
		m_faceIndices.add(new Integer(1));
		m_faceIndices.add(new Integer(0));
		m_faceIndices.add(new Integer(0));
		m_faceIndices.add(new Integer(2));
		m_faceIndices.add(new Integer(3));
		
		finishLoading();			  
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
		
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line = "";
			
			while((line = reader.readLine()) != null)
			{
				String[] tokens = line.split(" ");
				
				switch(tokens[0])
				{
					case ("mtllib"):
						System.out.println("TODO: load Material");
						break;
						
					case ("usemtl"):
						System.out.println("TODO: using Material");
						break;
					
					case ("s"):
						System.out.println("TODO: s");
						break;
						
					case ("o"):
						System.out.println("Loading Object " + tokens[1]);
						break;
						
					case ("v"):
						float x = Float.valueOf(tokens[1]).floatValue();
						float y = Float.valueOf(tokens[2]).floatValue();
						float z = Float.valueOf(tokens[3]).floatValue();
						
						m_vertices.add(new Vertex(new Vector3f(x, y, z)));
						break;
						
					case ("vt"):
						System.out.println("TODO: load textures");
						break;
					
					case ("vn"):
						System.out.println("TODO: load normals");
						break;
						
					case ("f"):
						for(int i = 1; i != tokens.length; i++)
						{
							String[] faceIndicesGroup = tokens[i].split("/");
							m_faceIndices.add(Integer.valueOf(faceIndicesGroup[0])-1);
							//for(int j = 0; j != faceIndicesGroup.length; j++)
							//{
								
							//}
						}
						System.out.println("TODO: load faces");
						break;
					
					default:
						
				}
			}
			
			reader.close();
			finishLoading();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void finishLoading()
	{
		m_size = m_faceIndices.size();
		
		glBindBuffer(GL_ARRAY_BUFFER, m_vbo);

		FloatBuffer buffer = BufferUtils.createFloatBuffer(m_vertices.size() * Vertex.SIZE);
		for(int i = 0; i != m_vertices.size(); i++)
		{
			buffer.put(m_vertices.get(i).position().x());
			buffer.put(m_vertices.get(i).position().y());
			buffer.put(m_vertices.get(i).position().z());
		}
		buffer.flip();
		
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_ibo);
		
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(m_faceIndices.size());
		for(int i = 0; i != m_faceIndices.size(); i++)
		{
			indicesBuffer.put(m_faceIndices.get(i).intValue());
		}
		indicesBuffer.flip();
		
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
	}
}
