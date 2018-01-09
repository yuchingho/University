<!-- INPUT WITH KEYBOARD -->
<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
var game = new Phaser.Game(800, 600, Phaser.CANVAS, 'phaser-example', { preload: preload, create: create, update: update, render : render });

function preload() {

    game.stage.backgroundColor = '#FFFFFFF';
	game.load.image('background','assets/background.png');
    game.load.image('sonic', 'assets/sprites/sonic_havok_sanity.png');
    game.load.image('phaser', 'assets/sprites/phaser1.png');

}

var cursors;

function create() {
	//add background and start from the middle of the background
	thebg = game.add.sprite(0, 0, 'background');

    //  Modify the world and camera bounds
    game.world.resize(1920, 1200);
    cursors = game.input.keyboard.createCursorKeys();

}

function update() {

    if (cursors.up.isDown)
    {
        game.camera.y -= 4;
    }
    else if (cursors.down.isDown)
    {
        game.camera.y += 4;
    }

    if (cursors.left.isDown)
    {
        game.camera.x -= 4;
    }
    else if (cursors.right.isDown)
    {
        game.camera.x += 4;
    }

}

function render() {

    // game.debug.cameraInfo(game.camera, 32, 32);

}
	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>