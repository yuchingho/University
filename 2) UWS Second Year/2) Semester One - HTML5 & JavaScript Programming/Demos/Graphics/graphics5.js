/**
 * Created by alistair on 05/11/2013.
 */

var canvas, context, cellImage;

function mirrorContext(ctx) {
    ctx.scale(-1, 1);
}
function ImageStrip(source, numCells, context) {
    var me = this;
    this.cellCount = 0;
    this.cellDuration = 3;
    this.source = source;
    this.context = context;
    this.x = 0; this.dx = 0;
    this.y = 0; this.dy = 0;
    this.cellNo = 0;
    this.numCells = numCells;
    this.img = new Image();
    this.img.onload = function() {
        me.width = me.img.width / me.numCells;
        me.height = me.img.height;
    };
    this.img.src = source;
}
ImageStrip.prototype.nextCell = function() {
    this.cellCount += 1;
    if (this.cellCount > this.cellDuration) {
        this.cellCount = 0;
        this.cellNo += 1;
        if (this.cellNo === this.numCells) {
            this.cellNo = 0;
        }
    }
};
ImageStrip.prototype.setPosition = function(x, y){
    this.x = x;
    this.y = y;
};
ImageStrip.prototype.setSpeed = function(dx, dy){
    this.dx = dx;
    this.dy = dy;
};
ImageStrip.prototype.inReverse = function() {
    return this.dx < 0;
}
ImageStrip.prototype.move = function() {
    // Clear rect of previous frame...
    // this.context.clearRect(this.x, this.y, this.width, this.height);
    this.context.clearRect(0, 0, canvas.width, 66);
    this.x += this.dx;
    this.y += this.dy;
};
ImageStrip.prototype.step = function() {
    this.nextCell();
    this.move();
};
ImageStrip.prototype.draw = function() {
    var cell_x = this.width * (this.cellNo % 8);
    this.context.save();
    if (this.inReverse()) {
        this.context.scale(-1, 1);
        this.context.drawImage(this.img, cell_x, 0, this.width, this.height, this.x, this.y, -this.width, this.height);
    } else {
        this.context.drawImage(this.img, cell_x, 0, this.width, this.height, this.x, this.y, this.width, this.height);
    }
    this.context.restore();
};

function animate() {
    window.requestAnimationFrame(animate);
    cellImage.step();
    cellImage.draw();
    if(cellImage.x > canvas.width) {
        cellImage.x = canvas.width;
        cellImage.dx = -4;
    } else if(cellImage.x < 0) {
        cellImage.x = 0;
        cellImage.dx = 4;
    }
}

window.onload = function () {
    canvas = document.getElementById("c");
    context = canvas.getContext("2d");
    cellImage = new ImageStrip("sonicstrip.png", 8, context);
    cellImage.setSpeed(4, 0);
    animate();
};
