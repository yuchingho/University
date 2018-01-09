var preload = function(game){}
 
preload.prototype = {
	preload: function(){ 
		this.game.stage.backgroundColor = '#FFFFFFF';
		this.game.load.image('background','assets/background.png');
		this.game.load.atlasJSONHash('botAll', 'assets/spritesheetALLBAGMAN.png', 'assets/spritesALLBAGMAN.json');
		this.game.load.atlasJSONHash('botYellow', 'assets/bagman_json.png', 'assets/bagman.json');
		this.game.load.atlasJSONHash('botBlue', 'assets/spritesheetblue.png', 'assets/sprites_blue.json');
		this.game.load.atlasJSONHash('botRed', 'assets/spritesheet_red.png', 'assets/sprites_red.json');
		this.game.load.image('mushroom', 'assets/mushroom2.png');
		this.game.load.spritesheet('buttonSprite', 'assets/button_sprite_sheet.png', 193, 71);
		this.game.load.image('atari', 'assets/atari800xl.png');
		this.game.load.spritesheet('buttonhorizontal', 'assets/button-horizontal.png',96,64);
		this.game.load.spritesheet('buttonvertical', 'assets/button-vertical.png',64,64);

		this.game.load.image('fire1', 'assets/fire1.png');
		this.game.load.image('fire2', 'assets/fire2.png');
		this.game.load.image('fire3', 'assets/fire3.png');
		this.game.load.image('smoke', 'assets/smoke-puff.png');	

	},
  	create: function(){
		this.game.state.start("GameTitle");
	}
}