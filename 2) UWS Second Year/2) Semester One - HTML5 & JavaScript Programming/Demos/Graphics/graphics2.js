/**
 * Created by alistair on 05/11/2013.
 */

function showFillText(text) {
    context.fillStyle = '#f00';
    context.font = 'italic bold 30px sans-serif';
    context.textBaseline = 'bottom';
    context.fillText(text, 50, 100);
}

function twoDigit(n) {
    if (n.toString().length < 2) {
        return "0" + n;
    } else {
        return n.toString();
    }
}

window.onload = function () {
    canvas = document.getElementById("c");
    context = canvas.getContext("2d");
    var d = new Date();
    var str = twoDigit(d.getHours())+":"+twoDigit(d.getMinutes())+":"+ twoDigit(d.getSeconds());
    showFillText(str);
};
