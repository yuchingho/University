/**
 * Created with JetBrains WebStorm.
 * User: alistairmcmonnies
 * Date: 24/08/2012
 * Time: 13:00
 * To change this template use File | Settings | File Templates.
 */

var canvas, context,
    x=10, y=10;

var requestAnimationFrame = window.requestAnimationFrame       ||
                            window.webkitRequestAnimationFrame ||
                            window.mozRequestAnimationFrame    ||
                            window.oRequestAnimationFrame      ||
                            window.msRequestAnimationFrame     ||
                            function( callback ){
                                window.setTimeout(callback, 1000 / 60);
                            };

var render = function(){
    x += 3;
    y += 3;
    // context.clearRect(0, 0, canvas.width, canvas.height);
    context.fillRect(x, y, 10, 10);
    //context.arc(x, y, 5, 0, 2* Math.PI);
    context.fill();
};

var doAnimate = function(){
    requestAnimationFrame(doAnimate);
    render();
}

var setup = function(){
    // First we need a canvas and a context...
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    context.fillStyle = "#ff0000";
    context.globalCompositeOperation = "source-over";
    // window.setInterval(doAnimate, 100);
    doAnimate();
};

window.onload = setup;