package engine.core;

public class Vector3f 
{
	private float m_x;
	private float m_y;
	private float m_z;
	
	public Vector3f(float x, float y, float z)
	{
		m_x = x;
		m_y = y;
		m_z = z;
	}
	
	public float length() { return (float)Math.sqrt(m_x * m_x + m_y * m_y + m_z * m_z); }
	
	public float max() { return Math.max(m_x, Math.max(m_y, m_z)); }
	
	public float dot(Vector3f r) { return m_x * r.x() + m_y * r.y() + m_z * r.z(); }
	
	public Vector3f normalize()
	{
		float length = length();
		return new Vector3f(m_x / length, m_y / length, m_z / length);
	}
	
	public Vector3f cross(Vector3f r)
	{
		float x_ = m_y * r.z() - m_z * r.y();
		float y_ = m_z * r.x() - m_x * r.z();
		float z_ = m_x * r.y() - m_y * r.x();
		
		return new Vector3f(x_, y_, z_);
	}
	
	public Vector3f rotate(Vector3f axis, float angle)
	{
		
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle/2));
		float cosHalfAngle = (float)Math.cos(Math.toRadians(angle/2));
		
		float rX = axis.x() * sinHalfAngle;
		float rY = axis.y() * sinHalfAngle;
		float rZ = axis.z() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();
		
		Quaternion w = rotation.mul(this).mul(conjugate);
		
		m_x = w.x();
		m_y = w.y();
		m_z = w.z();
		
		return this;
	}
	
	public Vector3f add(Vector3f r) { return new Vector3f(m_x + r.x(), m_y + r.y(), m_z + r.z()); }
	public Vector3f add(float r) { return new Vector3f(m_x + r, m_y + r, m_z + r); }
	
	public Vector3f sub(Vector3f r) { return new Vector3f(m_x - r.x(), m_y - r.y(), m_z - r.z()); }
	public Vector3f sub(float r) { return new Vector3f(m_x - r, m_y - r, m_z - r); }
	
	public Vector3f mul(Vector3f r) { return new Vector3f(m_x * r.x(), m_y * r.y(), m_z * r.z()); }
	public Vector3f mul(float r) { return new Vector3f(m_x * r, m_y * r, m_z * r); }
	
	public Vector3f div(Vector3f r) { return new Vector3f(m_x / r.x(), m_y / r.y(), m_z / r.z()); }
	public Vector3f div(float r) { return new Vector3f(m_x / r, m_y / r, m_z / r); }

	public String toString() { return "("+m_x+"/"+m_y+"/"+m_z+")"; }
	
	public float x() { return m_x; }
	public float y() { return m_y;}
	public float z() { return m_z; }

	public void setX(float x) { m_x = x; }
	public void setY(float y) {	m_y = y; }
	public void setZ(float z) {	m_z = z; }
}
