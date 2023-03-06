"use strict";

var canvas;
var gl;

var theta = 0.0;
var thetaLoc;
var togglecontrol=false;
var control=0;
var speed=0.1;
window.onload = function init()
{
    canvas = document.getElementById( "gl-canvas" );

    gl = canvas.getContext("webgl2");//get webgl2 from WebGL2RenderingContext
    if ( !gl ) { alert( "WebGL isn't available" ); }

    //
    //  Configure WebGL
    //
    gl.viewport( 0, 0, canvas.width, canvas.height );
    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );

    document.getElementById("toggle").addEventListener("click",function (){
        control+=1;
        togglecontrol=true;
    });
    document.getElementById("fast").addEventListener("click",function (){
        speed+=0.1;
    });
    document.getElementById("slow").addEventListener("click",function (){
        speed-=0.1;
    });
    document.getElementById("color").addEventListener("click",function (){

    });
    //  Load shaders and initialize attribute buffers
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

    var vertices = [
        vec2(  -0.5,  -0.5 ),
        vec2(  0,  0.5 ),
        vec2( 0.5,  -0.5 ),

    ];


    // Load the data into the GPU
    var bufferId = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, bufferId );
    gl.bufferData( gl.ARRAY_BUFFER, flatten(vertices), gl.STATIC_DRAW );

    // Associate out shader variables with our data buffer
    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );

    thetaLoc = gl.getUniformLocation( program, "theta" );

    render();
};


function render() {

    gl.clear( gl.COLOR_BUFFER_BIT );


    if(control%2==0){
        theta += 0.1*speed;
    }
    else{
        theta -= 0.1*speed;
    }

    gl.uniform1f( thetaLoc, theta );

    gl.drawArrays( gl.TRIANGLE_STRIP, 0, 4 );

    window.requestAnimFrame(render);
}
