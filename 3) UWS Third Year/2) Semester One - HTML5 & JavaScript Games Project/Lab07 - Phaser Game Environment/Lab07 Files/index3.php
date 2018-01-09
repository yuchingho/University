<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
		var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update, render: render });

		function preload() {

		    game.load.tilemap('map', 'assets/testjsonmultilayers.json', null, Phaser.Tilemap.TILED_JSON);
		    game.load.image('tiles', 'assets/New Piskel(3).png');
		    game.load.image('tiles2', 'assets/catastrophi_tiles_16.png');
		}

		var map;
		var layer = {};

		function create() {

		    //  Because we're loading CSV map data we have to specify the tile size here or we can't render it
		    map = game.add.tilemap('map');

		    //  Now add in the tileset
		    map.addTilesetImage('New Piskel(3)','tiles');
		    map.addTilesetImage('catastrophi_tiles_16','tiles2');
 

		    layer[0] = map.createLayer('Tile Layer 1');
		    layer[0].resizeWorld();
		    layer[1] = map.createLayer('Tile Layer 2');
		    layer[1].resizeWorld();
			
			   
		    //  Create our layer
//		    layer = map.createLayer(0);

		}

		function update() {


		}

		function render() {

		}
	</script>
    </head>
    <body>
    	<div id="phaser-example"></div>
    </body>
</html>