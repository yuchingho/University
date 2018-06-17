/**
 * Created by alistair on 05/11/2013.
 */

var canvas, context, current;

window.onload = function() {
    canvas = document.getElementById("c");
    context = canvas.getContext("2d");
    canvas.onmousedown = mouseDown;
    canvas.onmouseup = mouseUp;
    canvas.onmousemove = mouseMove;
};

function doCurve(e){
    x = e.x - canvas.offsetLeft;
    y = e.y - canvas.offsetTop;
    context.beginPath();
    context.moveTo(20, 20);
    context.quadraticCurveTo(x, y, 200, 20);
    context.stroke();
}

function mouseDown(e){
    current = context.getImageData(0, 0,
        canvas.width, canvas.height);
    doCurve(e);
}

function mouseMove(e){
    if(current){
            context.putImageData( current, 0, 0);
        doCurve(e);
    }
}

function mouseUp(e){
    context.putImageData( current, 0, 0);
    doCurve(e);
    current = null;
}
