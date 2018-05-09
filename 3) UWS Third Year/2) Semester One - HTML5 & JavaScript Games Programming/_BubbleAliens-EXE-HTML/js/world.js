var world;
var layer;

function preloadWorld() {
    game.load.image("worldbg", "tiled/worldbg.png");
    game.load.image("worldCollide", "tiled/worldCollide.png");
    game.load.tilemap("world", "tiled/world.json", null, Phaser.Tilemap.TILED_JSON);
}

function createWorld() {
    // Comment out to see worldCollisionReference
    game.add.tileSprite(0, 0, 800, 608, "worldbg");
    
    // Adding Tiled
    world = game.add.tilemap("world");
    world.addTilesetImage("world", "worldCollide");
    world.setCollisionBetween(1, 12);
    
    // Adding map layers
    layer = world.createLayer("layer");
    layer.resizeWorld();

    // Don't set new world bounds as bullets will disappear when they pass the bounds
    //game.world.setBounds(0,0 640, 608);
}