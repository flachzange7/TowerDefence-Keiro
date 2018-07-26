package engine.rendering;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class Shader 
{
	public enum ShaderType
	{
		VERTEX, FRAGMENT, GEOMETRY;
	};
	
	private ShaderType m_type;
	private int m_shaderID;
	private boolean m_isCompiled;
	private String m_log;
	private String m_sourceCode;
	
	public Shader(ShaderType type)
	{
		m_type = type;
		m_isCompiled = false;
		m_log = "";
		m_sourceCode = "";
		
		switch(type)
		{
			case VERTEX:
				m_shaderID = glCreateShader(GL_VERTEX_SHADER);
				break;
			
			case FRAGMENT:
				m_shaderID = glCreateShader(GL_FRAGMENT_SHADER);
				break;
			
			case GEOMETRY:
				m_shaderID = glCreateShader(GL_GEOMETRY_SHADER);
				break;
		}
		
		if(m_shaderID == 0)
		{
			System.err.println("ERROR: Creation of Shader failed");
			System.exit(1);
		}
		
		
	}
	
	public boolean compileFormSourceCode(String filename)
	{
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = null;
		
		try
		{
			reader = new BufferedReader(new FileReader("res/shaders/" + filename));
			String line;
			
			while((line = reader.readLine()) != null)
			{
				builder.append(line).append("\n");
			}
			
			reader.close();
			m_sourceCode = builder.toString();
		}
		catch(Exception e)
		{
			System.out.println("Error: Failed to load Shader");
			e.printStackTrace();
		}
		
		glShaderSource(m_shaderID, m_sourceCode);
		glCompileShader(m_shaderID);
		
		if(glGetShaderi(m_shaderID, GL_COMPILE_STATUS) == 0)
		{
			System.err.println(glGetShaderInfoLog(m_shaderID, 1024));
			System.exit(1);
		}
		
		return true;
	}
	
	public int shaderID()
	{
		return m_shaderID;
	}
	
	public ShaderType shaderType()
	{
		return m_type;
	}
	
	public boolean isCompiled()
	{
		return m_isCompiled;
	}
	
	public String log()
	{
		return m_log;
	}
	
	public String sourceCode()
	{
		return m_sourceCode;
	}
	
	
	
}
