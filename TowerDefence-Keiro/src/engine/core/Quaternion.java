package engine.core;

public class Quaternion 
{
	private float m_x;
	private float m_y;
	private float m_z;
	private float m_w;
	
	public Quaternion(float x, float y, float z, float w)
	{
		m_x = x;
		m_y = y;
		m_z = z;
		m_w = w;
	}
	
	public Quaternion(Vector3f axis, float angle)
	{
		float sinHalfAngle = (float)Math.sin(angle / 2);
		float cosHalfAngle = (float)Math.cos(angle / 2);

		this.m_x = axis.x() * sinHalfAngle;
		this.m_y = axis.y() * sinHalfAngle;
		this.m_z = axis.z() * sinHalfAngle;
		this.m_w = cosHalfAngle;
	}
	
	public float length()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z + m_w * m_w);
	}

	public float x() 
	{
		return m_x;
	}
	
	public Quaternion normalize()
	{
		float length = length();
		
		m_x /= length;
		m_y /= length;
		m_z /= length;
		m_w /= length;
		
		return this;
	}
	
	
	public Quaternion conjugate()
	{
		return new Quaternion(-m_x, -m_y, -m_z, -m_w);
	}
	
	public Quaternion add(Quaternion r)
	{
		return new Quaternion(m_x + r.x(), m_y + r.y(), m_z + r.z(), m_w + r.w());
	}
	
	public Quaternion sub(Quaternion r)
	{
		return new Quaternion(m_x - r.x(), m_y - r.y(), m_z - r.z(), m_w - r.w());
	}

	public Quaternion mul(Quaternion r)
	{
		float w_ = m_w * r.w() - m_x * r.x() - m_y * r.y() - m_z * r.z();
		float x_ = m_x * r.w() + m_w * r.x() + m_y * r.z() - m_z * r.y();
		float y_ = m_y * r.w() + m_w * r.y() + m_z * r.x() - m_x * r.z();
		float z_ = m_z * r.w() + m_w * r.z() + m_x * r.y() - m_y * r.x();
		
		return new Quaternion(x_, y_, z_, w_);
		
	}
	
	public Quaternion mul(float r)
	{
		return new Quaternion(m_x * r, m_y * r, m_z * r, m_w * r);
	}
	
	public Quaternion mul(Vector3f r)
	{
		float w_ = -m_x * r.x() - m_y * r.y() - m_z * r.z();
		float x_ =  m_w * r.x() + m_y * r.z() - m_z * r.y();
		float y_ =  m_w * r.y() + m_z * r.x() - m_x * r.z();
		float z_ =  m_w * r.z() + m_x * r.y() - m_y * r.x();
		
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public float y() 
	{
		return m_x;
	}
	
	public float z() 
	{
		return m_z;
	}
	
	public float w() 
	{
		return m_w;
	}

	public void setX(float x) 
	{
		this.m_x = x;
	}
	
	public void setY(float y) 
	{
		this.m_y = y;
	}
	
	public void setZ(float z) 
	{
		this.m_z = z;
	}
	
	public void setW(float w) 
	{
		this.m_w = w;
	}
}
