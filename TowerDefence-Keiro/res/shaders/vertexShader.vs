#version 330

layout (location = 0) in vec3 position;
uniform float size;

void main()
{
	gl_Position = vec4(position, 1.0);
}
