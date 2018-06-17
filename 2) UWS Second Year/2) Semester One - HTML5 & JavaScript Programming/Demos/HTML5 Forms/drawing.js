/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 20/08/2012
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */

var Shape = function(x, y, width, height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.lineStyle = "black";
    this.fillStyle = null;
};

Shape.prototype.draw = function(context){
    context.strokeStyle = this.lineStyle;
    context.fillStyle = this.fillStyle;
    context.beginPath();
    // Note - no actual drawing gets done here.
    // What would we draw?
    if(this.drawShape){
        this.drawShape(context);
    }
    context.stroke();
    if(this.fillStyle){
        context.fill();
    }
};

Shape.prototype.resize = function(xx, yy){
    this.width = xx - this.x;
    this.height = yy - this.y;
};

Shape.prototype.moveTo = function(x, y){
    this.x = x;
    this.y = y;
};

Shape.prototype.moveBy = function(dx, dy){
    this.x += dx;
    this.y += dy;
};

Shape.prototype.setLineStyle = function(style){
    this.lineStyle = style;
};

Shape.prototype.setFillStyle = function(style){
    this.fillStyle = style;
};

var Line = function(x, y, width, height){
    Shape.apply(this, arguments);
};

Line.prototype = new Shape();

Line.prototype.drawShape = function(context){
    context.moveTo(this.x, this.y);
    context.lineTo(this.x+this.width,
                   this.y+this.height);
};

var Rectangle = function(x, y, width, height){
    Shape.apply(this, arguments);
};

Rectangle.prototype = new Shape();

Rectangle.prototype.drawShape = function(context){
    context.moveTo(this.x, this.y);
    context.lineTo(this.x+this.width, this.y);
    context.lineTo(this.x+this.width,
                   this.y+this.height);
    context.lineTo(this.x, this.y+this.height);
    context.closePath();
};

var pythagoras = function(w, h){
    return Math.sqrt(w*w + h*h);
};

var Circle = function(x, y, width, height){
    Shape.apply(this, arguments);
};

Circle.prototype = new Shape();

Circle.prototype.drawShape = function(context){
    // Work out centre and radius from the bounding rectangle...
    var x = this.x + this.width/2;
    var y = this.y + this.height/2;
    var radius = pythagoras(x - this.x, y - this.y);
    context.arc(x, y, radius, 0, 2*Math.PI);
};

var Triangle = function(x, y, width, height){
    Shape.apply(this, arguments);
    this.direction - "U";   // U=Up, D=Down, L=Left, R=Right
};

Triangle.prototype = new Shape();

Triangle.prototype.drawShape = function(context){
    context.beginPath();
    switch(this.direction){
        case "U":
            context.moveTo(this.x+this.width/2, this.y);
            context.lineTo(this.x+this.width, this.y+this.height);
            context.lineTo(this.x, this.y+this.height);
            break;
        case "L":
            context.moveTo(this.x, this.y+this.height/2);
            context.lineTo(this.x+this.width, this.y);
            context.lineTo(this.x+this.width, this.y+this.height);
            break;
        case "D":
            context.moveTo(this.x, this.y);
            context.lineTo(this.x+this.width, this.y);
            context.lineTo(this.x+this.width/2, this.y+this.height);
            break;
        case "R":
            context.moveTo(this.x, this.y+this.height/2);
            context.lineTo(this.x+this.width, this.y);
            context.lineTo(this.x+this.width, this.y+this.height);
            break;
        default:
            // Same as "U"
            context.moveTo(this.x+this.width/2, this.y);
            context.lineTo(this.x+this.width, this.y+this.height);
            context.lineTo(this.x, this.y+this.height);
    }
    context.closePath();

};

var shapeFactory = function(type, x, y){
    var shape;
    switch(type){
        case "Line":
            shape = new Line(x, y, 1, 1);
            break;
        case "Rectangle":
            shape = new Rectangle(x, y, 1, 1);
            break;
        case "Circle":
            shape = new Circle(x, y, 1, 1);
            break;
        case "Triangle":
            shape = new Triangle(x, y, 1, 1);
            break;
        default:
            shape = null;
    };
    return shape;
};

var doDrawing = function(){
    for(var i= 0, j=shapeList.length; i<j; i++){
        shapeList[i].draw(context);
    }
};

var canvas, context, lineStyle, fillStyle, shapeType, drawing=false,
    shapeList = [], x, y, x2, y2, currentState, currentShape;

var mouseUp = function(e){
    // Just completed a drawing operation.  Add the new shape to the
    // display list and draw the whole thing...
    drawing = false;
    //context.globalCompositeOperation = "source-over";
    context.putImageData(currentState, 0, 0);
    delete currentState;
    currentShape.setLineStyle(lineStyle);
    currentShape.setFillStyle(fillStyle);
    shapeList.push(currentShape);
    doDrawing();
};

var mouseDown = function(e){
    drawing = true;
    // Stash the current screen state, so we can restore it every
    // time the mouse moves...
    currentState = context.getImageData(0, 0, canvas.width, canvas.height);
    x = e.x - canvas.offsetLeft;
    y = e.y - canvas.offsetTop;
    // What are we drawing...
    context.strokeStyle = "#000";
    currentShape = shapeFactory(shapeType, x, y);
    context.beginPath();
    currentShape.drawShape(context);
    context.stroke();
};

var mouseMove = function(e){
    if(drawing){
        // We need to drag out a bounding box to get the
        // shapes position and size.
        // Start by restoring the screen to its state when the mouse
        // button was pressed...
        context.putImageData(currentState, 0, 0);

        // Now work the that the shape should appear at now...
        x2 = e.x - canvas.offsetLeft;
        y2 = e.y - canvas.offsetTop;
        currentShape.resize(x2, y2);
        // And draw it.
        context.beginPath();
        currentShape.drawShape(context);
        context.stroke();
    }
};

function changeLine(){
    lineStyle = document.getElementById("line").value;
};

function changeFill(){
    fillStyle = document.getElementById("fill").value;
};

function changeShape(){
    shapeType = document.getElementById("shape").value;
};

var setup = function(){
    // First we need a canvas and a context...
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");
    context.fillStyle = null;
    context.lineStyle = "#fff";
    //context.fillRect(0, 0, canvas.width, canvas.height);
    // Now we can hang some event handlers on the canvas...
    canvas.onmousedown = mouseDown;
    canvas.onmouseup = mouseUp;
    canvas.onmousemove = mouseMove;
};

window.onload = setup;