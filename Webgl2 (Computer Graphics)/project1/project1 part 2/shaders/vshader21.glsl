#version 300 es
out vec4 vPosition;

void
main()
{
    gl_PointSize = 1.0;
    gl_Position = vPosition;
}
