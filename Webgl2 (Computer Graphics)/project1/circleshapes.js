/////////////////////////////////////////////////////////////////////////////////////////////////////
//SMALL CIRCLES     SMALL CIRCLES      SMALL CIRCLES       SMALL CIRCLES       SMALL CIRCLES
/////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////
//SMALL CIRCLES     SMALL CIRCLES      SMALL CIRCLES       SMALL CIRCLES       SMALL CIRCLES
/////////////////////////////////////////////////////////////////////////////////////////////////////
var vertices = [];//THE ARRAY WHİCH HAS CİRCLES VERTEXES
var vertCount = 2;
var kactane=0;  //How many small circles we have
var bigscale=2;
var smallscale=6;
//SMALL CIRCLES POSITIONS
var konumlar=[
    -0.5,0.5,
    0,0.7,
    0.5,0.5,
    0.7,0,
    0.5,-0.50,
    0,-0.7,
    -0.5,-0.50,
    -0.7,0
];

for(var a=0;a<=14;a+=2) {

    kactane+=1;
    for (var i = 0.0; i <= 360; i += 1) {
        // degrees to radians
        var j = i * Math.PI / 180;
        // X Y Z
        var vert1 = [
            konumlar[a]+(Math.sin(j) / smallscale),
            konumlar[a+1]+(Math.cos(j) / smallscale),
            // 0,
        ];
        var vert2 = [
            konumlar[a],
            konumlar[a+1],
            // 0,
        ];
        vertices = vertices.concat(vert1);
        vertices = vertices.concat(vert2);
    }

}
var n =vertices.length/(vertCount*kactane);

/////////////////////////////////////////////////////////////////////////////////////////////////////
//BIG CIRCLES     BIG CIRCLES       BIG CIRCLES      BIG CIRCLES      BIG CIRCLES
/////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////////////
//BIG CIRCLES     BIG CIRCLES       BIG CIRCLES      BIG CIRCLES      BIG CIRCLES
/////////////////////////////////////////////////////////////////////////////////////////////////////

//CREATE THE ARRAY WHICH HAS INFO OF BIG CIRCLES POSITIONS
var vertices2 = [];//ARRAY WHICH HAS INFO OF BIG CIRCLES POSITIONS
var vertCount2 = 2;
var kactane2=0;//How many small circles we have
//BIG CIRCLES POSITIONS
var konumlar2=[
    -0.5,0.5,
    0.5,0.5,
    0.5,-0.5,
    -0.5,-0.5
];
//I created 4 piece 3/4 circles.
for(var a=0;a<=6;a+=2) {
    kactane2+=1;
    //Circle 1;
    if(a==0){
        for (var i =-180; i <= 90; i += 1) {

            // degrees to radians

            var j = i * Math.PI / 180;
            // X Y Z
            var vert1 = [
                konumlar2[a] + (Math.sin(j) / bigscale),
                konumlar2[a + 1] + (Math.cos(j) / bigscale),
                // 0,
            ];
            var vert2 = [
                konumlar2[a],
                konumlar2[a + 1],
                // 0,
            ];
            vertices2 = vertices2.concat(vert1);
            vertices2 = vertices2.concat(vert2);


        }
    }
    //Circle 2;
    else if(a==2){
        for (var i = -90; i <= 180; i += 1) {

            // degrees to radians

            var j = i * Math.PI / 180;
            // X Y Z
            var vert1 = [
                konumlar2[a] + (Math.sin(j) / 2),
                konumlar2[a + 1] + (Math.cos(j) / 2),
                // 0,
            ];
            var vert2 = [
                konumlar2[a],
                konumlar2[a + 1],
                // 0,
            ];
            vertices2 = vertices2.concat(vert1);
            vertices2 = vertices2.concat(vert2);

        }
    }
    //Circle 3;
    else if(a==4){
        for (var i = 0; i <= 270; i += 1) {

            // degrees to radians

            var j = i * Math.PI / 180;
            // X Y Z
            var vert1 = [
                konumlar2[a] + (Math.sin(j) / 2),
                konumlar2[a + 1] + (Math.cos(j) / 2),
                // 0,
            ];
            var vert2 = [
                konumlar2[a],
                konumlar2[a + 1],
                // 0,
            ];
            vertices2 = vertices2.concat(vert1);
            vertices2 = vertices2.concat(vert2);

        }
    }

    //Circle 4;
    else if(a==6){
        for (var i = 90; i <= 360; i += 1) {

            // degrees to radians

            var j = i * Math.PI / 180;
            // X Y Z
            var vert1 = [
                konumlar2[a] + (Math.sin(j) / 2),
                konumlar2[a + 1] + (Math.cos(j) / 2),
                // 0,
            ];
            var vert2 = [
                konumlar2[a],
                konumlar2[a + 1],
                // 0,
            ];
            vertices2 = vertices2.concat(vert1);
            vertices2 = vertices2.concat(vert2);
        }
    }

}
var n2 =vertices2.length/(vertCount2*kactane2);