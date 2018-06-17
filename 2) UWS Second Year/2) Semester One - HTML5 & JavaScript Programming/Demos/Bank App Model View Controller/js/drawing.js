function drawLine(context, x, y, width, height){
    context.beginPath();
    context.moveTo(x, y);
    context.lineTo(x+width, y+height);
    context.stroke();
}

function drawCircle(context, x, y, radius){
    context.beginPath();
    context.arc(x, y, radius, 0, 2*Math.PI);
    context.stroke();
}

function drawRectPath(context, x, y, width, height){
    context.beginPath();
    context.moveTo(x, y);
    context.lineTo(x+width, y);
    context.lineTo(x+width, y+height);
    context.lineTo(x, y+height);
    context.closePath();
    context.fill();
}

var Line = function(x, y, width, height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.outline = "black";
};

Line.prototype.setColour = function(outline, fill){
    this.outline = outline;
    this.fill = this.fill || fill;
};

Line.prototype.draw = function(context){
    context.strokeStyle = this.outline;
    context.beginPath();
    context.moveTo(this.x, this.y);
    context.lineTo(this.x+this.width, this.y+this.height);
    context.stroke();
    context.fill();
};

var Triangle = function(x, y, width, height){

};

function drawing(){
    var context = document.getElementById("canvas").getContext("2d");
    drawLine(context, 10, 10, 20, 20);
    drawCircle(context, 60, 60, 35);
    drawRectPath(context, 140, 20, 50, 30);
    var l = new Line(20, 200, 50, 70);
    l.setColour("#ff0000", "#0000ff");
    l.draw(context);
    var r = new Rectangle(400, 100, 100, 20);
    r.setColour("#0f0", "#888");
    r.draw(context);
}


window.onload = drawing;


