<!doctype html>
<html>
	<head>
    		<script src="library/phaser.min.js"></script>
    		<script src="js/boot.js"></script>
		<script src="js/preload.js"></script>
		<script src="js/gametitle.js"></script>
		<script src="js/thegame.js"></script>
		<script src="js/gameover.js"></script>	
    		<style>
    			body{margin:0}
    		</style>
		<script>
			(function() {
				var game = new Phaser.Game(800, 600, Phaser.CANVAS, "game");
				game.state.add("Boot",boot);
				game.state.add("Preload",preload);
				game.state.add("GameTitle",gameTitle);
				game.state.add("TheGame",theGame);
				game.state.add("GameOver",gameOver);
				game.state.start("Boot");
			})();    
		</script>
    </head>
    <body>
    </body>
</html>