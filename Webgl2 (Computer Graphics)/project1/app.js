"use strict";
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    init();
};

function init() {
    var step_1=false;
    var step_2=false;
    var step_3=false;
    const canvas = document.querySelector("#glCanvas"); //get canvas element
    const gl = canvas.getContext("webgl2"); //get webgl2 from WebGL2RenderingContext
    document.addEventListener("keypress",function (key){
        if(key.key==="1")
        {
            step_1=true;
        }
        else if(key.key==="2")
        {
            step_2=true;
        }
        else if(key.key==="3")
        {
            step_3=true;
        }
    });
    if (!gl) { // if your browser does not support webgl2
        alert("WebGL2 is not working on this browser!");
        return;
    }
    if(step_1){

    }
    //create shaders
    const vertex_shader = gl.createShader(gl.VERTEX_SHADER); //built-in type in WebGL2RenderingContext
    gl.shaderSource(vertex_shader, v_shader_2); //add shader source to vertex shader
    gl.compileShader(vertex_shader); //compile the vertex shader
    if (!gl.getShaderParameter(vertex_shader, gl.COMPILE_STATUS)) { //if shader is not compiled
        var info = gl.getShaderInfoLog(vertex_shader); //get the error info
        alert("Could not compile vertex shader. \n\n" + info);
        return;
    }

    const fragment_shader = gl.createShader(gl.FRAGMENT_SHADER); //built-in type in WebGL2RenderingContext
    gl.shaderSource(fragment_shader, f_shader_2); //add shader source to fragment shader
    gl.compileShader(fragment_shader); //compile the fragment shader
    if (!gl.getShaderParameter(fragment_shader, gl.COMPILE_STATUS)) { //if shader is not compiled
        var info = gl.getShaderInfoLog(fragment_shader); //get the error info
        alert("Could not compile fragment shader. \n\n" + info);
        return;
    }

    //create program
    const program = gl.createProgram();
    gl.attachShader(program, vertex_shader); //attach vertex shader to the program
    gl.attachShader(program, fragment_shader); //attach fragment shader to the program
    gl.linkProgram(program); //link the program
    if (!gl.getProgramParameter(program, gl.LINK_STATUS)) { //if program is not linked
        var info = gl.getProgramInfoLog(program);
        alert("Could not link WebGL2 program. \n\n" + info);
        return;
    }

    const numComponents = 2;  // pull out 2 values per iteration
    const type = gl.FLOAT;    // the data in the buffer is 32bit floats
    const normalize = false;  // don't normalize
    const stride = 0;         // how many bytes to get from one set of values to the next
                              // 0 = use type and numComponents above
    const offset = 0;// how many bytes inside the buffer to start from






    gl.useProgram(program); //use the program
    gl.clearColor(0.4, 0.8, 0.3, 1.0);  // Clear to white, fully opaque
    gl.clear(gl.COLOR_BUFFER_BIT); // Clear the canvas before we start drawing on it.
    var Projmatrix = gl.getUniformLocation(program , "Projmatrix");
    var Viewmatrix = gl.getUniformLocation(program, "Viewmatrix");
    var Movematrix = gl.getUniformLocation(program, "Movematrix");

    var vertex_location = gl.getAttribLocation(program, "a_position");

    /*========================= Matrixes ========================= */

    function get_projection(angle, a, zMin, zMax) {
        var ang = Math.tan((angle*.5)*Math.PI/180);//angle*.5
        return [
            0.5/ang, 0 , 0, 0,
            0, 0.5*a/ang, 0, 0,
            0, 0, -(zMax+zMin)/(zMax-zMin), -1,
            0, 0, (-2*zMax*zMin)/(zMax-zMin), 0
        ];
    }

    var p_matrix = get_projection(40, canvas.width/canvas.height, 1, 100);
    var move_matrix = [1,0,0,0, 0,1,0,0, 0,0,1,0, 0,0,0,1];
    var v_matrix = [1,0,0,0, 0,1,0,0, 0,0,1,0, 0,0,0,1];


    //translating z
    var timeLocation = gl.getUniformLocation(program, "u_time");
    v_matrix[14] = v_matrix[14]-2; //zoom
    gl.uniform1f(timeLocation,1);
    /*=======================ROTATE========================*/
    function rotateZ(c, angle) {
        var a = Math.cos(angle);
        var b = Math.sin(angle);
        var ab0 = c[0], ab4 = c[4], ab8 = c[8];

        c[0] = a*c[0]-b*c[1];
        c[4] = a*c[4]-b*c[5];
        c[8] = a*c[8]-b*c[9];
        c[1] = a*c[1]+b*ab0;
        c[5] = a*c[5]+b*ab4;
        c[9] = a*c[9]+b*ab8;
    }
    gl.uniformMatrix4fv(Projmatrix, false, p_matrix);
    gl.uniformMatrix4fv(Viewmatrix, false, v_matrix);
    gl.uniformMatrix4fv(Movematrix, false, move_matrix);

    const buffer_circle = gl.createBuffer();
    gl.bindBuffer(gl.ARRAY_BUFFER, buffer_circle); //bind the buffer
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW); //buffer data for circle vertices
    gl.enableVertexAttribArray(vertex_location); //enable attributes to use them
    gl.vertexAttribPointer(vertex_location, numComponents, type, normalize, stride, offset); //set the attributes order

    for (var i = 0; i < 8; i++) {
        gl.drawArrays(gl.TRIANGLE_STRIP, n * i, n); //draw the shape that has 3 vertices
    }
    const buffer_circle2 = gl.createBuffer(); //create buffer for big circle
    gl.bindBuffer(gl.ARRAY_BUFFER, buffer_circle2); //bind the buffer for big circles
    gl.vertexAttribPointer(vertex_location, numComponents, type, normalize, stride, offset); //set the attributes order
    gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW); //buffer data for circle vertices

    for (var i = 0; i < 4; i++) {
        gl.drawArrays(gl.TRIANGLE_STRIP, n2*i, n2); //draw the shape that has 3 vertices

    }



    var donusacısı=0;
    var scale_adjustment=false;
    var saga=0;
    var sola=0;

        function renderLoop(timeStamp) {


            if(step_1) {
                if(!scale_adjustment){
                        v_matrix[14] = v_matrix[14]-3; //zoom
                        scale_adjustment=true;
                }
            // set time uniform
                    if(!step_3){
                        gl.uniform1f(timeLocation,1);
                    }
                else if(step_3) {
                    gl.uniform1f(timeLocation, timeStamp / 1000.0);
                }
            if(step_2) {
                donusacısı += 0.2;
                if (donusacısı <= 8) {
                    rotateZ(move_matrix, 20 * 0.001);
                    sola+=1;

                } else if (donusacısı > 8 && donusacısı <= 24) {
                    saga=0;
                    rotateZ(move_matrix, (-1) * 20 * 0.001);
                    saga+=1;

                } else if (donusacısı > 24 && donusacısı <= 40.2) {
                    sola=0;
                    rotateZ(move_matrix, 20 * 0.001);

                } else {
                    donusacısı = 8;
                }
            }
            gl.enable(gl.DEPTH_TEST);
            gl.depthFunc(gl.LEQUAL);
            gl.clearColor(0.4, 0.8, 0.3, 1.0);
            gl.clearDepth(1.0);
            gl.viewport(0.0, 0.0, canvas.width, canvas.height);
            gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

                gl.uniformMatrix4fv(Projmatrix, false, p_matrix);
                gl.uniformMatrix4fv(Viewmatrix, false, v_matrix);
                gl.uniformMatrix4fv(Movematrix, false, move_matrix);

            const buffer_circle = gl.createBuffer();
            gl.bindBuffer(gl.ARRAY_BUFFER, buffer_circle); //bind the buffer
            gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices), gl.STATIC_DRAW); //buffer data for circle vertices
            gl.enableVertexAttribArray(vertex_location); //enable attributes to use them
            gl.vertexAttribPointer(vertex_location, numComponents, type, normalize, stride, offset); //set the attributes order

            for (var i = 0; i < 8; i++) {
                gl.drawArrays(gl.TRIANGLE_STRIP, n * i, n); //draw the shape that has 3 vertices
            }
            const buffer_circle2 = gl.createBuffer(); //create buffer for big circle
            gl.bindBuffer(gl.ARRAY_BUFFER, buffer_circle2); //bind the buffer for big circles
            gl.vertexAttribPointer(vertex_location, numComponents, type, normalize, stride, offset); //set the attributes order
            gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(vertices2), gl.STATIC_DRAW); //buffer data for circle vertices

            for (var i = 0; i < 4; i++) {
                gl.drawArrays(gl.TRIANGLE_STRIP, n2 * i, n2); //draw the shape that has 3 vertices

            }

            }
            window.requestAnimationFrame(renderLoop);
        }

// start the loop
        window.requestAnimationFrame(renderLoop);








}



