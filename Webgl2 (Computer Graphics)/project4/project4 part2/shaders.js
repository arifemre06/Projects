/********************************************************************/
const v_shader = `#version 300 es
    in vec4 a_position;
    in  vec4 vColor;
    out vec4 color;
    
    uniform mat4 vmatrix;
    uniform mat4 mmatrix;
    void main() {
        gl_Position = a_position*vmatrix*mmatrix;    
        color = vColor;
    }
`;

const f_shader = `#version 300 es

    precision mediump float;
    in vec4 color;
    out vec4 o_color;
    void main() {
        o_color = color;
    }
`;