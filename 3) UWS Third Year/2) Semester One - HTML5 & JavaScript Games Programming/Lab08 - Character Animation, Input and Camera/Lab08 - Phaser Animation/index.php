<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update });

function preload() {
    game.load.atlasJSONHash('bot', 'assets/bagman_json.png', 'assets/bagman.json');
}

var bot;

function create() {

    //  This sprite is using a texture atlas for all of its animation data
    bot = game.add.sprite(200, 200, 'bot');

    //  Here we add a new animation called 'run'
    //  We haven't specified any frames because it's using every frame in the texture atlas
    bot.animations.add('run');

    //  And this starts the animation playing by using its key ("run")
    //  15 is the frame rate (15fps)
    //  true means it will loop when it finishes
    bot.animations.play('run', 15, true);

}

function update() {

    bot.x += 2;

    if (bot.x > game.world.width)
    {
        bot.x = 0;
    }

}
	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>