/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/********************************************************************/

const v_shader_2 = `#version 300 es
    in vec3 a_position;
    uniform mat4 Projmatrix;
    uniform mat4 Viewmatrix;
    uniform mat4 Movematrix;
    void main() {
        gl_Position = Projmatrix*Viewmatrix*Movematrix*vec4(a_position, 1.);    
    }
`;

const f_shader_2 = `#version 300 es

    precision mediump float;
    uniform float u_time;
    in vec4 color;
    out vec4 o_color;
    void main() {
        o_color = vec4(abs(sin(u_time)),0.0,0.0,1.0);
    }
`;


