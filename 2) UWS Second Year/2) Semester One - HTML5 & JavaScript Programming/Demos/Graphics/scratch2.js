/**
 * Created by alistairmcmonnies on 03/03/15.
 */

function ImageStrip(source, numCells) {
    var me = this;
    this.image = new Image();
    this.image.src = source;
    this.image.onload = function() {
        me.width = me.image.width / numCells;
        me.height = me.image.height;
    };
    // Coordinates to draw the image segment at...
    this.x = 0;
    this.y = 0;
    // Image speed...
    this.dx = 0;
    this.dy = 0;
    this.numCells = numCells;
    this.cellNo = 0;
    this.direction = 1;
}
ImageStrip.prototype.nextCell = function() {
    this.cellNo = (this.cellNo + 1) % this.numCells;
};
ImageStrip.prototype.reverseX = function() {
    this.direction *= -1;
};
ImageStrip.prototype.setPosition = function(x, y){
    this.x = x;
    this.y = y;
};
ImageStrip.prototype.setSpeed = function(dx, dy) {
    this.dx = dx;
    this.dy = dy;
};
ImageStrip.prototype.move = function() {
    this.x += this.dx;
    this.y += this.dy;
};
ImageStrip.prototype.moveBy = function(dx, dy) {
    this.x += dx;
    this.y += dy;
};
ImageStrip.prototype.draw = function (context) {
    // move the drawing context to 0, 0 of the image cell...
    context.translate(this.x, this.y);
    // mirror the drawing context if necessary...
    if(this.direction < 0) {
        context.scale(-1, 1);
    }
    // draw the thing...
    var cellStart = this.cellNo * this.width;
    context.drawImage(this.image, cellStart, 0, this.width, this.height, 0, 0, this.direction * this.width, this.height);
    // restore the previous transformation state...
    if(this.direction < 0) {
        context.scale(-1, 1);
    }
    context.translate(-this.x, -this.y);
};

function drawAll() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    sprite.nextCell();
    sprite.move();
    sprite.draw(ctx);
    setTimeout(drawAll, 50);
}

document.getElementById("toggle").onclick = function(){
    sprite.reverseX();
};

document.getElementById('right').onclick = function() {
    sprite.moveBy(5, 0);
};

document.getElementById('left').onclick = function() {
    sprite.moveBy(-5, 0);
};

document.getElementById('goright').onclick = function() {
    sprite.dx += 5;
};

document.getElementById('goleft').onclick = function() {
    sprite.dx -= 5;
};


var sprite=null, ctx, canvas;
window.onload = function() {
    canvas = document.getElementById("c");
    ctx = canvas.getContext("2d");
    sprite = new ImageStrip('sonicstrip.png', 8);
    backg = new ImageStrip('background.png', 1);
    drawAll();
}