<!-- ATTACHING CAMERA TO OBJECTS -->
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
    game.load.atlasJSONHash('bot', 'assets/bagman_json.png', 'assets/bagman.json');
    game.load.image('mushroom', 'assets/mushroom2.png');
}

var cursors;
var bot;


function create() {
	//add background and start from the middle of the background
	thebg = game.add.sprite(0, 0, 'background');

	
    //  Modify the world and camera bounds
    game.world.resize(1920, 1200);

	//add text to background
    game.add.text(game.world.centerX/2, 32, "Welcome to Pacrio World", { font: "32px Arial", fill: "#f26c4f", align: "left" });

	//add text which follows the camera
    var t = game.add.text(200, 500, "this text is fixed to the camera", { font: "32px Arial", fill: "#FF3300", align: "center" });
    t.fixedToCamera = true;
    t.cameraOffset.setTo(200, 500);

	//add controller
    cursors = game.input.keyboard.createCursorKeys();

	//populate mushrooms
	for (var i = 0; i < 200; i++)
    {
        theMushroom = game.add.sprite(game.world.randomX, game.world.randomY, 'mushroom');
		theMushroom.scale.x = 0.3;
		theMushroom.scale.y = 0.3;
    }
    
	//  This sprite is using a texture atlas for all of its animation data
    bot = game.add.sprite(game.world.centerX/2, game.world.centerY/2, 'bot');

    //  Here we add a new animation called 'run'
    //  We haven't specified any frames because it's using every frame in the texture atlas
    bot.animations.add('run');

    //  And this starts the animation playing by using its key ("run")
    //  15 is the frame rate (15fps)
    //  true means it will loop when it finishes
    bot.animations.play('run', 15, true);

	game.camera.follow(bot);
	

	
}

function update() {

    if (cursors.up.isDown)
    {
        bot.y -= 4;
    }
    else if (cursors.down.isDown)
    {
        bot.y += 4;
    }

    if (cursors.left.isDown)
    {
        bot.x -= 4;
    }
    else if (cursors.right.isDown)
    {
        bot.x += 4;
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