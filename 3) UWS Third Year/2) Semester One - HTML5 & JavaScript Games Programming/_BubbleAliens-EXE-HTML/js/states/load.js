var loadState = {
    preload: function() {
        preloadWorld();
        preloadPlayer();
    },
    
    create: function() {
        game.state.start("play");
    },
};