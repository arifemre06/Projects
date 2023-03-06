"use strict";








var lineArr=[];



function ReadData(){       //this will read file and send information to other function
    var http;

    if (window.XMLHttpRequest) {
        http = new XMLHttpRequest();
    }
    else {
        http = new ActiveXObject("Microsoft.XMLHTTP");
    }

    http.onreadystatechange = function () {
        if (http.readyState != 4) {
            return;
        }
        var lines = http.responseText;
        var yedek=lines.split('\n');
        for(var i=0;i<yedek.length;i++){
            lineArr.push(yedek[i]);
        }
        init();
    }

    http.open("GET", "https://raw.githubusercontent.com/arifemre06/dragon_object/main/dragon_10k.obj", true);
    http.send();

}




window.onload = function() {
    ReadData();
};


function init() {
    var pointsArray = [];
    var verticesArray=[];
    var points=[];
    var floor=[
        -0.4,0,-0.4,1,
        -0.4,0,0.4,1,
        0.4,0.01,0.4,1,
        -0.4,0,-0.4,1,
        0.4,0.01,0.4,1,
        0.4,0,-0.4,1
    ];
    var floorcolor=[
        1.0,0.0,0.0,1.0,
        1.0,0.0,0.0,1.0,
        1.0,0.0,0.0,1.0,
        1.0,0.0,0.0,1.0,
        1.0,0.0,0.0,1.0,
        1.0,0.0,0.0,1.0
    ]
    var dragoncolor=[];
    var rotationspeed=1;
    const canvas = document.querySelector("#glCanvas");
    const gl = canvas.getContext("webgl2");

    if (!gl) { // if your browser does not support webgl2
        alert("WebGL2 is not working on this browser!");
        return;
    }
    document.addEventListener("keypress" ,function (key){
        if(key.key==="+") {
            rotationspeed+=0.5;
        }
        else if(key.key==="-"){
            rotationspeed-=0.5;
        }
        });
    //create shaders
    const vertex_shader = gl.createShader(gl.VERTEX_SHADER); //built-in type in WebGL2RenderingContext
    gl.shaderSource(vertex_shader, v_shader); //add shader source to vertex shader
    gl.compileShader(vertex_shader); //compile the vertex shader
    if (!gl.getShaderParameter(vertex_shader, gl.COMPILE_STATUS)) { //if shader is not compiled
        var info = gl.getShaderInfoLog(vertex_shader); //get the error info
        alert("Could not compile vertex shader. \n\n" + info);
        return;
    }

    const fragment_shader = gl.createShader(gl.FRAGMENT_SHADER); //built-in type in WebGL2RenderingContext
    gl.shaderSource(fragment_shader, f_shader); //add shader source to fragment shader
    gl.compileShader(fragment_shader); //compile the fragment shader
    if (!gl.getShaderParameter(fragment_shader, gl.COMPILE_STATUS)) { //if shader is not compiled
        var info = gl.getShaderInfoLog(fragment_shader); //get the error info
        alert("Could not compile fragment shader. \n\n" + info);
        return;
    }

    const program = gl.createProgram();
    gl.attachShader(program, vertex_shader); //attach vertex shader to the program
    gl.attachShader(program, fragment_shader); //attach fragment shader to the program
    gl.linkProgram(program); //link the program
    if (!gl.getProgramParameter(program, gl.LINK_STATUS)) { //if program is not linked
        var info = gl.getProgramInfoLog(program);
        alert("Could not link WebGL2 program. \n\n" + info);
        return;
    }

    gl.useProgram(program); //use the program
    gl.clearColor(0.0, 1.0, 1.0, 1.0);  // Clear to white, fully opaque
    gl.clear(gl.COLOR_BUFFER_BIT); // Clear the canvas before we start drawing on it.

/*
    function projection( angle, aspect, zmin, zmax )
    {
        var f = 1.0 / Math.tan( (angle * Math.PI / 180.0) / 2 );
        var d = zmax - zmin;

        var result = [f / aspect,0,0,0,
        0,f,0,0,
        0,0,-(zmin + zmax) / d,-2 * zmin * zmax / d,
        0,0,-1,0.0]


        return result;
    }
*/

    function rotateX(c, angle) {
        var a = Math.cos(angle);
        var b = Math.sin(angle);
        var ab0 = c[0], ab8 = c[8];

        c[0] = a*c[0]-b*c[2];
        c[8] = a*c[8]-b*c[10];
        c[2] = a*c[2]+b*ab0;
        c[10] = a*c[10]+b*ab8;
    }




    var viewmatrix=gl.getUniformLocation(program,"vmatrix");
    var modelmatrix=gl.getUniformLocation(program,"mmatrix");




    var move_matrix = [1,0,0,0, 0,1,0,0, 0,0,1,0, 0,0,0,1];
    var v_matrix = [3,0,0,0, 0,3,0,0, 0,0,3,0, 0,0,0,1];


    gl.uniformMatrix4fv(viewmatrix, false, v_matrix);
    gl.uniformMatrix4fv(modelmatrix, false, move_matrix);



    var vertex_location = gl.getAttribLocation(program, "a_position");

    for(var i=0;i<lineArr.length;i++){
        if(lineArr[i][0]=="f"){
            pointsArray.push(lineArr[i].split(" "));
        }
        else if(lineArr[i][0]=="v"){
            verticesArray.push(lineArr[i].split(" "));
        }
    }


    for(var i=0;i<pointsArray.length;i++){


        points.push(verticesArray[pointsArray[i][1]-1][1]);
        points.push(verticesArray[pointsArray[i][1]-1][2]);
        points.push(verticesArray[pointsArray[i][1]-1][3]);
        points.push(1);

        points.push(verticesArray[pointsArray[i][2]-1][1]);
        points.push(verticesArray[pointsArray[i][2]-1][2]);
        points.push(verticesArray[pointsArray[i][2]-1][3]);
        points.push(1);

        points.push(verticesArray[pointsArray[i][3]-1][1]);
        points.push(verticesArray[pointsArray[i][3]-1][2]);
        points.push(verticesArray[pointsArray[i][3]-1][3]);
        points.push(1);
    }

    for (var i=0;i<pointsArray.length*3;i++){
            dragoncolor.push(0.0,1.0,0.0,1.0);
    }










    function renderLoop(timestamp){

        gl.enable(gl.DEPTH_TEST);
        gl.depthFunc(gl.LEQUAL);
        gl.clearColor(0.0, 1.0, 1.0, 1.0);
        gl.clearDepth(1.0);
        gl.viewport(0.0, 0.0, canvas.width, canvas.height);
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        rotateX(move_matrix,20*0.002*rotationspeed)

        gl.uniformMatrix4fv(viewmatrix, false, v_matrix);
        gl.uniformMatrix4fv(modelmatrix, false, move_matrix);

        const buffer_dragon = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, buffer_dragon);
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(points), gl.STATIC_DRAW);
        gl.enableVertexAttribArray(vertex_location);
        gl.vertexAttribPointer( vertex_location, 4, gl.FLOAT, false, 0, 0 );

        var colorBuffer = gl.createBuffer();
        gl.bindBuffer( gl.ARRAY_BUFFER, colorBuffer);
        gl.bufferData( gl.ARRAY_BUFFER,new Float32Array(dragoncolor), gl.STATIC_DRAW );

        var vColor = gl.getAttribLocation( program, "vColor" );
        gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vColor);

        gl.drawArrays(gl.TRIANGLES, 0, pointsArray.length*3);



        const buffer_dragon2 = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, buffer_dragon2);
        gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(floor), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vertex_location, 4, gl.FLOAT, false, 0, 0 );

        var colorBuffer2 = gl.createBuffer();
        gl.bindBuffer( gl.ARRAY_BUFFER, colorBuffer2);
        gl.bufferData( gl.ARRAY_BUFFER,new Float32Array(floorcolor), gl.STATIC_DRAW );


        gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vColor);

        gl.drawArrays(gl.TRIANGLES, 0, 6);


        window.requestAnimationFrame(renderLoop);

}

// start the loop
window.requestAnimationFrame(renderLoop);







}