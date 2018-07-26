package engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderEngine 
{
	public RenderEngine()
	{
		initGraphics();
	}
	
	public String getOpenGLVersion()
	{
		return glGetString(GL_VERSION);
	}
	
	public void initGraphics()
	{
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		
		glEnable(GL_DEPTH_TEST);
	}
	
	public void clearScreen()
	{
		// TODO: Stencil buffer bit
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		// TODO: Depth clamp
		
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
}
