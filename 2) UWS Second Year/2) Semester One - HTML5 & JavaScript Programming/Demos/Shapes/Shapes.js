/**
 * Created with JetBrains WebStorm.
 * User: alistair
 * Date: 26/02/2013
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */

var context, // 2D Drawing context
    currentShape, // Holder for name of the current drawn shape
    currentStroke, // Current outline colour
    currentFill, // Current fill colour
    shapes = []; // List of all shapes drawn on the canvas.

/**
 * called if the page is re-sized or the device is rotated
 */
function set_page_size() {
    var canvas = document.getElementById("canvas");
    canvas.width = $(window).width() - 36;
    canvas.height = $(window).height() - ($('header').outerHeight() || 0) - ($('footer').outerHeight() || 0) - 120;
    context = canvas.getContext("2d");
    draw(context);
}

/**
 * This is the general initialize routine - wires up the
 * tool bar items, and sets up the canvas mouse/touch events.
 */
$(document).on("pageshow", function(){
    set_page_size();
    // Wire up the object buttons...
    $("#rect").click(doRect);
    $("#circ").click(doCirc);
    $("#line").click(doLine);
    strokeElement = $("#col").get(0);
    fillElement = $("#fill").get(0);
    $("#canvas").on("vmousedown", mouseDown);
    $("#canvas").on("mousemove", mouseMove);
    $("#canvas").on("mouseup", mouseUp);
});

/**
 * Functions for selecting current shape.
 */
function doRect() {
    currentShape = "rect";
}
function doCirc() {
    currentShape = "circ";
}
function doLine() {
    currentShape = "line";
}

/**
 * This set up all <select> elements to by jQuery Mobile style.
 */
$(document).bind('mobileinit',function(){
    $.mobile.selectmenu.prototype.options.nativeMenu = false;
});

/**
 * Window is resized or device is rotated.
 */
$(window).resize(function() {
    set_page_size();
});

/**
 * Draw everything on the specified 2D Canvas Context
 * @param ctx
 */
function draw(ctx){
    var s, i;
    for (i = 0; i < shapes.length; i += 1) {
        s = shapes[i];
        s.draw(context);
    }
}

/**
 * The parent Shape type - allows inheritance to do away with
 * pointless repetition.
 * @param x
 * @param y
 * @param colour
 * @param fill
 * @constructor
 */
function Shape(x, y, colour, fill){
    this.x = x;
    this.y = y;
    this.colour = colour;
    this.fill = fill;
}
/**
 * This function will set up drawing styles for a shape.
 * @param context
 */
Shape.prototype.setup = function(context) {
    context.strokeStyle = this.colour;
    context.fillStyle = this.fill;
};

/**
 * First concrete class - defines a Rectangle.
 * @param x
 * @param y
 * @param col
 * @param fill
 * @param w
 * @param h
 * @constructor
 */
function Rect(x, y, col, fill, w, h) {
    Shape.apply(this, arguments);
    this.width = w;
    this.height = h;
}
Rect.prototype = new Shape();
Rect.prototype.draw = function(context){
    this.setup(context);
    context.beginPath();
    context.fillRect(this.x, this.y, this.width, this.height);
    context.strokeRect(this.x, this.y, this.width, this.height);
};

/**
 * A Circle class
 * @param x
 * @param y
 * @param col
 * @param fill
 * @param rad
 * @constructor
 */
function Circ(x, y, col, fill, rad) {
    Shape.apply(this, arguments);
    this.radius = rad;
}
Circ.prototype = new Shape();
Circ.prototype.draw = function (context){
    this.setup(context);
    context.beginPath();
    context.arc(this.x, this.y, this.radius, 0, 2 * Math.PI);
    context.stroke();
    context.fill();
    context.closePath();
};

/**
 * A line class.
 * @param x
 * @param y
 * @param col
 * @param fill
 * @param w
 * @param h
 * @constructor
 */
function Line(x, y, col, fill, w, h) {
    Shape.apply(this, arguments);
    this.width = w;
    this.height = h;
}
Line.prototype = new Shape();
Line.prototype.draw = function(context) {
    this.setup(context);
    context.moveTo(this.x, this.y);
    context.lineTo(this.x + this.width, this.y + this.height);
    context.stroke();
};

/**
 * These are UI event definitions - handle user interactions.
 */
var vx, vy, image, dragging;

/**
 * Work out the radius of a circle given distance between
 * two points.
 * @param dx
 * @param dy
 * @returns {number}
 * @constructor
 */
function Pythagoras(dx, dy) {
    return Math.sqrt(dx * dx + dy * dy);
}
/**
 * Work out mouse/touch coordinates within the canvas element.
 * @param evt
 * @returns {number}
 */
function getCanvasX(evt) {
    return evt.pageX - context.canvas.offsetLeft;
}
function getCanvasY(evt) {
    return evt.pageY - context.canvas.offsetTop;
}
/**
 * Handle mouse/vmouse event - mousedown, touchdown etc.
 * The basic principle for the rubber-banding operation is
 * that every mouse-move event, the display is restored to
 * the state when the mouse-down/touch down event happened,
 * then the rubber-band is drawn on top.
 * @param evt
 */
function mouseDown(evt) {
    vx = getCanvasX(evt);
    vy = getCanvasY(evt);
    dragging = true;
    image = context.getImageData(0, 0, context.canvas.width, context.canvas.height);
}
/**
 * Mouse/vmouse is moved
 * @param evt
 */
function mouseMove(evt) {
    var x, y;
    if(dragging) {
        x = getCanvasX(evt);
        y = getCanvasY(evt);
        if(image) {
            context.putImageData(image, 0, 0);
            context.strokeRect(vx, vy, x-vx, y-vy);
        }
    }
}
/**
 * Mouse is released - touch ends.
 * @param evt
 */
function mouseUp(evt) {
    var vxx, vyy, shape, rad;
    vxx = getCanvasX(evt);
    vyy = getCanvasY(evt);
    if(!currentShape) return;
    currentStroke = strokeElement.options[strokeElement.selectedIndex].value;
    currentFill = fillElement.options[fillElement.selectedIndex].value;
    switch (currentShape) {
        case "rect":
            shape = new Rect(vx, vy, currentStroke, currentFill, vxx-vx, vyy-vy);
            break;
        case "circ":
            rad = Pythagoras(vxx-vx, vyy-vy);
            shape = new Circ(vx, vy, currentStroke, currentFill, rad);
            break;
        case "line":
            shape = new Line(vx, vy, currentStroke, currentFill, vxx-vx, vyy-vy);
            break;
    }
    shapes.push(shape);
    context.putImageData(image, 0, 0);
    image = null;
    draw(context);
}