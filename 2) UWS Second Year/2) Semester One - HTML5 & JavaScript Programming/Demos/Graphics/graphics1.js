/**
 * Created by alistair on 05/11/2013.
 */
var canvas, context, x=30;

window.onload = function() {
    canvas = document.getElementById("c");
    context = canvas.getContext("2d");
    context.fillStyle = 'red';
    setInterval(draw, 100);
}

function draw() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.fillRect(x, 30, 50, 50);
    x += 2;
}
