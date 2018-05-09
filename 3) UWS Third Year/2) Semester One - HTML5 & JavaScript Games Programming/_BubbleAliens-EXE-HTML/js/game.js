var game = new Phaser.Game(800, 608, Phaser.CANVAS, "BubbleAliens");

game.state.add("boot", bootState);
game.state.add("load", loadState);
game.state.add("menu", menuState);
game.state.add("play", playState);
game.state.add("end", endState);

game.state.start("boot");