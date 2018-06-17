/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 03/10/2012
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
var time, go;

window.onload = function(){
    go = document.getElementById("go");
    time = document.getElementById("time");
    delay = document.getElementById("delay");

    var alarm = function(){
        alert("Time's up");
    }

    go.onclick = function(){
        // Get the time first....
        var t = time.value;
        var ms = t * 1000;
        setTimeout(alarm, ms);
    }

    time.oninput = function(){
        delay.innerText = time.value;
    };

};