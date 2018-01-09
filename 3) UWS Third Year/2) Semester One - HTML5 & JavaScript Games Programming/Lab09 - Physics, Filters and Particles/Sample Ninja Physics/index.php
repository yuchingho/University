<!-- 
Add a keyboard hotkey to control the platform
-->
<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
    	<script src="library/phaser-ninja-physics.js"></script>
		
	<script>
		var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update});
/*
		function preload() {

		    game.load.spritesheet('ninja-tiles', 'assets/ninja-tiles128.png', 128, 128, 34);
		    game.load.image('a', 'assets/firstaid.png');

		}

		var sprite1;
		var cursors;

		var tile1;
		var tile2;



		function create() {

			game.stage.smoothed = true;

			//	Activate the Ninja physics system
			game.physics.startSystem(Phaser.Physics.NINJA);

			// game.physics.ninja.gravity = 0.1;

		    sprite1 = game.add.sprite(500, 200, 'a');

		    //  Enable the physics body for the Ninja physics system
		    //	By default it will create an AABB body for the sprite
		    game.physics.ninja.enableAABB(sprite1);

		    //	But you can change it to either a Tile or a Circle
		    tile1 = game.add.sprite(0, 500, 'ninja-tiles', 14);
		    tile1.width = 100;
		    tile1.height = 100;

		    game.physics.ninja.enableTile(tile1, tile1.frame);

		    cursors = game.input.keyboard.createCursorKeys();

		}

		function collisionHandler() {
			game.stage.backgroundColor = 0xff0000;
		}

		function update() {

			game.physics.ninja.collide(sprite1, tile1, collisionHandler, null, this);

			tile1.body.moveRight(1);

		    if (cursors.left.isDown)
		    {
		        sprite1.body.moveLeft(20);
		    }
		    else if (cursors.right.isDown)
		    {
		        sprite1.body.moveRight(20);
		    }

		    if (cursors.up.isDown)
		    {
		        sprite1.body.moveUp(20);
		    }
		    else if (cursors.down.isDown)
		    {
		        sprite1.body.moveUp(20);
		    }

		}
*/
		
		function preload() {

		    game.load.tilemap('map', 'assets/ninja-tilemap.json', null, Phaser.Tilemap.TILED_JSON);
		    game.load.image('ball', 'assets/shinyball.png');
		    game.load.image('sky', 'assets/sky2.png');
		    game.load.image('kenney', 'assets/kenney.png');

		}

		var sprite1;
		var cursors;
		var map;
		var layer;
		var tiles;

		function create() {

		    var sky = game.add.image(0, 0, 'sky');
		    sky.fixedToCamera = true;

		    //  Activate the Ninja physics system
		    game.physics.startSystem(Phaser.Physics.NINJA);

		    map = game.add.tilemap('map');

		    map.addTilesetImage('kenney');
    
		    layer = map.createLayer('Tile Layer 1');

		    layer.resizeWorld();

		    var slopeMap = { '32': 1, '77': 1, '95': 2, '36': 3, '137': 3, '140': 2 };

		    tiles = game.physics.ninja.convertTilemap(map, layer, slopeMap);

		    sprite1 = game.add.sprite(50, 50, 'ball');

		    game.physics.ninja.enableCircle(sprite1, sprite1.width / 2);

		    //  A little more bounce
		    sprite1.body.bounce = 0.5;

		    game.camera.follow(sprite1);

		    cursors = game.input.keyboard.createCursorKeys();

		}

		function update() {

		    for (var i = 0; i < tiles.length; i++)
		    {
		        sprite1.body.circle.collideCircleVsTile(tiles[i].tile);

		    }

		    if (cursors.left.isDown)
		    {
		        sprite1.body.moveLeft(20);
		    }
		    else if (cursors.right.isDown)
		    {
		        sprite1.body.moveRight(20);
		    }

		    if (cursors.up.isDown)
		    {
		        sprite1.body.moveUp(20);
		    }
		    else if (cursors.down.isDown)
		    {
		        sprite1.body.moveUp(20);
		    }

		}		
	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>