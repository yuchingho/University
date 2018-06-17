/**
 * Created by alistair on 05/11/2013.
 */

var x, y, dx, dy, canvas, context;

function tick() {
    if (x < 5 || x > canvas.width - 5)  dx = -dx;
    x += dx;
    if (y < 0 || y > canvas.height - 5)  dy = -dy;
    y += dy;
    context.fillStyle = "white";
    //context.clearRect(0, 0, canvas.width, canvas.height);
    context.beginPath();
    context.arc(x, y, 10, 0, Math.PI * 2);
    context.fillStyle = "blue";
    context.fill();
}

window.onload = function () {
    canvas = document.getElementById("c");
    context = canvas.getContext("2d");
    x = 5;    y = 5;
    dx = 2;    dy = 2;
    setInterval(tick, 20);
}
