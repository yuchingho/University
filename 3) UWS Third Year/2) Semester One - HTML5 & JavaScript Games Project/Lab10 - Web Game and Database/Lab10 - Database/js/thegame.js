var theGame = function(game){
	var cursors;
	var bot;
	var dragablePlatform;
	var left;
	var right;
	var higher;
	var lower;
	var buttonleft;
	var buttonright;
	var rightHotkey;
	var leftHotkey;
	var upHotkey;
	var downHotkey;
	var theMushroom;
	var emitter;
	var score;
}
 
theGame.prototype = {
	preload: function(){
		this.score = 0;	
		this.left=false;
		this.right=false;
		this.higher=false;
		this.lower=false;
	},
  	create: function(){
		//add background and start from the middle of the background
		thebg = this.game.add.sprite(0, 0, 'background');

		
		//  Modify the world and camera bounds
		this.game.world.resize(1920, 1200);

		//add controller
		cursors = this.game.input.keyboard.createCursorKeys();

		//populate mushrooms
		 theMushroom = this.game.add.group();
		 theMushroom.enableBody = true;
		 theMushroom.physicsBodyType = Phaser.Physics.ARCADE;

		 for (var i = 0; i < 50; i++)
		 {
			 var c = theMushroom.create(this.game.world.randomX, Math.random() * 500, 'mushroom', this.game.rnd.integerInRange(0, 36));
			 c.body.immovable = true;
	//		c.scale.x = 0.3;
	//		c.scale.y = 0.3;
		 }
			
		
		//  This sprite is using a texture atlas for all of its animation data
		bot = this.game.add.sprite(this.game.world.centerX/2, this.game.world.centerY/2, 'botYellow');
		bot.animations.add('run');
		bot.animations.play('run', 15, true);
		bot.anchor.setTo(1.6, 0.45);
		bot.scale.x = 1.3;
		bot.scale.y = 1.3;

		dragablePlatform = this.game.add.sprite(350, 400, 'atari');
		
		//makes the camera to follow the movement of the bot
		this.game.camera.follow(dragablePlatform);
		//several different types of camera :
	//    this.game.camera.follow(bot, Phaser.Camera.FOLLOW_LOCKON);
	//    this.game.camera.follow(bot, Phaser.Camera.FOLLOW_PLATFORMER);
	//    this.game.camera.follow(bot, Phaser.Camera.FOLLOW_TOPDOWN);
	//    this.game.camera.follow(bot, Phaser.Camera.FOLLOW_TOPDOWN_TIGHT);

		//add panel which will follows the camera
		var graphics = this.game.add.graphics(0, 500);
		graphics.lineStyle(2, 0x0000FF, 1);
		graphics.beginFill(0x0000FF, 1);
		graphics.drawRect(0, 0, 900, 100);
		graphics.endFill();
		graphics.cameraOffset.setTo(0, 0);
		graphics.fixedToCamera = true;
			
		//add events on button
		var Thebutton = this.game.add.button(0, 0, 'buttonSprite', this.actionOnClick, this, 2, 1, 0);
		Thebutton.onInputOver.add(this.over, this);
		Thebutton.onInputOut.add(this.out, this);
		Thebutton.onInputUp.add(this.up, this);
		Thebutton.fixedToCamera = true;
		Thebutton.cameraOffset.setTo(10, 510);
		
		//add virtual this.game controller
		buttonleft = this.game.add.button(400, 520, 'buttonhorizontal', null, this, 0, 1, 0, 1);
		buttonleft.fixedToCamera = true;
		buttonleft.events.onInputOver.add(function(){left=true;});
		buttonleft.events.onInputOut.add(function(){left=false;});
		buttonleft.events.onInputDown.add(function(){left=true;});
		buttonleft.events.onInputUp.add(function(){left=false;});
		
		buttonright = this.game.add.button(600, 520, 'buttonhorizontal', null, this, 0, 1, 0, 1);
		buttonright.fixedToCamera = true;
		buttonright.events.onInputOver.add(function(){right=true;});
		buttonright.events.onInputOut.add(function(){right=false;});
		buttonright.events.onInputDown.add(function(){right=true;});
		buttonright.events.onInputUp.add(function(){right=false;});
		
		buttondown = this.game.add.button(515, 550, 'buttonvertical', null, this, 0, 1, 0, 1);
		buttondown.scale.setTo(1,0.6)
		buttondown.fixedToCamera = true;
		buttondown.events.onInputOver.add(function(){lower=true;});
		buttondown.events.onInputOut.add(function(){lower=false;});
		buttondown.events.onInputDown.add(function(){lower=true;});
		buttondown.events.onInputUp.add(function(){lower=false;});
		
		buttonup = this.game.add.button(515, 505, 'buttonvertical', null, this, 0, 1, 0, 1);
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
		this.game.input.keyboard.addKeyCapture([ Phaser.Keyboard.W, Phaser.Keyboard.A, Phaser.Keyboard.S, Phaser.Keyboard.D ]);
		
		//add physics arcade
		this.game.physics.startSystem(Phaser.Physics.ARCADE);
		this.game.physics.enable( [ bot,dragablePlatform ], Phaser.Physics.ARCADE);	
	 
		dragablePlatform.body.immovable = true;
		

		//  This gets it moving
		bot.body.velocity.setTo(200, 200);
		bot.body.bounce.y = 1;
		bot.body.bounce.x = 1;
		bot.body.gravity.y = 200;
		
		//  This makes the this.game world bounce-able
		bot.body.collideWorldBounds = true;
		
		// emitter
		emitter = this.game.add.emitter(this.game.world.centerX, this.game.world.centerY, 400);

		emitter.makeParticles( [ 'fire1', 'fire2', 'fire3', 'smoke' ] );

		emitter.gravity = 200;
		emitter.setAlpha(1, 0, 3000);
		emitter.setScale(0.8, 0, 0.8, 0, 3000);

		emitter.start(false, 3000, 5);

	},
	update: function(){
		this.game.physics.arcade.collide(bot, dragablePlatform, this.collisionHandler, null, this);
		this.game.physics.arcade.collide(bot, theMushroom, this.collisionHandlerScoring, null, this);
		
		//for the character
		if (this.higher || cursors.up.isDown || upHotkey.isDown)
		{
			dragablePlatform.y -= 8;
		}
		else if (this.lower || cursors.down.isDown || downHotkey.isDown)
		{
			dragablePlatform.y += 8;
		}

		if (this.left || cursors.left.isDown || leftHotkey.isDown)
		{
			dragablePlatform.x -= 8;
		}
		else if (this.right || cursors.right.isDown || rightHotkey.isDown)
		{
			dragablePlatform.x += 8;
		}
		
		//angle of the character when bounching
		if(bot.body.velocity.x<0){
			bot.body.rotation = 180;
		} else {
			bot.body.rotation = 0;		
		}
		
		//update emmitter
		var px = bot.body.velocity.x;
		var py = bot.body.velocity.y;
		px *= -1;
		py *= -1;
		emitter.minParticleSpeed.set(px, py);
		emitter.maxParticleSpeed.set(px, py);
		emitter.emitX = bot.x;
		emitter.emitY = bot.y;
	},
	collisionHandler: function(obj1, obj2){
		this.game.stage.backgroundColor = '#992d2d';
//		console.log("Collide");
	},
	collisionHandlerScoring: function(obj1, obj2){
		this.game.stage.backgroundColor = '#992d2d';
		this.score++;
		obj2.kill();
		if(this.score>5)
			this.game.state.start("GameOver",true,false,this.score);	
	},
	up: function(){

	},
	over: function(){
		bot.loadTexture('botBlue');
		runAnim();	
	},
	out: function(){
		bot.loadTexture('botYellow');
		runAnim();
	},
	actionOnClick: function(){
		bot.loadTexture('botRed');
		runAnim();
	},
	runAnim: function(){
		bot.animations.add('run');
		bot.animations.play('run', 15, true);
	}
	

}