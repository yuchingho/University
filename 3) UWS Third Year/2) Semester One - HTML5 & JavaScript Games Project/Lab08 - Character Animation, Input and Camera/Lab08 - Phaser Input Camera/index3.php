<!-- CREATE AN HUD -->
<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update });

function preload() {

    game.stage.backgroundColor = '#FFFFFFF';
	game.load.image('background','assets/background.png');
    game.load.atlasJSONHash('bot', 'assets/bagman_json.png', 'assets/bagman.json');
    game.load.image('mushroom', 'assets/mushroom2.png');
}

var cursors;
var bot;
var floor;

function create() {
	//add background and start from the middle of the background
	thebg = game.add.sprite(0, 0, 'background');

	
    //  Modify the world and camera bounds
    game.world.resize(1920, 1200);

	//add text to background
    game.add.text(game.world.centerX/2, 32, "Welcome to Pacrio World", { font: "32px Arial", fill: "#f26c4f", align: "left" });
	
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
    bot.animations.add('run');
    bot.animations.play('run', 15, true);
	bot.anchor.setTo(1.6, 0.45);
	
	//makes the camera to follow the movement of the bot
	game.camera.follow(bot);
	//several different types of camera :
//    game.camera.follow(bot, Phaser.Camera.FOLLOW_LOCKON);
//    game.camera.follow(bot, Phaser.Camera.FOLLOW_PLATFORMER);
//    game.camera.follow(bot, Phaser.Camera.FOLLOW_TOPDOWN);
//    game.camera.follow(bot, Phaser.Camera.FOLLOW_TOPDOWN_TIGHT);

	//add panel which will follows the camera
	var graphics = game.add.graphics(0, 500);
    graphics.lineStyle(2, 0x0000FF, 1);
	graphics.beginFill(0x0000FF, 1);
    graphics.drawRect(0, 0, 900, 100);
	graphics.endFill();
	graphics.cameraOffset.setTo(0, 0);
    graphics.fixedToCamera = true;
	
	//add text which follows the camera
    var t = game.add.text(200, 500, "this text is fixed to the camera\nYou can use this as HUD for your game", { font: "32px Arial", fill: "#FF3300", align: "center" });
    t.fixedToCamera = true;
    t.cameraOffset.setTo(120, 500);
	
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

	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>