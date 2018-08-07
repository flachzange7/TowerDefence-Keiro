package engine.core;

public class Transform 
{
	private Vector3f m_translation;
	private Vector3f m_rotation;
	private Vector3f m_scaling;
	
	public Transform()
	{
		m_translation = new Vector3f(0, 0, 0);
		m_rotation = new Vector3f(0, 0, 0);
		m_scaling = new Vector3f(1, 1, 1);
	}
	
	public Matrix4f transformation()
	{
		Matrix4f tranformationMatrix = new Matrix4f().initAsTranslation(m_translation.x(), m_translation.y(), m_translation.z());
		Matrix4f roationMatrix = new Matrix4f().initAsRotation(m_rotation.x(), m_rotation.y(), m_rotation.z());
		Matrix4f scaleMatrix = new Matrix4f().initAsScale(m_scaling.x(), m_scaling.y(), m_scaling.z());

		return tranformationMatrix.mul(roationMatrix.mul(scaleMatrix));
	}

	public Vector3f translation() 
	{
		return m_translation;
	}
	
	public Vector3f rotation()
	{
		return m_rotation;
	}
	
	public Vector3f scaling()
	{
		return m_scaling;
	}

	public void setTranslation(Vector3f translation) 
	{
		m_translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) 
	{
		m_translation = new Vector3f(x, y, z);
	}
	
	public void setRotation(Vector3f rotation) 
	{
		m_rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) 
	{
		m_rotation = new Vector3f(x, y, z);
	}
	
	public void setScaling(Vector3f scaling) 
	{
		m_scaling = scaling;
	}
	
	public void setScaling(float x, float y, float z) 
	{
		m_scaling = new Vector3f(x, y, z);
	}
}