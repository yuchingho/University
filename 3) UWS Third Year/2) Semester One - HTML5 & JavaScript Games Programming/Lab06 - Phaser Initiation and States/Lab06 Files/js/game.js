var game = new Phaser.Game(800, 600, Phaser.CANVAS, 'phaser-example');

game.state.add('preloadStateVar', preloadState);
game.state.add('createStateVar', createState);
console.log("game state");
game.state.start('preloadStateVar');