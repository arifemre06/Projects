

var canvas;
var gl;

var NumVertices  = 18;

var pointsArray = [];
var colorsArray = [];
var islocked=false;
var rotateSpeed=0.3;
var vertices = [
    vec4(-0.5, -0.5,  1.5, 1.0),
    vec4(0.5, -0.5,  1.5, 1.0),
    vec4(-0.5, -0.5, 0.5, 1.0),
    vec4( 0.5, -0.5, 0.5, 1.0),

    vec4(0,  0.3,  1, 1.0)

];

var vertexColors = [

    vec4( 1.0, 0.0, 0.0, 1.0),  // red 0
    vec4( 1.0, 1.0, 0.0, 1.0),  // yellow 1
    vec4( 0.0, 1.0, 0.0, 1.0 ),  // green 2
    vec4( 0.0, 0.0, 1.0, 1.0 ),  // blue 3
    vec4( 1.0, 0.0, 1.0, 1.0 )  // magenta 4


];


var near = 0.3;
var far = 3.0;
var radius = 4.0;
var theta  = 0.0;
var phi    = 0.0;
var dr = 5.0 * Math.PI/180.0;

var  fovy = 45.0;  // Field-of-view in Y direction angle (in degrees)
var  aspect = 1.0;       // Viewport aspect ratio

var modelViewMatrix, projectionMatrix;
var modelViewMatrixLoc, projectionMatrixLoc;
var eye;
const at = vec3(0.0, 0.0, 0.0);
const up = vec3(0.0, 1.0, 0.0);



function quad(a, b, c, d,e) {
    pointsArray.push(vertices[a]);
    colorsArray.push(vertexColors[e]);
    pointsArray.push(vertices[b]);
    colorsArray.push(vertexColors[e]);
    pointsArray.push(vertices[c]);
    colorsArray.push(vertexColors[e]);
    pointsArray.push(vertices[a]);
    colorsArray.push(vertexColors[e]);
    pointsArray.push(vertices[c]);
    colorsArray.push(vertexColors[e]);
    pointsArray.push(vertices[d]);
    colorsArray.push(vertexColors[e]);
}
function triangle(a,b,c,d){
    pointsArray.push(vertices[a]);
    colorsArray.push(vertexColors[d]);
    pointsArray.push(vertices[b]);
    colorsArray.push(vertexColors[d]);
    pointsArray.push(vertices[c]);
    colorsArray.push(vertexColors[d]);
}

function colorCube()
{
    quad( 0,1,3,2,4 );
    triangle( 1,3,4,1);
    triangle( 2,3,4,2);
    triangle( 1,0,4,0 );
    triangle( 0,2,4,3 );

}

window.onload = function init() {

    canvas = document.getElementById( "gl-canvas" );


    gl=canvas.getContext("webgl2");
    if ( !gl ) { alert( "WebGL2 isn't available" ); }

    document.addEventListener("keypress" ,function (key){
        if(key.key==="p") {
            if(!islocked) {
                canvas.requestPointerLock();
                islocked=true;
            }
            else{
                document.exitPointerLock();
                islocked=false;
            }
        }
    });

    gl.viewport( 0, 0, canvas.width, canvas.height );

    aspect =  canvas.width/canvas.height;

    gl.clearColor( 1.0, 1.0, 1.0, 1.0 );

    gl.enable(gl.DEPTH_TEST);


    document.addEventListener('pointerlockchange', lockChangeAlert, false);
    document.addEventListener('mozpointerlockchange', lockChangeAlert, false);
/////////////////////////////////////////////////////////////////////////////////////////
    function lockChangeAlert() {
        if (document.pointerLockElement === canvas ||
            document.mozPointerLockElement === canvas) {

            document.addEventListener("mousemove", rotatePyramid, false);
        } else {

            document.removeEventListener("mousemove", rotatePyramid, false);
        }
    }
  ///////////////////////////////////////////////////////////////////////////////////////
    //
    //  Load shaders and initialize attribute buffers
    //
    var program = initShaders( gl, "vertex-shader", "fragment-shader" );
    gl.useProgram( program );

    colorCube();

    var cBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, cBuffer);
    gl.bufferData( gl.ARRAY_BUFFER, flatten(colorsArray), gl.STATIC_DRAW );

    var vColor = gl.getAttribLocation( program, "vColor" );
    gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vColor);

    var vBuffer = gl.createBuffer();
    gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer);
    gl.bufferData( gl.ARRAY_BUFFER, flatten(pointsArray), gl.STATIC_DRAW );

    var vPosition = gl.getAttribLocation( program, "vPosition" );
    gl.vertexAttribPointer( vPosition, 4, gl.FLOAT, false, 0, 0 );
    gl.enableVertexAttribArray( vPosition );

    modelViewMatrixLoc = gl.getUniformLocation( program, "modelViewMatrix" );
    projectionMatrixLoc = gl.getUniformLocation( program, "projectionMatrix" );

// sliders for viewing parameters

    document.getElementById("zFarSlider").onchange = function() {
        far = event.srcElement.value;
    };
    document.getElementById("zNearSlider").onchange = function() {
        near = event.srcElement.value;
    };
    document.getElementById("radiusSlider").onchange = function() {
        radius = event.srcElement.value;
    };
    document.getElementById("aspectSlider").onchange = function() {
        aspect = event.srcElement.value;
    };
    document.getElementById("fovSlider").onchange = function() {
        fovy = event.srcElement.value;
    };

    render();
}



function rotatePyramid(e) {


    phi += rotateSpeed*e.movementY * Math.PI / 180;
    theta += -1*rotateSpeed*e.movementX * Math.PI / 180;



}




var render = function(){

    gl.clear( gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

    eye = vec3(radius * Math.sin(theta) * Math.cos(phi),
        radius * Math.sin(theta) * Math.sin(phi), radius * Math.cos(theta));

    modelViewMatrix = lookAt(eye, at , up);
    projectionMatrix = perspective(fovy, aspect, near, far);

    gl.uniformMatrix4fv( modelViewMatrixLoc, false, flatten(modelViewMatrix) );
    gl.uniformMatrix4fv( projectionMatrixLoc, false, flatten(projectionMatrix) );

    gl.drawArrays( gl.TRIANGLES, 0, NumVertices );


        requestAnimFrame(render);


}

