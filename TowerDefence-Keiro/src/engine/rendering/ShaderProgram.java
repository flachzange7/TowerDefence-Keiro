package engine.rendering;

import static org.lwjgl.opengl.GL20.*;
import java.util.ArrayList;
import java.util.HashMap;

import engine.core.Matrix4f;
import engine.core.Vector3f;
import engine.rendering.Shader.ShaderType;

public class ShaderProgram 
{
	private ArrayList<Shader> m_shaders;
	private HashMap<String, Integer> m_uniformList;
	
	private String m_log;
	private int m_programID;
	
	public ShaderProgram()
	{
		m_programID = glCreateProgram();
		m_shaders = new ArrayList<Shader>();
		m_uniformList = new HashMap<String, Integer>();
		
		if(m_programID == 0)
		{
			System.err.println("ERROR: Creating new program failed");
			System.exit(1);
		}
	}
	
	public void addShader(Shader newShader)
	{
		glAttachShader(m_programID, newShader.shaderID());
		m_shaders.add(newShader);
	}
	
	public void addShader(String filename, ShaderType type)
	{
		Shader newShader = new Shader(type);
		newShader.compileFormSourceCode(filename);
		
		glAttachShader(m_programID, newShader.shaderID());
		m_shaders.add(newShader);
	}
	
	public boolean bind()
	{
		glUseProgram(m_programID);
		// TODO: C fix me
		return false;
	}	
	
	public boolean link()
	{
		glLinkProgram(m_programID);
		
		if(glGetProgrami(m_programID, GL_LINK_STATUS) == 0)
		{
			System.err.println(glGetProgramInfoLog(m_programID, 1024));
			System.exit(1);
		}
		
		glValidateProgram(m_programID);
		
		if(glGetProgrami(m_programID, GL_VALIDATE_STATUS) == 0)
		{
			System.err.println(glGetProgramInfoLog(m_programID, 1024));
			System.exit(1);
		}
		
		// TODO: C fix me
		return false;
	}
	
	public boolean isLinked()
	{
		// TODO: C: implement me
		return false;
	}
	
	public int programID()
	{
		return m_programID;
	}
	
	public String log()
	{
		return m_log;
	}
	
	public ArrayList<Shader> shader()
	{
		return m_shaders;
	}
	
	public void removeAllShader()
	{
		// TODO: C: implement me
	}
	
	public void removeShader(Shader shader)
	{
		// TODO: C: implement me
	}
	
	public void enableAttributeArray(int location)
	{
		glEnableVertexAttribArray(location);
	}
	
	public void disableAttributeArray(int location)
	{
		glDisableVertexAttribArray(location);
	}
	
	public void enableAttributeArray(String name)
	{
		// TODO: C: implement me
	}
	
	public void disableAttributeArray(String name)
	{
		// TODO: C: implement me
	}
	
	public int atrributeLocation(String locationName)
	{
		// TODO: C: implement me
		return 0;
	}
	
	public void bindAttributeLocation(String locationName)
	{
		// TODO: C: implement me
	}
	
	
	public void registerUniform(String name)
	{
		int uniformLocation = glGetUniformLocation(m_programID, name);
		
		if(uniformLocation == 0xFFFFFF)
		{
			System.err.println("ERROR: getting uniform location failed");
			System.exit(1);
		}
		
		m_uniformList.put(name, uniformLocation);
	}
	
	public void setUniformValue(String name, int value)
	{
		glUniform1i(m_uniformList.get(name), value);
	}
	
	public void setUniformValue(String name, float value)
	{
		glUniform1f(m_uniformList.get(name), value);
	}
	
	public void setUniformValue(String name, Vector3f value)
	{
		glUniform3f(m_uniformList.get(name), value.x(), value.y(), value.z());
	}
	
	public void setUniformValue(String name, Matrix4f value)
	{
		glUniformMatrix4(m_uniformList.get(name), true, value.buffer());
	}
	
}
