#version 300 es
#ifdef GL_ES
precision highp float;
#endif

out vec4 myOutputColor;

void main() {
    myOutputColor = vec4(1, 0, 0, 1);  // red
}
