<!doctype html>
<html>
	<head>
    	<script src="library/phaser.min.js"></script>
	<script>
		var game = new Phaser.Game(800, 600, Phaser.AUTO, 'phaser-example', { preload: preload, create: create, update: update, render: render });

		function preload() {

		    game.load.tilemap('map', 'assets/testcsv.csv', null, Phaser.Tilemap.CSV);
		    game.load.image('tiles', 'assets/New Piskel(3).png');

		}

		var map;
		var layer;

		function create() {

		    //  Because we're loading CSV map data we have to specify the tile size here or we can't render it
		    map = game.add.tilemap('map', 16, 16);

		    //  Now add in the tileset
		    map.addTilesetImage('tiles');
    
		    //  Create our layer
		    layer = map.createLayer(0);

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