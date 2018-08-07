package engine.core;

public class Camera 
{
	public static final Vector3f yAxis = new Vector3f(0, 1, 0);
	
	private Vector3f m_pos;
	private Vector3f m_forward;
	private Vector3f m_up;
	
	public Camera()
	{
		this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
	}
	
	public Camera(Vector3f pos, Vector3f forward, Vector3f up)
	{
		m_pos = pos;
		m_forward = forward;
		m_up = up;
		
		up.normalize();
		forward.normalize();
	}
	
	public void move(Vector3f direction, float amount)
	{
		m_pos = m_pos.add(direction.mul(amount));
	}
	
	public void rotateX(float angle)
	{
		Vector3f Haxis = yAxis.cross(m_forward);
		Haxis.normalize();
		
		m_forward.rotate(Haxis, angle);
		m_forward.normalize();
		
		m_up = m_forward.cross(Haxis);
		m_up.normalize();
	}
	
	public void rotateY(float angle)
	{
		Vector3f Haxis = yAxis.cross(m_forward);
		Haxis.normalize();
		
		m_forward.rotate(yAxis, angle);
		m_forward.normalize();
		
		m_up = m_forward.cross(Haxis);
		m_up.normalize();
	}
	
	public void input()
	{
		float moveAmount = (float)(10 * Time.delta());
		float rotationAmount = (float)(100 * Time.delta());
		
		if(Input.keyDown(Input.KEY_W))
			move(forward(), moveAmount);
		if(Input.keyDown(Input.KEY_S))
			move(forward(), -moveAmount);
		if(Input.keyDown(Input.KEY_A))
			move(left(), moveAmount);
		if(Input.keyDown(Input.KEY_D))
			move(right(), moveAmount);
	}
	
	public Vector3f left()
	{
		Vector3f left = m_forward.cross(m_up);
		left.normalize();
		return left;
	}
	
	public Vector3f right()
	{
		Vector3f right = m_up.cross(m_forward);
		right.normalize();
		return right;
	}
	
	public Vector3f position()
	{
		return m_pos;
	}
	
	public Vector3f forward()
	{
		return m_forward;
	}
	
	public Vector3f up()
	{
		return m_up;
	}
	
	public void setPosition(Vector3f pos)
	{
		m_pos = pos;
	}
	
	public void setForward(Vector3f forward)
	{
		m_forward = forward;
	}
	
	public void setUp(Vector3f up)
	{
		m_up = up;
	}
}
