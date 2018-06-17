/**
 * Created by alistair on 14/10/15.
 */

function Timer(name, duration, callback) {
    this.name = name;
    this.duration = duration;
    this.callback = callback;
}
Timer.prototype.ticktock = function(){
    this.duration -= 1;
    if(this.duration === 0) {
        this.callback();
    }
};
Timer.prototype.toHTML = function() {
    return "<tr><td>" + this.name + "</td><td>Remaining " + this.duration + "</td></tr>";
};

var timers = [];
function ticktock() {
    for(var t = 0; t < timers.length; t += 1){
        timers[t].ticktock();
    }
    updateList();
}

function updateList(){
    var table = "<table style='border:solid'>";
    for(var t = 0; t < timers.length; t += 1) {
        table += timers[t].toHTML();
    }
    table += "</table>";
    document.getElementById("list").innerHTML = table;
}


function addTimer(name, duration, callback){
    timers.push(new Timer(name, duration, callback));
    updateList();
}

function go(){
    setInterval(ticktock, 1000);
}

function doAlert() {
    alert("BANG!");
}

window.onload = function() {
    document.getElementById("add").onclick = function() {
        var name = prompt("Enter a name: "),
            t, interval;
        while(true){
            t = prompt("Enter a duration (in seconds): ");
            interval = parseInt(t);
            if(!isNaN(interval)){
                break;
            }
        }
        addTimer(name, interval, doAlert);
    };
    go();
};

