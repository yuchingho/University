<!-- 
Add a keyboard hotkey to control the platform
-->
<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update });

function preload() {

    game.stage.backgroundColor = '#FFFFFFF';
	game.load.image('background','assets/background.png');
    game.load.atlasJSONHash('botAll', 'assets/spritesheetALLBAGMAN.png', 'assets/spritesALLBAGMAN.json');
    game.load.atlasJSONHash('botYellow', 'assets/bagman_json.png', 'assets/bagman.json');
    game.load.atlasJSONHash('botBlue', 'assets/spritesheetblue.png', 'assets/sprites_blue.json');
    game.load.atlasJSONHash('botRed', 'assets/spritesheet_red.png', 'assets/sprites_red.json');
    game.load.image('mushroom', 'assets/mushroom2.png');
	game.load.spritesheet('buttonSprite', 'assets/button_sprite_sheet.png', 193, 71);
    game.load.image('atari', 'assets/atari800xl.png');
	game.load.spritesheet('buttonhorizontal', 'assets/button-horizontal.png',96,64);
	game.load.spritesheet('buttonvertical', 'assets/button-vertical.png',64,64);
}

var cursors;
var bot;
var dragablePlatform;
var left=false;
var right=false;
var higher=false;
var lower=false;
var buttonleft;
var buttonright;
var rightHotkey;
var leftHotkey;
var upHotkey;
var downHotkey;

function create() {
	//add background and start from the middle of the background
	thebg = game.add.sprite(0, 0, 'background');

	
    //  Modify the world and camera bounds
    game.world.resize(1920, 1200);

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
    bot = game.add.sprite(game.world.centerX/2, game.world.centerY/2, 'botYellow');
    bot.animations.add('run');
    bot.animations.play('run', 15, true);
	bot.anchor.setTo(1.6, 0.45);

    dragablePlatform = game.add.sprite(350, 400, 'atari');
	
	//makes the camera to follow the movement of the bot
	game.camera.follow(dragablePlatform);
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
		
	//add events on button
    var Thebutton = game.add.button(0, 0, 'buttonSprite', actionOnClick, this, 2, 1, 0);
    Thebutton.onInputOver.add(over, this);
    Thebutton.onInputOut.add(out, this);
    Thebutton.onInputUp.add(up, this);
    Thebutton.fixedToCamera = true;
    Thebutton.cameraOffset.setTo(10, 510);
	
	//add virtual game controller
    buttonleft = game.add.button(400, 520, 'buttonhorizontal', null, this, 0, 1, 0, 1);
    buttonleft.fixedToCamera = true;
    buttonleft.events.onInputOver.add(function(){left=true;});
    buttonleft.events.onInputOut.add(function(){left=false;});
    buttonleft.events.onInputDown.add(function(){left=true;});
    buttonleft.events.onInputUp.add(function(){left=false;});
	
    buttonright = game.add.button(600, 520, 'buttonhorizontal', null, this, 0, 1, 0, 1);
    buttonright.fixedToCamera = true;
    buttonright.events.onInputOver.add(function(){right=true;});
    buttonright.events.onInputOut.add(function(){right=false;});
    buttonright.events.onInputDown.add(function(){right=true;});
    buttonright.events.onInputUp.add(function(){right=false;});
	
    buttondown = game.add.button(515, 550, 'buttonvertical', null, this, 0, 1, 0, 1);
	buttondown.scale.setTo(1,0.6)
    buttondown.fixedToCamera = true;
    buttondown.events.onInputOver.add(function(){lower=true;});
    buttondown.events.onInputOut.add(function(){lower=false;});
    buttondown.events.onInputDown.add(function(){lower=true;});
    buttondown.events.onInputUp.add(function(){lower=false;});
	
    buttonup = game.add.button(515, 505, 'buttonvertical', null, this, 0, 1, 0, 1);
	buttonup.scale.setTo(1,0.6)
    buttonup.fixedToCamera = true;
    buttonup.events.onInputOver.add(function(){higher=true;});
    buttonup.events.onInputOut.add(function(){higher=false;});
    buttonup.events.onInputDown.add(function(){higher=true;});
    buttonup.events.onInputUp.add(function(){higher=false;});
	
	
	//add hotkey to control the platform
	rightHotkey = this.game.input.keyboard.addKey(Phaser.Keyboard.D);
	leftHotkey = this.game.input.keyboard.addKey(Phaser.Keyboard.A);
	upHotkey = this.game.input.keyboard.addKey(Phaser.Keyboard.W);
	downHotkey = this.game.input.keyboard.addKey(Phaser.Keyboard.S);
    //  Stop the following keys from propagating up to the browser
    game.input.keyboard.addKeyCapture([ Phaser.Keyboard.W, Phaser.Keyboard.A, Phaser.Keyboard.S, Phaser.Keyboard.D ]);
	
	
	
	//add physics arcade
    game.physics.startSystem(Phaser.Physics.ARCADE);
    game.physics.enable( [ bot,dragablePlatform ], Phaser.Physics.ARCADE);	
 
    dragablePlatform.body.immovable = true;

    //  This gets it moving
    bot.body.velocity.setTo(200, 200);
    bot.body.bounce.y = 1;
	bot.body.bounce.x = 1;
    bot.body.gravity.y = 200;
    
    //  This makes the game world bounce-able
    bot.body.collideWorldBounds = true;
}

function update() {
    game.physics.arcade.collide(bot, dragablePlatform, collisionHandler, null, this);
	
	//for the character
    if (higher || cursors.up.isDown || upHotkey.isDown)
    {
        dragablePlatform.y -= 4;
    }
    else if (lower || cursors.down.isDown || downHotkey.isDown)
    {
        dragablePlatform.y += 4;
    }

    if (left || cursors.left.isDown || leftHotkey.isDown)
    {
        dragablePlatform.x -= 4;
    }
    else if (right || cursors.right.isDown || rightHotkey.isDown)
    {
        dragablePlatform.x += 4;
    }
	
	//angle of the character when bounching
	if(bot.body.velocity.x<0){
		bot.body.rotation = 180;
	} else {
		bot.body.rotation = 0;		
	}
}

function collisionHandler (obj1, obj2) {

    game.stage.backgroundColor = '#992d2d';
	console.log("Collide");
}

function up() {
//   bot.loadTexture('botYellow');
//	runAnim();	
}

function over() {
    bot.loadTexture('botBlue');
	runAnim();
}

function out() {
    bot.loadTexture('botYellow');
	runAnim();
}

function actionOnClick () {
    bot.loadTexture('botRed');
	runAnim();
}

function runAnim() {
    bot.animations.add('run');
    bot.animations.play('run', 15, true);
}
	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>