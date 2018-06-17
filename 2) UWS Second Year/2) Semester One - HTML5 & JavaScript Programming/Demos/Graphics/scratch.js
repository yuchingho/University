/**
 * Created by alistairmcmonnies on 02/03/15.
 */

var canvas = document.getElementById("c"),
    context = canvas.getContext("2d"),
    image = new Image(),
    cell = 0,
    ani = new ImageStrip('sonicstrip.png', 8);

function ImageStrip(source, numCells, whenReadyFunc){
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
ImageStrip.prototype.reverse = function() {
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
    if(this.minx == undefined || this.miny == undefined) {
        return;
    }
    if(this.x > this.maxx) {
        this.x = this.maxx;
        this.dx *= -1;
        this.direction = -1;
    } else if(this.x < this.minx) {
        this.x = this.minx;
        this.dx *= -1;
        this.direction = 1;
    }
    if(this.y > this.maxy) {
        this.y = this.maxy;
        this.dy *= -1;
    } else if (this.y < this.miny) {
        this.y = this.miny;
        this.dy *= -1;
    }
};
ImageStrip.prototype.setLimits = function (minx, miny, maxx, maxy){
    this.minx = minx;
    this.maxx = maxx;
    this.miny = miny;
    this.maxy = maxy;
};
ImageStrip.prototype.draw = function(ctx) {
    ctx.translate(-this.x, -this.y);
    // ctx.clearRect(0, 0, canvas.width, canvas.height);
    console.log("Cleared: " + this.x + ', ' + this.y + ' to ' + this.direction*this.width + ', ' + this.height);
    if(this.direction < 0) {
        ctx.scale(-1, 1);
    }
    this.move();
    var cellStart = this.cellNo * this.width;
    this.nextCell();
    ctx.drawImage(this.image, cellStart, 0, this.width, this.height, 0, 0, this.direction * this.width, this.height);
    //ctx.drawImage(this.image, cellStart, 0, this.width, this.height, this.x, this.y, this.direction * this.width, this.height);
    console.log("Drew: " + this.x + ', ' + this.y + ' to ' + this.direction*this.width + ', ' + this.height);
    if(this.direction < 0) {
        ctx.resetTransform();   //scale(-1, 1);
    }
};

function drawAll() {
    context.clearRect(0, 0, 100, 400);
    context.drawImage(image, cell, 0, 64, 64, 0, 0, 64, 64);
    ani.draw(context);
    context.scale(-1, 1);
    context.drawImage(image, cell, 0, 64, 64, 0, 100, -64, 64);
    cell += 64;
    if (cell >= 512) {cell = 0;}
}

image.onload = function(){
    ani.setPosition(0, 200);
    ani.direction = 1;
    ani.setLimits(0, 0, 336, 200);
    ani.setSpeed(5, 0);
    setInterval(drawAll, 50);
};
image.src = "sonicstrip.png";

document.getElementById('toggle').onclick = function() {
    ani.direction = -ani.direction;
}



