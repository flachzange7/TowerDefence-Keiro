package engine.core;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Matrix4f 
{
	private float[][] m_m;
	
	public Matrix4f()
	{
		m_m = new float[4][4];
	}
	
	public Matrix4f initAsIdentity()
	{
		m_m[0][0] = 1;	m_m[0][1] = 0;	m_m[0][2] = 0;	m_m[0][3] = 0;
		m_m[1][0] = 0;	m_m[1][1] = 1;	m_m[1][2] = 0;	m_m[1][3] = 0;
		m_m[2][0] = 0;	m_m[2][1] = 0;	m_m[2][2] = 1;	m_m[2][3] = 0;
		m_m[3][0] = 0;	m_m[3][1] = 0;	m_m[3][2] = 0;	m_m[3][3] = 1;

		return this;
	}
	
	public Matrix4f initAsTranslation(float x, float y, float z)
	{
		m_m[0][0] = 1;	m_m[0][1] = 0;	m_m[0][2] = 0;	m_m[0][3] = x;
		m_m[1][0] = 0;	m_m[1][1] = 1;	m_m[1][2] = 0;	m_m[1][3] = y;
		m_m[2][0] = 0;	m_m[2][1] = 0;	m_m[2][2] = 1;	m_m[2][3] = z;
		m_m[3][0] = 0;	m_m[3][1] = 0;	m_m[3][2] = 0;	m_m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initAsRotation(float x, float y, float z)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);
		
		rz.m_m[0][0] = (float)Math.cos(z);rz.m_m[0][1] = -(float)Math.sin(z);rz.m_m[0][2] = 0;				rz.m_m[0][3] = 0;
		rz.m_m[1][0] = (float)Math.sin(z);rz.m_m[1][1] = (float)Math.cos(z);rz.m_m[1][2] = 0;					rz.m_m[1][3] = 0;
		rz.m_m[2][0] = 0;					rz.m_m[2][1] = 0;					rz.m_m[2][2] = 1;					rz.m_m[2][3] = 0;
		rz.m_m[3][0] = 0;					rz.m_m[3][1] = 0;					rz.m_m[3][2] = 0;					rz.m_m[3][3] = 1;
		
		rx.m_m[0][0] = 1;					rx.m_m[0][1] = 0;					rx.m_m[0][2] = 0;					rx.m_m[0][3] = 0;
		rx.m_m[1][0] = 0;					rx.m_m[1][1] = (float)Math.cos(x);rx.m_m[1][2] = -(float)Math.sin(x);rx.m_m[1][3] = 0;
		rx.m_m[2][0] = 0;					rx.m_m[2][1] = (float)Math.sin(x);rx.m_m[2][2] = (float)Math.cos(x);rx.m_m[2][3] = 0;
		rx.m_m[3][0] = 0;					rx.m_m[3][1] = 0;					rx.m_m[3][2] = 0;					rx.m_m[3][3] = 1;
		
		ry.m_m[0][0] = (float)Math.cos(y);ry.m_m[0][1] = 0;					ry.m_m[0][2] = -(float)Math.sin(y);ry.m_m[0][3] = 0;
		ry.m_m[1][0] = 0;					ry.m_m[1][1] = 1;					ry.m_m[1][2] = 0;					ry.m_m[1][3] = 0;
		ry.m_m[2][0] = (float)Math.sin(y);ry.m_m[2][1] = 0;					ry.m_m[2][2] = (float)Math.cos(y);ry.m_m[2][3] = 0;
		ry.m_m[3][0] = 0;					ry.m_m[3][1] = 0;					ry.m_m[3][2] = 0;					ry.m_m[3][3] = 1;
		
		m_m = rz.mul(ry.mul(rx)).m();
		
		return this;
	}
	
	public Matrix4f initAsScale(float x, float y, float z)
	{
		m_m[0][0] = x;	m_m[0][1] = 0;	m_m[0][2] = 0;	m_m[0][3] = 0;
		m_m[1][0] = 0;	m_m[1][1] = y;	m_m[1][2] = 0;	m_m[1][3] = 0;
		m_m[2][0] = 0;	m_m[2][1] = 0;	m_m[2][2] = z;	m_m[2][3] = 0;
		m_m[3][0] = 0;	m_m[3][1] = 0;	m_m[3][2] = 0;	m_m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initAsPerspective(float fov, float aspectRatio, float zNear, float zFar)
	{
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;
		
		m_m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);	m_m[0][1] = 0;					m_m[0][2] = 0;	m_m[0][3] = 0;
		m_m[1][0] = 0;						m_m[1][1] = 1.0f / tanHalfFOV;	m_m[1][2] = 0;	m_m[1][3] = 0;
		m_m[2][0] = 0;						m_m[2][1] = 0;					m_m[2][2] = (-zNear -zFar)/zRange;	m_m[2][3] = 2 * zFar * zNear / zRange;
		m_m[3][0] = 0;						m_m[3][1] = 0;					m_m[3][2] = 1;	m_m[3][3] = 0;
		
		
		return this;
	}

	public Matrix4f initAsOrthographic(float left, float right, float bottom, float top, float near, float far)
	{
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		m_m[0][0] = 2/width;m_m[0][1] = 0;	m_m[0][2] = 0;	m_m[0][3] = -(right + left)/width;
		m_m[1][0] = 0;	m_m[1][1] = 2/height;m_m[1][2] = 0;	m_m[1][3] = -(top + bottom)/height;
		m_m[2][0] = 0;	m_m[2][1] = 0;	m_m[2][2] = -2/depth;m_m[2][3] = -(far + near)/depth;
		m_m[3][0] = 0;	m_m[3][1] = 0;	m_m[3][2] = 0;	m_m[3][3] = 1;

		return this;
	}

	public Matrix4f initAsRotation(Vector3f forward, Vector3f up)
	{
		Vector3f f = forward.normalize();
		
		Vector3f r = up.normalize();
		r = r.cross(f);
		
		Vector3f u = f.cross(r);

		return initAsRotation(f, u, r);
	}

	public Matrix4f initAsRotation(Vector3f forward, Vector3f up, Vector3f right)
	{
		Vector3f f = forward;
		Vector3f r = right;
		Vector3f u = up;

		m_m[0][0] = r.x();	m_m[0][1] = r.y();	m_m[0][2] = r.z();	m_m[0][3] = 0;
		m_m[1][0] = u.x();	m_m[1][1] = u.y();	m_m[1][2] = u.z();	m_m[1][3] = 0;
		m_m[2][0] = f.x();	m_m[2][1] = f.y();	m_m[2][2] = f.z();	m_m[2][3] = 0;
		m_m[3][0] = 0;		m_m[3][1] = 0;		m_m[3][2] = 0;		m_m[3][3] = 1;

		return this;
	}

	public Vector3f transform(Vector3f r)
	{
		return new Vector3f(m_m[0][0] * r.x() + m_m[0][1] * r.y() + m_m[0][2] * r.z() + m_m[0][3],
		                    m_m[1][0] * r.x() + m_m[1][1] * r.y() + m_m[1][2] * r.z() + m_m[1][3],
		                    m_m[2][0] * r.x() + m_m[2][1] * r.y() + m_m[2][2] * r.z() + m_m[2][3]);
	}
	
	public Matrix4f mul(Matrix4f r)
	{
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				res.set(i, j, m_m[i][0] * r.get(0, j) +
						m_m[i][1] * r.get(1, j) +
						m_m[i][2] * r.get(2, j) +
						m_m[i][3] * r.get(3, j));
			}
		}
		
		return res;
	}
	
	public float[][] m()
	{
		float[][] res = new float[4][4];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = m_m[i][j];
		
		return res;
	}
	
	public float get(int x, int y)
	{
		return m_m[x][y];
	}

	public void setM(float[][] m)
	{
		m_m = m;
	}
	
	public void set(int x, int y, float value)
	{
		m_m[x][y] = value;
	}
	
	public FloatBuffer buffer()
	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
		
		for(int i = 0; i != 4; i++)
			for(int j = 0; j != 4; j++)
				buffer.put(m_m[i][j]);
		
		buffer.flip();
		
		return buffer;
	}
	
}
